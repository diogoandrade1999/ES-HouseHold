package pt.ua.simulator.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;

import pt.ua.simulator.entities.House;
import pt.ua.simulator.entities.Room;
import pt.ua.simulator.repositories.HouseRepository;
import pt.ua.simulator.repositories.RoomRepository;

@Configuration
public class RoomListener {

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private HouseRepository houseRepository;

    @KafkaListener(topics = "esp51-room", groupId = "esp51", containerFactory = "roomKafkaListenerContainerFactory")
    public void roomListener(Room room) {
        House house = room.getHouse();
        long houseId = house.getHouseId();
        if (this.houseRepository.getById(houseId) == null) {
            house = new House();
            house.setHouseId(houseId);
        }
        house.addRoom(room);
        this.houseRepository.save(house);
        this.roomRepository.save(room);
    }
}
