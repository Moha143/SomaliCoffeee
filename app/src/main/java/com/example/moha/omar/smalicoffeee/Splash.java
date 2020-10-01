package com.example.moha.omar.smalicoffeee;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
public class Splash extends AppCompatActivity {
    FirebaseAuth auth;
    Button btnlogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_splash);
        auth = FirebaseAuth.getInstance ();
        btnlogin = findViewById (R.id.btn_logins);
        btnlogin.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent (Splash.this, Login.class);
                startActivity (intent);
            }
        });
    }
  @Override
   protected void onStart() {
        super.onStart ();
    //  Log.v ("onstart", auth.getCurrentUser ().getUid ());
        if (auth.getCurrentUser () != null) {
            Intent intent = new Intent (this,Home.class);
            startActivity (intent);
        }
    }


}
