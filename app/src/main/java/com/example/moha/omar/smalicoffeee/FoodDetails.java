package com.example.moha.omar.smalicoffeee;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

public class FoodDetails extends AppCompatActivity {
TextView tvFoodname, tvRestaurant, tvRating ;
ImageView foodimage;
LinearLayout linearLayout;
RelativeLayout relativeLayout;

Button BtnOrderNow;
FirebaseAuth auth;
FirebaseFirestore db;
String foodnames,imgurl, restaurants,ratings ;
String userID,phone,name,email , price;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_food_details);
        auth= FirebaseAuth.getInstance ();
        db= FirebaseFirestore.getInstance ();
        userID=auth.getCurrentUser ().getUid ();

        UserGetData();

        tvFoodname=findViewById (R.id.food_name_in_details);
        tvRestaurant=findViewById (R.id.restaurant_name_in_details);
        tvRating= findViewById (R.id.food_rating_in_details);
        foodimage=findViewById (R.id.img_details);
        BtnOrderNow=findViewById (R.id.btn_ordernow);
        Intent intent= getIntent ();
        if (intent !=null)
        {
            foodnames = intent.getStringExtra ("Foodname");
            restaurants= intent.getStringExtra ("restuarant");
            ratings= intent.getStringExtra ("Rating");
            price=intent.getStringExtra ("price");

            tvFoodname.setText (foodnames);
            tvRestaurant.setText (restaurants);
            tvRating.setText (ratings);
            imgurl = intent.getStringExtra ("imageUrl");
            Glide.with (this).load (imgurl).into (foodimage);
        }
        BtnOrderNow.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                orderFood ();
            }
        });
    }

    public  void UserGetData()
    {

        db.collection ("Users").document (userID).get ().addOnCompleteListener (new OnCompleteListener<DocumentSnapshot> () {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful ())
                {
                    DocumentSnapshot snapshot= task.getResult ();

                    name = snapshot.getString ("name");
                    email = snapshot.getString ("email");
                    phone = snapshot.getString ("phone");

                }
                else
                {
                  Toast.makeText (FoodDetails.this, "Could not get USER Data", Toast.LENGTH_SHORT).show ();

                }
            }
        });

    }
    public  void  orderFood(){
        HashMap<String, Object > Order = new HashMap<> ();
        Order.put ("Foodname",foodnames);
        Order.put ("restuarant",restaurants);
        Order.put ("Rating",ratings);
        Order.put ("userid", userID);
        Order.put ("name", name);
        Order.put ("email", email);
        Order.put ("phone", phone);
        Order.put ("price",price);
        db.collection ("Orders").add (Order).addOnCompleteListener (new OnCompleteListener<DocumentReference> () {
            @Override
            public void onComplete(@NonNull Task<DocumentReference> task) {

                if (task.isSuccessful ())
                {
                    Log.e ("OrderCompleted" , "Order Successful");

                    Toast.makeText (FoodDetails.this,"Your Order Completed ", Toast.LENGTH_SHORT).show ();
                }
                else
                {
                    Toast.makeText (FoodDetails.this,"Your Order not completed ", Toast.LENGTH_SHORT).show ();
                }
            }
        });

    }
}
