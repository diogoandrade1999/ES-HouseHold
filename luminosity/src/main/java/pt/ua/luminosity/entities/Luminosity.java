package ua.pt.luminosity.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Luminosity {

    @Id
    private Date date;

    private double luminosity;
    private long houseId;
    private long roomId;

    public double getLuminosity() {
        return this.luminosity;
    }

    public void setLuminosity(double luminosity) {
        this.luminosity = luminosity;
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
        return "{" + "luminosity='" + getLuminosity() + "'" + ", date='" + getDate() + "'" + ", houseId='"
                + getHouseId() + "'" + ", roomId='" + getRoomId() + "'" + "}";
    }

}