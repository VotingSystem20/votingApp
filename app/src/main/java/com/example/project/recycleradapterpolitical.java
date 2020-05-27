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

public class recycleradapterpolitical extends RecyclerView.Adapter<recycleradapterpolitical.ViewHolder> {

    private Context mcontex;
    private ArrayList<politicalimages> imagesList;


    public recycleradapterpolitical(Context context, ArrayList<politicalimages> imagesList){
        this.mcontex=context;
        this.imagesList=imagesList;
    }



    @NonNull
    @Override
    public recycleradapterpolitical.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.poliiticalitem,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {


        holder.textView.setText(imagesList.get(position).getParty());
        holder.textview1.setText(imagesList.get(position).getParty());
        holder.textView2.setText(imagesList.get(position).getParty());

        Picasso.get().load(imagesList.get(position).getUrl()).into(holder.imgv);
    }

    @Override
    public int getItemCount() {
        return imagesList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView imgv;
        TextView textView,textview1,textView2;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imgv=(ImageView)itemView.findViewById(R.id.imgvote);
            textView=(TextView)itemView.findViewById(R.id.tvpoliticalpositionitem);

            textview1=(TextView)itemView.findViewById(R.id.tvpoliticalnameitem);

            textView2 =(TextView)itemView.findViewById(R.id.tvpoliticalpartyitem);

        }
    }
}
