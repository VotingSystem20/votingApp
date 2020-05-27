package com.example.project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class signupact extends AppCompatActivity {
    EditText sname,spass,sconpass,semail,svotercard,smobile,sadhar;
    Button ssub;
    private FirebaseAuth mAuth;
    DatabaseReference dbref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signupact);
        ssub=findViewById(R.id.signsub);
        sname=findViewById(R.id.signname);
        spass=findViewById(R.id.signpass);
        sconpass=findViewById(R.id.signpassconf);
        semail=findViewById(R.id.signemail);
        svotercard=findViewById(R.id.signvotercardno);
        smobile=findViewById(R.id.signmobile);
        sadhar=findViewById(R.id.signadharcardno);
        dbref= FirebaseDatabase.getInstance().getReference("registerdata");
mAuth=FirebaseAuth.getInstance();



        ssub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String name=sname.getText().toString(),pass=spass.getText().toString(),email=semail.getText().toString(),
                        votercard=svotercard.getText().toString(),adhar=sadhar.getText().toString(),mobile=smobile.getText().toString(),passcon=sconpass.getText().toString();


                if(name.equals("")|| pass.equals("")|| email.equals("")||votercard.equals("")||adhar.equals("")||mobile.equals("")||(!pass.equals(passcon))|| pass.length()<=6){
                    Toast.makeText(getApplicationContext(),"please fill all data prperly",Toast.LENGTH_LONG).show();
                }
                else {

                    mAuth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if(task.isSuccessful()){
                                signupuser s=new signupuser(false,adhar,email,mobile,name,votercard);
                                dbref.child(votercard).setValue(s);
                                Toast.makeText(getApplicationContext(),"registered successfully",Toast.LENGTH_LONG).show();
                            }
                            else {

                                Toast.makeText(getApplicationContext(),"registered failure  ",Toast.LENGTH_LONG).show();

                            }
                        }
                    });

                }

            }
    }
    );

}}
