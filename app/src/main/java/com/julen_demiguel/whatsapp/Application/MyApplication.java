package com.julen_demiguel.whatsapp.Application;

import android.app.Application;

import androidx.constraintlayout.widget.Group;

import com.julen_demiguel.whatsapp.Models.Chat;
import com.julen_demiguel.whatsapp.Models.ChatGroup;
import com.julen_demiguel.whatsapp.Models.Message;
import com.julen_demiguel.whatsapp.Models.User;

import java.util.concurrent.atomic.AtomicInteger;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmObject;
import io.realm.RealmResults;

public class MyApplication extends Application {
    public static AtomicInteger userID = new AtomicInteger();
    public static AtomicInteger chatID = new AtomicInteger();
    public static AtomicInteger groupID = new AtomicInteger();
    public  static AtomicInteger mensajeID = new AtomicInteger();

    private <T extends RealmObject> AtomicInteger getIdByTable(Realm realm, Class<T>anyClass){
        RealmResults<T> results = realm.where(anyClass).findAll();
        if(results.size()>0){
            return new AtomicInteger(results.max("id").intValue());
        } else{
            return new AtomicInteger(0);
        }
    }

    @Override
    public void  onCreate(){
        super.onCreate();
        setUpRealmConfig();
        Realm.init(getApplicationContext());
        Realm realm = Realm.getDefaultInstance();
        userID = getIdByTable(realm, User.class);
        mensajeID = getIdByTable(realm, Message.class);
        groupID = getIdByTable(realm, ChatGroup.class);
        chatID = getIdByTable(realm, Chat.class);
        realm.close();
    }

    private void setUpRealmConfig(){
        Realm.init(getApplicationContext());
        RealmConfiguration config = new RealmConfiguration.Builder().deleteRealmIfMigrationNeeded().build();
        Realm.setDefaultConfiguration(config);
    }

}
