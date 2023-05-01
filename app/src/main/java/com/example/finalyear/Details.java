package com.example.finalyear;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Details implements Serializable {

    String amount, name, width,status,orderID,price;

    public Details () {

    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Details (String amount, String name, String width,String status,String orderID,String Price) {
        this.amount = amount;
        this.name = name;
        this.width = width;
        this.status=status;
        this.orderID = orderID;
        this.price = Price;
    }

    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("amount", amount);
        result.put("name", name);
        result.put("width", width);
        result.put("status", status);
        result.put("orderID", orderID);
        result.put("price",price);
        return result;
    }


}