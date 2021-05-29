package pt.ua.household.model;

import java.util.Date;

public class Light {

    private long lightId;

    private double light;
    private Date date;
    private long houseId;
    private long roomId;

    public long getLightId() {
        return this.lightId;
    }

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
        return "{" +
            " lightId='" + getLightId() + "'" +
            ", light='" + getLight() + "'" +
            ", date='" + getDate() + "'" +
            ", houseId='" + getHouseId() + "'" +
            ", roomId='" + getRoomId() + "'" +
            "}";
    }

}