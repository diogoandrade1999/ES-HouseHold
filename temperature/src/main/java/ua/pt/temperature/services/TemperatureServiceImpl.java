package ua.pt.temperature.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.pt.temperature.entities.Temperature;
import ua.pt.temperature.repositories.TemperatureRepository;

@Service
public class TemperatureServiceImpl implements TemperatureService {

    @Autowired
    private TemperatureRepository temperatureRepository;

    @Override
    public Temperature saveTemperature(Temperature temperature) {
        return this.temperatureRepository.save(temperature);
    }

    @Override
    public Iterable<Temperature> getTemperatureByDate(Date startDate, Date endDate) {
        return this.temperatureRepository.findByDateBetween(startDate, endDate);
    }

    @Override
    public Iterable<Temperature> getTemperatureByDateAndHouse(Date startDate, Date endDate, long houseId) {
        return this.temperatureRepository.findByDateBetweenAndHouseId(startDate, endDate, houseId);
    }

    @Override
    public Iterable<Temperature> getTemperatureByDateAndHouseAndRoom(Date startDate, Date endDate, long houseId,
            long roomId) {
        return this.temperatureRepository.findByDateBetweenAndHouseIdAndRoomId(startDate, endDate, houseId, roomId);
    }

}
