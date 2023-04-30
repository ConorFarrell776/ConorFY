package com.example.finalyear;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Details implements Serializable {

    String amount, name, width,status;

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

    public Details (String amount, String name, String width,String status) {
        this.amount = amount;
        this.name = name;
        this.width = width;
        this.status=status;
    }

    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("amount", amount);
        result.put("name", name);
        result.put("width", width);
        result.put("status", status);
        return result;
    }

    public String toString() {
        return this.amount + " frames, " + name + ", Width " + width + " Meter";
    }
}