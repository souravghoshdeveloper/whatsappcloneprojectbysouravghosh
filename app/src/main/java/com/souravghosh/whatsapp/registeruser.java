package com.souravghosh.whatsapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.regex.Pattern;

public class registeruser extends AppCompatActivity implements View.OnClickListener {

    private TextView gotosignin, registeruser;
    private EditText editTextFullname, editTextage, editTextemail, editTextpassword, editTextconfirmpassword;
    RadioGroup male, female;
    private ProgressBar progressbar;

    CheckBox showpassword;

    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registeruser);

        mAuth = FirebaseAuth.getInstance();

        gotosignin = (TextView) findViewById(R.id.gotosignin);
        gotosignin.setOnClickListener(this);

        registeruser = (Button) findViewById(R.id.registeruser);
        registeruser.setOnClickListener(this);

        editTextFullname = (EditText) findViewById(R.id.fullname);
        editTextage = (EditText) findViewById(R.id.age);
        editTextemail = (EditText) findViewById(R.id.email);
        editTextpassword = (EditText) findViewById(R.id.password);
        editTextconfirmpassword = (EditText) findViewById(R.id.confirmpassword);


        progressbar = (ProgressBar) findViewById(R.id.progressBar);


        editTextpassword = findViewById(R.id.password);
        showpassword = findViewById(R.id.showpassword);

        showpassword.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    editTextpassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }else {
                    editTextpassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.gotosignin:
                startActivity(new Intent(this, signinactivity.class));
                finish();
            break;
            case R.id.registeruser:
                registeruser();
                break;
        }
    }

    private void registeruser() {
        final String fullname = editTextFullname.getText().toString().trim();
        final String age = editTextage.getText().toString().trim();
        final String email = editTextemail.getText().toString().trim();
        String password = editTextpassword.getText().toString().trim();
        String confirmpassword = editTextconfirmpassword.getText().toString().trim();

        if(fullname.isEmpty()){
            editTextFullname.setError("Full Name is required");
            editTextFullname.requestFocus();
            return;
        }
        if(fullname.length() > 25){
            editTextFullname.setError("Please Enter a Valid Name!");
            editTextFullname.requestFocus();
            return;
        }

        if(age.isEmpty()){
            editTextage.setError("Age is required");
            editTextage.requestFocus();
            return;
        }

        if(email.isEmpty()){
            editTextemail.setError("Email is required");
            editTextemail.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            editTextemail.setError("Please Enter a Valid Email");
            editTextemail.requestFocus();
            return;
        }

        if(password.isEmpty()){
            editTextpassword.setError("Password is required!");
            editTextpassword.requestFocus();
            return;
        }
        if(password.length() < 6){
            editTextpassword.setError("Min. length should be 6 characters!");
            editTextpassword.requestFocus();
            return;
        }

        if(confirmpassword.isEmpty()){
            editTextconfirmpassword.setError("Confirm Password is required!");
            editTextconfirmpassword.requestFocus();
            return;
        }
        if(confirmpassword.length() < 6){
            editTextconfirmpassword.setError("Min. length should be 6 characters!");
            editTextconfirmpassword.requestFocus();
            return;
        }

        if (!password.equals(confirmpassword))
        {
            editTextpassword.setError("Your Password & Confirm Password Should be Same!");
            editTextpassword.requestFocus();
            return;
        }


        progressbar.setVisibility(View.VISIBLE);
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            User user = new User(fullname, age, email);

                            FirebaseDatabase.getInstance().getReference("Users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()){
                                        Toast.makeText(registeruser.this, "User has Been Registered Sucessfully", Toast.LENGTH_LONG).show();
                                        startActivity(new Intent(registeruser.this, signinactivity.class));
                                        finish();
                                        progressbar.setVisibility((View.GONE));

                                        // redirected to Login Layout
                                    }else {
                                        Toast.makeText(registeruser.this, "Failed to Register! Try Again!", Toast.LENGTH_LONG).show();
                                        progressbar.setVisibility(View.GONE);
                                    }
                                }
                            });
                        }else {
                            Toast.makeText(registeruser.this, "Failed to Register! Try Again!", Toast.LENGTH_LONG).show();
                            progressbar.setVisibility(View.GONE);
                        }
                    }
                });
    }
}