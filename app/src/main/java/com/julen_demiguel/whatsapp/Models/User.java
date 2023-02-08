package com.julen_demiguel.whatsapp.Models;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable {
    String name;
    String corre;
    String img;
    String password;
    String telef;

    public User(String name, String corre, String telef ) {
        this.name = name;
        this.corre = corre;
        this.telef = telef;
        this.password = "";
        this.img = "";

    }


    public User() {


    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCorre() {
        return corre;
    }

    public void setCorre(String corre) {
        this.corre = corre;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}
