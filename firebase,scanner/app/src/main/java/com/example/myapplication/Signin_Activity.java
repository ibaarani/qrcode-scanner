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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Signin_Activity extends AppCompatActivity {

    private EditText Mail,Pass;
    private TextView Mtext;
    private TextView sign_up;

    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_signin_);

        Mail=findViewById(R.id.memail);
        Pass=findViewById(R.id.mpass);
        Mtext=findViewById(R.id.loginuser);
        sign_up=findViewById(R.id.sign);

        mAuth=FirebaseAuth.getInstance();

        Mtext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Signin_Activity.this,Login_Activity.class));
            }
        });

        sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createuser();
            }
        });
    }

    private void createuser(){
        String email = Mail.getText().toString();
        String pass = Pass.getText().toString();

        if (!email.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            if (!pass.isEmpty()){
                mAuth.createUserWithEmailAndPassword(email,pass)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                Toast.makeText(Signin_Activity.this,"register Success",Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(Signin_Activity.this, Login_Activity.class));
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(Signin_Activity.this, "register error", Toast.LENGTH_SHORT).show();
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