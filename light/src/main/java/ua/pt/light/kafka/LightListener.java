package ua.pt.light.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;

import ua.pt.light.entities.Light;
import ua.pt.light.services.LightService;

@Configuration
public class LightListener {

    @Autowired
    private LightService lightService;

    @KafkaListener(topics = "esp51-light", groupId = "esp51", containerFactory = "lightKafkaListenerContainerFactory")
    public void lightListener(Light light) {
        this.lightService.saveLight(light);
    }
}
