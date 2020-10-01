package com.example.moha.omar.smalicoffeee;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Home extends AppCompatActivity {

    BottomNavigationView btnnav;
    FragmentManager manager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_home);
        btnnav = findViewById (R.id.bottoms);
        manager = getSupportFragmentManager ();
      //  loadfragment (new Explore ());
        btnnav.setOnNavigationItemSelectedListener (listener);

    }
    BottomNavigationView.OnNavigationItemSelectedListener listener = new BottomNavigationView.OnNavigationItemSelectedListener () {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment= null;
            switch (item.getItemId ()){
                case  R.id.explore :

                    fragment = new Explore ();
                    break;
                case R.id.myorder:

                    fragment = new OrderFragment ();
                    break;
                case R.id.favourite:
                    fragment = new Favourite ();
                    break;
                case R.id.profile:
                   // fragment = new Profile ();

                    break;

            }
            return loadfragment (fragment);

        }
    };

    private  boolean loadfragment(Fragment fragment)
    {
        if(fragment != null)
        {
            manager.beginTransaction ().replace (R.id.fragment_container, fragment).commit ();
            return true;

        }
        return false;
    }

}
