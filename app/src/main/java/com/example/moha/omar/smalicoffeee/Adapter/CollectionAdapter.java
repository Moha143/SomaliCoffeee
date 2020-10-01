package com.example.moha.omar.smalicoffeee.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.moha.omar.smalicoffeee.Model.MyCollections;
import com.example.moha.omar.smalicoffeee.R;

import java.util.ArrayList;


public class CollectionAdapter  extends  RecyclerView.Adapter<CollectionAdapter.CollectionViewHolder>{

    private LayoutInflater inflater;
    private ArrayList<MyCollections> CollectionArrayList = new ArrayList<> ();
    private Context context;
    public  CollectionAdapter (Context context , ArrayList<MyCollections> Collectionss)
    {
        inflater = LayoutInflater.from (context);
        this.context=context;
    this.CollectionArrayList=Collectionss;

    }


    @NonNull
    @Override
    public CollectionViewHolder onCreateViewHolder(@NonNull ViewGroup parent,int viewType) {
        View view=inflater.inflate (R.layout.tamplate_collection,parent,false);

        return  new CollectionViewHolder (view);
    }

    @Override
    public void onBindViewHolder(@NonNull CollectionViewHolder holder,int position) {

        MyCollections mycollection = CollectionArrayList.get (position);
        holder.tvCollectionName.setText (mycollection.getName ());
        Glide .with (context).load (mycollection.getImage ()).into (holder.imgCollectionIcon);


    }

    @Override
    public int getItemCount() {
        return CollectionArrayList.size ();
    }

    public  class  CollectionViewHolder extends RecyclerView.ViewHolder{
        TextView tvCollectionName ;
        ImageView imgCollectionIcon;
        public CollectionViewHolder(@NonNull View itemView) {
            super (itemView);
            tvCollectionName=itemView.findViewById (R.id.collectionName);
            imgCollectionIcon=itemView.findViewById (R.id.collectionImage);


        }
    }
}
