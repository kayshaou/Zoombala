package com.jakesmommy.utils.configuration.kafka;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import static com.jakesmommy.utils.configuration.kafka.KafkaConfiguration.GROUP_ID;

@Slf4j
@Component
public class ConsumerAbstract {

    @KafkaListener(topics = {"zbl_inward"}, groupId = GROUP_ID)
    public void listen(ConsumerRecord<String, String> payload) {
        log.info("message Received as {}",payload.value());
    }
}
