package ua.pt.temperature.services;

import ua.pt.temperature.entities.Temperature;

public interface TemperatureService {

    Iterable<Temperature> listAllTemperatures();

    Temperature getTemperatureById(long id);

    Temperature saveTemperature(Temperature temperature);

    void deleteTemperatureById(long id);

    long count();
}
