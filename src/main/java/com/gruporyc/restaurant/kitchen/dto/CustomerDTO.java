package com.gruporyc.restaurant.kitchen.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * CustomerDTO: Data transformation object for json transformation of Customer object
 * @author jmunoz
 * @since 07/08/2019
 * @version 1.0.0
 */
public class CustomerDTO {
    private String id;
    private String names;
    private String lastNames;
    private String address1;
    private String address2;
    private String city;
    private String state;
    private String Country;
    private String email;
    private String phone;

    /**
     * @return the CustomerDTO's universal identifier
     */
    @JsonProperty("id")
    public String getId() {
        return id;
    }

    /**
     * @param id the CustomerDTO's universal identifier
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the CustomerDTO's first name
     */
    @JsonProperty("first_name")
    public String getNames() {
        return names;
    }

    /**
     * @param names the CustomerDTO's first name
     */
    public void setNames(String names) {
        this.names = names;
    }

    /**
     * @return the CustomerDTO's last name
     */
    @JsonProperty("last_name")
    public String getLastNames() {
        return lastNames;
    }

    /**
     * @param lastNames the CustomerDTO's last name
     */
    public void setLastNames(String lastNames) {
        this.lastNames = lastNames;
    }

    /**
     * @return the CustomerDTO's address 1st line
     */
    @JsonProperty("address_1")
    public String getAddress1() {
        return address1;
    }

    /**
     * @param address1 the CustomerDTO's address 1st line
     */
    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    /**
     * @return the CustomerDTO's address 2nd line
     */
    @JsonProperty("address_2")
    public String getAddress2() {
        return address2;
    }

    /**
     * @param address2 the CustomerDTO's address 2nd line
     */
    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    /**
     * @return the CustomerDTO's city
     */
    @JsonProperty("city")
    public String getCity() {
        return city;
    }

    /**
     * @param city the CustomerDTO's address city
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * @return the CustomerDTO's state
     */
    @JsonProperty("state")
    public String getState() {
        return state;
    }

    /**
     * @param state the CustomerDTO's address state
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * @return the CustomerDTO's country
     */
    @JsonProperty("country")
    public String getCountry() {
        return Country;
    }

    /**
     * @param country the CustomerDTO's address country
     */
    public void setCountry(String country) {
        Country = country;
    }

    /**
     * @return the CustomerDTO's email
     */
    @JsonProperty("email")
    public String getEmail() {
        return email;
    }

    /**
     * @param email the CustomerDTO's email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the CustomerDTO's phone
     */
    @JsonProperty("phone")
    public String getPhone() {
        return phone;
    }

    /**
     * @param phone the CustomerDTO's phone
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "CustomerDTO{" +
                "id=" + id +
                ", names='" + names + '\'' +
                ", lastNames='" + lastNames + '\'' +
                ", address1='" + address1 + '\'' +
                ", address2='" + address2 + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", Country='" + Country + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
