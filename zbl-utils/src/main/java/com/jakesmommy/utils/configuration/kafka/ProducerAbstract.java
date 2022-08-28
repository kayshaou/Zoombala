package com.jakesmommy.utils.configuration.kafka;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

/**
 * This class produces message to Kafka
 */
@Slf4j
public abstract class ProducerAbstract {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    void publish(String topicName, String message) {
        kafkaTemplate.send(topicName, message);
    }

    void publish(String topicName, String message, boolean isNonBlocking) {
        ListenableFuture<SendResult<String, String>> future =
                kafkaTemplate.send(topicName, message);

        future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {
            @Override
            public void onSuccess(SendResult<String, String> result) {
                log.info("Sent message=[" + message +
                        "] with offset=[" + result.getRecordMetadata().offset() + "] partition=["+result.getRecordMetadata().partition()+"]");
            }
            @Override
            public void onFailure(Throwable ex) {
                System.out.println("Unable to send message=["
                        + message + "] due to : " + ex.getMessage());
            }
        });
    }


}
