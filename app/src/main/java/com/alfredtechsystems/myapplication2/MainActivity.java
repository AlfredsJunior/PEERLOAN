package com.alfredtechsystems.myapplication2;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.alfredtechsystems.myapplication2.db.AppDatabase;
import com.alfredtechsystems.myapplication2.db.dbModel.AdminUser;
import com.alfredtechsystems.myapplication2.db.dbModel.User;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.signup_button)
    Button signUpButton;
    @BindView(R.id.button_forgotpassword)
    Button forgotPassword;

    @BindView(R.id.textView)
    TextView headerViewText;

    @BindView(R.id.button_loginasAdmin)
    Button loginAsAdmin;

    @BindView(R.id.button_loginasuser)
    Button loginAsuser;

    @BindView(R.id.button_exit)
    Button btnexit;

    @BindView(R.id.editText_password)
    EditText editPassword;

    @BindView(R.id.editText_userid)
    EditText userId;

    @BindView(R.id.editText_username)
    EditText userName;

    private MainActivityViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        //init viewmodel
        viewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);
        //fetchUsers();
        //fetchAdmins();

    }
    Boolean doLogin;

    void fetchUsers(){
        viewModel.getmUsers().observe(this, new Observer<List<User>>() {

            @Override
            public void onChanged(@Nullable List<User> users) {
                doLogin = performLogin(users);
            }
        });
    }
    void fetchAdmins(){
        viewModel.getmAdminUsers().observe(this, new Observer<List<AdminUser>>() {
            @Override
            public void onChanged(@Nullable List<AdminUser> adminUsers) {
                //mAdminUsers = adminUsers;
            }
        });
    }

    @OnClick(R.id.button_loginasAdmin)
    public void loginAdminClick(){
        Intent intent = new Intent(this,Admin_login.class);
        startActivity(intent);
    }

    @OnClick(R.id.button_exit)
    public void exitClick(){
        finish();
    }

    @Override
    protected void onDestroy() {
        AppDatabase.destroyPeerLoanDb();
        super.onDestroy();
    }

    @OnClick(R.id.button_loginasuser)
    public void setLoginAsuserseerClick(){

        if(TextUtils.isEmpty(userName.getText())){
            userName.setError("Enter your Fucking  name");
            return;
        }

        if(TextUtils.isEmpty(userId.getText())){
            userName.setError("Enter Your Fucking id");
            return;
        }

        if(TextUtils.isEmpty(editPassword.getText())){
            userName.setError("Enter Your Fucking Password");
            return;
        }

        if (performLogin(viewModel.getmUsers().getValue())) {
            Intent intent = new Intent(this, home_user.class);
            finish();
            startActivity(intent);
        } else{
            Toast.makeText(this, "Wrong username, id or Password!", Toast.LENGTH_SHORT).show();
        }
    }

    private Boolean performLogin(List<User> mUsers){
        for (User user : mUsers) {
            if (userName.getText().equals(user.userName) &&
                    userId.getText().equals(user.userId) &&
                    editPassword.getText().equals(user.userPassword)) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    @OnClick(R.id.button_forgotpassword)
    public  void setForgotPassword(){
        Intent j = new Intent(this, reset_password.class);
        startActivity(j);
    }

    @OnClick(R.id.signup_button)
    public  void signUpPage(){
        Intent j = new Intent(this, SignupActivity.class);
        startActivity(j);
    }

}
