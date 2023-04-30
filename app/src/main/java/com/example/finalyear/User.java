package com.example.finalyear;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class User {
    private String email;
    private String id;
    private boolean isAdmin;


    public User() {

    }

    public User(String email,String id, boolean isAdmin) {
        this.email = email;
        this.id=id;
        this.isAdmin = isAdmin;
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

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }


}

