package pt.ua.household.entities;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "houses")
public class House {

    @Id
    @GeneratedValue
    @Column(name = "house_id")
    private long id;

    @OneToMany(mappedBy = "house")
    private Set<Room> rooms;

    @ManyToMany(mappedBy = "houses")
    private Set<User> users;

    public long getHouseId() {
        return this.id;
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

    public Set<User> getUsers() {
        return this.users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "{" + " id='" + getHouseId() + "'" + ", rooms='" + getRooms() + "'" + ", users='" + getUsers() + "'"
                + "}";
    }

}
