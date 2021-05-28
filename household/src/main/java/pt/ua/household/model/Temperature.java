package pt.ua.household.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class Temperature {

    @JsonProperty("temperatureId")
    private long temperatureId;
    @JsonProperty("temperature")
    private double temperature;
    @JsonProperty("date")
    private Date date;
    @JsonProperty("houseId")
    private long houseId;
    @JsonProperty("roomId")
    private long roomId;

    public long getTemperatureId() {
        return this.temperatureId;
    }

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

}