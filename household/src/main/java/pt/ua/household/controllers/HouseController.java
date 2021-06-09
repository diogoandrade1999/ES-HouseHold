package pt.ua.household.controllers;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import pt.ua.household.entities.House;
import pt.ua.household.entities.Room;
import pt.ua.household.entities.User;
import pt.ua.household.model.Simulator;
import pt.ua.household.services.HouseService;
import pt.ua.household.services.RoomService;
import pt.ua.household.services.UserService;

@Controller
@RequestMapping("/houses")
public class HouseController {

    @Autowired
    private KafkaTemplate<String, Simulator> kafkaTemplate;

    public void sendRoom(Simulator room, String topic) {
        this.kafkaTemplate.send(topic, room);
    }

    @Autowired
    private UserService userService;

    @Autowired
    private HouseService houseService;

    @Autowired
    private RoomService roomService;

    @RequestMapping(method = RequestMethod.GET)
    public String houses(Principal principal, Model model) {
        User user = this.userService.getUserById(Long.parseLong(principal.getName().split("_")[0]));
        model.addAttribute("username", user.getFirstName() + " " + user.getLastName());
        model.addAttribute("login", true);
        model.addAttribute("houses", user.getHouses());
        return "pages/houses";
    }

    @RequestMapping(value = "new", method = RequestMethod.GET)
    public String newHouse(Principal principal) {
        User user = this.userService.getUserById(Long.parseLong(principal.getName().split("_")[0]));
        House house = new House();
        house.addUsers(user);
        this.houseService.saveHouse(house);
        user.addHouse(house);
        this.userService.saveUser(user);
        return "redirect:/houses";
    }

    @RequestMapping(value = "remove/{houseId}", method = RequestMethod.GET)
    public String removeHouse(@PathVariable long houseId, Principal principal) {
        User user = this.userService.getUserById(Long.parseLong(principal.getName().split("_")[0]));
        House house = this.houseService.getHouseById(houseId);
        if (!user.getHouses().contains(house))
            return "redirect:/houses";
        user.removeHouse(house);
        this.userService.saveUser(user);
        for (Room room : house.getRooms()) {
            this.sendRoom(new Simulator(room.getRoomId(), room.getHouse().getHouseId()), "esp51-room-delete");
            this.roomService.removeRoom(room);
        }
        this.houseService.removeHouse(house);
        return "redirect:/houses";
    }

    @RequestMapping(value = "dashboard/{houseId}", method = RequestMethod.GET)
    public String dashboard(@PathVariable long houseId, Principal principal, Model model) {
        User user = this.userService.getUserById(Long.parseLong(principal.getName().split("_")[0]));
        House house = this.houseService.getHouseById(houseId);
        if (!user.getHouses().contains(house))
            return "redirect:/houses";
        model.addAttribute("username", user.getFirstName() + " " + user.getLastName());
        model.addAttribute("login", true);
        model.addAttribute("house", house);
        model.addAttribute("room", null);
        return "pages/dashboard";
    }

    @RequestMapping(value = "dashboard/{houseId}/{roomId}", method = RequestMethod.GET)
    public String dashboardRoom(@PathVariable long houseId, @PathVariable long roomId, Principal principal,
            Model model) {
        User user = this.userService.getUserById(Long.parseLong(principal.getName().split("_")[0]));
        House house = this.houseService.getHouseById(houseId);
        if (!user.getHouses().contains(house))
            return "redirect:/houses";
        Room room = this.roomService.getRoomById(roomId);
        if (!house.getRooms().contains(room))
            return "redirect:/houses";
        model.addAttribute("username", user.getFirstName() + " " + user.getLastName());
        model.addAttribute("login", true);
        model.addAttribute("house", house);
        model.addAttribute("room", room);
        return "pages/dashboard";
    }

    @RequestMapping(value = "rooms/{houseId}", method = RequestMethod.GET)
    public String rooms(@PathVariable long houseId, Principal principal, Model model) {
        User user = this.userService.getUserById(Long.parseLong(principal.getName().split("_")[0]));
        House house = this.houseService.getHouseById(houseId);
        if (!user.getHouses().contains(house))
            return "redirect:/houses";
        model.addAttribute("username", user.getFirstName() + " " + user.getLastName());
        model.addAttribute("login", true);
        model.addAttribute("house", house);
        return "pages/rooms";
    }

    @RequestMapping(value = "room/new/{houseId}", method = RequestMethod.GET)
    public String newRoom(@PathVariable long houseId, Principal principal) {
        User user = this.userService.getUserById(Long.parseLong(principal.getName().split("_")[0]));
        House house = this.houseService.getHouseById(houseId);
        if (!user.getHouses().contains(house))
            return "redirect:/houses";
        Room room = new Room();
        room.setHouse(house);
        this.roomService.saveRoom(room);
        this.houseService.saveHouse(house);
        this.sendRoom(new Simulator(room.getRoomId(), room.getHouse().getHouseId()), "esp51-room-new");
        return "redirect:/houses/rooms/" + houseId;
    }

    @RequestMapping(value = "room/remove/{houseId}/{roomId}", method = RequestMethod.GET)
    public String removeRoom(@PathVariable long houseId, @PathVariable long roomId, Principal principal) {
        User user = this.userService.getUserById(Long.parseLong(principal.getName().split("_")[0]));
        House house = this.houseService.getHouseById(houseId);
        if (!user.getHouses().contains(house))
            return "redirect:/houses";
        Room room = this.roomService.getRoomById(roomId);
        if (!house.getRooms().contains(room))
            return "redirect:/houses/rooms/" + houseId;
        house.removeRoom(room);
        this.houseService.saveHouse(house);
        this.sendRoom(new Simulator(room.getRoomId(), room.getHouse().getHouseId()), "esp51-room-delete");
        this.roomService.removeRoom(room);
        return "redirect:/houses/rooms/" + houseId;
    }

}
