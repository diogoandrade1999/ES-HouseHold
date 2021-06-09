package pt.ua.simulator.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;

import pt.ua.simulator.entities.Room;
import pt.ua.simulator.repositories.RoomRepository;

@Configuration
public class RoomListener {

    @Autowired
    private RoomRepository roomRepository;

    @KafkaListener(topics = "esp51-room-new", groupId = "esp51", containerFactory = "roomKafkaListenerContainerFactory")
    public void roomListenerNew(Room room) {
        Room r = new Room();
        r.setRoomId(room.getRoomId());
        r.setHouseId(room.getHouseId());
        this.roomRepository.save(room);
    }

    @KafkaListener(topics = "esp51-room-delete", groupId = "esp51", containerFactory = "roomKafkaListenerContainerFactory")
    public void roomListenerDelete(Room room) {
        Room r = this.roomRepository.getById(room.getRoomId());
        if (r != null)
            this.roomRepository.delete(r);
    }

}
