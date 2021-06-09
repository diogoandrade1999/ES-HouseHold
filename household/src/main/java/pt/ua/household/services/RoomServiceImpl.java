package pt.ua.household.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pt.ua.household.entities.Room;
import pt.ua.household.repositories.RoomRepository;

@Service
public class RoomServiceImpl implements RoomService {

    @Autowired
    private RoomRepository roomRepository;

    @Override
    public Room getRoomById(long id) {
        return this.roomRepository.findById(id).orElse(null);
    }

    @Override
    public Room saveRoom(Room room) {
        return this.roomRepository.save(room);
    }

    @Override
    public void removeRoom(Room room) {
        this.roomRepository.delete(room);
    }

}
