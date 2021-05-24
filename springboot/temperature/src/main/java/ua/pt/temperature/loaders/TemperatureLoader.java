package ua.pt.temperature.loaders;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import ua.pt.temperature.domains.Temperature;
import ua.pt.temperature.repositories.TemperatureRepository;

@Component
public class TemperatureLoader implements ApplicationListener<ContextRefreshedEvent> {

    private Logger log = LogManager.getLogger(TemperatureLoader.class);

    @Autowired
    private TemperatureRepository temperatureRepository;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        Temperature temperature = new Temperature();
        temperature.setTemperature(15);
        temperature.setHouseId(1);
        this.temperatureRepository.save(temperature);

        this.log.info("Temperature Saved!");
    }
}
