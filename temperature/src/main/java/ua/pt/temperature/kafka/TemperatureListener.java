package ua.pt.temperature.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;

import ua.pt.temperature.entities.Temperature;
import ua.pt.temperature.services.TemperatureService;

import java.util.concurrent.CountDownLatch;

@Configuration
public class TemperatureListener {

    @Autowired
    private TemperatureService temperatureService;

    private CountDownLatch latch = new CountDownLatch(1);
    private Temperature payload = null;

    @KafkaListener(topics = "${temperature.topic}", groupId = "${spring.kafka.consumer.group-id}", containerFactory = "temperatureKafkaListenerContainerFactory")
    public void temperatureListener(Temperature temperature) {

        this.temperatureService.saveTemperature(temperature);

        setPayload(temperature);
        latch.countDown();
    }

    public CountDownLatch getLatch() {
        return latch;
    }

    public Temperature getPayload() {
        return payload;
    }

    private void setPayload(Temperature payload) {
        this.payload = payload;
    }

}
