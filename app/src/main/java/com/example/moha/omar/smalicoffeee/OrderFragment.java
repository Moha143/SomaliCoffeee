package com.example.moha.omar.smalicoffeee;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moha.omar.smalicoffeee.Adapter.MyOrdersAdapter;
import com.example.moha.omar.smalicoffeee.Model.Foods;
import com.example.moha.omar.smalicoffeee.Model.myOrders;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class OrderFragment extends Fragment {
    RecyclerView recyclerViewOrder;
    MyOrdersAdapter myOrdersAdapt;
    ArrayList<myOrders> myOrdersArrayList = new ArrayList<> ();
    TextView total_price , total_tx_fees;
    FirebaseFirestore db;
    FirebaseAuth auth;
    String userID;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,@Nullable ViewGroup container,@Nullable Bundle savedInstanceState) {
        View view= inflater.inflate (R.layout.orders, container,false);
        auth=FirebaseAuth.getInstance ();
        db= FirebaseFirestore.getInstance ();
        userID=auth.getCurrentUser ().getUid ();
        recyclerViewOrder= view.findViewById (R.id.recycle_View_orders);
        total_price= view.findViewById (R.id.subtotal_prices);
        total_tx_fees=view.findViewById (R.id.tx_and_fees_total);
        myOrdersAdapt = new MyOrdersAdapter ( getContext (), myOrdersArrayList);
        recyclerViewOrder.setAdapter (myOrdersAdapt);
        recyclerViewOrder.setLayoutManager (new LinearLayoutManager (getActivity ()));
        getMyOrders ();
        return view;
    }

    public  void  getMyOrders(){

        db.collection ("Orders").whereEqualTo ("userid",userID).get().addOnCompleteListener (new OnCompleteListener<QuerySnapshot> () {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful ()){
                    Log.e ("SucccessfulMyOrder","yes");
                    Log.e ("sizeresult",String.valueOf (task.getResult ().size ()));

                    myOrders orderby;
                    for (DocumentSnapshot snapshot: task.getResult ())
                    {
                        String foodname= snapshot.getString ("Foodname");
                        String restuarant= snapshot.getString ("restuarant");
                        String email= snapshot.getString ("price");
                        String phone= snapshot.getString ("phone");
                        String userid= snapshot.getString ("userid");
                        String Rating= snapshot.getString ("Rating");
                        String name= snapshot.getString ("name");
                        String price= snapshot.getString ("email");
                        String date="";
                        
                        orderby= new myOrders (foodname,restuarant,price,phone,userid,Rating,name,email);
                        myOrdersArrayList.add (orderby);

                    }
                  myOrdersAdapt.notifyDataSetChanged ();
                    Log.e ("Succcessfulsize",String.valueOf (myOrdersArrayList.size ()));

                }

            }
        });

        }

}
