package com.jakesmommy.utils.configuration.kafka;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.config.TopicConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaAdmin;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.web.context.support.GenericWebApplicationContext;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
@Slf4j
public class KafkaConfiguration {
//    @Value(value="${kafka.bootstrap.server.config}")
//    public String BOOTSTRAP_SERVERS_CONFIG;
//    @Value(value="${kafka.serializer.class.config}")
//    public String KEY_SERIALIZER_CLASS_CONFIG;
//    @Value(value="${kafka.value.class.config}")
//    public String VALUE_SERIALIZER_CLASS_CONFIG;
    private final GenericWebApplicationContext context;

    @Autowired(required = false)
    TopicConfiguration topicConfiguration;

    public KafkaConfiguration(GenericWebApplicationContext context, TopicConfiguration topicConfiguration){
        this.context = context;
        this.topicConfiguration = topicConfiguration;
    }

    @Bean
    public ProducerFactory<Integer, String> producerFactory() throws ClassNotFoundException {
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
    public KafkaTemplate<Integer, String> kafkaTemplate() throws ClassNotFoundException {
        return new KafkaTemplate<Integer, String>(producerFactory());
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





}
