package pt.ua.household.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import pt.ua.household.model.Humidity;
import pt.ua.household.model.Light;
import pt.ua.household.model.Temperature;

import java.util.concurrent.CountDownLatch;

@Service
@Transactional
@Component
@EnableKafka
public class ServiceSensorReceiverImpl {

    private static final String TOPIC_TEMPERATURE = "esp51-temperature";
    private static final String TOPIC_COMMANDS = "esp51-commands";

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    private static Logger logger = LogManager.getLogger(ServiceSensorReceiverImpl.class);

    private CountDownLatch latch = new CountDownLatch(1);

    public CountDownLatch getLatch() {
        return latch;
    }

    /*
    @KafkaListener(topics = TOPIC_TEMPERATURE, groupId = "1")
    public void consumeTemperature(String message) throws IOException {
        // logger.info(String.format("Consumed message -> %s", message));

        // Convert json message to Temperature object
        ObjectMapper mapper = new ObjectMapper();
        Temperature temp = mapper.readValue(message, Temperature.class);
        logger.info("Temperature Received -> " + temp.getTemperature());

        if (temp.getTemperature() >= 20) {
            sendCommand("Turn on AC!");
        }

    }
    */
    public void sendCommand(String message) {
        // logger.info("Sending message " + message + " to topic "+ TOPIC_COMMANDS);
        kafkaTemplate.send(TOPIC_COMMANDS, message);

    }

    @Scheduled(fixedRate = 5000)
    public void resolveTemperatureState(){
        RestTemplate restTemplate = new RestTemplate();
        String resourceUrl = "http://localhost:51020/temperature/recent";
        long houseId = 1;
        long roomId = 1;
        ResponseEntity<Temperature> response = restTemplate.getForEntity(resourceUrl + "/"+houseId + "/"+roomId, Temperature.class);
        Temperature temperature = response.getBody();
        logger.info("Temperature Received in API -> " + temperature.getTemperature());

    }

    @Scheduled(fixedRate = 5000)
    public void resolveHumidityState(){
        RestTemplate restTemplate = new RestTemplate();
        String resourceUrl = "http://localhost:51040/humidity/recent";
        long houseId = 1;
        long roomId = 1;
        ResponseEntity<Humidity> response = restTemplate.getForEntity(resourceUrl + "/"+houseId + "/"+roomId, Humidity.class);
        Humidity humidity = response.getBody();
        logger.info("Humidity Received in API -> " + humidity.getHumidity());

    }

    @Scheduled(fixedRate = 5000)
    public void resolveLightState(){
        RestTemplate restTemplate = new RestTemplate();
        String resourceUrl = "http://localhost:51030/light/recent";
        long houseId = 1;
        long roomId = 1;
        ResponseEntity<Light> response = restTemplate.getForEntity(resourceUrl + "/"+houseId + "/"+roomId, Light.class);
        Light light = response.getBody();
        logger.info("Light Received in API -> " + light.getLight());

    }


}
