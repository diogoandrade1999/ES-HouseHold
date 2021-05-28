package pt.ua.simulator.scheduler;

import java.util.Date;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import pt.ua.simulator.domains.Humidity;

@Component
public class HumidityScheduler {

    @Autowired
    private KafkaTemplate<String, Humidity> kafkaTemplate;

    public void sendHumidity(Humidity humidity) {
        this.kafkaTemplate.send("esp51-humidity", humidity);
    }

    @Scheduled(fixedRate = 5000)
    public void humidity() {
        Humidity humidity = new Humidity();
        humidity.setHouseId(1);
        humidity.setRoomId(1);
        humidity.setHumidity(new Random().nextDouble() * 25);
        humidity.setDate(new Date());
        System.out.println(humidity);
        this.sendHumidity(humidity);
    }
}
