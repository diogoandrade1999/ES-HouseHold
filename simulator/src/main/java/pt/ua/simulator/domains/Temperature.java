package pt.ua.simulator.domains;

import java.util.Date;

public class Temperature {

    private double temperature;
    private Date date;
    private long houseId;
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