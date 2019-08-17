package com.gruporyc.restaurant.kitchen.services.implementations;

import com.gruporyc.restaurant.kitchen.dto.OrderDTO;
import com.gruporyc.restaurant.kitchen.dto.OrderItemDTO;
import com.gruporyc.restaurant.kitchen.dto.SimpleResponse;
import com.gruporyc.restaurant.kitchen.services.ApiGatewayManager;
import com.gruporyc.restaurant.kitchen.utilities.PropertyManager;
import com.gruporyc.restaurant.kitchen.utilities.RestTemplateHelper;
import com.gruporyc.restaurant.kitchen.utilities.TextsHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;

import java.util.*;

/**
 * CustomerApiManagerImpl: Service that implements operations by using API gateway
 * @author jmunoz
 * @since 07/08/2019
 * @version 1.0.0
 */
@Component
public class ApiGatewayManagerImpl implements ApiGatewayManager {

    private static final Logger LOGGER = LogManager.getLogger(ApiGatewayManagerImpl.class);

    @Autowired
    private TextsHelper textsHelper;

    @Autowired
    private RestTemplateHelper rt;

    @Autowired
    private Properties properties;

    public ApiGatewayManagerImpl () {
        this.rt = new RestTemplateHelper();
        PropertyManager pm = new PropertyManager();
        this.properties = pm.getInstance();
    }

    @Override
    public List<OrderDTO> getActiveOrders() {
        try{
            ResponseEntity<OrderDTO[]> response = rt.processRequestGet(
                    properties.getProperty("api.restaurant.gateway.endpoint")  + "/orders/active",  null, OrderDTO[].class);
            return Arrays.asList(Objects.requireNonNull(response.getBody()));
        } catch(HttpClientErrorException ex) {
            if(ex.getStatusCode().equals(HttpStatus.NOT_FOUND))
                return null;
        }
        return null;
    }

    @Override
    public SimpleResponse updateOrderStatus(String orderId, String status) {
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("status", status);
        ResponseEntity<SimpleResponse> response = rt.processRequestPost(
                properties.getProperty("api.restaurant.gateway.endpoint") + "/orders/" + orderId + "/status", requestBody, SimpleResponse.class);
        return (response.getStatusCode() == HttpStatus.OK) ? response.getBody() : null;
    }

    @Override
    public OrderDTO getOrderById(String orderId) {
        try{
            ResponseEntity<OrderDTO> response = rt.processRequestGet(
                    properties.getProperty("api.restaurant.gateway.endpoint")  + "/orders/" + orderId,  null, OrderDTO.class);
            return Objects.requireNonNull(response.getBody());
        } catch(HttpClientErrorException ex) {
            if(ex.getStatusCode().equals(HttpStatus.NOT_FOUND))
                return null;
        }
        return null;
    }

    @Override
    public SimpleResponse updateOrderItemStatus(String orderId, String itemId, String status) {
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("status", status);
        ResponseEntity<SimpleResponse> response = rt.processRequestPost(
                properties.getProperty("api.restaurant.gateway.endpoint") + "/orders/" + orderId +
                        "/item/" + itemId + "/status", requestBody, SimpleResponse.class);
        return (response.getStatusCode() == HttpStatus.OK) ? response.getBody() : null;
    }
}
