package com.souravghosh.whatsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class Developer_info extends AppCompatActivity {
    private ImageButton backbutton5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_developer_info);

        backbutton5 = (ImageButton) findViewById(R.id.backbutton);
        backbutton5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Developer_info.this, signinactivity.class));
                finish();
            }
        });
    }
}