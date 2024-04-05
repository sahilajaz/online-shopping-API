package com.shopping.application.orderservice.dto;

import lombok.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class OrderRequest {
    private List<OrderLineItemsDto> orderLineItemsList;
}
