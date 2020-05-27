package com.example.project;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class recycleradapter extends RecyclerView.Adapter<recycleradapter.ViewHolder> {

    private Context mcontex;
    private ArrayList<images> imagesList;


    public recycleradapter(Context context,ArrayList<images> imagesList){
        this.mcontex=context;
        this.imagesList=imagesList;
    }



    @NonNull
    @Override
    public recycleradapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.voteitem,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {


        holder.textView.setText(imagesList.get(position).getParty());

        Picasso.get().load(imagesList.get(position).getUrl()).into(holder.imgv);
    }

    @Override
    public int getItemCount() {
        return imagesList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView imgv;
        TextView textView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imgv=(ImageView)itemView.findViewById(R.id.imgvote);
            textView=(TextView)itemView.findViewById(R.id.tvvoteitem);

        }
    }
}
