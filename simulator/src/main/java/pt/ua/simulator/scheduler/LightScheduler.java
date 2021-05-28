package pt.ua.simulator.scheduler;

import java.util.Date;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import pt.ua.simulator.domains.Light;

@Component
public class LightScheduler {

    @Autowired
    private KafkaTemplate<String, Light> kafkaTemplate;

    public void sendLight(Light light) {
        this.kafkaTemplate.send("esp51-light", light);
    }

    @Scheduled(fixedRate = 5000)
    public void light() {
        Light light = new Light();
        light.setHouseId(1);
        light.setRoomId(1);
        light.setLight(new Random().nextDouble() * 25);
        light.setDate(new Date());
        System.out.println(light);
        this.sendLight(light);
    }
}
