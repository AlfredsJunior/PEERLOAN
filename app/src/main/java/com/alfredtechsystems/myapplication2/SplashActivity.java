package com.alfredtechsystems.myapplication2;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.untu.fis.app.untumobile.DataClasses.dbOperations;


public class SplashActivity extends AppCompatActivity {

    /** Duration of wait **/
    private final int SPLASH_TIME_OUT = 4000;
    private dbOperations userData;
    private boolean userExist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        userData = new dbOperations(this);
        userData.open();
        userExist = userData.userCount();
        Log.i("EXIST", ""+userExist);
        new Handler().postDelayed(new Runnable() {

            /*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             */

            @Override
            public void run() {
                // This method will be executed once the timer is over
                // Start your app main activity
                if(!userExist){
                    Intent i = new Intent(SplashActivity.this, LoginActivity.class);
                    startActivity(i);
                }
                else {
                    Intent i = new Intent(SplashActivity.this, StartActivity.class);
                    startActivity(i);
                }
                // close this activity
                finish();
            }
        }, SPLASH_TIME_OUT);
    }
}
