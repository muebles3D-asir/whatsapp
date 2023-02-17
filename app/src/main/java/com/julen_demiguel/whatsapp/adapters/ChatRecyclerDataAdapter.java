package com.julen_demiguel.whatsapp.adapters;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.julen_demiguel.whatsapp.Application.MyApplication;
import com.julen_demiguel.whatsapp.Models.Chat;
import com.julen_demiguel.whatsapp.Models.Message;
import com.julen_demiguel.whatsapp.Models.User;
import com.julen_demiguel.whatsapp.R;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import io.realm.RealmResults;

public class ChatRecyclerDataAdapter extends RecyclerView.Adapter<ChatRecyclerDataAdapter.RecyclerDataHolder>{
    private List<Chat> chats;
    private OnItemClickListener listener;

    public ChatRecyclerDataAdapter (List<Chat> chats, OnItemClickListener listener) {
        this.chats = chats;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ChatRecyclerDataAdapter.RecyclerDataHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.showchat, parent, false);
        return new RecyclerDataHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChatRecyclerDataAdapter.RecyclerDataHolder holder, int position) {
        holder.asignData(chats.get(position), listener);
    }

    @Override
    public int getItemCount() {
        return chats.size();
    }

    public class RecyclerDataHolder extends RecyclerView.ViewHolder {
        ImageView imgFoto;
        TextView txtTitulo, txtMensaje;

        public RecyclerDataHolder(@NonNull View itemView) {
            super(itemView);
            imgFoto = itemView.findViewById(R.id.imgFotoDePerfil);
            txtTitulo = itemView.findViewById(R.id.txtTituloChat);
            txtMensaje = itemView.findViewById(R.id.txtMensajeChat);
        }

        public void  asignData(Chat chat, OnItemClickListener listener){
            User otherUser = chat.getOtherUser();
            imgFoto.setImageResource(otherUser.getImg());
            txtTitulo.setText(otherUser.getName());
            txtMensaje.setText(chat.getLastMessage());
            itemView.setOnClickListener(view -> listener.onItemClick(getAdapterPosition()));
        }
    }

    public interface  OnItemClickListener {
        public void onItemClick(int position);
    }


}
