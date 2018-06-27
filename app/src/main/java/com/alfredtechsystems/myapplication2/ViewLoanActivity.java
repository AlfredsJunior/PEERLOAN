package com.alfredtechsystems.myapplication2;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.github.mikephil.charting.charts.PieChart;
import com.untu.fis.app.untumobile.DataClasses.dbLoans;
import com.untu.fis.app.untumobile.DataClasses.dbOperations;

import java.util.ArrayList;
import java.util.List;

public class ViewLoanActivity extends AppCompatActivity {
    PieChart mChart;
    private dbOperations dboperation;
    private dbLoans myDataset;
    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    public long value;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_loan);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        viewPager = (ViewPager) findViewById(R.id.container);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

    }

    private void setupViewPager(ViewPager viewPager) {
        Bundle b = getIntent().getExtras();
       ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        LoanOverviewFragment loan = new LoanOverviewFragment();
        LoanTransactionFragment transaction = new LoanTransactionFragment();
        LoanScheduleFragment schedule = new LoanScheduleFragment();
        schedule.setArguments(b);
        transaction.setArguments(b);
        loan.setArguments(b);
        adapter.addFragment(loan, "OVERVIEW");
        adapter.addFragment(transaction, "TRANSACTIONS");
        adapter.addFragment(schedule, "SCHEDULE");
        viewPager.setAdapter(adapter);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }
}
