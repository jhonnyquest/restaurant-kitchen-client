package com.gruporyc.restaurant.kitchen.handlers;

import com.gruporyc.restaurant.kitchen.dto.OrderDTO;
import com.gruporyc.restaurant.kitchen.dto.SimpleResponse;
import com.gruporyc.restaurant.kitchen.services.ApiGatewayManager;
import com.gruporyc.restaurant.kitchen.utilities.PropertyManager;
import com.gruporyc.restaurant.kitchen.utilities.Template;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import com.google.gson.Gson;
import org.springframework.web.client.HttpClientErrorException;
import spark.Route;

import java.text.SimpleDateFormat;
import java.util.*;

import static com.gruporyc.restaurant.kitchen.utilities.ModelEntry.withModel;
import static com.gruporyc.restaurant.kitchen.utilities.Template.render;

public class OrderHandler {
    @Autowired
    ApiGatewayManager apiManager;

    private final static Logger LOGGER = LogManager.getLogger(OrderHandler.class);
    private Properties properties;

    public OrderHandler(ApiGatewayManager apiManager) {
        this.apiManager = apiManager;
        PropertyManager pm = new PropertyManager();
        this.properties = pm.getInstance();
    }

    public Route viewActiveOrders() {
        return (req, resp) -> {
            try{
                List<OrderDTO> activeOrders = apiManager.getActiveOrders();
                List<OrderDTO> highOrders = new ArrayList<>();
                List<OrderDTO> mediumOrders = new ArrayList<>();
                List<OrderDTO> lowOrders = new ArrayList<>();
                Date currentDate = new Date();
                for (OrderDTO order: activeOrders) {
                    Date createDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(order.getCreateDate());
                    long waitingTime = (currentDate.getTime() - createDate.getTime())/60000;
                    order.setWaitingTime(waitingTime);
                    if(waitingTime > Long.valueOf(properties.getProperty("order.high.priority.time"))) {
                        highOrders.add(order);
                    } else if(waitingTime < Long.valueOf(properties.getProperty("order.high.priority.time")) &&
                            waitingTime > Long.valueOf(properties.getProperty("order.medium.priority.time"))) {
                        mediumOrders.add(order);
                    } else {
                        lowOrders.add(order);
                    }
                }
                return render(req, Template.LOAD_ORDERS,
                        withModel("highOrders", highOrders),
                        withModel("mediumOrders", mediumOrders),
                        withModel("lowOrders", lowOrders));
            } catch (HttpClientErrorException ex) {
                LOGGER.error(ex);
                return render(req, Template.NO_ACTIVE_ORDERS);
            }
        };
    }

    public Route updateOrderStatus() {
        return (req, resp) -> {
            String orderId = req.params(":orderId");
            String status = req.queryParams("status");
            SimpleResponse response = apiManager.updateOrderStatus(orderId, status);
            return new Gson().toJson(response);
        };
    }

    public Route getOrderById() {
        return (req, resp) -> {
            String orderId = req.params("orderId");
            OrderDTO Order = apiManager.getOrderById(orderId);
            return new Gson().toJson(Order);
        };
    }

    public Route updateOrderItemStatus() {
        return (req, resp) -> {
            String orderId = req.params(":orderId");
            String itemId = req.params(":itemId");
            String status = req.queryParams("status");
            SimpleResponse response = apiManager.updateOrderItemStatus(orderId, itemId,status);
            return new Gson().toJson(response);
        };
    }
}
