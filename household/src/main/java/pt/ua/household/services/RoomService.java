package pt.ua.household.services;

import pt.ua.household.entities.Room;

public interface RoomService {

    Room getRoomById(long id);

    Room saveRoom(Room room);

    void removeRoom(Room room);

}
