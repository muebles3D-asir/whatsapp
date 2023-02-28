package com.julen_demiguel.whatsapp.adapters;


import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.julen_demiguel.whatsapp.Models.Chat;
import com.julen_demiguel.whatsapp.Models.User;
import com.julen_demiguel.whatsapp.R;

import java.util.List;

public class ChatRecyclerDataAdapter extends RecyclerView.Adapter<ChatRecyclerDataAdapter.RecyclerDataHolder> {
    private List<Chat> chats;
    private OnItemClickListener listener;

    public ChatRecyclerDataAdapter(List<Chat> chats, OnItemClickListener listener) {
        this.chats = chats;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ChatRecyclerDataAdapter.RecyclerDataHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.chat_item, parent, false);
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

        public void asignData(Chat chat, OnItemClickListener listener) {
            User otherUser = chat.getOtherUser();
            imgFoto.setImageResource(otherUser.getImg());
            txtTitulo.setText(otherUser.getName());
            String mensaje = chat.getLastMessage();
            txtMensaje.setText(mensaje);
            itemView.setOnClickListener(view -> listener.onItemClick(getAdapterPosition()));

        }
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }


}
