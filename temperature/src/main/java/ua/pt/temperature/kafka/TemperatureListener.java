package ua.pt.temperature.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;

import ua.pt.temperature.domains.Temperature;
import ua.pt.temperature.services.TemperatureService;

@Configuration
public class TemperatureListener {

    @Autowired
    private TemperatureService temperatureService;

    @KafkaListener(topics = "temperature", groupId = "esp51", containerFactory = "temperatureKafkaListenerContainerFactory")
    public void temperatureListener(Temperature temperature) {
        this.temperatureService.saveTemperature(temperature);
    }
}