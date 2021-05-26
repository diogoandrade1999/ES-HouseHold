package ua.pt.simulator.scheduler;

import java.util.Date;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import ua.pt.simulator.domains.Temperature;

@Component
public class TemperatureScheduler {

    @Autowired
    private KafkaTemplate<String, Temperature> kafkaTemplate;

    public void sendTemperature(Temperature temperature) {
        this.kafkaTemplate.send("temperature", temperature);
    }

    @Scheduled(fixedRate = 5000)
    public void temperature() {
        Temperature temperature = new Temperature();
        temperature.setHouseId(1);
        temperature.setRoomId(1);
        temperature.setTemperature(new Random().nextDouble() * 25);
        temperature.setDate(new Date());
        this.sendTemperature(temperature);
    }
}
