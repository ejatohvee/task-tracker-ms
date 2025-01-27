package org.ejatohvee.tasktrackerapi.kafka;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.ejatohvee.tasktrackerapi.dtos.EmailDto;
import org.ejatohvee.tasktrackerapi.kafka.props.KafkaProducerProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaProducerConfig {
    @Bean
    public ProducerFactory<String, EmailDto> emailDtoProducerFactory(KafkaProducerProperties producerProperties) {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, producerProperties.getBootstrapServers());
        configProps.put(ProducerConfig.CLIENT_ID_CONFIG, producerProperties.getClientId());
        configProps.put(ProducerConfig.ACKS_CONFIG, producerProperties.getAcksMode());
        configProps.put(ProducerConfig.DELIVERY_TIMEOUT_MS_CONFIG, (int) producerProperties.getDeliveryTimeout().toMillis());
        configProps.put(ProducerConfig.LINGER_MS_CONFIG, producerProperties.getLingerMs());
        configProps.put(ProducerConfig.BATCH_SIZE_CONFIG, producerProperties.getBatchSize());
        configProps.put(ProducerConfig.MAX_IN_FLIGHT_REQUESTS_PER_CONNECTION, producerProperties.getMaxInFLightPerConnection());
        configProps.put(ProducerConfig.ENABLE_IDEMPOTENCE_CONFIG, producerProperties.getEnableIdempotence());
        configProps.put(JsonSerializer.ADD_TYPE_INFO_HEADERS, false);
        configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);

        return new DefaultKafkaProducerFactory<>(configProps);
    }

    @Bean
    public KafkaTemplate<String, EmailDto> kafkaTemplate(ProducerFactory<String, EmailDto> producerFactory) {
        return new KafkaTemplate<>(producerFactory);
    }
}
