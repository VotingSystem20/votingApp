package com.example.project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends Activity {
    EditText uemail,upass;
    Button us;
    TextView usignup;
private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();
        uemail = findViewById(R.id.logemail);
        upass = findViewById(R.id.logpass);
        us = findViewById(R.id.lsub);
        usignup = findViewById(R.id.lsignup);


            us.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (uemail.getText().toString().equals("register")) {
                        Intent i3 = new Intent(getApplicationContext(), Registeradmin.class);
                        startActivity(i3);
                    }
                    else if(uemail.getText().toString().equals("dp")){
                      Intent i4=new Intent(getApplicationContext(), piechartresult.class);
                        startActivity(i4);

                    }
                    else if(uemail.getText().toString().equals("dj")){
                        Intent i5=new Intent(getApplicationContext(), Authentication_activity.class);
                        startActivity(i5);

                    }
                    else
                        {

                        mAuth.signInWithEmailAndPassword(uemail.getText().toString(), upass.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if (task.isSuccessful()) {
                             //   Toast.makeText(getApplicationContext(), "login", Toast.LENGTH_LONG).show();
                              Intent i1 = new Intent(getApplicationContext(), MainActivity.class);
                               startActivity(i1);
                                finish();
                            } else {
                                Toast.makeText(getApplicationContext(), "failed login", Toast.LENGTH_LONG).show();
                            }
                        }
                    });

                }}
            });
            usignup.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i2 = new Intent(getApplicationContext(), signupact.class);
                    startActivity(i2);
                    finish();

                }
            });
        }
    }
