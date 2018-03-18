package com.alfredtechsystems.myapplication2.adminRoles;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.alfredtechsystems.myapplication2.R;

import butterknife.ButterKnife;

public class LoanApproveAdmin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loan_approve_admin);
        ButterKnife.bind(this);
    }
}
