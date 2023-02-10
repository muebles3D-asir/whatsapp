package com.julen_demiguel.whatsapp.Models;

import com.julen_demiguel.whatsapp.Application.MyApplication;

import java.io.Serializable;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class User extends RealmObject implements Serializable {

    @PrimaryKey
    private int id;
    private String name;
    private String img;
    private String password;
    private String telef;

    public User(){}

    public User(String name, String telef ) {
        this.id = MyApplication.userID.incrementAndGet();

        this.name = name;
        this.telef = telef;
        this.password = "";
        this.img = "";
    }

    public User(String name,  String telef, String password) {
        this.id = MyApplication.userID.incrementAndGet();
        this.name = name;
        this.telef = telef;
        this.password = password;
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
        return telef.equals(user.telef);
    }

}
