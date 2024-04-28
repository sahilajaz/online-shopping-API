package com.shopping.application.inventoryservice.controller;

import com.shopping.application.inventoryservice.service.InventoryService;
import com.shopping.application.inventoryservice.response.InventoryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
public class InventoryServiceController {
    @Autowired
    private InventoryService inventoryService;
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<InventoryResponse> isInstock(@RequestParam List<String> skuCode){
      return inventoryService.IsItemPresent(skuCode);
    }
}
