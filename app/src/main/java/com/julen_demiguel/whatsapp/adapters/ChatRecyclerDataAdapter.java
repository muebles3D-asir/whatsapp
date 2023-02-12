package com.julen_demiguel.whatsapp.adapters;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.julen_demiguel.whatsapp.Models.Chat;

import java.util.ArrayList;

import io.realm.RealmResults;

public class ChatRecyclerDataAdapter extends RecyclerView.Adapter<ChatRecyclerDataAdapter.RecyclerDataHolder>{
    ArrayList<Chat> chats;
    private OnItemClickListener listener;

    public ChatRecyclerDataAdapter (ArrayList<Chat> listData) {
        this.chats = listData;

    }
    public ChatRecyclerDataAdapter (ArrayList<Chat> listData, OnItemClickListener lisener) {
        this.chats = listData;
        this.listener = lisener;
    }



    @NonNull
    @Override
    public ChatRecyclerDataAdapter.RecyclerDataHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.show_hobbies, null, false);
        return new RecyclerDataHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerDataHolder holder, int position) {

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerDataAdapter.RecyclerDataHolder holder, int position) {
        holder.asignData(listData.get(position), listener);

    }

    @Override
    public int getItemCount() {

        return listData.size();
    }

    public class RecyclerDataHolder extends RecyclerView.ViewHolder {

        TextView tipo;
        TextView nombre;
        TextView nota;
        LinearLayout linearLayout;

        public RecyclerDataHolder(@NonNull View itemView) {
            super(itemView);
            tipo =itemView.findViewById(R.id.idtxtTipo);
            nombre = itemView.findViewById(R.id.idtxtNombre);
            nota = itemView.findViewById(R.id.idtxtPuntuacion);
            linearLayout = itemView.findViewById(R.id.idLinearLayout);

        }
        public void  asignData(Hobby hobbiesRecycler, OnItemClickListener listener){
            tipo.setText(hobbiesRecycler.getType());
            nombre.setText(hobbiesRecycler.getName());
            nota.setText(hobbiesRecycler.getNota());


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClick(hobbiesRecycler, getAdapterPosition());
                }
            });
        }

    }
    public interface  OnItemClickListener{
        public void onItemClick(Hobby hobby, int position);
    }
}
