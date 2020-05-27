package com.example.project;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.LinkedList;
import java.util.List;

public class accountlfragment extends Fragment {
    private FirebaseAuth mAuth;
    static String a = null;
    String accuntname="",accountadhar="",accountvotercard="",accountvarified="",accountmobile="",accountemail="";
    private DatabaseReference dbref;
    private List<varyfyclass> vr=new LinkedList<>();
    TextView tv,tv1,tv2,tv3,tv4;


    //Main create view

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {



        View rootview=inflater.inflate(R.layout.fregment_account,container,false);

mAuth=FirebaseAuth.getInstance();


     tv=(TextView)rootview.findViewById(R.id.accountvote);
        tv1=(TextView)rootview.findViewById(R.id.tvacoountname);
        tv2=(TextView)rootview.findViewById(R.id.tvacoountstatus);

        tv3=(TextView)rootview.findViewById(R.id.tvacoountmobile);

        tv4=(TextView)rootview.findViewById(R.id.accountemail);


        final String s = mAuth.getCurrentUser().getEmail().toString();



      //  String tru=vr.get(0).toString();
    //

        String sr=tv.getText().toString();

        // Call read data method  Reference 1.1

        readData(new FirebaseCallBack() {
            @Override
            public void onCallBack(String varify, String name, String adhar, String votercard, String mobile) {
                if (varify.equals("true")){

                    tv2.setText("Acoount is varified");
                    tv2.setTextColor(Color.parseColor("#22D701"));

                }
                else {
                    tv2.setText("Acoount not varified");

                    tv2.setTextColor(Color.parseColor("#F23513"));

                }
                accountvarified=varify;
                accuntname=name;
                accountadhar=adhar;
                accountvotercard=votercard;
                accountmobile=mobile;

                tv1.setText(name);
                tv.setText(votercard);
                tv3.setText(mobile);

                mAuth=FirebaseAuth.getInstance();
                tv4.setText(mAuth.getCurrentUser().getEmail().toString());
                tv4.setTextColor(Color.parseColor("#F0FFFE"));

            }


        });

       // Toast.makeText(getContext(),sr,Toast.LENGTH_LONG).show();
        return rootview;

    }




    //  1.1 Read data from firebasecaall back
    private void readData(final FirebaseCallBack firebaseCallBack){

        dbref = FirebaseDatabase.getInstance().getReference("registerdata");


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
                            String key = foodSnapshot.getKey();

//                            dbref.child(key).child("varify").addValueEventListener(new ValueEventListener() {
//                                @Override
//                                public void onDataChange(@NonNull DataSnapshot dataSnapshot1) {
//
//                                    String v = dataSnapshot1.getValue().toString();
//                                    //                  varyfyclass vfc=dataSnapshot1.getValue(varyfyclass.class);
//
//                                    //   tv.setText(v);
//                                    //                vr.add(vfc);
//                                    //list.add(v);
//                                    //String a=list.get(0);
//
////                                    vr[0].setVarify(v);
//
//
//                                    firebaseCallBack.onCallBack(v);
//                                    //Log.i("MainActivity",""+ vr[0].varify);
//
//                                }
//
//                                @Override
//                                public void onCancelled(@NonNull DatabaseError databaseError) {
//
//                                }
//                            });




                            dbref.child(key).addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                    String adhar1=dataSnapshot.child("aadhar").getValue().toString();
                                    String name1=dataSnapshot.child("name").getValue().toString();

                                    String mobile1=dataSnapshot.child("mobile").getValue().toString();

                                    String votercard1=dataSnapshot.child("votercardnumber").getValue().toString();


                                    String v = dataSnapshot.child("varify").getValue().toString();


                                    firebaseCallBack.onCallBack(v,name1,adhar1,votercard1,mobile1);

                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });



                            //   Toast.makeText(getApplicationContext(),a,Toast.LENGTH_LONG).show();

                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }

                });

    }


    // Interface for getting data
    public interface FirebaseCallBack{

        void onCallBack(String varify,String name,String adhar,String votercard,String mobile);
    }
}
