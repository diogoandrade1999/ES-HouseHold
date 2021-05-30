package pt.ua.humidity.services;

import java.util.Date;

import pt.ua.humidity.entities.Humidity;

public interface HumidityService {

    Humidity saveHumidity(Humidity humidity);

    Iterable<Humidity> getHumidityByDate(Date startDate, Date endDate);

    Iterable<Humidity> getHumidityByDateAndHouse(Date startDate, Date endDate, long houseId);

    Iterable<Humidity> getHumidityByDateAndHouseAndRoom(Date startDate, Date endDate, long houseId, long roomId);

    Humidity getRecentHumidity(long houseId, long roomId);

}
