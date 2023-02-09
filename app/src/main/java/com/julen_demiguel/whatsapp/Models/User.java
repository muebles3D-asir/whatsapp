package com.julen_demiguel.whatsapp.Models;

import com.julen_demiguel.whatsapp.Application.MyApplication;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class User extends RealmObject implements Serializable {

    @PrimaryKey
    private int id;
    private String name;
    private String corre;
    private String img;
    private String password;
    private String telef;

    public User(){}

    public User(String name, String corre, String telef ) {
        this.id = MyApplication.userID.incrementAndGet();
        this.name = name;
        this.corre = corre;
        this.telef = telef;
        this.password = "";
        this.img = "";
    }

    public int getId() {
        return id;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return corre.equals(user.corre);
    }

}
