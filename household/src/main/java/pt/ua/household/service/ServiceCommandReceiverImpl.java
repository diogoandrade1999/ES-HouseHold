package pt.ua.household.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pt.ua.household.model.Temperature;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

@Service
@Transactional
public class ServiceCommandReceiverImpl {

    private static final String TOPIC_COMMANDS = "esp51_commands";

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    private static Logger logger = LogManager.getLogger(ServiceCommandReceiverImpl.class);

    private CountDownLatch latch = new CountDownLatch(1);

    public CountDownLatch getLatch() {
        return latch;
    }

    @KafkaListener(topics = TOPIC_COMMANDS, groupId = "1")
    public void consumeCommand(String command) throws IOException {
        logger.info(String.format("Command to perform -> %s", command));

    }


}
