package pt.ua.luminosity.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;

import pt.ua.luminosity.entities.Luminosity;
import pt.ua.luminosity.services.LuminosityService;

@Configuration
public class LuminosityListener {

    @Autowired
    private LuminosityService luminosityService;

    @KafkaListener(topics = "esp51-luminosity", groupId = "esp51", containerFactory = "luminosityKafkaListenerContainerFactory")
    public void luminosityListener(Luminosity luminosity) {
        this.luminosityService.saveLuminosity(luminosity);
    }
}
