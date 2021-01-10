package com.souravghosh.whatsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class termsandcondictionfordeleteaccount extends AppCompatActivity {
    private ImageButton backbutton4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_termsandcondictionfordeleteaccount);

        backbutton4 = (ImageButton) findViewById(R.id.backbutton);
        backbutton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(termsandcondictionfordeleteaccount.this, registeruser.class));
                finish();
            }
        });
    }
}