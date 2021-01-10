package com.souravghosh.whatsapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class permanentlydeleteyouraccount extends AppCompatActivity {
    private Button delete;
    private TextView emailaddress;
    private ProgressBar progressbar;
    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;

    private ImageButton backbutton3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permanentlydeleteyouraccount);


        backbutton3 = (ImageButton) findViewById(R.id.backbutton);
        backbutton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(permanentlydeleteyouraccount.this, signinactivity.class));
                finish();
            }
        });



        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();

        emailaddress = (TextView) findViewById(R.id.emailaddress);
        emailaddress.setText(firebaseUser.getEmail());


        progressbar = (ProgressBar) findViewById(R.id.progressBar);

        delete = (Button) findViewById(R.id.delete);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(permanentlydeleteyouraccount.this);
                dialog.setTitle("Are You Sure?");
                dialog.setMessage("If you click here delete your account will be permanantky deleted");
                dialog.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        progressbar.setVisibility(View.VISIBLE);
                        firebaseUser.delete().addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                progressbar.setVisibility(View.GONE);
                                if (task.isSuccessful()){
                                    Toast.makeText(permanentlydeleteyouraccount.this,"Account Deleted Sucessfully!", Toast.LENGTH_LONG ).show();
                                    startActivity(new Intent(permanentlydeleteyouraccount.this, signinactivity.class));
                                    finish();
                                }else {
                                    Toast.makeText(permanentlydeleteyouraccount.this,task.getException().getMessage(), Toast.LENGTH_LONG ).show();
                                }
                            }
                        });
                    }
                });
                dialog.setNegativeButton("Dismiss", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                AlertDialog alertDialog = dialog.create();
                alertDialog.show();
            }
        });



    }
}