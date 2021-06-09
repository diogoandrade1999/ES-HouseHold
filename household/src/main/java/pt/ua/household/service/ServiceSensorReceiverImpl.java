package pt.ua.household.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import pt.ua.household.entities.Room;
import pt.ua.household.model.Humidity;
import pt.ua.household.model.Luminosity;
import pt.ua.household.model.Temperature;
import pt.ua.household.repositories.RoomRepository;

import java.util.concurrent.CountDownLatch;

@Service
@Transactional
@Component
public class ServiceSensorReceiverImpl {

    private static final String TOPIC_COMMANDS = "esp51-commands";

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    private RoomRepository roomRepository;

    private static Logger logger = LogManager.getLogger(ServiceSensorReceiverImpl.class);

    public void sendCommand(String message) {
        // logger.info("Sending message " + message + " to topic "+ TOPIC_COMMANDS);
        kafkaTemplate.send(TOPIC_COMMANDS, message);

    }

    @Scheduled(fixedRate = 5000)
    public void getStates() {
        for (Room room : roomRepository.findAll()) {
            long roomId = room.getRoomId();
            long houseId = room.getHouse().getHouseId();
            Temperature temperature = resolveTemperatureState(houseId, roomId);
            if (temperature != null)
                logger.info("Temperature Received in API -> " + temperature.getTemperature());

            Humidity humidity = resolveHumidityState(houseId, roomId);
            if (humidity != null)
                logger.info("Humidity Received in API -> " + humidity.getHumidity());

            Luminosity luminosity = resolveLightState(houseId, roomId);
            if (luminosity != null)
                logger.info("Luminosity Received in API -> " + luminosity.getLight());
        }
    }

    public Temperature resolveTemperatureState(long houseId, long roomId) {
        RestTemplate restTemplate = new RestTemplate();
        String resourceUrl = "http://localhost:51020/temperature/recent";
        ResponseEntity<Temperature> response = restTemplate.getForEntity(resourceUrl + "/" + houseId + "/" + roomId,
                Temperature.class);
        Temperature temperature = response.getBody();
        return temperature;

    }

    public Humidity resolveHumidityState(long houseId, long roomId) {
        RestTemplate restTemplate = new RestTemplate();
        String resourceUrl = "http://localhost:51040/humidity/recent";
        ResponseEntity<Humidity> response = restTemplate.getForEntity(resourceUrl + "/" + houseId + "/" + roomId,
                Humidity.class);
        Humidity humidity = response.getBody();
        return humidity;

    }

    public Luminosity resolveLightState(long houseId, long roomId) {
        RestTemplate restTemplate = new RestTemplate();
        String resourceUrl = "http://localhost:51030/luminosity/recent";
        ResponseEntity<Luminosity> response = restTemplate.getForEntity(resourceUrl + "/" + houseId + "/" + roomId,
                Luminosity.class);
        Luminosity luminosity = response.getBody();
        return luminosity;
    }

}
