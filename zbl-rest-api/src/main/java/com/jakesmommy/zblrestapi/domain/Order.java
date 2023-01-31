package com.jakesmommy.zblrestapi.domain;

import com.jakesmommy.zblrestapi.constant.Status;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Order {
    private String uuid;
    private List<Item> itemList;
    private LocalDateTime createdDateTime;
    private LocalDateTime updatedDateTime;
    private Status status;
    private Error error;
}
