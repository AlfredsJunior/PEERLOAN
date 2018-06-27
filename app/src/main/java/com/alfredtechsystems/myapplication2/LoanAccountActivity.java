package com.alfredtechsystems.myapplication2;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class LoanAccountActivity extends AppCompatActivity {

    String url = "http://192.168.2.103:8089/UntuMobile.svc/accounts";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loan_account);

        Resources res = getResources();

       // final PieChart pie = (PieChart) this.findViewById(R.id.Pie);
     //   pie.addItem("Agamemnon", 2, res.getColor(R.color.seafoam));
      //  pie.addItem("Bocephus", 3.5f, res.getColor(R.color.chartreuse));
       // pie.addItem("Calliope", 2.5f, res.getColor(R.color.emerald));
        //pie.addItem("Daedalus", 3, res.getColor(R.color.bluegrass));
        //pie.addItem("Euripides", 1, res.getColor(R.color.turquoise));
        //pie.addItem("Ganymede", 3, res.getColor(R.color.slate));
    }
}
