package ua.pt.temperature.entities;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Temperature {

    @Id
    @JsonProperty("date")
    private Date date;

    @JsonProperty("temperature")
    private double temperature;

    @JsonProperty("houseId")
    private long houseId;

    @JsonProperty("roomId")
    private long roomId;

    public double getTemperature() {
        return this.temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public Date getDate() {
        return this.date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public long getHouseId() {
        return this.houseId;
    }

    public void setHouseId(long houseId) {
        this.houseId = houseId;
    }

    public long getRoomId() {
        return this.roomId;
    }

    public void setRoomId(long roomId) {
        this.roomId = roomId;
    }

    @Override
    public String toString() {
        return "{" + "temperature='" + getTemperature() + "'" + ", date='" + getDate() + "'" + ", houseId='"
                + getHouseId() + "'" + ", roomId='" + getRoomId() + "'" + "}";
    }

}