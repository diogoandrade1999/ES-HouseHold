package ua.pt.simulator.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Room {

    @Id
    private long roomId;

    @ManyToOne
    @JoinColumn(name = "room_house", nullable = false)
    private House house;

    private double temperature;
    private double humidity;
    private int light;

    public long getRoomId() {
        return this.roomId;
    }

    public void setRoomId(long roomId) {
        this.roomId = roomId;
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
        return "{" + " roomId='" + getRoomId() + "'" + ", house='" + getHouse() + "'" + ", temperature='"
                + getTemperature() + "'" + ", humidity='" + getHumidity() + "'" + ", light='" + getLight() + "'" + "}";
    }

}
