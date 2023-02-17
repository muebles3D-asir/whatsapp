package com.julen_demiguel.whatsapp.adapters;

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

        import java.util.ArrayList;
        import java.util.List;

        import io.realm.RealmResults;

public class ContactRecyclerAdapter  extends RecyclerView.Adapter<ContactRecyclerAdapter.RecyclerDataHolder>{
    private List<User> contacts;
    private OnItemClickListener listener;

    public ContactRecyclerAdapter (List<User> contacts, OnItemClickListener listener) {
        this.contacts = contacts;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ContactRecyclerAdapter.RecyclerDataHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_contacts, parent, false);
        return new ContactRecyclerAdapter.RecyclerDataHolder(view);
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

        public void  asignData(User contact, ContactRecyclerAdapter.OnItemClickListener listener){
            imgContact.setImageResource(contact.getImg());
            txtNombre.setText(contact.getName());
            itemView.setOnClickListener(view -> listener.onItemClick(getAdapterPosition()));
        }
    }

    public interface  OnItemClickListener {
        public void onItemClick(int position);
    }
}
