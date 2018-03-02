package com.example.abhi.newproject;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;

public class first extends AppCompatActivity {
    private FirebaseAuth mAuth;
 Button signup;
 EditText email1;
 EditText password1;
 String email,password;
 //ProgressBar pbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        signup=(Button)findViewById(R.id.login_button);
        email1=(EditText)findViewById(R.id.email);
        password1=(EditText)findViewById(R.id.password);
       //pbar=(ProgressBar)findViewById(R.id.progressBar);

        mAuth = FirebaseAuth.getInstance();

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               createAccount();

            }
        });}

        public void createAccount()
    {
        email=email1.getText().toString();
         password=password1.getText().toString();

         if(email.isEmpty())
         {
             email1.setError("enter valid email");
             email1.requestFocus();
             return;
         }
         if(!(Patterns.EMAIL_ADDRESS.matcher(email).matches()))
        {
            email1.setError("enter valid email");
            email1.requestFocus();
            return;
        }
        if(password.length()<6)
        {
            password1.setError("min length 6 digits");
            password1.requestFocus();
            return;
        }
        //pbar.setVisibility(View.VISIBLE);

        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
              //  pbar.setVisibility(View.GONE);
                if (task.isSuccessful()) {
                    finish();
                    startActivity(new Intent(first.this, second.class));
                    Toast.makeText(first.this,"successful login",Toast.LENGTH_SHORT).show();
                } else {

                    if (task.getException() instanceof FirebaseAuthUserCollisionException) {
                        Toast.makeText(getApplicationContext(), "You are already registered", Toast.LENGTH_SHORT).show();

                    } else {
                        Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });
    }

}
