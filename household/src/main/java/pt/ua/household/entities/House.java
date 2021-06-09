package pt.ua.household.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "esp51_houses")
public class House {

    @Id
    @GeneratedValue
    @Column(name = "house_id")
    private long id;

    @OneToMany(mappedBy = "house")
    private Set<Room> rooms = new HashSet<>();

    @ManyToMany(mappedBy = "houses")
    private Set<User> users = new HashSet<>();

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

    public void removeRoom(Room room) {
        this.rooms.remove(room);
    }

    public Set<User> getUsers() {
        return this.users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public void addUsers(User user) {
        this.users.add(user);
    }

    @Override
    public String toString() {
        return "{" + " id='" + getHouseId() + "'" + "}";
    }

}
