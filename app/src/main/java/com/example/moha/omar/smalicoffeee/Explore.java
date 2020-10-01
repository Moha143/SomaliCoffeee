package com.example.moha.omar.smalicoffeee;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moha.omar.smalicoffeee.Adapter.CategoryAdapter;
import com.example.moha.omar.smalicoffeee.Adapter.CollectionAdapter;
import com.example.moha.omar.smalicoffeee.Adapter.FoodAdapter;
import com.example.moha.omar.smalicoffeee.Model.Catergories;
import com.example.moha.omar.smalicoffeee.Model.Foods;
import com.example.moha.omar.smalicoffeee.Model.MyCollections;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class Explore extends Fragment  implements  FoodAdapter.onFoodClick{
//Food

    RecyclerView foodsRecycleView;
    ArrayList<Foods> foodsArrayList = new ArrayList<> ();
    FoodAdapter foodAdapter ;

    // Category
    RecyclerView CategoryRecycleView;
    ArrayList<Catergories>CategoryArrayList = new ArrayList<> ();
   CategoryAdapter categoryAdapter;

    //my
    //
    // myCollections

    RecyclerView CollectionRecycleView;
    ArrayList<MyCollections>CollectionArrayList = new ArrayList<> ();
CollectionAdapter collectionAdapter;
FirebaseFirestore db;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,@Nullable ViewGroup container,@Nullable Bundle savedInstanceState) {
        View view= inflater.inflate (R.layout.explore, container,false);
        db=FirebaseFirestore.getInstance ();
        foodsRecycleView = view.findViewById (R.id.recycleViewfoods);
        foodAdapter = new FoodAdapter ( getContext (), foodsArrayList);
        foodsRecycleView.setAdapter (foodAdapter);

        foodAdapter.setFoodClick ( this);

        foodsRecycleView.setLayoutManager (new LinearLayoutManager (getContext (), LinearLayoutManager.HORIZONTAL,false));
         getfoods ();
//Category

        CategoryRecycleView  = view.findViewById (R.id.recycleViewCategories);
        categoryAdapter = new CategoryAdapter ( getContext (), CategoryArrayList);
        //db=FirebaseFirestore.getInstance ();

        CategoryRecycleView.setAdapter (categoryAdapter);
        CategoryRecycleView.setLayoutManager (new LinearLayoutManager (getContext (), LinearLayoutManager.HORIZONTAL,false));
        getcategory ();

        // Collections

        CollectionRecycleView   = view.findViewById (R.id.collectionRec);
        collectionAdapter = new CollectionAdapter ( getContext (), CollectionArrayList);
       // db=FirebaseFirestore.getInstance ();
        CollectionRecycleView.setAdapter (collectionAdapter);
        CollectionRecycleView.setLayoutManager (new LinearLayoutManager (getContext (), LinearLayoutManager.HORIZONTAL,false));
        getmycollection ();
        return view;


    }


    //Read Data

    private void   getfoods(){

         CollectionReference collReference= db.collection ("Foods");
        collReference.get().addOnCompleteListener (new OnCompleteListener<QuerySnapshot> () {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    Log.v ("isSucceesful CFoods ", "yeshaa");
                    Foods foods;
                for (DocumentSnapshot snapshot: task.getResult ())
                {
                    String name= snapshot.getString ("Name");
                    String image= snapshot.getString ("image");

                    String restaurant= snapshot.getString ("restuarant");
                    String price=snapshot.getString ("price");
                  //  Timestamp timestamp =snapshot.getTimestamp ("date");
                  //  String date=getTimeDate (timestamp);
                    String date="";

                    foods = new Foods (name,image,date,restaurant,"4.5",price);

                    foodsArrayList.add(foods);
                }
                foodAdapter.notifyDataSetChanged ();

                } //else {
                 //Log.e ("Error", "Something error");

                //}

            }
        });

    }

    private  void  getcategory()
    {
        CollectionReference collReference= db.collection ("Categories");
        collReference.get ().addOnCompleteListener (new OnCompleteListener<QuerySnapshot> () {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    Log.v ("isSucceesful Category", "yes");
                  Catergories catergories;

                    for (DocumentSnapshot document: task.getResult ())
                    {
                        String name= document.getString ("name");
                        String image= document.getString ("image");

                        String place = document.getString ("places");
                        catergories = new  Catergories (name, image,place);
                        CategoryArrayList.add (catergories);
                    }


                    categoryAdapter.notifyDataSetChanged ();
                }

            }

        });

    }

    private  void  getmycollection()
    {
         CollectionReference collReference= db.collection ("Collections");
        collReference.get ().addOnCompleteListener (new OnCompleteListener<QuerySnapshot> () {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    Log.v ("isSucceesful mycollrc ", "yes");
                    MyCollections myCollections;
                    for (DocumentSnapshot documentSnapshot: task.getResult ())
                    {
                        String name= documentSnapshot.getString ("Name");
                        String image= documentSnapshot.getString ("image");
                        myCollections = new MyCollections (name, image);
                        CollectionArrayList.add (myCollections);
                    }
                    collectionAdapter.notifyDataSetChanged ();
                }
            }

        });
    }

//implement interface method
    @Override
    public void OnFoodClick(String Foodname,String imageUrl,String restuarant,String Rating , String price) {

        Intent intent = new Intent (getActivity (), FoodDetails.class);
        intent.putExtra ("Foodname", Foodname);
        intent.putExtra ("imageUrl", imageUrl);
        intent.putExtra ("restuarant", restuarant);
        intent.putExtra ("Rating", Rating);
        intent.putExtra ("price", price);
        startActivity (intent);

    }
}
