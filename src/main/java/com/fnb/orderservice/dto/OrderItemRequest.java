package com.fnb.orderservice.dto;

import lombok.Data;

@Data
public class OrderItemRequest {
    private Long orderItemId;

    private Integer quantity;
}
