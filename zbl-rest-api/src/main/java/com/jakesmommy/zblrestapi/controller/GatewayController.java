package com.jakesmommy.zblrestapi.controller;

import com.jakesmommy.utils.configuration.kafka.ProducerAbstract;
import com.jakesmommy.utils.converter.JsonConverter;
import com.jakesmommy.zblrestapi.constant.TopicsConstants;
import com.jakesmommy.zblrestapi.decorator.IsAuthorized;
import com.jakesmommy.zblrestapi.domain.Item;
import com.jakesmommy.zblrestapi.domain.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@RestController
@Slf4j
@RequestMapping("/api/v1")
public class GatewayController {

    @Autowired
    @Qualifier("producerAbstract")
    ProducerAbstract producerAbstract;

    @Autowired
    @Qualifier("jsonConverter")
    JsonConverter jsonConverter;

    /* receive the request from the order this will push the message to producer */
    @IsAuthorized
    @GetMapping("/order/init")
    public ResponseEntity<Order> initializeOrder(@RequestBody Order order) {
        try {
            order.setUuid(UUID.randomUUID().toString());
            order.setCreatedDateTime(LocalDateTime.now());
            String uuid = UUID.randomUUID().toString();
            List<Item> itemList = Arrays.asList(Item.builder()
                    .uuid(uuid)
                    .itemAlias("itemAlias-" + uuid)
                    .itemName("itemName-" + uuid)
                    .build());
            order.setItemList(itemList);
            String deserOrder = jsonConverter.deserialize(order);
            log.info("deserOrder {}", deserOrder);
            producerAbstract.publish(TopicsConstants.Order.INCOMING, deserOrder);
        } catch (Exception e){
            log.error("err {}",e);
            return new ResponseEntity<>(order, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(order, HttpStatus.CREATED);
    }

}
