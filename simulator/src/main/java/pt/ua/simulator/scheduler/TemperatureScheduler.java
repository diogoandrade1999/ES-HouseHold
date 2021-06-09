package pt.ua.simulator.scheduler;

import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import pt.ua.simulator.domains.Temperature;
import pt.ua.simulator.entities.Room;
import pt.ua.simulator.repositories.RoomRepository;

@Component
public class TemperatureScheduler {

    @Autowired
    private KafkaTemplate<String, Temperature> kafkaTemplate;

    @Autowired
    private RoomRepository roomRepository;

    public void sendTemperature(Temperature temperature) {
        this.kafkaTemplate.send("esp51-temperature", temperature);
    }

    @Scheduled(fixedRate = 5000)
    public void temperature() {
        // today
        Date date = new Date();

        for (Room room : roomRepository.findAll()) {
            Temperature temperature = new Temperature();
            temperature.setRoomId(room.getRoomId());
            temperature.setHouseId(room.getHouseId());
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
            this.sendTemperature(temperature);
        }
    }
}
