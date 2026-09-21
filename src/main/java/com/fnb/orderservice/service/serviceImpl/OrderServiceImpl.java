package com.fnb.orderservice.service.serviceImpl;

import com.fnb.orderservice.dto.OrderRequest;
import com.fnb.orderservice.dto.OrderResponse;
import com.fnb.orderservice.entity.Order;
import com.fnb.orderservice.repository.OrderRepository;
import com.fnb.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    @Override
    public OrderResponse placeOrder(OrderRequest orderRequest) {
        return null;
    }
}
