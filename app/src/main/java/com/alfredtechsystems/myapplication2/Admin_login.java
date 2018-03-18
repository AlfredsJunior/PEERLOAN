package com.alfredtechsystems.myapplication2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Admin_login extends AppCompatActivity {
    public static final String PASSUSER = "USER_PASSED";
    public static final String TAG = Admin_login.class.getSimpleName();


        @BindView(R.id.button_Admin_login)
        Button login;
        @BindView(R.id.editText_admin_id)
        EditText adminId;

        @BindView(R.id.editText_admin_name)
        EditText adminName;

        @BindView(R.id.editText_adminpassword)
        EditText adminPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);

        ButterKnife.bind(this);
    }

    @OnClick(R.id.button_Admin_login)
    public void loginuseerClick(){

        if(TextUtils.isEmpty(adminId.getText())){
            adminId.setError("Fucking enter some id");
            return;
        }

        if(TextUtils.isEmpty(adminName.getText())){
            adminName.setError("Fucking enter some name");
            return;
        }

        if(TextUtils.isEmpty(adminPassword.getText())){
            adminPassword.setError("Fucking enter some id");
            return;
        }

        Toast.makeText(Admin_login.this, "Successfully logged in", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getApplicationContext(),admin_home.class);
        intent.putExtra(PASSUSER,adminName.getText());
        //finish();
        startActivity(intent);
    }
}
