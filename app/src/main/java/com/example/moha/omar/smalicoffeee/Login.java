package com.example.moha.omar.smalicoffeee;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {

    TextView singup;
EditText edEmail, edPassword;
Button btnlogin_page;
    FirebaseAuth auth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_login);
        singup = findViewById (R.id.singup);
        edEmail=findViewById (R.id.EDmyemai);
        edPassword=findViewById (R.id.EDmypassword);
        btnlogin_page=findViewById (R.id.btn_login_page);

        auth =FirebaseAuth.getInstance ();

        singup.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent ( Login.this , Create_Account.class);
                startActivity (intent);
            }
        });
        btnlogin_page.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {

                String email=edEmail.getText ().toString ();
                String password =edPassword.getText ().toString ();

                auth.signInWithEmailAndPassword (email,password).addOnCompleteListener (new OnCompleteListener<AuthResult> () {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful ())
                        {
                            Log.v ("Successful ", "Login");
                            Intent intent = new Intent ( Login.this , Home.class);
                            startActivity (intent);
                        }
                        else
                        {
                            Toast.makeText (Login.this, "Please check your Email or Password",Toast.LENGTH_SHORT).show ();
                        }



                    }
                });
            }
        });
    }
}
