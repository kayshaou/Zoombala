package com.jakesmommy.discountmanagement.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
public class Order {

    private UUID uuid;
    private List<Item> items;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
    private Double orderTotal; /* before discount */
    private Double orderSubTotal; /* after discount */
    private PromoCode promoCode;


}
