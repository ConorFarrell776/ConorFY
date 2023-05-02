package com.example.finalyear;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class User {
    private String email;
    private String id;
    String admin;
    String eircode;

    public User() {

    }

    public User(String email,String id,String admin,String eircode) {
        this.email = email;
        this.id=id;
        this.admin=admin;
        this.eircode=eircode;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAdmin() {
        return admin;
    }

    public void setAdmin(String admin) {
        this.admin = admin;
    }

    public String getEircode() {
        return eircode;
    }

    public void setEircode(String eircode) {
        this.eircode = eircode;
    }


}

