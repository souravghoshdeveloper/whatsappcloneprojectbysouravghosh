package com.souravghosh.whatsapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.souravghosh.whatsapp.Adapter.ChatAdapter;
import com.souravghosh.whatsapp.Modeles.MassagesModel;
import com.souravghosh.whatsapp.databinding.ActivityChatDetailBinding;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Chat_Detail_Activity extends AppCompatActivity {

    ActivityChatDetailBinding binding;
    FirebaseDatabase database;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityChatDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        database = FirebaseDatabase.getInstance();
        auth = FirebaseAuth.getInstance();

        final String senderId = auth.getUid();
        String reciveId = getIntent().getStringExtra("UserId");
        String fullname = getIntent().getStringExtra("fullname");
        String profile_image = getIntent().getStringExtra("profile_image");

        binding.fullname.setText(fullname);
        Picasso.get().load(profile_image).placeholder(R.drawable.ic_profile).into(binding.profileImage);


        binding.backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Chat_Detail_Activity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        final ArrayList<MassagesModel> massagesModels = new ArrayList<>();

        final ChatAdapter chatAdapter = new ChatAdapter(massagesModels, this);

        binding.chatrecyclerview.setAdapter(chatAdapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        binding.chatrecyclerview.setLayoutManager(layoutManager);
        
    }
}