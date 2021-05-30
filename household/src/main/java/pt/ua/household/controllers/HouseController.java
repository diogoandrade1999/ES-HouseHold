package pt.ua.household.controllers;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import pt.ua.household.entities.User;
import pt.ua.household.services.UserService;

@Controller
@RequestMapping("/house")
public class HouseController {

    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.GET)
    public String houses(Principal principal, Model model) {
        User user = this.userService.getUserById(Long.parseLong(principal.getName().split("_")[0]));
        model.addAttribute("houses", user.getHouses());
        return "pages/house";
    }
}
