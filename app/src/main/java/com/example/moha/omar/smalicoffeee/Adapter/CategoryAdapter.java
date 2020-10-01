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
import com.example.moha.omar.smalicoffeee.Model.Catergories;
import com.example.moha.omar.smalicoffeee.Model.Foods;
import com.example.moha.omar.smalicoffeee.R;

import java.util.ArrayList;
import java.util.Locale;


public class CategoryAdapter extends  RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>{

    private LayoutInflater inflater;
    private ArrayList<Catergories> CategoryArrayList = new ArrayList<> ();
    private Context context;

    public CategoryAdapter(Context context , ArrayList<Catergories>  catergory) {
        inflater = LayoutInflater.from (context);
        this.context=context;
        this.CategoryArrayList=catergory;



    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent,int viewType) {
    View view =inflater.inflate (R.layout.category_tamplate,parent,false);
    return  new  CategoryViewHolder(view);


    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder,int position) {
        Catergories currentCatergory;
        currentCatergory = CategoryArrayList.get (position);
        holder.tvCategoryName.setText (currentCatergory.getName ());
        holder.tvCategoryPlaces.setText (currentCatergory.getPlaces ());
        Glide.with (context).load (currentCatergory.getImage ()).into (holder.CategoryImages);
    }

    @Override
    public int getItemCount() {
        return CategoryArrayList.size ();
    }

    public  class  CategoryViewHolder extends RecyclerView.ViewHolder {
TextView tvCategoryName, tvCategoryPlaces;
ImageView CategoryImages;


        public CategoryViewHolder(@NonNull View itemView) {
            super (itemView);

            tvCategoryName= itemView.findViewById (R.id.categoryName);
            tvCategoryPlaces= itemView.findViewById (R.id.places);
            CategoryImages= itemView.findViewById (R.id.categoryImages);


        }
    }
}
