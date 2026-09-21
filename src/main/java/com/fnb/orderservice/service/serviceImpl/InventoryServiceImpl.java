package com.fnb.orderservice.service.serviceImpl;

import com.fnb.orderservice.dto.InventoryItemRequest;
import com.fnb.orderservice.dto.InventoryItemResponse;
import com.fnb.orderservice.entity.InventoryItem;
import com.fnb.orderservice.repository.InventoryItemRepository;
import com.fnb.orderservice.service.InventoryService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InventoryServiceImpl implements InventoryService {

    private final InventoryItemRepository inventoryItemRepository;

    @Override
    public InventoryItemResponse addInventoryItem(InventoryItemRequest inventoryItemRequest) {
        //Building InventoryItem entity

        InventoryItem inventoryItem = InventoryItem.builder()
                                    .itemName(inventoryItemRequest.getItemName())
                                    .description(inventoryItemRequest.getDescription())
                                    .price(inventoryItemRequest.getPrice())
                                    .stockQuantity(inventoryItemRequest.getStockQuantity())
                                    .build();

        inventoryItemRepository.save(inventoryItem);
        return toResponse(inventoryItem);
    }

    private InventoryItemResponse toResponse(InventoryItem inventoryItem) {
        return InventoryItemResponse.builder()
                .itemId(inventoryItem.getItemId())
                .itemName(inventoryItem.getItemName())
                .description(inventoryItem.getDescription())
                .price(inventoryItem.getPrice())
                .stockQuantity(inventoryItem.getStockQuantity())
                .build();
    }
}
