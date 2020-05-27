package com.example.project;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
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

import java.util.ArrayList;

public class politicalfragment extends Fragment {

    private RecyclerView recyclerView;
    private DatabaseReference dbref,db,dbset;
    private StorageReference storageReference;
    private ArrayList<politicalimages> imagesList;
    private recycleradapterpolitical recycleradapterpolitical;
    private ImageView imgv;
    private FirebaseAuth mAuth;
    private String sstate,city,vordno;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootview= inflater.inflate(R.layout.fregment_political,container,false);


        recyclerView=rootview.findViewById(R.id.recyclerViewPolitical);




        dbref= FirebaseDatabase.getInstance().getReference();

        LinearLayoutManager layoutManager=new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);


        final String[] datet = new String[1];

        // MainActivity mainActivity=new MainActivity(






        imagesList=new ArrayList<politicalimages>();
        init();
        return rootview;
    }
    public void init(){






                // ORIGINAL KHEL

                dbref=FirebaseDatabase.getInstance().getReference("POST");



                Query query=dbref.child("POLITICALPARTIES");

                query.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        for (DataSnapshot snapshot:dataSnapshot.getChildren()){

                            politicalimages politicalimagesimages=new politicalimages();

                            politicalimagesimages.setUrl(snapshot.child("url").getValue().toString());
                            politicalimagesimages.setParty(snapshot.child("partyposition").getValue().toString());
                            politicalimagesimages.setPartyname(snapshot.child("name").getValue().toString());
                            politicalimagesimages.setPartyposition(snapshot.child("partyname").getValue().toString());

                            imagesList.add(politicalimagesimages);


                        }

                        recycleradapterpolitical recycleradapterpolitical=new recycleradapterpolitical(getContext(),imagesList);
                        recyclerView.setAdapter(recycleradapterpolitical);
                        recycleradapterpolitical.notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });










        clearall();
    }



    public void clearall(){
        if(imagesList != null){

            imagesList.clear();
            if(recycleradapterpolitical!=null){
                recycleradapterpolitical.notifyDataSetChanged();
            }

        }
        imagesList=new ArrayList<politicalimages>();
    }
}
