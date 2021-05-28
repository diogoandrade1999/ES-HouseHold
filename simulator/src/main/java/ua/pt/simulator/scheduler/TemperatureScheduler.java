package ua.pt.simulator.scheduler;

import java.util.Calendar;
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
        this.kafkaTemplate.send("esp51-temperature", temperature);
    }

    @Scheduled(fixedRate = 5000)
    public void temperature() {
        Temperature temperature = new Temperature();
        temperature.setHouseId(1);
        temperature.setRoomId(1);

        // today
        Date date = new Date();
        temperature.setDate(date);

        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int hour = cal.get(Calendar.HOUR_OF_DAY);
        int month = cal.get(Calendar.MONTH);

        double temp;
        Random r = new Random();

        // Check if hour is between 7am and 7pm (day)
        if (hour >= 7 && hour <= 19) {
            if (month >= 1 && month <= 3)
                temp = (r.nextDouble() * (18 - 13)) + 13;
            else if (month >= 4 && month <= 6)
                temp = (r.nextDouble() * (23 - 16)) + 16;
            else if (month >= 6 && month <= 9)
                temp = (r.nextDouble() * (27 - 16)) + 16;
            else
                temp = (r.nextDouble() * (19 - 14)) + 14;
        } else {
            if (month >= 1 && month <= 3)
                temp = (r.nextDouble() * (15 - 9)) + 9;
            else if (month >= 4 && month <= 6)
                temp = (r.nextDouble() * (20 - 14)) + 14;
            else if (month >= 6 && month <= 9)
                temp = (r.nextDouble() * (24 - 14)) + 14;
            else
                temp = (r.nextDouble() * (17 - 10)) + 10;
        }
        temperature.setTemperature(temp);
        System.out.println(temperature);
        this.sendTemperature(temperature);
    }
}
