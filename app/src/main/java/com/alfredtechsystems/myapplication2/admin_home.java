package com.alfredtechsystems.myapplication2;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.alfredtechsystems.myapplication2.adminRoles.LoanApproveAdmin;
import com.alfredtechsystems.myapplication2.adminRoles.StudentsDetails;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class admin_home extends AppCompatActivity {

    public static final String PASSUSER = "USER_PASSED";

    @BindView(R.id.button_check_for_loanees)
    Button checkforloanees;

    @BindView(R.id.button_loners)
    Button loaners;


    @BindView(R.id.button_approveloan)
    Button approveLoan;

    @BindView(R.id.button_studentdetails)
    Button studentDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);
        ButterKnife.bind(this);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String value = extras.getString(PASSUSER);
            //The key argument here must match that used in the other activity
            View view = this.getWindow().getDecorView().findViewById(android.R.id.content);
            Snackbar.make(view, "Welcome " + value, Snackbar.LENGTH_LONG);
            Log.d("MMM", "onCreate: " + value);

        }

        studentDetails.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                implementMe(studentDetails);
            }
        });
    }
    @Override
    protected void onStart() {
        super.onStart();
    }

    @OnClick
    public void implementMe(Button button) {

        Toast.makeText(this, "Implement " + button.getText(), Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.button_studentdetails)
    public void studentDetails() {
        Intent k = new Intent(this, StudentsDetails.class);
        startActivity(k);
    }

    @OnClick(R.id.button_approveloan)
    public void approveLoan() {
        Intent l = new Intent(this, LoanApproveAdmin.class);
        startActivity(l);
    }

    @OnClick(R.id.button_loners)
    public void loaners() {
        Intent m = new Intent(this, LoanApproveAdmin.class);
        startActivity(m);
    }
    @OnClick(R.id.button_check_for_loanees)
    public void checkforloanees() {
        Intent n = new Intent(this, LoanApproveAdmin.class);
        startActivity(n);
    }


}