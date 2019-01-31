package com.abqglobal;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.abqglobal.bakbak.R;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.firebase.client.Firebase;

import org.json.JSONException;
import org.json.JSONObject;

public class BlogActivity extends AppCompatActivity {


    EditText username1, email1, blog1;
    Button save;
    String url = "https://bakbak2-8b93b.firebaseio.com/ashwani.json";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blog);

        username1 = findViewById(R.id.username1);
        email1 = findViewById(R.id.email1);
        blog1 = findViewById(R.id.blog1);
        save = findViewById(R.id.save);
        Firebase.setAndroidContext(this);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StringRequest stringRequest = new StringRequest(0, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Firebase firebase = new Firebase("https://bakbak2-8b93b.firebaseio.com/ashwani");

                        if (response.equals("null")) {
                            // username file is created and password is passed inside
                            firebase.child(username1.getText().toString()).child(blog1.getText().toString()).child("email").setValue(email1.getText().toString());
                            // here "password" is key id and there is no need to create key id for username or filename
                        } else {
                            // to break JSON data for accessing username
                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                if (jsonObject.has(username1.getText().toString())) {
                                    Toast.makeText(BlogActivity.this, "Username already exists.", Toast.LENGTH_SHORT).show();
                                } else {
                                    firebase.child(username1.getText().toString()).child(blog1.getText().toString()).child("email").setValue(email1.getText().toString());
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
                RequestQueue requestQueue = Volley.newRequestQueue(BlogActivity.this);
                requestQueue.add(stringRequest);
            }
        });
    }
}
