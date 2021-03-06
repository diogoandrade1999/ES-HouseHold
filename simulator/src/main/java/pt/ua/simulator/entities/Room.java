package pt.ua.simulator.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "esp51_simulator")
public class Room {

    @Id
    @Column(name = "room_id")
    private long id;

    private long houseId;

    private double temperature;
    private double humidity;
    private int light;

    public long getRoomId() {
        return this.id;
    }

    public void setRoomId(long roomId) {
        this.id = roomId;
    }

    public long getHouseId() {
        return this.houseId;
    }

    public void setHouseId(long houseId) {
        this.houseId = houseId;
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
        return "{" + " id='" + getRoomId() + "'" + ", houseId='" + getHouseId() + "'" + ", temperature='"
                + getTemperature() + "'" + ", humidity='" + getHumidity() + "'" + ", light='" + getLight() + "'" + "}";
    }

}
