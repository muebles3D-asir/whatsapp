package com.julen_demiguel.whatsapp.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import com.julen_demiguel.whatsapp.Application.MyApplication;
import com.julen_demiguel.whatsapp.Models.Chat;
import com.julen_demiguel.whatsapp.Models.User;
import com.julen_demiguel.whatsapp.R;
import com.julen_demiguel.whatsapp.adapters.ChatRecyclerDataAdapter;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;


public class ChatsFragment extends Fragment {

    RecyclerView recyclerView;
    ChatRecyclerDataAdapter recyclerDataAdapter;
    Realm realm;
    RealmResults<Chat> results;
    RealmList<Chat> userChats = new RealmList<>();

    public ChatsFragment() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,  Bundle savedInstanceState) {
        realm = Realm.getDefaultInstance();

        results = realm.where(Chat.class).findAll();
        for (Chat chat: results) {
            if(chat.getParticipants().contains(MyApplication.currentUser)){
                userChats.add(chat);
            }
        }
       View view = inflater.inflate(R.layout.fragment_chats, container, false);
        recyclerView = view.findViewById(R.id.idrecyclerShowChats);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 1));
        recyclerDataAdapter = new ChatRecyclerDataAdapter(userChats, new ChatRecyclerDataAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {

            }
        });
                return view;
    }
}