package com.julen_demiguel.whatsapp.adapters;

import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.ImageView;
import android.widget.TextView;

        import androidx.annotation.NonNull;
        import androidx.recyclerview.widget.RecyclerView;

import com.julen_demiguel.whatsapp.Models.User;
        import com.julen_demiguel.whatsapp.R;

import java.util.List;

public class ContactRecyclerDataAdapter extends RecyclerView.Adapter<ContactRecyclerDataAdapter.RecyclerDataHolder>{
    private List<User> contacts;
    private OnItemClickListener listener;

    public ContactRecyclerDataAdapter(List<User> contacts, OnItemClickListener listener) {
        this.contacts = contacts;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ContactRecyclerDataAdapter.RecyclerDataHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_item, parent, false);
        return new ContactRecyclerDataAdapter.RecyclerDataHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerDataHolder holder, int position) {
        holder.asignData(contacts.get(position), listener);
    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }



    public class RecyclerDataHolder extends RecyclerView.ViewHolder {
        ImageView imgContact;
        TextView txtNombre;

        public RecyclerDataHolder(@NonNull View itemView) {
            super(itemView);
            imgContact = itemView.findViewById(R.id.imgFotoDePerfilShow);
            txtNombre = itemView.findViewById(R.id.lblNombreUser);
        }

        public void  asignData(User contact, ContactRecyclerDataAdapter.OnItemClickListener listener){
            imgContact.setImageResource(contact.getImg());
            txtNombre.setText(contact.getName());
            itemView.setOnClickListener(view -> listener.onItemClick(getAdapterPosition()));
        }
    }

    public interface  OnItemClickListener {
        void onItemClick(int position);
    }
}