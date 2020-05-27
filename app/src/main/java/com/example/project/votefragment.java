package com.example.project;

import android.content.Context;
import android.media.Image;
import android.nfc.Tag;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

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
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class votefragment extends Fragment {

   private RecyclerView recyclerView;
   private DatabaseReference dbref,db,dbset;
   private StorageReference storageReference;
   private ArrayList<images> imagesList;
   private recycleradapter recyclerAdapter;
   private ImageView imgv;
   private FirebaseAuth mAuth;
   private String sstate,city,vordno;
   // private votefragment votefragment;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View rootview=inflater.inflate(R.layout.fregment_vote,container,false);
       // Intent i=new Intent(getContext(),piechartresult.class);
        //startActivity(i);




        return rootview;
    }}




