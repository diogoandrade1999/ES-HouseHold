package pt.ua.simulator.scheduler;

import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import pt.ua.simulator.domains.Luminosity;

@Component
public class LuminosityScheduler {

    @Autowired
    private KafkaTemplate<String, Luminosity> kafkaTemplate;

    public void sendLuminosity(Luminosity luminosity) {
        this.kafkaTemplate.send("esp51-luminosity", luminosity);
    }

    @Scheduled(fixedRate = 5000)
    public void luminosity() {
        Luminosity luminosity = new Luminosity();
        luminosity.setHouseId(1);
        luminosity.setRoomId(1);

        // today
        Date date = new Date();
        luminosity.setDate(date);

        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int hour = cal.get(Calendar.HOUR_OF_DAY);
        int month = cal.get(Calendar.MONTH);

        double lum;
        Random r = new Random();

        // Check if hour is between 7am and 7pm (day)
        if (hour >= 7 && hour <= 19) {
            if (month >= 1 && month <= 3)
                lum = (r.nextDouble() * (180 - 120)) + 120;
            else if (month >= 4 && month <= 6)
                lum = (r.nextDouble() * (200 - 130)) + 130;
            else if (month >= 6 && month <= 9)
                lum = (r.nextDouble() * (500 - 150)) + 150;
            else
                lum = (r.nextDouble() * (120 - 100)) + 100;
        } else {
            if (month >= 1 && month <= 3)
                lum = (r.nextDouble() * (6 - 1)) + 1;
            else if (month >= 4 && month <= 6)
                lum = (r.nextDouble() * (8 - 1)) + 1;
            else if (month >= 6 && month <= 9)
                lum = (r.nextDouble() * (10 - 1)) + 1;
            else
                lum = (r.nextDouble() * (3 - 1)) + 1;
        }
        luminosity.setLuminosity(lum);
        this.sendLuminosity(luminosity);
    }
}
