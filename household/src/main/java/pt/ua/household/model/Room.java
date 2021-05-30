package pt.ua.household.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

public class Room {

    private long roomId;
    private boolean AirConditionerOn;

    public Room(long roomId){
        this.roomId = roomId;
        this.AirConditionerOn = false;

    }

    public long getRoomId() {
        return roomId;
    }

    public void setRoomId(long roomId) {
        this.roomId = roomId;
    }

    public boolean isAirConditionerOn() {
        return AirConditionerOn;
    }

    public void setAirConditionerOn(boolean airConditionerOn) {
        AirConditionerOn = airConditionerOn;
    }


}
