package com.jakesmommy.utils.configuration.kafka;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

/**
 * This class produces message to Kafka
 */
@Slf4j
@NoArgsConstructor
@Component("producerAbstract")
public class ProducerAbstract {

    @Autowired KafkaTemplate<String, String> kafkaTemplate;
    public void publish(String topicName, String message) {
        kafkaTemplate.send(topicName, message);
    }

    public void publish(String topicName, String message, boolean isNonBlocking) {
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
