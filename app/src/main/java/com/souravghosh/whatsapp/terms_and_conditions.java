package com.souravghosh.whatsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.souravghosh.whatsapp.databinding.ActivityChatDetailBinding;

public class terms_and_conditions extends AppCompatActivity {

    private ImageButton backbutton1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terms_and_conditions);

        backbutton1 = (ImageButton) findViewById(R.id.backbutton);
        backbutton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(terms_and_conditions.this, registeruser.class));
                finish();
            }
        });
    }
}