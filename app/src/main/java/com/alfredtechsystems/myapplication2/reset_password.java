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

public class reset_password extends AppCompatActivity {

    @BindView(R.id.btnSave)
    Button btnsave;
    @BindView(R.id.editText_newpassword)
    EditText newPassword;

    @BindView(R.id.editText_confirmpassword)
    EditText editText_confirmpassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

        ButterKnife.bind(this);
    }
    @OnClick(R.id.btnSave)
    public void savepasswordClick() {

        if (TextUtils.isEmpty(newPassword.getText())) {
            newPassword.setError("Enter Your New Password");
            return;
        }

        if (TextUtils.isEmpty(editText_confirmpassword.getText())) {
            editText_confirmpassword.setError("Enter Your New Confirmation Password");
            return;
        }
        Toast.makeText(this, "Successfully saved New Paasword", Toast.LENGTH_SHORT).show();



    }
    }
