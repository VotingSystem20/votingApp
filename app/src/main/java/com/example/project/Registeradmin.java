package com.example.project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.solver.widgets.Snapshot;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Registeradmin extends Activity {
    EditText regcotercard,state,city,vordno;
    Button getdata,varify;
    TextView tv;
    DatabaseReference dbref,db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registeradmin);

        regcotercard=findViewById(R.id.regvotercardno);
        state=findViewById(R.id.reguserstate);
        city=findViewById(R.id.regusercity);
        vordno=findViewById(R.id.reguservordnumber);
        getdata=findViewById(R.id.refgetdatabtn);
        varify=findViewById(R.id.refvarifybtn);
        tv=findViewById(R.id.regdetail);


        db=FirebaseDatabase.getInstance().getReference("registerdata");
        dbref= FirebaseDatabase.getInstance().getReference("2020");

        getdata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                final String vc=regcotercard.getText().toString();

                if(vc.equals("")){
                    Toast.makeText(getApplicationContext(),"Enter card no",Toast.LENGTH_LONG).show();
                }
                else {
                    db.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            String vcc=regcotercard.getText().toString();
                            if(dataSnapshot.child(vc).exists()) {

                              String enam=  dataSnapshot.child(vc).child("name").getValue().toString();
                                String em=  dataSnapshot.child(vc).child("email").getValue().toString();
                                String eaha=  dataSnapshot.child(vc).child("aadhar").getValue().toString();
                                String evt=  dataSnapshot.child(vc).child("votercardnumber").getValue().toString();
                                String emo=  dataSnapshot.child(vc).child("mobile").getValue().toString();


                                tv.setText("voter card numver: "+evt+"\n name: "+enam+"\n mobile number: "+emo+"\n email: "+em+"\n aadhar: "+eaha);
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });

                    }
                }
            }
        );
        if(!tv.getText().toString().equals("")){
            Toast.makeText(getApplicationContext(),"data is there",Toast.LENGTH_LONG).show();
        }
        varify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String s=state.getText().toString();
                final String c=city.getText().toString();
                final String vrc=vordno.getText().toString();

                dbref.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(dataSnapshot.child(s).exists()){
                            dbref.child(s).addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot1) {
                                    if(dataSnapshot1.child(c).exists()){

                                        dbref.child(s).child(c).addListenerForSingleValueEvent(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(@NonNull DataSnapshot dataSnapshot2) {
                                                if(dataSnapshot2.child(vrc).exists()){
                                                    dbref.child(s).child(c).child(vrc).child(regcotercard.getText().toString()).setValue("AVAILABLE");

                                                    db.child(regcotercard.getText().toString()).child("varify").setValue("true");
                                                    db.child(regcotercard.getText().toString()).child("location").setValue(s+"/"+c+"/"+vrc);

                                                    Toast.makeText(getApplicationContext(),"varified",Toast.LENGTH_LONG).show();
                                                }
                                                else {
                                                    Toast.makeText(getApplicationContext(),"vord not found",Toast.LENGTH_LONG).show();
                                                }
                                            }

                                            @Override
                                            public void onCancelled(@NonNull DatabaseError databaseError) {

                                            }
                                        });

                                    }
                                    else {
                                        Toast.makeText(getApplicationContext(),"city not found",Toast.LENGTH_LONG).show();
                                    }
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });

                        }
                        else{
                            Toast.makeText(getApplicationContext(),"state not foun",Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });

    }
}
