package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login_Activity extends AppCompatActivity {
    private EditText Mail,Pass;
    private TextView Mtext;
    private TextView login;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login_);


        Mail=findViewById(R.id.loginmail);
        Pass=findViewById(R.id.loginpass);
        Mtext=findViewById(R.id.register);
        login=findViewById(R.id.login);

        mAuth=FirebaseAuth.getInstance();

        Mtext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login_Activity.this,Signin_Activity.class));
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginUser();
            }
        });
    }



    private void loginUser(){
        String email = Mail.getText().toString();
        String pass = Pass.getText().toString();

        if (!email.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            if (!pass.isEmpty()){
              mAuth.signInWithEmailAndPassword(email,pass)
                      .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                          @Override
                          public void onSuccess(AuthResult authResult) {
                              Toast.makeText(Login_Activity.this, "Login success", Toast.LENGTH_SHORT).show();
                              startActivity(new Intent(Login_Activity.this,Home_Activity.class));
                              finish();
                          }
                      }).addOnFailureListener(new OnFailureListener() {
                  @Override
                  public void onFailure(@NonNull Exception e) {
                      Toast.makeText(Login_Activity.this, "login error", Toast.LENGTH_SHORT).show();
                  }
              });

            }else{
                Pass.setError("Empty Fields Are not Allowed");
            }
        }else if(email.isEmpty()){
            Mail.setError("Empty Fields Are not Allowed");
        }else{
            Mail.setError("enter correct email");
        }

    }
}