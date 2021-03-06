package pt.ua.household.entities;

import javax.persistence.*;

@Entity
@Table(name = "esp51_rooms")
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "room_id")
    private long id;

    @ManyToOne
    @JoinColumn(name = "rooms_house", nullable = false)
    private House house;

    private double temperature;
    private double humidity;
    private int light;

    public long getRoomId() {
        return this.id;
    }

    public House getHouse() {
        return this.house;
    }

    public void setHouse(House house) {
        this.house = house;
    }

    public double getTemperature() {
        return this.temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public double getHumidity() {
        return this.humidity;
    }

    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }

    public int getLight() {
        return this.light;
    }

    public void setLight(int light) {
        this.light = light;
    }

    @Override
    public String toString() {
        return "{" + " id='" + getRoomId() + "'" + ", temperature='" + getTemperature() + "'" + ", humidity='"
                + getHumidity() + "'" + ", light='" + getLight() + "'" + "}";
    }

}
