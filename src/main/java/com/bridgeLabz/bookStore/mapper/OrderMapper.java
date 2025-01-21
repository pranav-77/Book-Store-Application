package com.bridgeLabz.bookStore.mapper;

import com.bridgeLabz.bookStore.responsedto.OrderResponse;
import com.bridgeLabz.bookStore.model.Order;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper {

    public OrderResponse mapToOrderResponse(Order order) {
        OrderResponse orderResponse = new OrderResponse();
        orderResponse.setOrderId(order.getOrderId());
        orderResponse.setUserId(order.getUser().getUserId());
        orderResponse.setOrderDate(order.getOrderDate());
        orderResponse.setPrice(order.getPrice());
        orderResponse.setAddress(order.getAddress());
        orderResponse.setQuantity(order.getQuantity());
        return orderResponse;
    }
}

