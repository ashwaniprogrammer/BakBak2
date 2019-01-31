package com.abqglobal.bakbak;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.firebase.client.Firebase;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import com.google.firebase.messaging.FirebaseMessagingService;

import org.json.JSONException;
import org.json.JSONObject;

public class RegisterActivity extends AppCompatActivity {

    //Declare java objects for Xml Widgets

    Button back, register;
    EditText username_r, password_r;
    String url="https://bakbak2-8b93b.firebaseio.com/ashwani.json";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        // to initialize FireBase reference
        Firebase.setAndroidContext(this);

        // Typecast or Initialization Conevert Xml Widgets to Java Objects
        back = findViewById(R.id.back);
        register = findViewById(R.id.register);
        username_r = findViewById(R.id.username_r);
        password_r = findViewById(R.id.password_r);



        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StringRequest stringRequest=new StringRequest(0,url, new Response.Listener<String>() {
                    @Override
                    // data received from database
                    public void onResponse(String response) {
                        //to create new file on database
                        Firebase firebase=new Firebase("https://bakbak2-8b93b.firebaseio.com/ashwani");

                        // to register single user without any testing or validation

                        // to check the user is the first user or not

                        if(response.equals("null")){
                            // username file is created and password is passed inside
                            firebase.child(username_r.getText().toString()).child("password")
                                    .setValue(password_r.getText().toString());
                        // here "password" is key id and there is no need to create key id for username or filename
                        }

                        else{
                            // to break JSON data for accessing username
                            try {
                                JSONObject jsonObject=new JSONObject(response);
                                if(jsonObject.has(username_r.getText().toString())){
                                    Toast.makeText(RegisterActivity.this, "Username already exists.", Toast.LENGTH_SHORT).show();
                                }
                                else{
                                    firebase.child(username_r.getText().toString()).child("password")
                                            .setValue(password_r.getText().toString());
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
                RequestQueue requestQueue= Volley.newRequestQueue(RegisterActivity.this);
                requestQueue.add(stringRequest);
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
