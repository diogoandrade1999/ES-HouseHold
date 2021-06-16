package ua.pt.temperature.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.*;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.testcontainers.containers.KafkaContainer;
import org.testcontainers.utility.DockerImageName;
import ua.pt.temperature.entities.Temperature;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import org.springframework.kafka.support.serializer.JsonSerializer;
import ua.pt.temperature.repositories.TemperatureRepository;
import ua.pt.temperature.services.TemperatureService;


import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@Import(ua.pt.temperature.kafka.KafkaTestContainersLiveTest.KafkaTestContainersConfiguration.class)
@SpringBootTest
@DirtiesContext
@TestPropertySource(locations = "classpath:test.properties")
public class KafkaTestContainersLiveTest {

    @ClassRule
    public static KafkaContainer kafka = new KafkaContainer(DockerImageName.parse("confluentinc/cp-kafka:latest"));

    @Autowired
    public KafkaTemplate<String, Temperature> template;

    @Autowired
    public TemperatureListener temperatureListener;

    @Autowired
    public TemperatureRepository temperatureRepository;

    private String topic = "esp51-temperature-test";

    @Test
    public void givenKafkaDockerContainer_whenSendingTemperature_thenTemperatureReceived() throws Exception {

        Temperature toSend = new Temperature();
        toSend.setDate(new Date());
        toSend.setTemperature(20);
        toSend.setHouseId(1);
        toSend.setRoomId(1);
        template.send(topic, toSend);
        temperatureListener.getLatch().await(10000, TimeUnit.MILLISECONDS);
        assertThat(temperatureRepository.findAll().size(),is(1));
        assertThat(temperatureRepository.findFirstByHouseIdAndRoomIdOrderByDateDesc(1,1).getTemperature(), is(20.0));

    }

    @TestConfiguration
    static class KafkaTestContainersConfiguration {

        @Bean
        ConcurrentKafkaListenerContainerFactory<String, Temperature> temperatureKafkaListenerContainerFactory() {
            ConcurrentKafkaListenerContainerFactory<String, Temperature> factory = new ConcurrentKafkaListenerContainerFactory<>();
            factory.setConsumerFactory(consumerFactoryTest());
            return factory;
        }

        @Bean
        public ConsumerFactory<String, Temperature> consumerFactoryTest() {
            Map<String, Object> props = new HashMap<>();
            props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafka.getBootstrapServers());
            props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
            props.put(ConsumerConfig.GROUP_ID_CONFIG, "esp51");
            ObjectMapper om = new ObjectMapper();
            return new DefaultKafkaConsumerFactory<>(props, new StringDeserializer(),
                    new JsonDeserializer<Temperature>(Temperature.class, om, false));
        }

        @Bean
        public ProducerFactory<String, ?> producerFactoryTest() {
            Map<String, Object> configProps = new HashMap<>();
            configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafka.getBootstrapServers());
            configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
            configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
            return new DefaultKafkaProducerFactory<>(configProps);
        }

        @Bean
        public KafkaTemplate<String, ?> kafkaTemplate() {
            return new KafkaTemplate<>(producerFactoryTest());
        }

    }

}