package com.fnb.orderservice.service.serviceImpl;

import com.fnb.orderservice.dto.OrderItemRequest;
import com.fnb.orderservice.dto.OrderItemResponse;
import com.fnb.orderservice.dto.OrderRequest;
import com.fnb.orderservice.dto.OrderResponse;
import com.fnb.orderservice.entity.InventoryItem;
import com.fnb.orderservice.entity.Order;
import com.fnb.orderservice.entity.OrderItem;
import com.fnb.orderservice.entity.OrderStatus;
import com.fnb.orderservice.repository.InventoryItemRepository;
import com.fnb.orderservice.repository.OrderRepository;
import com.fnb.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private InventoryItemRepository inventoryItemRepository;
    private OrderRepository orderRepository;

    @Override
    public OrderResponse placeOrder(OrderRequest orderRequest) {
        Order order = Order.builder()
                .customerId(orderRequest.getCustomerId())
                .orderDate(LocalDateTime.now())
                .orderStatus(OrderStatus.PLACED)
                .totalAmount(BigDecimal.ZERO)
                .build();

        BigDecimal total = BigDecimal.ZERO;

        for (OrderItemRequest item : orderRequest.getItems()){
            InventoryItem inventoryItem = inventoryItemRepository.findById(item.getOrderItemId())
                    .orElseThrow(()-> new RuntimeException("Item not found " + item.getOrderItemId()));

            inventoryItem.setStockQuantity(inventoryItem.getStockQuantity() - item.getQuantity());
            inventoryItemRepository.save(inventoryItem);

            BigDecimal subtotal = inventoryItem.getPrice().multiply(BigDecimal.valueOf(item.getQuantity()));
            total = total.add(subtotal);

            OrderItem orderItem = OrderItem.builder()
                    .inventoryItem(inventoryItem)
                    .quantity(item.getQuantity())
                    .unitPriceAtPurchase(inventoryItem.getPrice())
                    .subtotal(subtotal).
                    build();

            order.addItem(orderItem);
        }
        order.setTotalAmount(total);
        order = orderRepository.save(order);

        return toResponse(order);
    }

    private OrderResponse toResponse(Order order){
        List<OrderItemResponse> items = order.getItems().stream()
                .map(orderItem -> OrderItemResponse.builder()
                        .orderItemId(orderItem.getOrder().getOrderId())
                        .itemName(orderItem.getInventoryItem().getItemName())
                        .quantity(orderItem.getQuantity())
                        .price(orderItem.getUnitPriceAtPurchase())
                        .quantity(orderItem.getQuantity())
                        .totalPrice(orderItem.getSubtotal())
                        .build()
                )
                .toList();

        return OrderResponse.builder()
                .orderId(order.getOrderId())
                .customerId(order.getCustomerId())
                .orderDate(order.getOrderDate())
                .status(order.getOrderStatus().name())
                .totalAmount(order.getTotalAmount())
                .orderItems(items)
                .build();
    }
}
