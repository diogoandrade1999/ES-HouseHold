package pt.ua.luminosity.entities;

import java.util.Date;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "esp51_luminosity")
public class Luminosity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    @JsonProperty("date")
    private Date date;

    @JsonProperty("luminosity")
    private double luminosity;

    @JsonProperty("houseId")
    private long houseId;

    @JsonProperty("roomId")
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