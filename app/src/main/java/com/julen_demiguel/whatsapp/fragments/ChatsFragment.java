package com.julen_demiguel.whatsapp.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import com.julen_demiguel.whatsapp.Application.MyApplication;
import com.julen_demiguel.whatsapp.Models.Chat;
import com.julen_demiguel.whatsapp.Models.Message;
import com.julen_demiguel.whatsapp.Models.User;
import com.julen_demiguel.whatsapp.R;
import com.julen_demiguel.whatsapp.adapters.ChatRecyclerDataAdapter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;


public class ChatsFragment extends Fragment {

    RecyclerView recyclerView;
    ChatRecyclerDataAdapter recyclerDataAdapter;
    Realm realm;
    RealmResults<Chat> results;
    List<Chat> userChats = new RealmList<>();
    private ChatListener callback;

    public ChatsFragment() { }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        try {
            callback = (ChatListener) context;
        }catch (Exception e){
            throw new ClassCastException(context.toString() + "should implement DataListener");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        realm = Realm.getDefaultInstance();

        results = realm.where(Chat.class).findAll();
        if (results.size() == 0) {
            realm.beginTransaction();
            realm.copyToRealm(getDummyData());
            realm.commitTransaction();
            results = realm.where(Chat.class).findAll();
        }
        // TODO: MODIFICAR ESTO
//        for (result : results){
//            if (result.)
//        }
        userChats.addAll(results);

        View view = inflater.inflate(R.layout.fragment_chats, container, false);
        recyclerView = view.findViewById(R.id.idrecyclerShowChats);

        recyclerDataAdapter = new ChatRecyclerDataAdapter(userChats, new ChatRecyclerDataAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                callback.openChat(results.get(position).getId());
            }
        });
        recyclerView.setAdapter(recyclerDataAdapter);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 1));
        return view;
    }

    public Chat getDummyData() {
        RealmList<User> usersChat = new RealmList<User>();
        usersChat.add(MyApplication.currentUser);
        User userExample = new User("Paco", "123456789", "Hola");
        usersChat.add(userExample);
        RealmList<Message> messages = new RealmList<Message>();
        Date date = new Date();
        Message mensaje = new Message("Hola gente", MyApplication.currentUser, date);
        messages.add((mensaje));

        Chat chat = new Chat(messages, usersChat);
        return chat;
    }

    public interface ChatListener {
        public void openChat(int id);
    }
}