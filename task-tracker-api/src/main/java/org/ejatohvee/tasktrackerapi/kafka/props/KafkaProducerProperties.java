package org.ejatohvee.tasktrackerapi.kafka.props;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.time.Duration;

@Getter
@Setter
@ConfigurationProperties("kafka")
public class KafkaProducerProperties {
    private String bootstrapServers;

    private String clientId;

    private String acksMode;

    private Duration deliveryTimeout;

    private Integer lingerMs;

    private Integer batchSize;

    private Integer maxInFLightPerConnection;

    private Boolean enableIdempotence;
}
