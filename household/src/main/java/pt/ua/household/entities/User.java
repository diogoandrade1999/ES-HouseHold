package pt.ua.household.entities;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue
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
    private Set<House> houses;

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

    @Override
    public String toString() {
        return "{" + " id='" + getUserId() + "'" + ", password='" + getPassword() + "'" + ", firstName='"
                + getFirstName() + "'" + ", lastName='" + getLastName() + "'" + ", email='" + getEmail() + "'"
                + ", contact='" + getContact() + "'" + ", address='" + getAddress() + "'" + ", houses='" + getHouses()
                + "'" + "}";
    }

}
