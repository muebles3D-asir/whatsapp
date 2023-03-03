package com.julen_demiguel.whatsapp.adapters;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.julen_demiguel.whatsapp.Application.MyApplication;
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

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.message_estructure, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Message message = messages.get(position);
        String text = messages.get(position).getText();
        String name = otherUserChat.getName();
        int image = otherUserChat.getImg() == 0 ? R.drawable.perfil2 : otherUserChat.getImg();
        int color = message.getSender().equals(MyApplication.currentUser) ? Color.parseColor("#D9FDD3") : Color.parseColor("#AAAAAA");
        holder.assignData(text, image, name, color);
    }

    @Override
    public int getItemCount() {
        return messages.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvMessage;
        TextView tvMessage2;
//        ImageView perfilImg;
//        TextView nameMessage;
//        LinearLayout layoutMessage;

        public ViewHolder(View view) {
            super(view);
            tvMessage = view.findViewById(R.id.idlblMensajeShow);
            tvMessage = view.findViewById(R.id.idlblMensajeShow2);
//            perfilImg = (ImageView) view.findViewById(R.id.imgShowInChat);
//            nameMessage = (TextView) view.findViewById(R.id.nameShowInChat);
//            layoutMessage = (LinearLayout) view.findViewById(R.id.layoutMessage);
        }

        public void assignData(String message, int image, String name, int color) {
                if(color == Color.parseColor("#D9FDD3")){
                    tvMessage2.setText(message);

                    tvMessage.setBackgroundColor(Color.WHITE);
                }else{
                    tvMessage.setText(message);

                    tvMessage2.setBackgroundColor(Color.WHITE);
                }

//            if (perfilImg != null && nameMessage != null) {
//                perfilImg.setImageResource(image);
//                nameMessage.setText(name);
//            }
        }
    }
}