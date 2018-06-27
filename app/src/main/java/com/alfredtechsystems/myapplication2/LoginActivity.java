package com.alfredtechsystems.myapplication2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.untu.fis.app.untumobile.DataClasses.dbOperations;

import java.util.ArrayList;
import java.util.List;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity  {

    private boolean login;
    private dbOperations userData;

    List<Integer> myList = new ArrayList<Integer>();
    public TextView txtPswd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        userData = new dbOperations(this);
        userData.open();
        Button btn0 = (Button) findViewById(R.id.button0);
        Button btn1 = (Button) findViewById(R.id.button1);
        Button btn2 = (Button) findViewById(R.id.button2);
        Button btn3 = (Button) findViewById(R.id.button3);
        Button btn4 = (Button) findViewById(R.id.button4);
        Button btn5 = (Button) findViewById(R.id.button5);
        Button btn6 = (Button) findViewById(R.id.button6);
        Button btn7 = (Button) findViewById(R.id.button7);
        Button btn8 = (Button) findViewById(R.id.button8);
        Button btn9 = (Button) findViewById(R.id.button9);
        ImageButton btnYs = (ImageButton) findViewById(R.id.buttonYs);
        ImageButton btnNo = (ImageButton) findViewById(R.id.buttonBk);
        txtPswd = (TextView) findViewById(R.id.textPswd);
        myList.clear();
        btn0.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                addMember(0);
            }

        });
        btn1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                addMember(1);
            }

        });
        btn2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                addMember(2);
            }

        });
        btn3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                addMember(3);
            }

        });
        btn4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                addMember(4);
            }

        });
        btn5.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                addMember(5);
            }

        });
        btn6.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                addMember(6);
            }

        });
        btn7.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                addMember(7);
            }

        });
        btn8.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                addMember(8);
            }

        });
        btn9.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                addMember(9);
            }

        });
        btnYs.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (myList.size() < 4) {
                    Toast.makeText(getApplicationContext(),"Password Should Have 4 Characters",Toast.LENGTH_SHORT).show();
                    myList.clear();
                    txtPswd.setText("");
                }
                else {
                    String pinStr="";
                    for (int s : myList)
                    {
                        pinStr += Integer.toString(s);
                    }
                    int pin  = Integer.parseInt(pinStr);


                    login = userData.loginUser(pinStr);

                    if(login){
                        myList.clear();
                        Intent myIntent = new Intent(LoginActivity.this,
                                HomeActivity.class);
                        startActivity(myIntent);
                        txtPswd.setText("");
                    }
                    else {
                        myList.clear();
                        Toast.makeText(getApplicationContext(),"Invalid Password",Toast.LENGTH_SHORT).show();
                        txtPswd.setText("");
                    }
                }


            }

        });
        btnNo.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                removeMember();
            }

        });

    }

    public void addMember(Integer x) {
        String pinStr="";
        if (myList.size() < 4) {
            myList.add(x);
            for (int s : myList)
            {
                pinStr += Integer.toString(s);
            }
            txtPswd.setText(pinStr);
        }
    }
    public void removeMember() {
        String pinStr="";
        if(myList.size()>0) {
            myList.remove(myList.size()-1);
            for (int s : myList)
            {
                pinStr += Integer.toString(s);
            }
            txtPswd.setText(pinStr);
        }
    }
}

