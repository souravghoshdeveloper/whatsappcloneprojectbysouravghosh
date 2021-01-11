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
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class signinactivity extends AppCompatActivity implements View.OnClickListener {
    private TextView register, forgotpassword, delete_your_account, Developer_informaction;
    private EditText editTextEmail, editTextPassword;
    private Button signin;

    CheckBox showpassword;

    private FirebaseAuth mAuth;
    private ProgressBar progressbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signinactivity);

        register = (TextView) findViewById(R.id.register);
        register.setOnClickListener(this);

        signin = (Button) findViewById(R.id.signin);
        signin.setOnClickListener(this);

        editTextEmail = (EditText) findViewById(R.id.email);
        editTextPassword = (EditText) findViewById(R.id.password);

        progressbar = (ProgressBar) findViewById(R.id.progressBar);

        mAuth = FirebaseAuth.getInstance();

        forgotpassword = (TextView) findViewById(R.id.forgetpassword);
        forgotpassword.setOnClickListener(this);

        delete_your_account = (TextView) findViewById((R.id.delete_your_account));
        delete_your_account.setOnClickListener(this);

        Developer_informaction = (TextView) findViewById(R.id.Developer_informaction);
        Developer_informaction.setOnClickListener(this);


        editTextPassword = findViewById(R.id.password);
        showpassword = findViewById(R.id.showpassword);

        showpassword.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    editTextPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }else {
                    editTextPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.register:
                startActivity(new Intent(this, registeruser.class));
                finish();
                break;
            case R.id.signin:
                userLogin();
                break;
            case R.id.forgetpassword:
                startActivity(new Intent(this, resetpassword.class));
                break;
            case R.id.delete_your_account:
                startActivity(new Intent(this, delete_your_account.class));
                break;
            case  R.id.Developer_informaction:
                startActivity(new Intent(this, Developer_info.class));
                break;
        }
    }

    private void userLogin() {
        String email = editTextEmail.getText().toString().trim();
        final String password = editTextPassword.getText().toString().trim();

        if (email.isEmpty()){
            editTextEmail.setError("Email is required!");
            editTextEmail.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            editTextEmail.setError("Please Enter a Valid Email!");
            editTextEmail.requestFocus();
            return;
        }

        if(password.isEmpty()){
            editTextPassword.setError("Password is Required");
            editTextPassword.requestFocus();
            return;
        }
        if (password.length() < 6){
            editTextPassword.setError("Min. Password Length is 6 Characters!");
        }

        progressbar.setVisibility(View.VISIBLE);

        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                    if (user.isEmailVerified()){
                        // redirect to user Profile
                        startActivity(new Intent(signinactivity.this, MainActivity.class));
                        finish();
                        progressbar.setVisibility((View.GONE));
                    }else {
                        user.sendEmailVerification();
                        Toast.makeText(signinactivity.this,"Check Your Email to verify your Account!", Toast.LENGTH_LONG).show();
                        progressbar.setVisibility((View.GONE));
                    }

                }else {
                    Toast.makeText(signinactivity.this, "Failed to Login Please Check your credentials", Toast.LENGTH_LONG).show();
                    progressbar.setVisibility((View.GONE));
                }
            }
        });
    }
}