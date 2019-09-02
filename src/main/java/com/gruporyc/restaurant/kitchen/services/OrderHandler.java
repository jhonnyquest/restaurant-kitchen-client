package com.gruporyc.restaurant.kitchen.services;

import com.gruporyc.restaurant.kitchen.dto.OrderDTO;
import com.gruporyc.restaurant.kitchen.dto.SimpleResponse;

import javax.servlet.http.HttpServletRequest;

public interface OrderHandler {
    String ActiveOrders(HttpServletRequest request);
    SimpleResponse updateOrderStatus(String orderId, String status);
    OrderDTO getOrderById(String orderId);
    SimpleResponse updateOrderItemStatus(String orderId, String itemId, String status);
}
