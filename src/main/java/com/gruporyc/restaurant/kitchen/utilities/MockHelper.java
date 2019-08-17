package com.gruporyc.restaurant.kitchen.utilities;

import com.gruporyc.restaurant.kitchen.dto.CustomerDTO;
import com.gruporyc.restaurant.kitchen.dto.OrderDTO;
import com.gruporyc.restaurant.kitchen.dto.OrderItemDTO;
import com.gruporyc.restaurant.kitchen.enums.Status;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

public class MockHelper {

    private static final String[] NAME = {"Jhonny", "Ruben", "María", "Thiago"};
    private static final String[] LAST_NAME = {"Munoz", "Ibarra", "Monserrate", "Stark"};
    private static final String[] PHONE_PREFIX = {"+1", "+58", "+57", "+51"};

    private static final String[] CITY = {"TORONTO", "OTTAWA", "MONTREAL", "VANCOUVER"};
    private static final String[] STATE = {"ONTARIO", "QUEBEC", "ALBERTA", "MANITOBA"};
    private static final String[] COUNTRY = {"CANADA", "VENEZUELA", "COLOMBIA", "UNITED STATES"};
    private static final String[] ADDRES_LINE = {"Test addres Line 1", "Test addres Line 2", "Test addres Line 3",
            "Test addres Line 4"};
    private static final String[] ITEM_NAME = {"Test item 1", "Test item 2", "Test item 3", "Test item 4"};
    private static final String[] ITEM_DESCRIPTION = {"Test long description 1", "Test long description 2",
            "Test long description 3", "Test long description 4"};
    public static final int NUMBER_MAX_LENGHT = 100000000;

    private MockHelper() {}

    public static OrderDTO getOrderDtoByIdByStatus(String orderId, Status status) {

        List<OrderItemDTO> itemList = getItemList(new Random().nextInt(5) + 1 );
        CustomerDTO customer = newCustomer();
        OrderDTO order = new OrderDTO();
        order.setId(orderId);
        order.setStatus(status.name());
        order.setItems(itemList);
        order.setTotal(BigDecimal.valueOf(itemList.stream().mapToDouble(orderItemDTO -> orderItemDTO.getSubTotal().doubleValue()).sum()));
        order.setCustomerId(customer.getId());
        return order;
    }

    public static CustomerDTO newCustomer() {
        CustomerDTO customer = new CustomerDTO();
        customer.setId(getRandomId());
        customer.setNames(getRandomNames());
        customer.setLastNames(getRandomLastNames());
        customer.setEmail(getRandomEmail());
        customer.setPhone(getRandomPhone());
        customer.setCity(getRandomCity());
        customer.setState(getRandomState());
        customer.setCountry(getRandomCountry());
        customer.setAddress1(getRandomAddressLine());
        customer.setAddress2(getRandomAddressLine());
        return customer;
    }

    public static CustomerDTO getCustomerById(String customerId) {
        CustomerDTO customer = newCustomer();
        customer.setId(customerId);
        return customer;
    }

    public static OrderItemDTO newRandomItem() {
        OrderItemDTO item = new OrderItemDTO();
        long itemQtty = (long) new Random().nextInt(5);
        BigDecimal itemPrice = getRandomPrice();
        item.setId(getRandomId());
        item.setName(getRandomItemName());
        item.setDescription(getRandomItemDescription());
        item.setPrice(itemPrice);
        item.setQuantity(itemQtty);
        item.setSubTotal(itemPrice.multiply(new BigDecimal(itemQtty)));
        return item;
    }
    
    public static List<OrderItemDTO> getItemList(int qtty) {
        return new ArrayList<OrderItemDTO>() {{
            for (int i = 0 ; i < qtty ; i++) {
                add(newRandomItem());
            }
        }};
    }

    public static BigDecimal getRandomPrice() {
        return BigDecimal.valueOf(new Random().nextDouble() * (10 - 100000));
    }


    private static String getRandomString(int length) {

        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        Random random = new Random();
        StringBuilder buffer = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int randomLimitedInt = leftLimit + (int)
                    (random.nextFloat() * (rightLimit - leftLimit + 1));
            buffer.append((char) randomLimitedInt);
        }
        return buffer.toString();
    }

    public static String getRandomEmail() {
        return getRandomString(10) + "@" + getRandomString(10) + "." + getRandomString(3);
    }

    public static String getRandomPhone() {
        return PHONE_PREFIX[new Random().nextInt(3)] +
                (10000000000L + (long) (Math.random() * (9999999999L - 10000000000L)));
    }

    public static String getRandomNames() {
        return NAME[new Random().nextInt(NAME.length - 1)]
                + " " + NAME[new Random().nextInt(NAME.length - 1)];
    }

    public static String getRandomLastNames() {
        return LAST_NAME[new Random().nextInt(LAST_NAME.length - 1)]
                + " " + LAST_NAME[new Random().nextInt(LAST_NAME.length - 1)];
    }

    public static String getRandomCity() {
        return CITY[new Random().nextInt(CITY.length - 1)];
    }

    public static String getRandomState() {
        return STATE[new Random().nextInt(STATE.length - 1)];
    }

    public static String getRandomCountry() {
        return COUNTRY[new Random().nextInt(COUNTRY.length - 1)];
    }

    public static String getRandomAddressLine() {
        return ADDRES_LINE[new Random().nextInt(ADDRES_LINE.length - 1)];
    }

    public static String getRandomItemName() {
        return ITEM_NAME[new Random().nextInt(ITEM_NAME.length - 1)];
    }

    public static String getRandomItemDescription() {
        return ITEM_DESCRIPTION[new Random().nextInt(ITEM_DESCRIPTION.length - 1)];
    }

    public static String getRandomId() {
        return UUID.randomUUID().toString();
    }
}
