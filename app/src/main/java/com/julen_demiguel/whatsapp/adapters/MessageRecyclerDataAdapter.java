package com.julen_demiguel.whatsapp.adapters;

import android.graphics.Color;
import android.opengl.Visibility;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.julen_demiguel.whatsapp.Application.MyApplication;
import com.julen_demiguel.whatsapp.models.Message;
import com.julen_demiguel.whatsapp.models.User;
import com.julen_demiguel.whatsapp.R;



import java.util.List;

public class MessageRecyclerDataAdapter extends RecyclerView.Adapter<MessageRecyclerDataAdapter.ViewHolder> {

    private List<Message> messages;
    private boolean group;

    public MessageRecyclerDataAdapter(List<Message> messages, boolean group) {
        this.messages = messages;
        this.group = group;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.message_estructure, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Message message = messages.get(position);
        String text = message.getText();
        int image = message.getSender().getImg();
        int color = message.getSender().equals(MyApplication.currentUser) ? Color.parseColor("#D9FDD3") : Color.parseColor("#AAAAAA");
        holder.assignData(text, image, color, message.getSender().equals(MyApplication.currentUser));
    }

    @Override
    public int getItemCount() {
        return messages.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvMessage;
        TextView tvMessage2;
        ImageView perfilImg;

        public ViewHolder(View view) {
            super(view);
            tvMessage = view.findViewById(R.id.idlblMensajeShow);
            tvMessage2 = view.findViewById(R.id.idlblMensajeShow2);
            perfilImg = view.findViewById(R.id.imgShowInChat);
        }

        public void assignData(String message, int image, int color, boolean isCurrentUser) {
            if (color == Color.parseColor("#D9FDD3")) {
                tvMessage2.setText(message);
                tvMessage.setBackgroundColor(Color.WHITE);
            } else {
                tvMessage.setText(message);
                tvMessage2.setBackgroundColor(Color.WHITE);
            }

            if (perfilImg != null && !isCurrentUser) {
                perfilImg.setImageResource(image);
            } else if(perfilImg != null) {
                perfilImg.setVisibility(View.INVISIBLE);
            }
        }
    }
}