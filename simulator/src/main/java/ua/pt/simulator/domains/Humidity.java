package ua.pt.simulator.domains;

import java.util.Date;

public class Humidity {

    private long humidityId;

    private double humidity;
    private Date date;
    private long houseId;
    private long roomId;

    public long getHumidityId() {
        return this.humidityId;
    }

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
        return "{" +
            " humidityId='" + getHumidityId() + "'" +
            ", humidity='" + getHumidity() + "'" +
            ", date='" + getDate() + "'" +
            ", houseId='" + getHouseId() + "'" +
            ", roomId='" + getRoomId() + "'" +
            "}";
    }

}