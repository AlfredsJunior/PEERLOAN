package com.alfredtechsystems.myapplication2;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.alfredtechsystems.myapplication2.db.dbModel.User;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SignupActivity extends AppCompatActivity {

    @BindView(R.id.editText_ConfRegPass)
    EditText editText_ConfRegPass;
    @BindView(R.id.editText_signup_password)
    EditText editText_signup_password;

    @BindView(R.id.editText_fullname)
    EditText fullName;
    @BindView(R.id.editText_signup_Id)
    EditText editText_signup_Id;
    @BindView(R.id.editText6_email)
    EditText editText6_email;

    @BindView(R.id.editTextPhoneNo)
    EditText editTextPhoneNo;
    @BindView(R.id.buttonRegisterSave)
    Button buttonRegisterSave;
    @BindView(R.id.radioButton_male)
    RadioButton radioButton_male;
    @BindView(R.id.RadioButton_Female)
    RadioButton RadioButton_Female;

    public static final String TAG = "MainActivity";
    MainActivityViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        ButterKnife.bind(this);
        //init viewmodel
        viewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);
    }

    @OnClick(R.id.buttonRegisterSave)
    public void buttonRegisterSaveClick() {

        if (TextUtils.isEmpty(fullName.getText())) {
            fullName.setError(" enter your name");
            return;
        }

        if (TextUtils.isEmpty(editText6_email.getText())) {
            editText6_email.setError("FEnter Your Email");
            return;
        }

        if (TextUtils.isEmpty(editText_signup_Id.getText())) {
            editText_signup_Id.setError("Enter Your id");
            return;
        }
        if (TextUtils.isEmpty(editText_ConfRegPass.getText())) {
            editText_ConfRegPass.setError(" Enter Your Confirmation Password");
            return;
        }

        if (TextUtils.isEmpty(editText_signup_password.getText())) {
            editText_signup_password.setError("Enter Your Password");
            return;
        }

        User user = new User();
        user.userName = fullName.getText().toString();
        user.userEmail = editText6_email.getText().toString();
        user.userId = Integer.parseInt(editText_signup_Id.getText().toString());
        user.userPassword = editText_ConfRegPass.getText().toString();
        user.isAdmin = true;
        user.gender = 1;


        viewModel.insertAdminUser(user);
        Log.d(TAG, "buttonRegisterSaveClick: Saved " + user.userName);
        User rUser = viewModel.userById(1234L);

        Toast.makeText(this, "Added" + rUser.userName + " successfully", Toast.LENGTH_SHORT).show();
    }
}
