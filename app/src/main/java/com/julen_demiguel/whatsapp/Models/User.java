package com.julen_demiguel.whatsapp.Models;

import java.util.Date;

public class User {
    String name;
    String corre;
    String img;
    String password;
    Date fechaNac;

    public User(String name, String corre, String password, Date fechaNac) {
        this.name = name;
        this.corre = corre;
        this.password = password;

    }
    public User(String name, String corre, String password, Date fechaNac, ) {
        this.name = name;
        this.corre = corre;
        this.password = password;

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
