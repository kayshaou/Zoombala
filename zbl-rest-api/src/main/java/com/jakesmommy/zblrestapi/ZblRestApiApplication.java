package com.jakesmommy.zblrestapi;

import com.jakesmommy.utils.configuration.kafka.ProducerAbstract;
import com.jakesmommy.utils.converter.JsonConverter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

import javax.annotation.PostConstruct;

@SpringBootApplication
@EnableEurekaClient
public class ZblRestApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZblRestApiApplication.class, args);
	}

	@Bean("producerAbstract")
	public ProducerAbstract producerAbstract(){
		return new ProducerAbstract();
	}

	@Bean("jsonConverter")
	public JsonConverter jsonConverter(){
		return new JsonConverter();
	}


}
