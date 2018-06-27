package com.alfredtechsystems.myapplication2;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.untu.fis.app.untumobile.DataClasses.dbLoans;
import com.untu.fis.app.untumobile.DataClasses.dbOperations;

import java.util.ArrayList;


public class LoanOverviewFragment extends Fragment {
    PieChart mChart;
    private dbOperations dboperation;
    private dbLoans myDataset;
    SwipeRefreshLayout mSwipeRefreshLayout;

    public LoanOverviewFragment() {

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View myView = inflater.inflate(R.layout.fragment_loan_overview, container, false);
        dboperation = new dbOperations(getContext());
        dboperation.open();

        TextView txtBal = (TextView) myView.findViewById(R.id.textStatus);
        TextView txtState = (TextView) myView.findViewById(R.id.textView36);
        TextView txtDue = (TextView) myView.findViewById(R.id.textView39);
        Bundle b = this.getArguments();;
        String value = ""; // or other values
        if(b != null)
            value = b.getString("loan_id");
        myDataset = dboperation.getLoan(value);
        myDataset.toString();
        float principal_paid = Float.parseFloat(myDataset.getLn_pr_paid());
        float interest_paid = Float.parseFloat(myDataset.getLn_int_paid());
        float loan_amount = Float.parseFloat(myDataset.getLn_amount());
        float loan_balance = Float.parseFloat(myDataset.getLn_balance());
        String balance = "Balance : $" + myDataset.getLn_balance();
        String status = "Status : ";
        String due = "Amount Due : $"+myDataset.getLn_amt_due();
        if(myDataset.getLn_acc_sate().equals("ACTIVE")){
            status+="Up To Date";
        }
        else if(myDataset.getLn_acc_sate().equals("ACTIVE_IN_ARREARS")){
            status+="In Arrears";
        }
        else if(myDataset.getLn_acc_sate().equals("CLOSED_REJECTED")){
            status+="Rejected";
        }
        else if(myDataset.getLn_acc_sate().equals("CLOSED")){
            status += "Closed";
        }
        txtState.setText(status);
        txtDue.setText(due);
        txtBal.setText(balance);

        mChart = (PieChart) myView.findViewById(R.id.piechart);
        mChart.setUsePercentValues(false);
        mChart.getDescription().setEnabled(false);
        mChart.setExtraOffsets(5, 10, 5, 5);
        mChart.setDragDecelerationFrictionCoef(0.95f);
        mChart.setDrawHoleEnabled(true);
        mChart.setHoleColor(Color.TRANSPARENT);
        mChart.setTransparentCircleColor(Color.WHITE);
        mChart.setTransparentCircleAlpha(110);
        mChart.setHoleRadius(58f);
        mChart.setTransparentCircleRadius(61f);
        mChart.setDrawCenterText(true);
        mChart.setRotationAngle(0);
        mChart.setTouchEnabled(true);
        mChart.setRotationEnabled(false);
        mChart.setHighlightPerTapEnabled(true);
        mChart.setEntryLabelColor(R.color.half_black);
        mChart.setHighlightPerTapEnabled(true);
        Legend l = mChart.getLegend();
        l.setEnabled(false);
        mChart.setClickable(true);
        mChart.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                mChart.setCenterText("Hello World");
            }
        });
        //mChart.setOnTouchListener((v));

        ArrayList<PieEntry> yValues = new ArrayList<>();
        yValues.add(new PieEntry(principal_paid,"Principal Paid"));
        yValues.add(new PieEntry(interest_paid,"Interest Paid"));
        if(loan_balance>0f) {
            yValues.add(new PieEntry(loan_balance, "Balance"));
        }

        PieDataSet dataSet = new PieDataSet(yValues,"Cities");
        dataSet.setSliceSpace(3f);
        dataSet.setSelectionShift(5f);
        dataSet.setColors(ColorTemplate.COLORFUL_COLORS);

        PieData data = new PieData(dataSet);
        data.setValueTextSize(20f);
        data.setValueTextColor(Color.BLACK);


        mChart.setData(data);
        return myView;
    }


}
