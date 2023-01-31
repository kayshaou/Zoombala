package com.jakesmommy.discountmanagement.domain;

import com.jakesmommy.discountmanagement.constants.ProductType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;
@Getter
@Setter
@Builder
public class Item {
    public UUID uuid;
    private String itemDescription;
    private Double itemPrice;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
    private ProductType productType;
}
