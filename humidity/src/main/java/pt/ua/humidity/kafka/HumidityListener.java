package pt.ua.humidity.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;

import pt.ua.humidity.entities.Humidity;
import pt.ua.humidity.services.HumidityService;

@Configuration
public class HumidityListener {

    @Autowired
    private HumidityService humidityService;

    @KafkaListener(topics = "esp51-humidity", groupId = "esp51", containerFactory = "humidityKafkaListenerContainerFactory")
    public void humidityListener(Humidity humidity) {
        this.humidityService.saveHumidity(humidity);
    }
}
