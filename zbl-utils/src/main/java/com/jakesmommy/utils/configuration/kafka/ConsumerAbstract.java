package com.jakesmommy.utils.configuration.kafka;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;

@Slf4j
public abstract class ConsumerAbstract {

    @KafkaListener
    void listen() {

    }
}
