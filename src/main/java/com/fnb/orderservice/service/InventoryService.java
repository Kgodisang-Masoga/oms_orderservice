package com.fnb.orderservice.service;

import com.fnb.orderservice.dto.InventoryItemRequest;
import com.fnb.orderservice.dto.InventoryItemResponse;

public interface InventoryService {

    InventoryItemResponse addInventoryItem(InventoryItemRequest inventoryItemRequest);
}
