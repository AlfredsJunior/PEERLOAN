package com.alfredtechsystems.myapplication2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.alfredtechsystems.myapplication2.investModel.Invest;
import com.alfredtechsystems.myapplication2.investModel.InvestUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class user_invest extends AppCompatActivity {

    @BindView(R.id.buttonInvest)
    Button investBtn;
    @BindView(R.id.edittextAmountToInvest)
    EditText amountToInvest;

    @BindView(R.id.editTextInvestweeks)
    EditText noofweeks;

    @BindView(R.id.txtviewAmountTobeRePaid)
    TextView amountToBeRepaid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_invest);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.buttonInvest)
    public void investBtnClick() {

        if (TextUtils.isEmpty(amountToInvest.getText())) {
            amountToInvest.setError(" enter amount to invest");
            return;
        }

        if (TextUtils.isEmpty(noofweeks.getText())) {
            noofweeks.setError("Fucking enter some name");
            return;
        }


        Invest amount = new Invest(Double.parseDouble(String.valueOf(amountToInvest.getText())),
                Integer.parseInt(String.valueOf(noofweeks.getText())), 0.035D);

        InvestUtil investUtil = new InvestUtil(amount,true);

        double myamount;
        myamount = amount.getAmount() + (amount.getRate() * amount.getWeeks()*amount.getAmount());

        amountToBeRepaid.setText("Kshs "+ myamount);

        Toast.makeText(this, "Amounted Invested Succefully", Toast.LENGTH_SHORT).show();

    }




}