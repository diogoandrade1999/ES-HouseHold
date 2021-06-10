package pt.ua.household.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "esp51_users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "user_id")
    private Long id;

    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private String contact;
    private String address;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "users_houses", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "house_id"))
    private Set<House> houses = new HashSet<>();

    public Long getUserId() {
        return this.id;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContact() {
        return this.contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Set<House> getHouses() {
        return this.houses;
    }

    public void setHouses(Set<House> houses) {
        this.houses = houses;
    }

    public void addHouse(House house) {
        this.houses.add(house);
    }

    public void removeHouse(House house) {
        this.houses.remove(house);
    }

    @Override
    public String toString() {
        return "{" + " id='" + getUserId() + "'" + ", password='" + getPassword() + "'" + ", firstName='"
                + getFirstName() + "'" + ", lastName='" + getLastName() + "'" + ", email='" + getEmail() + "'"
                + ", contact='" + getContact() + "'" + ", address='" + getAddress() + "'" + "}";
    }

}
