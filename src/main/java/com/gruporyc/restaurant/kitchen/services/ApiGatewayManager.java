package com.gruporyc.restaurant.kitchen.services;

import com.gruporyc.restaurant.kitchen.dto.OrderDTO;
import com.gruporyc.restaurant.kitchen.dto.SimpleResponse;

import java.util.List;

/**
 * ApiGatewayManager: Public interface to expose API gateway manager implementation
 * @author jmunoz
 * @since 07/08/2019
 * @version 1.0.0
 */
public interface ApiGatewayManager {

    List<OrderDTO> getActiveOrders();

    SimpleResponse updateOrderStatus(String orderId, String status);

    OrderDTO getOrderById(String orderId);

    SimpleResponse updateOrderItemStatus(String orderId, String itemId, String status);
}
