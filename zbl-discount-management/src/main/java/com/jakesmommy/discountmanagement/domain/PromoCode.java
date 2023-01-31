package com.jakesmommy.discountmanagement.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Builder
public class PromoCode {
    private UUID uuid;
    private String alias;
    private Double discountPercentage;
    private String code;
}
