package com.alfredtechsystems.myapplication2;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class ViewLoansActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_loans);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);

        viewPager = (ViewPager) findViewById(R.id.loans_container);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.loans_tabs);
       tabLayout.setupWithViewPager(viewPager);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout1);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

    }

    private void setupViewPager(ViewPager viewPager) {
        Bundle b = getIntent().getExtras();
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new LoanApplicationsFragment(), "LOAN APPLICATIONS");
        adapter.addFragment(new LoanAccountsFragment(), "LOAN ACCOUNTS");
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


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout1);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            Intent myIntent = new Intent(ViewLoansActivity.this,
                    HomeActivity.class);
            startActivity(myIntent);
        }

       else if (id == R.id.nav_new_loan) {
            Intent myIntent = new Intent(ViewLoansActivity.this,
                    LoanApplicationActivity.class);
            startActivity(myIntent);

        } else if (id == R.id.nav_all_loans) {
            Intent myIntent = new Intent(ViewLoansActivity.this,
                    ViewLoansActivity.class);
            startActivity(myIntent);

//        } else if (id == R.id.nav_mobile_money) {
//            Intent myIntent = new Intent(ViewLoansActivity.this,
//                    MobileMoneyActivity.class);
//            startActivity(myIntent);
//
//        } else if (id == R.id.nav_settings) {
//            Intent myIntent = new Intent(ViewLoansActivity.this,
//                    SettingsActivity.class);
//            startActivity(myIntent);

        } else if (id == R.id.nav_feedback) {
            Intent myIntent = new Intent(ViewLoansActivity.this,
                    FeedbackActivity.class);
            startActivity(myIntent);
        } else if (id == R.id.nav_securities) {
            Intent myIntent = new Intent(ViewLoansActivity.this,
                    SecuritiesActivity.class);
            startActivity(myIntent);
        }
        else if (id == R.id.nav_branches) {
            Intent myIntent = new Intent(ViewLoansActivity.this,
                    BranchesActivity.class);
            startActivity(myIntent);
        } else if (id == R.id.nav_products) {
            Intent myIntent = new Intent(ViewLoansActivity.this,
                    LoanProductActivity.class);
            startActivity(myIntent);
        }
//        }  else if (id == R.id.nav_payments) {
//            Intent myIntent = new Intent(LoanTopupActivity.this,
//                    PaymentsActivity.class);
//            startActivity(myIntent);
        else if (id == R.id.nav_send_feedback) {
            Intent myIntent = new Intent(ViewLoansActivity.this,
                    AboutActivity.class);
            startActivity(myIntent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout1);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
