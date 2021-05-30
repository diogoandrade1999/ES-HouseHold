package pt.ua.household.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class Humidity {

    @JsonProperty("humidity")
    private double humidity;

    @JsonProperty("date")
    private Date date;

    @JsonProperty("houseId")
    private long houseId;

    @JsonProperty("roomId")
    private long roomId;

    public double getHumidity() {
        return this.humidity;
    }

    public void setHumidity(double humidity) {
        this.humidity = humidity;
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
        return "{" + "humidity='" + getHumidity() + "'" + ", date='" + getDate() + "'" + ", houseId='" + getHouseId()
                + "'" + ", roomId='" + getRoomId() + "'" + "}";
    }

}