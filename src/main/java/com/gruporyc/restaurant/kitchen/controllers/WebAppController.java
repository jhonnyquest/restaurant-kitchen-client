package com.gruporyc.restaurant.kitchen.controllers;

import com.gruporyc.restaurant.kitchen.dto.OrderDTO;
import com.gruporyc.restaurant.kitchen.dto.SimpleResponse;
import com.gruporyc.restaurant.kitchen.services.OrderHandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
public class WebAppController {

    @Autowired
    OrderHandler orderHandler;

    @RequestMapping(value = "/orders", method = {RequestMethod.GET})
    public String loadOrders(HttpServletRequest request){
        return orderHandler.ActiveOrders(request);
    }

    @RequestMapping(value = "/orders/{order_id}", method = {RequestMethod.GET})
    public OrderDTO getOrderById(@PathVariable("order_id") String OrderId){
        return orderHandler.getOrderById(OrderId);
    }

    @RequestMapping(value = "/orders/{order_id}/status", method = {RequestMethod.POST})
    public SimpleResponse updateOrderStatus(@PathVariable("order_id") String orderId,
                                            @RequestBody ModelMap payload){
        return orderHandler.updateOrderStatus(orderId, payload.get("status").toString());
    }

    @RequestMapping(value = "/orders/{order_id}/item/{item_id}/status", method = {RequestMethod.POST})
    public SimpleResponse updateOrderStatus(@PathVariable("order_id") String orderId,
                                            @PathVariable("item_id") String itemId,
                                            @RequestBody ModelMap payload){
        return orderHandler.updateOrderItemStatus(orderId, itemId, payload.get("status").toString());
    }
}
