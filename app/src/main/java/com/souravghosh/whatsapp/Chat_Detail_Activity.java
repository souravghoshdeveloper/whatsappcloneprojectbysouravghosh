package com.souravghosh.whatsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.souravghosh.whatsapp.databinding.ActivityChatDetailBinding;
import com.squareup.picasso.Picasso;

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

        String senderId = auth.getUid();
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
    }
}