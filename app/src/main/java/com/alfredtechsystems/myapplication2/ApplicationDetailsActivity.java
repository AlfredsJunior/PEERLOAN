package com.alfredtechsystems.myapplication2;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.github.mikephil.charting.charts.PieChart;
import com.hookedonplay.decoviewlib.DecoView;
import com.hookedonplay.decoviewlib.charts.DecoDrawEffect;
import com.hookedonplay.decoviewlib.charts.SeriesItem;
import com.hookedonplay.decoviewlib.events.DecoEvent;
import com.untu.fis.app.untumobile.DataClasses.dbApplications;
import com.untu.fis.app.untumobile.DataClasses.dbOperations;

public class ApplicationDetailsActivity extends AppCompatActivity {
    private dbOperations dboperation;
    TextView amount, tenure,details , date,status,sales,expenses,address,collateral,type,addressTxt,opsTxt,lengthOps;
    PieChart mChart;
    private DecoView mDecoView;
    private int mBackIndex;
    private int mSeries1Index;
    private int mSeries2Index;
    private int mSeries3Index;
    private final float mSeriesMax = 100f;
    private dbApplications myDataset;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_application_details);
        dboperation = new dbOperations(this);
        dboperation.open();

        amount = (TextView) findViewById(R.id.textAmntValue);
        tenure = (TextView) findViewById(R.id.textTenureValue);
        details = (TextView) findViewById(R.id.txtInfoValue);
        date = (TextView) findViewById(R.id.textDateValue);
        sales = (TextView) findViewById(R.id.textIncomeValue);
        expenses = (TextView) findViewById(R.id.textExpValue);
        address = (TextView) findViewById(R.id.textAddressValue);
        collateral = (TextView) findViewById(R.id.textCollateralValue);
        status = (TextView) findViewById(R.id.textStatus);
        type = (TextView) findViewById(R.id.textTypeValue);
        addressTxt = (TextView) findViewById(R.id.textAddress);
        opsTxt = (TextView) findViewById(R.id.textOps);
        lengthOps = (TextView) findViewById(R.id.textOpsValue);

        Bundle b = getIntent().getExtras();
        long value = 0; // or other values
        if(b != null)
            value = b.getLong("id");
        //long id = Long.parseLong(value);
        myDataset = dboperation.getApplication(value);
        myDataset.toString();


        amount.setText("$ " + String.format("%.2f",myDataset.getAppAmt()));
        date.setText(myDataset.getAppDate());
        tenure.setText(myDataset.getAppTenure() +" Months");
        details.setText(myDataset.getAppInfo());
        status.setText(myDataset.getAppStatus());
        sales.setText("$ " + myDataset.getApplcn_income());
        expenses.setText("$ " + myDataset.getApplcn_expense());
        address.setText(myDataset.getApplcn_address());
        collateral.setText("$ " + myDataset.getApplcn_assets());
        type.setText(myDataset.getApplcnType());
        lengthOps.setText(myDataset.getOps_length()+" Months");

        if(myDataset.getApplcnType().equals("Personal")) {
            addressTxt.setText("Employer");
            opsTxt.setText("Duration Of Employment");
        }

        mDecoView = (DecoView) findViewById(R.id.dynamicArcView);
        float progress = 0f;
        // Change the icon for Windows and iPhone
        if (myDataset.getAppStatus().equals("ERROR")) {
            progress = 5f;
        } else if (myDataset.getAppStatus().equals("SUBMITTED")) {
            progress = 20f;
        } else if (myDataset.getAppStatus().equals("ASSESSMENT")) {
            progress = 40f;
        } else if (myDataset.getAppStatus().equals("APPROVED")) {
            progress = 70f;
        } else if (myDataset.getAppStatus().equals("DISBURSED")) {
            progress = 100f;
        }else if (myDataset.getAppStatus().equals("PENDING")) {
            progress = 10f;
        }
        else{
            progress = 100f;
        }
        // Create required data series on the DecoView
        createBackSeries();
        createDataSeries1();
       // createDataSeries2();
       // createDataSeries3();

        // Setup events to be fired on a schedule
        createEvents(progress);
    }

    private void createBackSeries() {
        SeriesItem seriesItem = new SeriesItem.Builder(Color.parseColor("#FFE2E2E2"))
                .setRange(0, mSeriesMax, 0)
                .setInitialVisibility(true)
                .build();
        mBackIndex = mDecoView.addSeries(seriesItem);
    }

    private void createDataSeries1() {
        final SeriesItem seriesItem = new SeriesItem.Builder(Color.parseColor("#E54A3A"))
                .setRange(0, mSeriesMax, 0)
                .setInitialVisibility(false)
                .build();

        final TextView textPercentage = (TextView) findViewById(R.id.textPercentage);
        seriesItem.addArcSeriesItemListener(new SeriesItem.SeriesItemListener() {
            @Override
            public void onSeriesItemAnimationProgress(float percentComplete, float currentPosition) {
                float percentFilled = ((currentPosition - seriesItem.getMinValue()) / (seriesItem.getMaxValue() - seriesItem.getMinValue()));
                textPercentage.setText(String.format("%.0f%%", percentFilled * 100f));
            }

            @Override
            public void onSeriesItemDisplayProgress(float percentComplete) {

            }
        });


        final TextView textToGo = (TextView) findViewById(R.id.textRemaining);
        seriesItem.addArcSeriesItemListener(new SeriesItem.SeriesItemListener() {
            @Override
            public void onSeriesItemAnimationProgress(float percentComplete, float currentPosition) {
                textToGo.setText(String.format("Complete"));
            }

            @Override
            public void onSeriesItemDisplayProgress(float percentComplete) {
            }
        });

        mSeries1Index = mDecoView.addSeries(seriesItem);
    }

    private void createDataSeries2() {
        final SeriesItem seriesItem = new SeriesItem.Builder(Color.parseColor("#FFFF4444"))
                .setRange(0, mSeriesMax, 0)
                .setInitialVisibility(false)
                .build();

       /* final TextView textActivity2 = (TextView) findViewById(R.id.textActivity2);

        seriesItem.addArcSeriesItemListener(new SeriesItem.SeriesItemListener() {
            @Override
            public void onSeriesItemAnimationProgress(float percentComplete, float currentPosition) {
                textActivity2.setText(String.format("%.0f Km", currentPosition));
            }

            @Override
            public void onSeriesItemDisplayProgress(float percentComplete) {

            }
        });*/

        mSeries2Index = mDecoView.addSeries(seriesItem);
    }

    private void createDataSeries3() {
        final SeriesItem seriesItem = new SeriesItem.Builder(Color.parseColor("#FF6699FF"))
                .setRange(0, mSeriesMax, 0)
                .setInitialVisibility(false)
                .build();

        /*final TextView textActivity3 = (TextView) findViewById(R.id.textActivity3);

        seriesItem.addArcSeriesItemListener(new SeriesItem.SeriesItemListener() {
            @Override
            public void onSeriesItemAnimationProgress(float percentComplete, float currentPosition) {
                textActivity3.setText(String.format("%.2f Km", currentPosition));
            }

            @Override
            public void onSeriesItemDisplayProgress(float percentComplete) {

            }
        });*/

        mSeries3Index = mDecoView.addSeries(seriesItem);
    }

    private void createEvents(float currentposition) {
        mDecoView.executeReset();

        mDecoView.addEvent(new DecoEvent.Builder(mSeriesMax)
                .setIndex(mBackIndex)
                .setDuration(100)
                .setDelay(10)
                .build());

        mDecoView.addEvent(new DecoEvent.Builder(DecoDrawEffect.EffectType.EFFECT_SPIRAL_OUT)
                .setIndex(mSeries1Index)
                .setDuration(100)
                .setDelay(50)
                .build());

       mDecoView.addEvent(new DecoEvent.Builder(currentposition)
                .setIndex(mSeries1Index)
                .setDelay(100)
                .build());

        resetText();
    }

    private void resetText() {
        //((TextView) findViewById(R.id.textActivity1)).setText("");
        //((TextView) findViewById(R.id.textActivity2)).setText("");
        //((TextView) findViewById(R.id.textActivity3)).setText("");
        ((TextView) findViewById(R.id.textPercentage)).setText("");
        ((TextView) findViewById(R.id.textRemaining)).setText("");
    }
}
