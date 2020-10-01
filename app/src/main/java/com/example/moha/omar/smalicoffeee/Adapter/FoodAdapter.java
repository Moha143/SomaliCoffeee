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
import com.bumptech.glide.Glide;
import com.example.moha.omar.smalicoffeee.Model.Foods;
import com.example.moha.omar.smalicoffeee.R;
import java.util.ArrayList;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.foodViewHolder> {
    private LayoutInflater  inflater;
 private    ArrayList<Foods>  foodsArrayList = new ArrayList<> ();
 private Context context;

 onFoodClick foodClick;


 public  interface  onFoodClick{

     void OnFoodClick(  String Foodname, String imageUrl, String restuarant, String Rating  , String price);
    }

    public void setFoodClick(onFoodClick foodClick) {

        this.foodClick = foodClick;
    }


    public  FoodAdapter (Context context ,ArrayList<Foods> foodss)
{
    inflater = LayoutInflater.from (context);
    this.context=context;
    this.foodsArrayList=foodss;
}

    @NonNull
    @Override
    public foodViewHolder onCreateViewHolder(@NonNull ViewGroup parent,int viewType) {
    View view = inflater.inflate (R.layout.discovery_new_places, parent,false);
        return new foodViewHolder (view);

    }

    @Override
    public void onBindViewHolder(@NonNull foodViewHolder holder,int position) {

    final Foods currentfoods = foodsArrayList.get(position);
    holder.tvFoodname.setText (currentfoods.getName());
    holder.tvRestaurant.setText (currentfoods.getName ());
    holder.tvRating.setText (currentfoods.getRating());
    Glide .with (context).load (currentfoods.getImage()).into (holder.imgfoodIcon);

    holder.popular_foods.setOnClickListener (new View.OnClickListener () {
        @Override
        public void onClick(View v) {
            foodClick.OnFoodClick (currentfoods.getName (), currentfoods.getImage ()
                    , currentfoods.getName (), currentfoods.getRating (), currentfoods.getPrice ());


        }
    });


    }

    @Override
    public int getItemCount() {
        return foodsArrayList.size();
    }

    public  class  foodViewHolder extends RecyclerView.ViewHolder{
     TextView tvFoodname , tvRestaurant , tvRating , tvFreeDelivery ;
     ImageView imgfoodIcon;
     LinearLayout popular_foods;

     public foodViewHolder(@NonNull View itemView) {
         super (itemView);
         tvFoodname=itemView.findViewById (R.id.food_name);
         tvRestaurant=itemView.findViewById (R.id.restaurant_name);
         tvRating=itemView.findViewById (R.id.food_rating);
         tvFreeDelivery=itemView.findViewById (R.id.free_delive);
         imgfoodIcon  =itemView.findViewById (R.id.food_images);
         popular_foods=itemView.findViewById (R.id.popular_foods_container);


     }
 }
}
