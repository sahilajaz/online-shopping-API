package com.shopping.application.orderservice.service;

import com.shopping.application.orderservice.dto.InventoryResponse;
import com.shopping.application.orderservice.dto.OrderLineItemsDto;
import com.shopping.application.orderservice.dto.OrderRequest;
import com.shopping.application.orderservice.model.Order;
import com.shopping.application.orderservice.model.OrderLineItemsList;
import com.shopping.application.orderservice.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;


@Service
@Slf4j
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private WebClient.Builder webClient;
    @Autowired
    private Order order;

    public void placeOrder(OrderRequest orderRequest) {
        order.setOrderNumber(UUID.randomUUID().toString());
        List<OrderLineItemsList> orderLineItemLists = orderRequest.getOrderLineItemsList().stream()
                .map(this::mapToDto)
                .toList();
        order.setOrderLineItemsList(orderLineItemLists);
        List<String> skuCode = order.getOrderLineItemsList().stream().map(orderLineItemsList -> orderLineItemsList.getSkuCode()).toList();
        InventoryResponse[]  inventoryResponseArray = webClient.build().get().uri("http://localhost:8082/api/inventory" ,
                        uriBuilder -> uriBuilder.queryParam("skuCode" , skuCode).build())
                .retrieve()
                .bodyToMono(InventoryResponse[].class).block();
        boolean allproductsinstock = Arrays.stream(inventoryResponseArray).allMatch(InventoryResponseArray -> InventoryResponseArray.isInStock());
        if(allproductsinstock) {
             orderRepository.save(order);
         }
         else {
             throw new IllegalArgumentException("product is not in stock , plese try again later");
         }
    }

    private OrderLineItemsList mapToDto(OrderLineItemsDto orderLineItemsDto) {
        OrderLineItemsList orderLineItemsList = new OrderLineItemsList();
        orderLineItemsList.setPrice(orderLineItemsDto.getPrice());
        orderLineItemsList.setQuantity(orderLineItemsDto.getQuantity());
        orderLineItemsList.setSkuCode(orderLineItemsDto.getSkuCode());
        return orderLineItemsList;
    }
}
