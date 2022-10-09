package com.jakesmommy.utils.configuration.kafka;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.config.TopicConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.*;
import org.springframework.web.context.support.GenericWebApplicationContext;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Configuration
@EnableKafka
@Slf4j
public class KafkaConfiguration {
    private final GenericWebApplicationContext context;
    public static final String GROUP_ID = "group_id";

    @Autowired(required = false)
    TopicConfiguration topicConfiguration;


    public KafkaConfiguration(GenericWebApplicationContext context, TopicConfiguration topicConfiguration) {
        this.context = context;
        this.topicConfiguration = topicConfiguration;
    }

    @Bean
    public ProducerFactory<String, String> producerFactory() throws ClassNotFoundException {
        return new DefaultKafkaProducerFactory<>(producerConfigs());
    }

    @Bean
    public Map<String, Object> producerConfigs() throws ClassNotFoundException {
        Map<String, Object> configMap = new HashMap<>();
        configMap.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        configMap.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configMap.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        return configMap;
    }

    @Bean
    public KafkaTemplate<String, String> kafkaTemplate() throws ClassNotFoundException {
        return new KafkaTemplate<String, String>(producerFactory());
    }

    @Bean
    public KafkaAdmin kafkaAdmin() throws ClassNotFoundException {
        return new KafkaAdmin(producerConfigs());
    }


    @PostConstruct
    public void createTopics() {
        log.info(" -- PostConstruct --{}");
        for (TopicConfiguration.TopicConfigModel t : topicConfiguration.getTopics()) {
            context.registerBean(t.getName(), NewTopic.class, t::topic);
        }

    }

    public String toString() {
        log.info(" This is an overloaded method");
        return StringUtils.EMPTY;
    }

    @Bean
    public ConsumerFactory<String, String> consumerFactory() throws ClassNotFoundException {
        log.info(" consumerFactory registered");
        Map<String, Object> config = new HashMap<>();
        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        config.put(ConsumerConfig.GROUP_ID_CONFIG, GROUP_ID);
        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        return new DefaultKafkaConsumerFactory<>(config);
    }

    public ConcurrentKafkaListenerContainerFactory concurrentKafkaListenerContainerFactory() throws ClassNotFoundException {
        ConcurrentKafkaListenerContainerFactory<String, String> factory
                = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        return factory;
    }


}
