package com.example.project;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.StorageReference;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class VoteActivity extends Activity {
    private RecyclerView recyclerView;
    private DatabaseReference dbref,db,dbset;
    private StorageReference storageReference;
    private ArrayList<images> imagesList;
    private recycleradapter recyclerAdapter;
    private ImageView imgv;
    private FirebaseAuth mAuth;
    private String sstate,city,vordno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vote);

        recyclerView=findViewById(R.id.recyclerViewVOTE);




        dbref= FirebaseDatabase.getInstance().getReference();

        LinearLayoutManager layoutManager=new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);


        final String[] datet = new String[1];

        // MainActivity mainActivity=new MainActivity(


        Toast.makeText(getApplicationContext(),sstate,Toast.LENGTH_LONG).show();



        imagesList=new ArrayList<>();
        init();



    }


    public void init(){


        readlocation(new Firebasevotelocaation() {
            @Override
            public void onCallBack(String location) {
                int part=location.indexOf("/");

                sstate =location.substring(0,part);
                String c=location.substring(part+1);
                int part2=c.indexOf("/");
                //city=date.substring(c+2,);

                city=c.substring(0,part2);
                vordno=c.substring(part2+1);





        // ORIGINAL KHEL

        dbref=FirebaseDatabase.getInstance().getReference("ELECTIONDATE");

                Date cd = Calendar.getInstance().getTime();


                SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
                String formattedDate = df.format(cd);


        Query query=dbref.child(formattedDate).child("PARTTIES").child(city);

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot snapshot:dataSnapshot.getChildren()){

                    images images=new images();

                    images.setUrl(snapshot.child("url").getValue().toString());
                    images.setParty(snapshot.child("name").getValue().toString());

                    imagesList.add(images);


                }

                recyclerAdapter=new recycleradapter(getApplicationContext(),imagesList);
                recyclerView.setAdapter(recyclerAdapter);
                recyclerAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });






            }
        });


        clearall();
    }



    public void clearall(){
        if(imagesList != null){

            imagesList.clear();
            if(recyclerAdapter!=null){
                recyclerAdapter.notifyDataSetChanged();
            }

        }
        imagesList=new ArrayList<>();
    }


    public interface Firebasevotelocaation{

        void onCallBack(String location);
    }

    public void readlocation(final Firebasevotelocaation firebasevotelocaation){
        mAuth= FirebaseAuth.getInstance();
        String s = mAuth.getCurrentUser().getEmail().toString();



        String key;


        dbset = FirebaseDatabase.getInstance().getReference("registerdata");
        final Query userQuery = FirebaseDatabase.getInstance().getReference("registerdata").orderByChild("email");



        userQuery.equalTo(s).addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(final DataSnapshot dataSnapshot) {
                        for (DataSnapshot foodSnapshot : dataSnapshot.getChildren()) {
                            // result
                            final String key = foodSnapshot.getKey();

                            dbset.child(key).addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot2) {




                                    String v = dataSnapshot2.child("location").getValue().toString();
                                    firebasevotelocaation.onCallBack(v);





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
}


