package com.example.project;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static android.media.CamcorderProfile.get;

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth mAuth,mAutht;
    public String varifyacoountstatus = "false",votedate="false",votenext;
    public  String date,votercard="251098",state,city,vordno;
    private DatabaseReference dbref,dbdate,dbday,dbstate,dbe,df;
    //  private TextView mTextMessage;
    FloatingActionButton bt;
    List<String> list = new ArrayList<String>(1);

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            final Fragment[] selectedfragment = {null};
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    selectedfragment[0] = new homefragment();

                    break;
                case R.id.navigation_dashboard:
                    selectedfragment[0] = new searchfragment();
                    break;
                case R.id.navigation_notifications:

                    selectedfragment[0] =new resultfragment();

                         break;
                case R.id.navigation_party:
                    selectedfragment[0] = new politicalfragment();
                    break;
                case R.id.navigation_account:
                    selectedfragment[0] = new accountlfragment();
                    break;
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedfragment[0]).commit();
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();

        final String s = mAuth.getCurrentUser().getEmail().toString();
        dbref = FirebaseDatabase.getInstance().getReference("registerdata");
        setContentView(R.layout.activity_main);

        bt=findViewById(R.id.fab);

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                df=FirebaseDatabase.getInstance().getReference("ELECTIONDATE");

                dbe=FirebaseDatabase.getInstance().getReference("registerdata");
                 mAutht=FirebaseAuth.getInstance();


                rea(new Fire() {
                    @Override
                    public void onCallback1(String varify, String voter) {


                        varifyacoountstatus=varify;
                        votercard=voter;



                readdate(new Firevotedate() {
                    @Override
                    public void oncreatedate(String date) {
                        int part=date.indexOf("/");

                        state =date.substring(0,part);
                        String c=date.substring(part+1);
                        int part2=c.indexOf("/");
                        //city=date.substring(c+2,);

                        city=c.substring(0,part2);
                        vordno=c.substring(part2+1);
                        // Toast.makeText(getApplicationContext(),city,Toast.LENGTH_LONG).show();



                df.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        Date cd = Calendar.getInstance().getTime();




                        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");

                        String formattedDate = df.format(cd);

                        if(dataSnapshot.child(formattedDate).exists() && varifyacoountstatus.equals("true")){

                            if(dataSnapshot.child(formattedDate).child("STATE").getValue().toString().equals(state)){


                            Intent i4=new Intent(getApplicationContext(), VoteActivity.class);
                            startActivity(i4);
                            }
                            else {

                                Intent i6=new Intent(getApplicationContext(), Authentication_activity.class);
                                startActivity(i6);

                            }

                        }
                        else {

                            Intent i6=new Intent(getApplicationContext(), Authentication_activity.class);
                            startActivity(i6);

                        }


                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
                    }
                });

                    }

                    @Override
                    public void onCallback1(String varify) {

                    }
                });

            }

        });


        BottomNavigationView navView = findViewById(R.id.bottom_navigation);




        mAuth=FirebaseAuth.getInstance();

        String srt = mAuth.getCurrentUser().getEmail().toString();











        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);



    }

    private void rea(final Fire fire){
        mAuth=FirebaseAuth.getInstance();
        String s = mAuth.getCurrentUser().getEmail().toString();



        String key;


            final Query userQuery = FirebaseDatabase.getInstance().getReference("registerdata").orderByChild("email");

        userQuery.equalTo(s).addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(final DataSnapshot dataSnapshot) {
                        for (DataSnapshot foodSnapshot : dataSnapshot.getChildren()) {
                            // result
                            final String key = foodSnapshot.getKey();

                            dbref.child(key).addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot1) {

                                    String v = dataSnapshot1.child("varify").getValue().toString();
                                    //                  varyfyclass vfc=dataSnapshot1.getValue(varyfyclass.class);

                                    //   tv.setText(v);
                                    //                vr.add(vfc);
                                    //list.add(v);
                                    //String a=list.get(0);

//                                    vr[0].setVarify(v);


                                    fire.onCallback1(v,key);
                                    //Log.i("MainActivity",""+ vr[0].varify);

                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });

                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });}
    public interface Fire{

        void  onCallback1(String varify,String voter);

        void onCallback1(String varify);
        // void onCallBack(String varify,String name,String adhar,String votercard,String mobile);
    }

    public interface Firevotedate{

        void  oncreatedate(String date);

    }

    public void readdate(final Firevotedate firevotedate){


        mAuth=FirebaseAuth.getInstance();
        String s = mAuth.getCurrentUser().getEmail().toString();



        String key;


        dbref = FirebaseDatabase.getInstance().getReference("registerdata");
        final Query userQuery = FirebaseDatabase.getInstance().getReference("registerdata").orderByChild("email");



        userQuery.equalTo(s).addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(final DataSnapshot dataSnapshot) {
                        for (DataSnapshot foodSnapshot : dataSnapshot.getChildren()) {
                            // result
                            final String key = foodSnapshot.getKey();

                            dbref.child(key).addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot2) {

                                    if(dataSnapshot2.child("location").exists()) {


                                        String v = dataSnapshot2.child("location").getValue().toString();
                                        firevotedate.oncreatedate(v);

                                    }
                                    else {

                                    }

                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });

                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
    }


    public interface Firebasevoteresultandvotedate{

        void  oncreatedate(String votedate);

    }

    }