package pt.ua.household.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Alert {

    @JsonProperty("userId")
    private Long userId;

    @JsonProperty("sensorType")
    private String sensorType;

    @JsonProperty("message")
    private String message;

    public Alert(){

    }

    public Alert(Long userId, String sensorType, String message) {
        this.userId = userId;
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

    @Override
    public String toString() {
        return "{" + "userId='" + getUserId() + "'" + ", sensorType='" + getSensorType() + "'" + ", message='"
                + getMessage() + "'" + "}";
    }


}
