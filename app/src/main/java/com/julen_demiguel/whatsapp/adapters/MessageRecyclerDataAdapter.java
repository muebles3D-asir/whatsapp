package com.julen_demiguel.whatsapp.adapters;

import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.julen_demiguel.whatsapp.Models.Message;
import com.julen_demiguel.whatsapp.Models.User;
import com.julen_demiguel.whatsapp.R;


import java.util.List;

public class MessageRecyclerDataAdapter extends RecyclerView.Adapter<MessageRecyclerDataAdapter.ViewHolder> {

    private List<Message> messages;
    private User otherUserChat;

    public MessageRecyclerDataAdapter(List<Message> messages, User userChat) {
        this.messages = messages;
        this.otherUserChat = userChat;
    }

    public void addMessage(Message message) {
        messages.add(message);
        notifyItemInserted(messages.size() - 1);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.message_estructure, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String message = messages.get(position).getText();
        String name = otherUserChat.getName();
        int image = otherUserChat.getImg() == 0 ? R.drawable.perfil2 : otherUserChat.getImg();
        holder.assignData(message, image, name);
    }

    @Override
    public int getItemCount() {
        return messages.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView tvMessage;
        public ImageView perfilImg;
        public TextView nameMessage;

        public ViewHolder(View view) {
            super(view);
            tvMessage = (TextView) view.findViewById(R.id.idlblMensajeShow);
            perfilImg = (ImageView) view.findViewById(R.id.imgShowInChat);
            nameMessage = (TextView) view.findViewById(R.id.nameShowInChat);
        }

      public void assignData(String message, int image, String name) {
            tvMessage.setText(message);
            if(perfilImg != null && nameMessage != null) {
                perfilImg.setImageResource(image);
                nameMessage.setText(name);
            }

        }
    }
}