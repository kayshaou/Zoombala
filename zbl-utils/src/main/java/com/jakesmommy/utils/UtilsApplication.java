package com.jakesmommy.utils;

import com.jakesmommy.utils.configuration.kafka.KafkaConfiguration;
import com.jakesmommy.utils.configuration.kafka.ProducerAbstract;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.common.config.TopicConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.web.context.support.GenericWebApplicationContext;

import javax.annotation.PostConstruct;

@SpringBootApplication
@RefreshScope
@EnableKafka
public class UtilsApplication {


	public static void main(String[] args) {
		SpringApplication.run(UtilsApplication.class, args);

	}




}
