package com.julen_demiguel.whatsapp.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
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
    RealmResults<Chat> resultsChat;
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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        resultsChat = realm.where(Chat.class).findAll();
        userChats.clear();
        for (Chat chat : resultsChat) {
            if (chat.getParticipants().contains(MyApplication.currentUser))
                userChats.add(chat);
        }

        View view = inflater.inflate(R.layout.fragment_chats, container, false);
        recyclerView = view.findViewById(R.id.idrecyclerShowChats);

        recyclerDataAdapter = new ChatRecyclerDataAdapter(userChats, position -> callback.openChat(resultsChat.get(position).getId()));
        recyclerView.setAdapter(recyclerDataAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        return view;
    }

    public interface ChatListener {
        void openChat(int id);
    }
}