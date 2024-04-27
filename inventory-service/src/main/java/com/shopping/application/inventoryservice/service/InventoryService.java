package com.shopping.application.inventoryservice.service;

import com.shopping.application.inventoryservice.inventoryRepository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InventoryService {
    @Autowired
    private InventoryRepository inventoryRepository;


    public boolean IsItemPresent(String skuCode) {
        return inventoryRepository.findBySkuCode(skuCode).isPresent();
    }
}
