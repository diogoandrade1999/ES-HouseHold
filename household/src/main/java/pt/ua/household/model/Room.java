package pt.ua.household.model;

import org.springframework.stereotype.Component;


public class Room {

    private long roomId;
    private static int id;
    private boolean AirConditionerOn;

    public Room(){
        this.roomId = id;
        id +=1;
        this.AirConditionerOn = false;

    }

    public long getRoomId() {
        return roomId;
    }

    public void setRoomId(long roomId) {
        this.roomId = roomId;
    }

    public static int getId() {
        return id;
    }

    public static void setId(int id) {
        Room.id = id;
    }

    public boolean isAirConditionerOn() {
        return AirConditionerOn;
    }

    public void setAirConditionerOn(boolean airConditionerOn) {
        AirConditionerOn = airConditionerOn;
    }


}
