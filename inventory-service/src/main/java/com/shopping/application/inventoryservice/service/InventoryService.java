package com.shopping.application.inventoryservice.service;

import com.shopping.application.inventoryservice.inventoryRepository.InventoryRepository;
import com.shopping.application.inventoryservice.response.InventoryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventoryService {
    @Autowired
    private InventoryRepository inventoryRepository;


    public List<InventoryResponse> IsItemPresent(List<String> skuCode) {
        return inventoryRepository.findBySkuCodeIn(skuCode)
                .stream().map(inventory -> InventoryResponse.builder().skucode(inventory.getSkuCode())
                        .isInStock(inventory.getQuantity() >0 ).build()).toList();
    }
}
