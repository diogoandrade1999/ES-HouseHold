package pt.ua.humidity.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Humidity {

    @Id
    private Date date;

    private double humidity;
    private long houseId;
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
        return "{" +
            " date='" + getDate() + "'" +
            ", humidity='" + getHumidity() + "'" +
            ", houseId='" + getHouseId() + "'" +
            ", roomId='" + getRoomId() + "'" +
            "}";
    }

}