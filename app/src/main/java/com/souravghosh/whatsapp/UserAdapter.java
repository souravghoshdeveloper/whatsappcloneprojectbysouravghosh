package com.souravghosh.whatsapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.Viewholder>{

    ArrayList<User> list;
    Context context;

    public UserAdapter(ArrayList<User> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.sample_show_user , parent , false);

        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
    User user = list.get(position);
        Picasso.get().load(user.getprofilepic).placeholder(R.drawable.ic_profile).into(holder.Image);
        holder.username.setText(user.fullName);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {
        ImageView Image;
        TextView username , lastMassage;
        public Viewholder(@NonNull View itemView) {
            super(itemView);
            Image = itemView.findViewById(R.id.profile_image);
            username= itemView.findViewById(R.id.usernamelist);
            lastMassage = itemView.findViewById(R.id.lastMassage);
        }
    }
}
