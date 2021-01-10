package com.souravghosh.whatsapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.solver.state.State;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Locale;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.Viewholder> {

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
    public void onBindViewHolder(@NonNull final Viewholder holder, int position) {
    final User user = list.get(position);
        Picasso.get().load(user.getprofilepic).placeholder(R.drawable.ic_profile).into(holder.Image);
        holder.username.setText(user.fullName);

        FirebaseDatabase.getInstance().getReference().child("Chats").child(FirebaseAuth.getInstance().getUid() + user.getUserId()).orderByChild("timetamp")
                .limitToLast(1).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.hasChildren()){
                    for (DataSnapshot snapshot1 : snapshot.getChildren()){
                        holder.lastMassage.setText(snapshot1.child("message").getValue(String.class));
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, Chat_Detail_Activity.class);
                intent.putExtra("userId", user.getUserId());
                intent.putExtra("profile_image", user.getprofilepic);
                intent.putExtra("fullname", user.fullName);
                context.startActivity(intent);
            }
        });
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
