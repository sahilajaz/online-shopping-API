package com.shopping.application.orderservice.service;

import com.shopping.application.orderservice.dto.OrderLineItemsDto;
import com.shopping.application.orderservice.dto.OrderRequest;
import com.shopping.application.orderservice.model.Order;
import com.shopping.application.orderservice.model.OrderLineItemsList;
import com.shopping.application.orderservice.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
@Slf4j
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private Order order;

    public void placeOrder(OrderRequest orderRequest) {
        order.setOrderNumber(UUID.randomUUID().toString());
        List<OrderLineItemsList> orderLineItemLists = orderRequest.getOrderLineItemsList().stream()
                .map(this::mapToDto)
                .toList();
        order.setOrderLineItemsList(orderLineItemLists);
        orderRepository.save(order);
    }

    private OrderLineItemsList mapToDto(OrderLineItemsDto orderLineItemsDto) {
        OrderLineItemsList orderLineItemsList = new OrderLineItemsList();
        orderLineItemsList.setPrice(orderLineItemsDto.getPrice());
        orderLineItemsList.setQuantity(orderLineItemsDto.getQuantity());
        orderLineItemsList.setSkuCode(orderLineItemsDto.getSkuCode());
        return orderLineItemsList;
    }
}
