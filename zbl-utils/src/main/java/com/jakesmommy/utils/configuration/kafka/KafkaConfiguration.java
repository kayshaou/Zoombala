package com.jakesmommy.utils.configuration.kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaAdmin;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConfiguration {
    @Value(value="${kafka.bootstrap.server.config}")
    public String BOOTSTRAP_SERVERS_CONFIG;
    @Value(value="${kafka.serializer.class.config}")
    public String KEY_SERIALIZER_CLASS_CONFIG;
    @Value(value="${kafka.value.class.config}")
    public String VALUE_SERIALIZER_CLASS_CONFIG;

    @Bean
    public ProducerFactory<Integer, String> producerFactory() throws ClassNotFoundException {
        return new DefaultKafkaProducerFactory<>(producerConfigs());
    }
    @Bean
    public Map<String, Object> producerConfigs() throws ClassNotFoundException {
        Map<String, Object> configMap = new HashMap<>();
        configMap.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS_CONFIG);
        configMap.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, Class.forName(KEY_SERIALIZER_CLASS_CONFIG));
        configMap.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, Class.forName(VALUE_SERIALIZER_CLASS_CONFIG));
        return configMap;
    }

    @Bean
    public KafkaTemplate<Integer, String> kafkaTemplate() throws ClassNotFoundException {
        return new KafkaTemplate<Integer, String>(producerFactory());
    }

    @Bean
    public KafkaAdmin kafkaAdmin() throws ClassNotFoundException {
        return new KafkaAdmin(producerConfigs());
    }

    @Bean
    public NewTopic topic1(String topicName) {
        return new NewTopic(topicName, 1, (short) 1);
    }


}
