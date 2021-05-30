package pt.ua.household.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class Luminosity {

    @JsonProperty("luminosity")
    private double light;

    @JsonProperty("date")
    private Date date;

    @JsonProperty("houseId")
    private long houseId;

    @JsonProperty("roomId")
    private long roomId;

    public double getLight() {
        return this.light;
    }

    public void setLight(double light) {
        this.light = light;
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
        return "{" + "light='" + getLight() + "'" + ", date='" + getDate() + "'" + ", houseId='" + getHouseId() + "'"
                + ", roomId='" + getRoomId() + "'" + "}";
    }

}
