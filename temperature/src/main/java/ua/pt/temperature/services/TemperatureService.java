package ua.pt.temperature.services;

import ua.pt.temperature.domains.Temperature;

public interface TemperatureService {

    Iterable<Temperature> listAllTemperatures();

    Temperature getTemperatureById(long id);

    Temperature saveTemperature(Temperature temperature);

    long count();
}
