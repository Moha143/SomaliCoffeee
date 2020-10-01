package com.example.moha.omar.smalicoffeee;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Create_Account extends AppCompatActivity {

    EditText edname, edmail,edpassword,edconfirm,edphone;
    Button btn_create;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_create__account);
        getviews ();

        btn_create.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                String name = edname.getText ().toString ();
                String email = edmail.getText ().toString ();
                String phone = edphone.getText ().toString ();
                String password = edpassword.getText ().toString ();
             //    String confirm_pas = edconfirm.getText ().toString ();

                Intent intent = new Intent (Create_Account.this , Confirm.class);
                intent.putExtra ("name", name );
                intent.putExtra ("email", email );
                intent.putExtra ("phone", phone );

                intent.putExtra ("password", password );
              //  intent.putExtra ("age",confirm_pas );
                startActivity (intent);
            }
        });
    }
    private void  getviews(){
        edname= findViewById (R.id.ednames);
        edmail= findViewById (R.id.edmails);
        edpassword = findViewById (R.id.edpasswords);
      //  edconfirm = findViewById (R.id.edconfirms);
        edphone  = findViewById (R.id.edphones);

        btn_create= findViewById (R.id.btn_sign );
    }
}
