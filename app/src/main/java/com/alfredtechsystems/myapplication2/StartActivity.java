package com.alfredtechsystems.myapplication2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class StartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        Button login = (Button) findViewById(R.id.btnLogin);
        Button register = (Button) findViewById(R.id.btnRegister);
        Button contact = (Button) findViewById(R.id.btnContact);
       // Button locate = (Button) findViewById(R.id.btnLocate);
        login.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Start NewActivity.class
                Intent myIntent = new Intent(StartActivity.this,
                        LoginActivity.class);
                startActivity(myIntent);
            }

        });
        register.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Start NewActivity.class
                Intent myIntent = new Intent(StartActivity.this,
                        RegisterActivity.class);
                startActivity(myIntent);
            }

        });
        contact.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Start NewActivity.class
                Intent myIntent = new Intent(StartActivity.this,
                        BranchActivity.class);
                startActivity(myIntent);
            }

        });

//        locate.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                // Start NewActivity.class
//                Intent myIntent = new Intent(StartActivity.this,
//                        LocationActivity.class);
//                startActivity(myIntent);
//            }
//
//        });

    }
}
