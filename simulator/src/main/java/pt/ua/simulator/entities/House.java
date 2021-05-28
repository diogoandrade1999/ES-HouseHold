package pt.ua.simulator.entities;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class House {

    @Id
    private long houseId;

    @OneToMany(mappedBy = "house")
    private Set<Room> rooms;

    public long getHouseId() {
        return this.houseId;
    }

    public void setHouseId(long houseId) {
        this.houseId = houseId;
    }

    public Set<Room> getRooms() {
        return this.rooms;
    }

    public void setRooms(Set<Room> rooms) {
        this.rooms = rooms;
    }

    public void addRoom(Room room) {
        this.rooms.add(room);
    }

    @Override
    public String toString() {
        return "{" + " houseId='" + getHouseId() + "'" + ", rooms='" + getRooms() + "'" + "}";
    }

}
