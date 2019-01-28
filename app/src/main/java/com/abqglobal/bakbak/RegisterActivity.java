package com.abqglobal.bakbak;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {

    //Declare java objects for Xml Widgets

    Button back, register;
    EditText username_r, password_r;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // Typecast or Initialization Covert Xml Widgets to Java Objects
        back = findViewById(R.id.back);
        register = findViewById(R.id.register);
        username_r = findViewById(R.id.username_r);
        password_r = findViewById(R.id.password_r);
        firebaseAuth = FirebaseAuth.getInstance();


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (username_r.getText().toString().equals("") || password_r.getText().toString().equals("")) {
                    Toast.makeText(RegisterActivity.this, "Please enter your Username and Password", Toast.LENGTH_SHORT).show();
                } else {
                    firebaseAuth.createUserWithEmailAndPassword(username_r.getText().toString(), password_r.getText().toString()).addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(RegisterActivity.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                                password_r.setText("");
                                username_r.setText("");
                                finish();
                            }

                        }
                    });
                }
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // to close current activity
                finish();
                // intent creates problem time consuming
            }
        });

    }
}
