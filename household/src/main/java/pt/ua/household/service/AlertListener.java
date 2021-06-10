package pt.ua.household.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;
import pt.ua.household.model.Alert;


@Configuration
public class AlertListener {

    private static Logger logger = LogManager.getLogger(AlertListener.class);

    @KafkaListener(topics = "esp51-alerts", groupId = "esp51", containerFactory = "alertKafkaListenerContainerFactory")
    public void alertListener(Alert alert) {
        logger.info("Received an alert for user -> " + alert.getUserId());
    }

}
