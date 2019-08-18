package com.gruporyc.restaurant.kitchen.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

/**
 * OrderDto: Data transformation object for json transformation of Order object
 * @author jmunoz
 * @since 31/07/2019
 * @version 1.0.0
 */
public class OrderDTO {
    private String id;
    private BigDecimal total;
    private String table;
    private String status;
    @NotNull(message = "is required")
    private String customerId;
    @NotNull(message = "is required")
    private List<OrderItemDTO> items;
    private Long waitingTime;
    private String createDate;
    private String updateDate;


    /**
     * @return the Order's universal identifier
     */
    @JsonProperty("id")
    public String getId() {
        return id;
    }

    /**
     * @param id the order universal identifier
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the Order's total amount
     */
    @JsonProperty("total")
    public BigDecimal getTotal() {
        return total;
    }

    /**
     * @param total the order total amount
     */
    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    /**
     * @return the table that request order
     */
    @JsonProperty("table")
    public String getTable() {
        return table;
    }

    /**
     * @param table table that request order
     */
    public void setTable(String table) {
        this.table = table;
    }

    /**
     * @return the Order status
     */
    @JsonProperty("status")
    public String getStatus() {
        return status;
    }

    /**
     * @param status the order status
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return the Customer's universal identifier
     */
    @JsonProperty("customer_id")
    public String getCustomerId() {
        return customerId;
    }

    /**
     * @param customerId the customer universal identifier
     */
    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    /**
     * @return the list of order items
     */
    @JsonProperty("items")
    public List<OrderItemDTO> getItems() {
        return items;
    }

    /**
     * @param items the list of order items
     */
    public void setItems(List<OrderItemDTO> items) {
        this.items = items;
    }

    /**
     * @return the order waiting time
     */
    @JsonProperty("waiting_time")
    public Long getWaitingTime() {
        return waitingTime;
    }

    /**
     * @param waitingTime the order waiting time
     */
    public void setWaitingTime(Long waitingTime) {
        this.waitingTime = waitingTime;
    }

    /**
     * @return the created date
     */
    @JsonProperty("created_date")
    public String getCreateDate() {
        return createDate;
    }

    /**
     * @param createDate the created date
     */
    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    /**
     * @return the updated date
     */
    @JsonProperty("updated_date")
    public String getUpdateDate() {
        return updateDate;
    }

    /**
     * @param updateDate the updated date
     */
    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    @Override
    public String toString() {
        return "OrderDTO{" +
                "id='" + id + '\'' +
                ", total=" + total +
                ", table='" + table + '\'' +
                ", status='" + status + '\'' +
                ", customerId='" + customerId + '\'' +
                ", items=" + items +
                ", waitingTime=" + waitingTime +
                ", createDate='" + createDate + '\'' +
                ", updateDate='" + updateDate + '\'' +
                '}';
    }
}
