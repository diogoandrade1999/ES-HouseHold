package pt.ua.household.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

@Entity
@Table(name = "esp51_alerts")
public class Alert {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "alert_id")
    private long id;

    @JsonProperty("userId")
    private Long userId;

    @JsonProperty("houseId")
    private Long houseId;

    @JsonProperty("roomId")
    private Long roomId;

    @JsonProperty("sensorType")
    private String sensorType;

    @JsonProperty("message")
    private String message;

    public Alert(){

    }

    public Alert( Long userId, Long houseId, Long roomId, String sensorType, String message) {
        this.userId = userId;
        this.houseId = houseId;
        this.roomId = roomId;
        this.sensorType = sensorType;
        this.message = message;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getSensorType() {
        return sensorType;
    }

    public void setSensorType(String sensorType) {
        this.sensorType = sensorType;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Long getHouseId() {
        return houseId;
    }

    public void setHouseId(Long houseId) {
        this.houseId = houseId;
    }

    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }

    @Override
    public String toString() {
        return "{" + "userId='" + getUserId() + "'" + ", sensorType='" + getSensorType() + "'" + ", message='"
                + getMessage() + "'" + "}";
    }


}
