package pt.ua.humidity.entities;

import java.util.Date;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "esp51_humidity")
public class Humidity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    @JsonProperty("date")
    private Date date;

    @JsonProperty("humidity")
    private double humidity;

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
        return "{" + " date='" + getDate() + "'" + ", humidity='" + getHumidity() + "'" + ", houseId='" + getHouseId()
                + "'" + ", roomId='" + getRoomId() + "'" + "}";
    }

}