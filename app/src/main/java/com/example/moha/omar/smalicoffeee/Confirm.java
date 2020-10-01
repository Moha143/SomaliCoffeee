package com.example.moha.omar.smalicoffeee;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskExecutors;
import com.google.firebase.FirebaseException;
import com.google.firebase.Timestamp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class Confirm extends AppCompatActivity {

    String name , email, date, password , phone;
    Button btnfinish;
    String VerificationID;
    FirebaseAuth uth;
    FirebaseFirestore db;
    Boolean iscodeverify = false;
    EditText edcode ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_confirm);
        btnfinish= findViewById (R.id.btn_finish);
        edcode= findViewById (R.id.textcode);
        uth= FirebaseAuth .getInstance ();
        db=FirebaseFirestore.getInstance ();
        Intent intent = getIntent ();
        if(intent != null)
        {
            name = intent.getStringExtra ("name");
            email = intent.getStringExtra ("email");
            phone = intent.getStringExtra ("phone");
            password = intent.getStringExtra ("password");
            //    confirm_pas = intent.getStringExtra ("confirm");
            sendcode (phone);
        }

        btnfinish.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                if( ! iscodeverify   )
                {
                    String code = edcode .getText ().toString ();
                    verifyphone (code);
                }
            }
        });
    }

    private  void  sendcode(String phone){

        PhoneAuthProvider.getInstance ().verifyPhoneNumber ( "+252" + phone, 60 ,TimeUnit.SECONDS,TaskExecutors.MAIN_THREAD,callbacks );

    }
    private  PhoneAuthProvider.OnVerificationStateChangedCallbacks callbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks () {
        @Override
        public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {

            Log.v ("verify completed", phoneAuthCredential.getSmsCode ());
            verifyphone (phoneAuthCredential.getSmsCode ());
        }
        @Override
        public void onVerificationFailed(@NonNull FirebaseException e) {
            Log.v ("verify error", e.getMessage () );

        }
        @Override
        public void onCodeSent(@NonNull String s,@NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            super.onCodeSent (s,forceResendingToken);
            VerificationID = s;
        }
    };
    public void  verifyphone (String smscode){
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential (VerificationID, smscode);

        signwithcredential(credential);
    }
    private  void  signwithcredential (PhoneAuthCredential credential){

        uth.signInWithCredential (credential).addOnCompleteListener (new OnCompleteListener<AuthResult> () {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                iscodeverify = true;
                createEmailwithpassword();

            }
        }) .addOnFailureListener (new OnFailureListener () {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText (Confirm.this, "Something wrong Please try again letter" , Toast.LENGTH_LONG).show ();
            }
        });
    }
    // Step two
    private  void  createEmailwithpassword(){

        uth.createUserWithEmailAndPassword (email, password).addOnSuccessListener (new OnSuccessListener<AuthResult> () {
            @Override
            public void onSuccess(AuthResult authResult) {
                Log.e ("successful", "Succesfull  created email and password");
                adduser ();
            }
        }).addOnFailureListener (new OnFailureListener () {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.e ("Failure errors",e.getMessage ());
            }
        })
        ;
    }

    private  void  adduser(){

        String  userid = uth.getCurrentUser ().getUid ();
        HashMap<String, Object> user =new HashMap<> ();
        user.put ("name",name );
        user.put ("phone"  , phone);
        user.put ("email", email);
        user.put ("date" , new Timestamp ( new Date()));
        db.collection ("Users").document (userid).set (user).addOnCompleteListener (new OnCompleteListener<Void> () {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful ()){
                    Log.e ("successful", "data have been saved ");

                    Intent intent = new Intent (Confirm.this, Home.class);
                    startActivity (intent);
                }

            }
        }).addOnFailureListener (new OnFailureListener () {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.e ("database error",e.getMessage ());
            }
        });

    }



}
