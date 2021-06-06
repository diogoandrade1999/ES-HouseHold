package ua.pt.temperature.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import ua.pt.temperature.entities.Temperature;

import java.util.concurrent.CountDownLatch;

@Component
public class TemperatureConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(TemperatureConsumer.class);

    private CountDownLatch latch = new CountDownLatch(1);
    private Temperature payload = null;

    @KafkaListener(topics = "esp51-temperature", groupId = "esp51")
    public void receiveTemperature(Temperature temperature) {
        LOGGER.info("received payload='{}'", temperature.toString());
        setPayload(temperature);
        latch.countDown();
    }

    public CountDownLatch getLatch() {
        return latch;
    }

    public Temperature getPayload() {
        return payload;
    }

    private void setPayload(Temperature payload) {
        this.payload = payload;
    }
}
