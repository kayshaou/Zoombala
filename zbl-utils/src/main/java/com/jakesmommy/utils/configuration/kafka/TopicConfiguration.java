package com.jakesmommy.utils.configuration.kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;
@Configuration
@ConfigurationProperties(prefix="kafkas")
public class TopicConfiguration {
    List<TopicConfigModel> topics;

    static class TopicConfigModel {
        public String name;
        public Integer numPartitions;
        public Short replicaFactor;
        public NewTopic topic() {
            return new NewTopic(this.name, this.numPartitions, this.replicaFactor);
        }
    }

}
