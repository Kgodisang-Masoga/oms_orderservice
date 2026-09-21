package com.fnb.orderservice.service;

import com.fnb.orderservice.dto.OrderRequest;
import com.fnb.orderservice.dto.OrderResponse;

public interface OrderService {

    OrderResponse placeOrder(OrderRequest orderRequest);
}
