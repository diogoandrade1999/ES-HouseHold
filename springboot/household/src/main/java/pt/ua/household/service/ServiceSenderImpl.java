package pt.ua.household.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ServiceSenderImpl {

    private static final String TOPIC = "commands";

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    private static Logger logger = LogManager.getLogger(ServiceSenderImpl.class);


    @Scheduled(fixedRate = 10000)
    public void getTemperature(){
        logger.info("Checking temperature");

        double temperature = 20;

        if (temperature >= 20){
            logger.info("Sending command");
            sendMessage("Turn on AC!");
        }

    }
    public void sendMessage(String message) {
        logger.info("Sending message " + message + " to topic "+ TOPIC);
        kafkaTemplate.send(TOPIC, message);

    }


}
