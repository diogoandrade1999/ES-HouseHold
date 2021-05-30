package pt.ua.household.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class House {

    private ArrayList<Room> rooms = new ArrayList<>();

    private boolean CentralHeatingOn;

    public House(){
        Room room1 = new Room(1);
        Room room2 = new Room(2);
        Room room3 = new Room(3);
        rooms.add(room1);
        rooms.add(room2);
        rooms.add(room3);
        CentralHeatingOn = false;
    }

    public ArrayList<Room> getRooms() {
        return rooms;
    }

    public void setRooms(ArrayList<Room> rooms) {
        this.rooms = rooms;
    }

    public boolean isCentralHeatingOn() {
        return CentralHeatingOn;
    }

    public void setCentralHeatingOn(boolean centralHeatingOn) {
        CentralHeatingOn = centralHeatingOn;
    }
}
