package com.fnb.orderservice.controller;

import com.fnb.orderservice.dto.InventoryItemRequest;
import com.fnb.orderservice.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
public class inventoryController {

    private final InventoryService inventoryService;

    @PostMapping("/add")
    public ResponseEntity<?> addInventoryItem(@RequestBody InventoryItemRequest inventoryItemRequest){
        return ResponseEntity.status(HttpStatus.CREATED).body(inventoryService.addInventoryItem(inventoryItemRequest));
    }
}
