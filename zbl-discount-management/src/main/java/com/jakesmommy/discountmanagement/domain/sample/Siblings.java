package com.jakesmommy.discountmanagement.domain.sample;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Siblings {
    private String name;
    private String relationship;
    private List<FavoriteColor> favoriteColors;
}
