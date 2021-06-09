package pt.ua.simulator.scheduler;

import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import pt.ua.simulator.domains.Humidity;
import pt.ua.simulator.entities.Room;
import pt.ua.simulator.repositories.RoomRepository;

@Component
public class HumidityScheduler {

    @Autowired
    private KafkaTemplate<String, Humidity> kafkaTemplate;

    @Autowired
    private RoomRepository roomRepository;

    public void sendHumidity(Humidity humidity) {
        this.kafkaTemplate.send("esp51-humidity", humidity);
    }

    @Scheduled(fixedRate = 5000)
    public void humidity() {
        // today
        Date date = new Date();

        for (Room room : roomRepository.findAll()) {
            Humidity humidity = new Humidity();
            humidity.setRoomId(room.getRoomId());
            humidity.setHouseId(room.getHouseId());
            humidity.setDate(date);

            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            int hour = cal.get(Calendar.HOUR_OF_DAY);
            int month = cal.get(Calendar.MONTH);

            double hum;
            Random r = new Random();

            // Check if hour is between 7am and 7pm (day)
            if (hour >= 7 && hour <= 19) {
                if (month >= 1 && month <= 3)
                    hum = (r.nextDouble() * (60 - 30)) + 30;
                else if (month >= 4 && month <= 6)
                    hum = (r.nextDouble() * (100 - 70)) + 70;
                else if (month >= 6 && month <= 9)
                    hum = (r.nextDouble() * (80 - 50)) + 50;
                else
                    hum = (r.nextDouble() * (50 - 20)) + 20;
            } else {
                if (month >= 1 && month <= 3)
                    hum = (r.nextDouble() * (50 - 20)) + 20;
                else if (month >= 4 && month <= 6)
                    hum = (r.nextDouble() * (90 - 60)) + 60;
                else if (month >= 6 && month <= 9)
                    hum = (r.nextDouble() * (70 - 40)) + 40;
                else
                    hum = (r.nextDouble() * (40 - 10)) + 10;
            }
            humidity.setHumidity(hum);
            this.sendHumidity(humidity);
        }
    }
}
