package pt.ua.household.service;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import pt.ua.household.entities.Room;
import pt.ua.household.entities.User;
import pt.ua.household.model.Alert;
import pt.ua.household.model.Humidity;
import pt.ua.household.model.Luminosity;
import pt.ua.household.model.Temperature;
import pt.ua.household.repositories.RoomRepository;

@Component
@Transactional
public class NotificationsService {

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private KafkaTemplate<String, Alert> kafkaTemplate;


    private static Logger logger = LogManager.getLogger(NotificationsService.class);

    public void sendAlert(Alert alert) {
        this.kafkaTemplate.send("esp51-alerts", alert);
    }

    @Scheduled(fixedRate = 5000)
    public void getStates(){

        for (Room r: roomRepository.findAll()) {

            long houseId = r.getHouse().getHouseId();
            long roomId = r.getRoomId();
            Temperature temperature = resolveTemperatureState(houseId, roomId);
            //logger.info("Temperature Received in API -> " + temperature.getTemperature());

            Humidity humidity = resolveHumidityState(houseId, roomId);
            //logger.info("Humidity Received in API -> " + humidity.getHumidity());

            Luminosity luminosity = resolveLightState(houseId, roomId);
            //logger.info("Luminosity Received in API -> " + luminosity.getLight());

            if (temperature.getTemperature() >= 25.0) {

                for (User user: r.getHouse().getUsers()){
                    Alert alert = new Alert(user.getUserId(), r.getHouse().getHouseId(), r.getRoomId(),"temperature", "Temperature above normal!");
                    sendAlert(alert);
                }

            }

            else if (temperature.getTemperature() <= 15.0){

                for (User user: r.getHouse().getUsers()){
                    Alert alert = new Alert(user.getUserId(), r.getHouse().getHouseId(), r.getRoomId(), "temperature", "Temperature below normal!");
                    sendAlert(alert);
                }

            }

            if (humidity.getHumidity() >= 60){

                for (User user: r.getHouse().getUsers()){
                    Alert alert = new Alert(user.getUserId(), r.getHouse().getHouseId(), r.getRoomId(), "humidity", "Humidity above normal!");
                    sendAlert(alert);
                }

            }

            else if(humidity.getHumidity() <= 15){

                for (User user: r.getHouse().getUsers()){
                    Alert alert = new Alert(user.getUserId(), r.getHouse().getHouseId(), r.getRoomId(), "humidity", "Humidity below normal!");
                    sendAlert(alert);
                }

            }

            if (luminosity.getLight() >= 300){

                for (User user: r.getHouse().getUsers()){
                    Alert alert = new Alert(user.getUserId(), r.getHouse().getHouseId(), r.getRoomId(), "luminosity", "Luminosity above normal!");
                    sendAlert(alert);
                }
            }

            else if (luminosity.getLight() <= 100){

                for (User user: r.getHouse().getUsers()){
                    Alert alert = new Alert(user.getUserId(), r.getHouse().getHouseId(), r.getRoomId(), "luminosity", "Luminosity below normal!");
                    sendAlert(alert);
                }

            }
        }

    }


    public Temperature resolveTemperatureState(long houseId, long roomId) {
        RestTemplate restTemplate = new RestTemplate();
        String resourceUrl = "http://192.168.160.87:51020/temperature/recent";
        ResponseEntity<Temperature> response = restTemplate.getForEntity(resourceUrl + "/" + houseId + "/" + roomId,
                Temperature.class);
        Temperature temperature = response.getBody();
        return temperature;

    }

    public Humidity resolveHumidityState(long houseId, long roomId) {
        RestTemplate restTemplate = new RestTemplate();
        String resourceUrl = "http://192.168.160.87:51040/humidity/recent";
        ResponseEntity<Humidity> response = restTemplate.getForEntity(resourceUrl + "/" + houseId + "/" + roomId,
                Humidity.class);
        Humidity humidity = response.getBody();
        return humidity;


    }

    public Luminosity resolveLightState(long houseId, long roomId) {
        RestTemplate restTemplate = new RestTemplate();
        String resourceUrl = "http://192.168.160.87:51030/luminosity/recent";
        ResponseEntity<Luminosity> response = restTemplate.getForEntity(resourceUrl + "/" + houseId + "/" + roomId,
                Luminosity.class);
        Luminosity luminosity = response.getBody();
        return luminosity;

    }
}
