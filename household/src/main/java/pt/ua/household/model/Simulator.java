package pt.ua.household.model;

public class Simulator {

    private long roomId;
    private long houseId;

    private double temperature;
    private double humidity;
    private int light;

    public Simulator(long roomId, long houseId) {
        this.roomId = roomId;
        this.houseId = houseId;
    }

    public long getRoomId() {
        return this.roomId;
    }

    public void setRoomId(long roomId) {
        this.roomId = roomId;
    }

    public long getHouseId() {
        return this.houseId;
    }

    public void setHouseId(long houseId) {
        this.houseId = houseId;
    }

    public double getTemperature() {
        return this.temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public double getHumidity() {
        return this.humidity;
    }

    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }

    public int getLight() {
        return this.light;
    }

    public void setLight(int light) {
        this.light = light;
    }

    @Override
    public String toString() {
        return "{" + " id='" + getRoomId() + "'" + ", houseId='" + getHouseId() + "'" + ", temperature='"
                + getTemperature() + "'" + ", humidity='" + getHumidity() + "'" + ", light='" + getLight() + "'" + "}";
    }

}
