package com.alfredtechsystems.myapplication2.adminRoles;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.alfredtechsystems.myapplication2.R;

import butterknife.ButterKnife;

public class LoanersInvestors extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loaners_investors);
        ButterKnife.bind(this);
    }
}
