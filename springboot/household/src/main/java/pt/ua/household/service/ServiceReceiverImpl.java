package pt.ua.household.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

@Service
@Transactional
@Component
public class ServiceReceiverImpl {

    private static final String TOPIC = "commands";

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    private static Logger logger = LogManager.getLogger(ServiceReceiverImpl.class);

    private CountDownLatch latch = new CountDownLatch(1);

    public CountDownLatch getLatch() {
        return latch;
    }

    @KafkaListener(topics = TOPIC, groupId = "1")
    public void consume(String message) throws IOException {
        logger.info(String.format("Consumed message -> %s", message));
        logger.info("Actuator turning on AC!");

    }
}
