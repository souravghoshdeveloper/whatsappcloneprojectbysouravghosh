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
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class delete_your_account extends AppCompatActivity implements View.OnClickListener{
    private ImageButton backbutton2;
    private TextView termsandconditionstext;

    private EditText editTextEmail, editTextPassword;
    private Button delete_your_account;

    CheckBox showpassword, termsandconditions;

    private FirebaseAuth mAuth;
    private ProgressBar progressbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_your_account);
        backbutton2 = (ImageButton) findViewById(R.id.backbutton);
        backbutton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(delete_your_account.this, signinactivity.class));
                finish();
            }
        });


        termsandconditions = findViewById(R.id.termsandconditions);
        termsandconditionstext = (TextView) findViewById(R.id.termsandconditionstext);
        termsandconditionstext.setOnClickListener(this);


        delete_your_account = (Button) findViewById(R.id.delete_your_account);
        delete_your_account.setOnClickListener(this);

        editTextEmail = (EditText) findViewById(R.id.email);
        editTextPassword = (EditText) findViewById(R.id.password);

        progressbar = (ProgressBar) findViewById(R.id.progressBar);

        mAuth = FirebaseAuth.getInstance();

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
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.delete_your_account:
                delete_your_account();
                break;
            case R.id.termsandconditionstext:
                startActivity(new Intent(this, termsandcondictionfordeleteaccount.class));
                break;
        }
    }

    private void delete_your_account() {
        final String email = editTextEmail.getText().toString().trim();
        final String password = editTextPassword.getText().toString().trim();

        if (email.isEmpty()) {
            editTextEmail.setError("Email is required!");
            editTextEmail.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editTextEmail.setError("Please Enter a Valid Email!");
            editTextEmail.requestFocus();
            return;
        }

        if (password.isEmpty()) {
            editTextPassword.setError("Password is Required");
            editTextPassword.requestFocus();
            return;
        }
        if (password.length() < 6) {
            editTextPassword.setError("Min. Password Length is 6 Characters!");
        }


        if (termsandconditions.isChecked()) {
            progressbar.setVisibility(View.VISIBLE);

            mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                        if (user.isEmailVerified()) {
                            // redirect to user Profile
                            startActivity(new Intent(delete_your_account.this, permanentlydeleteyouraccount.class));
                            finish();
                            progressbar.setVisibility((View.GONE));
                        } else {
                            user.sendEmailVerification();
                            Toast.makeText(delete_your_account.this, "Check Your Email to verify your Account!", Toast.LENGTH_LONG).show();
                            progressbar.setVisibility((View.GONE));
                        }

                    } else {
                        Toast.makeText(delete_your_account.this, "Failed to Delete Your Account Check your credentials", Toast.LENGTH_LONG).show();
                        progressbar.setVisibility((View.GONE));
                    }
                }
            });
        }else {
            Toast.makeText(delete_your_account.this, "You must accept our terms and Condiction", Toast.LENGTH_LONG).show();
            termsandconditions.requestFocus();
            return;
        }
    }
}