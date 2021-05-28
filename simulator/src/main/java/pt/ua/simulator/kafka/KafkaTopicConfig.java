package pt.ua.simulator.kafka;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaAdmin;

@Configuration
public class KafkaTopicConfig {

    @Value(value = "${spring.kafka.bootstrap-servers}")
    private String bootstrapAddress;

    @Bean
    public KafkaAdmin kafkaAdmin() {
        Map<String, Object> configs = new HashMap<>();
        configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        return new KafkaAdmin(configs);
    }

    @Bean
    public NewTopic topicTemperature() {
        return new NewTopic("esp51-temperature", 1, (short) 1);
    }

    @Bean
    public NewTopic topicLight() {
        return new NewTopic("esp51-light", 1, (short) 1);
    }

    @Bean
    public NewTopic topicHumidity() {
        return new NewTopic("esp51-humidity", 1, (short) 1);
    }

}