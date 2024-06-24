package telran.java52.pulse.configuration;


import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import telran.java52.pulse.deserializer.PulseDeserializer;
import telran.java52.pulse.dto.PulseDto;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConsumerConfig {

    @Value("pkc-12576z.us-west2.gcp.confluent.cloud:9092")
    private String bootstrapServers;

    @Value("antstein-group1")
    private String groupId;

    @Bean
    ConsumerFactory<String, PulseDto> consumerFactory() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, PulseDeserializer.class);
        props.put(JsonDeserializer.TRUSTED_PACKAGES, "org.apache.kafka.common.serialization.Deserializer");

        return new DefaultKafkaConsumerFactory<>(props, new StringDeserializer(), new JsonDeserializer<>(PulseDto.class));
    }

    @Bean
    ConcurrentKafkaListenerContainerFactory<String, PulseDto> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, PulseDto> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        return factory;
    }
}
