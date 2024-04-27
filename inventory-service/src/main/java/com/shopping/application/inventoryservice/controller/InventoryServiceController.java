package com.shopping.application.inventoryservice.controller;

import com.shopping.application.inventoryservice.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/inventory")
public class InventoryServiceController {
    @Autowired
    private InventoryService inventoryService;
    @GetMapping("/{skuCode}")
    @ResponseStatus(HttpStatus.OK)
    public boolean isInstock(@PathVariable String skuCode){
      return inventoryService.IsItemPresent(skuCode);
    }
}
