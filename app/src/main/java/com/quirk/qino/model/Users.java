package com.quirk.qino.model;

/**
 * Created by albertcahyawan on 5/23/2017.
 */

public class Users {
    public String name;
    public String email;
    public String phonenumber;

    public Users() {
        this.name = "name";
        this.email = "Email";
        this.phonenumber = "phone";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }
}
