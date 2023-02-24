package com.julen_demiguel.whatsapp.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.julen_demiguel.whatsapp.Models.Message;
import com.julen_demiguel.whatsapp.R;

import java.util.List;

public class MessageRecyclerDataAdapter extends RecyclerView.Adapter<MessageRecyclerDataAdapter.ViewHolder> {

    private List<Message> messages;

    public MessageRecyclerDataAdapter(List<Message> messages) {
        this.messages = messages;
    }

    public void addMessage(Message message) {
        messages.add(message);
        notifyItemInserted(messages.size() - 1);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_chat, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String message = messages.get(position).getText();
        holder.tvMessage.setText(message);
    }

    @Override
    public int getItemCount() {
        return messages.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView tvMessage;

        public ViewHolder(View view) {
            super(view);
            tvMessage = view.findViewById(R.id.etMessage);
        }
    }
}