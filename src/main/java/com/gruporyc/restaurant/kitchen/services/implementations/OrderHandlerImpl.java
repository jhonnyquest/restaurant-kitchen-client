package com.gruporyc.restaurant.kitchen.services.implementations;

import com.gruporyc.restaurant.kitchen.dto.OrderDTO;
import com.gruporyc.restaurant.kitchen.dto.SimpleResponse;
import com.gruporyc.restaurant.kitchen.services.ApiGatewayManager;
import com.gruporyc.restaurant.kitchen.services.OrderHandler;
import com.gruporyc.restaurant.kitchen.utilities.PropertyManager;
import com.gruporyc.restaurant.kitchen.utilities.VTemplate;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import static com.gruporyc.restaurant.kitchen.utilities.ModelEntry.withModel;
import static com.gruporyc.restaurant.kitchen.utilities.VTemplate.ERROR_PAGE;
import static com.gruporyc.restaurant.kitchen.utilities.VTemplate.LOAD_ORDERS;

@Component
public class OrderHandlerImpl implements OrderHandler {

    @Autowired
    ApiGatewayManager apiManager;

    private Properties properties;

    private static final Logger LOGGER = LogManager.getLogger(OrderHandlerImpl.class);

    public OrderHandlerImpl () {
        PropertyManager pm = new PropertyManager();
        this.properties = pm.getInstance();
    }

    @Override
    public String ActiveOrders(HttpServletRequest request) {
        try{
            List<OrderDTO> activeOrders = apiManager.getActiveOrders();
            List<OrderDTO> highOrders = new ArrayList<>();
            List<OrderDTO> mediumOrders = new ArrayList<>();
            List<OrderDTO> lowOrders = new ArrayList<>();
            Date currentDate = new Date();
            for (OrderDTO order: activeOrders) {
                Date createDate = null;
                try {
                    createDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(order.getCreateDate());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                assert createDate != null;
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

            return VTemplate.render(request, LOAD_ORDERS,
                    withModel("highOrders", highOrders),
                    withModel("mediumOrders", mediumOrders),
                    withModel("lowOrders", lowOrders));
        } catch (Exception ex) {
            LOGGER.error(ex);
            return VTemplate.render(request, ERROR_PAGE,
                    withModel("errorCode", "500"));
        }
    }

    public SimpleResponse updateOrderStatus(String orderId, String status) {
        return apiManager.updateOrderStatus(orderId, status);
    }

    public OrderDTO getOrderById(String orderId) {
        OrderDTO order = null;
        try{
            order = apiManager.getOrderById(orderId);
        } catch (Exception ex) {
            throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
        }

        return order;
    }

    public SimpleResponse updateOrderItemStatus(String orderId, String itemId, String status) {
        return apiManager.updateOrderItemStatus(orderId, itemId,status);
    }
}
