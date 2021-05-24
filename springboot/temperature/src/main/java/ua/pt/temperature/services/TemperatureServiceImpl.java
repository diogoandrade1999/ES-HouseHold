package ua.pt.temperature.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.pt.temperature.domains.Temperature;
import ua.pt.temperature.repositories.TemperatureRepository;

@Service
public class TemperatureServiceImpl implements TemperatureService {

    @Autowired
    private TemperatureRepository temperatureRepository;

    @Override
    public Iterable<Temperature> listAllTemperatures() {
        return this.temperatureRepository.findAll();
    }

    @Override
    public Temperature saveTemperature(Temperature temperature) {
        return this.temperatureRepository.save(temperature);
    }

    @Override
    public long count() {
        return this.temperatureRepository.count();
    }

    @Override
    public Temperature getTemperatureById(long id) {
        return this.temperatureRepository.findById(id).orElse(null);
    }

}
