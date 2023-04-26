package com.example.finalyear;

public class Details {

    String amount, name, width,id;

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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Details (String amount, String name, String width,String id) {
        this.amount = amount;
        this.name = name;
        this.width = width;
        this.id=id;
    }

    public String toString() {
        return this.amount + " " + name + " " + width;
    }
}