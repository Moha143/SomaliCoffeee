package com.example.moha.omar.smalicoffeee.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moha.omar.smalicoffeee.Model.Foods;
import com.example.moha.omar.smalicoffeee.Model.myOrders;
import com.example.moha.omar.smalicoffeee.R;

import java.util.ArrayList;

public class MyOrdersAdapter  extends  RecyclerView.Adapter<MyOrdersAdapter.MyOrderViewHolder>  {

    private LayoutInflater inflater;

    private  ArrayList<myOrders> MyordersArrayList = new ArrayList<> ();

    private Context context;
    public  MyOrdersAdapter (Context context ,ArrayList<myOrders> orders)
    {
        inflater = LayoutInflater.from (context);
        this.context=context;
        this.MyordersArrayList=orders;
    }

    @NonNull
    @Override
    public MyOrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent,int viewType) {
        View view = inflater.inflate (R.layout.myorders_tamplate, parent, false);
        return   new MyOrderViewHolder (view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyOrderViewHolder holder,int position) {

        myOrders myOrder = MyordersArrayList.get (position);
        holder.tvFoodname.setText (myOrder.getFoodname ());
       holder.tvRestaurant.setText (myOrder.getRestuarant ());
       holder.tvPrice.setText (myOrder.getPrice ());
    }

    @Override
    public int getItemCount() {
        return MyordersArrayList.size ();

    }

    public  class  MyOrderViewHolder extends RecyclerView.ViewHolder{
        TextView tvFoodname , tvRestaurant , tvPrice ;

        public MyOrderViewHolder(@NonNull View itemView) {
            super (itemView);
            tvFoodname=itemView.findViewById (R.id.food_name_in_orders);
            tvRestaurant=itemView.findViewById (R.id.restaurant_name_in_orders);
            tvPrice=itemView.findViewById (R.id.price_in_orders);
        }
    }


}
