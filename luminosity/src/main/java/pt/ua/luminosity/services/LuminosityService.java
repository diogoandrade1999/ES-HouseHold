package pt.ua.luminosity.services;

import java.util.Date;

import pt.ua.luminosity.entities.Luminosity;

public interface LuminosityService {

    Luminosity saveLuminosity(Luminosity luminosity);

    Iterable<Luminosity> getLuminosityByDate(Date startDate, Date endDate);

    Iterable<Luminosity> getLuminosityByDateAndHouse(Date startDate, Date endDate, long houseId);

    Iterable<Luminosity> getLuminosityByDateAndHouseAndRoom(Date startDate, Date endDate, long houseId, long roomId);

    Luminosity getRecentLuminosity(long houseId, long roomId);

}
