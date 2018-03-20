package com.alfredtechsystems.myapplication2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.alfredtechsystems.myapplication2.model.LoanCalculator;
import com.alfredtechsystems.myapplication2.model.LoanCalculatorUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class home_user extends AppCompatActivity {

    /**
     *
     */
    @BindView(R.id.button_invest)
    Button button_invest;
    @BindView(R.id.editText_apply_weeks)
    EditText applyWeeks;

    @BindView(R.id.editText_loan_amount)
    EditText loan_amount;

    @BindView(R.id.amount_topay)
    TextView amountToPay;
    @BindView(R.id.btn_apply)
    Button btn_apply;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_user);
        ButterKnife.bind(this);
    }

   // @SuppressLint("SetTextI18n")
    @OnClick(R.id.btn_apply)
    public void btn_applyClick() {
        if (TextUtils.isEmpty(loan_amount.getText())) {
            loan_amount.setError("Enter LoanCalculator amount");
            return;
        }
        if (TextUtils.isEmpty(applyWeeks.getText())) {
            applyWeeks.setError("Enter  Number of Weeks");
            return;
        }

        LoanCalculator loanCalculator = new LoanCalculator(Double.parseDouble(String.valueOf(loan_amount.getText())), Integer.parseInt(String.valueOf(applyWeeks.getText())), 0.075);
        Toast.makeText(this, "Successfully applied ", Toast.LENGTH_SHORT).show();

        LoanCalculatorUtil loanUtil = new LoanCalculatorUtil(loanCalculator, true);

        amountToPay.setText("Congrats you've approved for " + loanUtil.loanAmountApproved());

        // Snackbar.make(findViewById(R.id.relative),"Proceed to investment",Snackbar.LENGTH_LONG).show();
     }

    @OnClick(R.id.button_invest)
    public void setInvest(){
        Intent gain = new Intent(this, user_invest.class);
        startActivity(gain);
}
}