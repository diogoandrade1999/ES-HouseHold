package ua.pt.temperature.services;

import java.util.Date;

import ua.pt.temperature.entities.Temperature;

public interface TemperatureService {

    Temperature saveTemperature(Temperature temperature);

    Iterable<Temperature> getTemperatureByDate(Date startDate, Date endDate);

    Iterable<Temperature> getTemperatureByDateAndHouse(Date startDate, Date endDate, long houseId);

    Iterable<Temperature> getTemperatureByDateAndHouseAndRoom(Date startDate, Date endDate, long houseId, long roomId);

}
