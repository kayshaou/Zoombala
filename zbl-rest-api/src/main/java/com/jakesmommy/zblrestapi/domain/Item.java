package com.jakesmommy.zblrestapi.domain;

import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Item {
    private String uuid;
    private String itemName;
    private String itemAlias;
    private LocalDateTime createdDateTime;
    private LocalDateTime updatedDateTime;

}
