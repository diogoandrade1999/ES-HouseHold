package pt.ua.simulator.domains;

import java.util.Date;

public class Luminosity {

    private double luminosity;
    private Date date;
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
        return "{" + " luminosity='" + getLuminosity() + "'" + ", date='" + getDate() + "'" + ", houseId='"
                + getHouseId() + "'" + ", roomId='" + getRoomId() + "'" + "}";
    }

}
