package com.jakesmommy.discountmanagement;

import com.jakesmommy.discountmanagement.domain.sample.Me;
import com.jakesmommy.discountmanagement.domain.sample.MyGrandma;
import com.jakesmommy.discountmanagement.domain.sample.MyMom;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.actuate.autoconfigure.security.servlet.ManagementWebSecurityAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class, ManagementWebSecurityAutoConfiguration.class})
@EnableEurekaClient
@EnableKafka
public class DiscountManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(DiscountManagementApplication.class, args);
    }

    public static void str() {
        Me me = Me.builder()
                .myMom(MyMom.builder()
                        .myGrandma(MyGrandma.builder().build()).
                        build()).build();
    }
}
