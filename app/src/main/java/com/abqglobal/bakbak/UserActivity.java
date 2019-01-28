package com.abqglobal.bakbak;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.google.firebase.messaging.FirebaseMessagingService;

public class UserActivity extends AppCompatActivity {
FirebaseMessagingService firebaseMessagingService;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        listView=findViewById(R.id.listview);

    }
}
