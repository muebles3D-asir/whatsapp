package com.julen_demiguel.whatsapp.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.julen_demiguel.whatsapp.Application.MyApplication;
import com.julen_demiguel.whatsapp.Models.Chat;
import com.julen_demiguel.whatsapp.Models.Message;
import com.julen_demiguel.whatsapp.Models.User;
import com.julen_demiguel.whatsapp.R;
import com.julen_demiguel.whatsapp.adapters.ChatRecyclerDataAdapter;

import java.util.Date;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;


public class ChatsFragment extends Fragment {

    RecyclerView recyclerView;
    ChatRecyclerDataAdapter recyclerDataAdapter;
    Realm realm;
    RealmResults<Chat> resultsChat;
    RealmResults<User> resultsUser;
    List<Chat> userChats = new RealmList<>();
    private ChatListener callback;

    public ChatsFragment() { }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        realm = Realm.getDefaultInstance();
        try {
            callback = (ChatListener) context;
        } catch (Exception e) {
            throw new ClassCastException(context + "should implement DataListener");
        }

        resultsChat = realm.where(Chat.class).findAll();
        if (resultsChat.size() == 0) {
            realm.beginTransaction();
            realm.copyToRealm(getDummyData(true));
            realm.copyToRealm(getDummyData(false));
            realm.commitTransaction();
            resultsChat = realm.where(Chat.class).findAll();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        resultsChat = realm.where(Chat.class).findAll();
        // TODO: MODIFICAR ESTO (Filtrar los chats)
        for (Chat chat : resultsChat) {
            if (chat.getParticipants().contains(MyApplication.currentUser))
                userChats.add(chat);
        }

        View view = inflater.inflate(R.layout.fragment_chats, container, false);
        recyclerView = view.findViewById(R.id.idrecyclerShowChats);

        recyclerDataAdapter = new ChatRecyclerDataAdapter(userChats, position -> callback.openChat(resultsChat.get(position).getId()));
        recyclerView.setAdapter(recyclerDataAdapter);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 1));
        return view;
    }

    private Chat getDummyData(boolean currentUser) {
        RealmList<User> usersChat = new RealmList<>();
        RealmList<Message> messages = new RealmList<>();
        User userExample = new User("Paco", "123456789", "test");
        User userExample2 = new User("Pepe", "668456978", "test");

        usersChat.add(userExample);
        Date date = new Date();

        if (currentUser) usersChat.add(MyApplication.currentUser);
        else usersChat.add(userExample2);

        Message mensaje = new Message("Hola gente", userExample, date);
        messages.add(mensaje);
        Chat chat = new Chat(messages, usersChat);
        return chat;
    }

    public interface ChatListener {
        void openChat(int id);
    }
}