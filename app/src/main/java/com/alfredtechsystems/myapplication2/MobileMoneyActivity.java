package com.alfredtechsystems.myapplication2;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;

import com.untu.fis.app.untumobile.DataClasses.dbSMS;
import com.untu.fis.app.untumobile.ListAdapters.CustomExpandableListAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MobileMoneyActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    public static final String OTP_REGEX = "[0-9]{1,6}";
    ArrayList<dbSMS> sms_db = new ArrayList<dbSMS>();
    ExpandableListView expandableListView;
    ExpandableListAdapter expandableListAdapter;
    List<String> expandableListTitle;
    HashMap<String, List<dbSMS>> expandableListDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mobile_money);

       // Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

//        Uri myMessage = Uri.parse("content://sms/");
//        ContentResolver cr = this.getContentResolver();
//        Cursor c = cr.query(myMessage, new String[]{"_id", "address", "date",
//                "body", "read"}, "address = '+263164'", null,"date DESC");
//        startManagingCursor(c);
//        List[] amount = getSmsLogs(c, MobileMoneyActivity.this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        expandableListView = (ExpandableListView) findViewById(R.id.expandableListView);
        //expandableListDetail = getData(amount[1],amount[0]);
        expandableListTitle = new ArrayList<String>(expandableListDetail.keySet());
        expandableListAdapter = new CustomExpandableListAdapter(this, expandableListTitle, expandableListDetail);
        expandableListView.setAdapter(expandableListAdapter);
        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {

            @Override
            public void onGroupExpand(int groupPosition) {
            }
        });

        expandableListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {

            @Override
            public void onGroupCollapse(int groupPosition) {

            }
        });

        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                return false;
            }
        });
    }

//    private List[] getSmsLogs(Cursor c, Context con) {
//
//        if (sms_db.size() > 0) {
//            sms_db.clear();
//        }
//        List[] amount = new List[2];
//        Double amountSent = 0.00;
//        Double amountRcvd = 0.00;
//        Double amountPaid = 0.00;
//        int count = 0;
//        ArrayList<String> dtmthns = new ArrayList<>();
//        ArrayList<dbSMS> dbList = new ArrayList<>();
//
//
//        try {
//            if (c.moveToFirst()) {
//                do {
//                    if (c.getString(c.getColumnIndexOrThrow("address")) == null) {
//                        c.moveToNext();
//                        continue;
//                    }
//                    dbSMS dbsms = new dbSMS();
//                    Date date = new Date();
//                    String datestr ="";
//                    count++;
//                    String Number = c.getString(c.getColumnIndexOrThrow("address")).toString();
//                    String _id = c.getString(c.getColumnIndexOrThrow("_id")).toString();
//                    String dat = c.getString(c.getColumnIndexOrThrow("date"));
//                    String Body = c.getString(c.getColumnIndexOrThrow("body")).toString();
//
//                    SimpleDateFormat format = new SimpleDateFormat("MMMM-yyyy");
//                    try {
//                        long dateLong = Long.parseLong(dat);
//                        date = new Date(dateLong);
//                        datestr = format.format(date);
//
//                        if(!dtmthns.contains(datestr)) {
//                            dtmthns.add(datestr);
//                        }
//                    } catch (Exception e) {
//                    }
//
//                    if (Body.contains("Transfer Confirmation")) {
//                        String[] myMessages = Body.split("(?<=[.])\\s+");
//                        if (myMessages[1].contains(" to ")) {
//                            String[] myAmount = myMessages[1].split("\\s+");
//                            double f = Double.parseDouble(myAmount[1]);
//                            amountSent = amountSent + f;
//                            Pattern pattern = Pattern.compile("to(.*?)Approval");
//                            Matcher matcher = pattern.matcher(myMessages[1]);
//                            while (matcher.find()) {
//                                dbsms.setName(matcher.group(1));
//                            }
//                            dbsms.setTrantyp("Money Sent");
//                            dbsms.setTrandate(date);
//                            dbsms.setAmount(f*-1);
//                            dbsms.setTranMonth(datestr);
//                        } else if (myMessages[1].contains(" from ")) {
//                            String[] myAmount = myMessages[1].split("\\s+");
//                            double f = Double.parseDouble(myAmount[1]);
//                            amountRcvd = amountRcvd + f;
//                            Pattern pattern = Pattern.compile("from(.*?)Approval");
//                            Matcher matcher = pattern.matcher(myMessages[1]);
//                            while (matcher.find()) {
//                                dbsms.setName(matcher.group(1));
//                            }
//                            dbsms.setTrantyp("Money Received");
//                            dbsms.setTrandate(date);
//                            dbsms.setAmount(f);
//                            dbsms.setTranMonth(datestr);
//                        }
//                    } else if (Body.contains("successfully paid")) {
//                        String[] myMessages = Body.split("(?<=[.])\\s+");
//                        String[] myAmount = myMessages[0].split("\\s+");
//                        double f = Double.parseDouble(myAmount[4].replaceAll("[^\\d.]", ""));
//                        amountPaid = amountPaid + f;
//                        Pattern pattern = Pattern.compile("to(.*?)Merchant");
//                        Matcher matcher = pattern.matcher(myMessages[0]);
//                        while (matcher.find()) {
//                            dbsms.setName(matcher.group(1));
//                        }
//                        dbsms.setTrantyp("Merchant Payment");
//                        dbsms.setTrandate(date);
//                        dbsms.setAmount(f*-1);
//                        dbsms.setTranMonth(datestr);
//                    }
//                    else if (Body.contains("Cash In Confirmation")) {
//                        String[] myMessages = Body.split("\\s+");
//                        //String[] myAmount = myMessages[4];
//                       double f = Double.parseDouble(myMessages[4].replaceAll("[^\\d.]", ""));
//                        amountSent = amountSent + f;
//                        Pattern pattern = Pattern.compile("from(.*?)Approval");
//                        Matcher matcher = pattern.matcher(Body);
//                        while (matcher.find()) {
//                            dbsms.setName(matcher.group(1));
//
//                        }
//                        dbsms.setTrantyp("Cash In");
//                        dbsms.setTrandate(date);
//                        dbsms.setAmount(f);
//                        dbsms.setTranMonth(datestr);
//                    }
//                    if(dbsms.getName()!=null) {
//                        dbList.add(dbsms);
//                    }
//
//                } while (c.moveToNext());
//            }
//            c.close();
//
//        } catch (Exception e) {
//            Toast.makeText(MobileMoneyActivity.this, "Error: " + e.getMessage(), Toast.LENGTH_LONG).show();
//            e.printStackTrace();
//        }
//        amount[0] = dbList;
//        amount[1] = dtmthns;
//        return amount;
//
//    }
    
    public static HashMap<String, List<dbSMS>> getData(List<String> mylist,List<dbSMS> smsList) {
        HashMap<String, List<dbSMS>> expandableListDetail = new HashMap<String, List<dbSMS>>();
        for (String y: mylist) {
            List<dbSMS> cricket = new ArrayList<dbSMS>();
            for (dbSMS h: smsList) {
                if(h.getTranMonth().contentEquals(y)){
                    cricket.add(h);
                }
            }
            if(cricket.size()>0) {

                expandableListDetail.put(y, cricket);
            }
        }
        return expandableListDetail;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
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

        if (id == R.id.nav_new_loan) {
            Intent myIntent = new Intent(MobileMoneyActivity.this,
                    LoanApplicationActivity.class);
            startActivity(myIntent);

        } else if (id == R.id.nav_all_loans) {
            Intent myIntent = new Intent(MobileMoneyActivity.this,
                    ViewLoansActivity.class);
            startActivity(myIntent);

//        } else if (id == R.id.nav_mobile_money) {
//            Intent myIntent = new Intent(MobileMoneyActivity.this,
//                    MobileMoneyActivity.class);
//            startActivity(myIntent);
//
//        } else if (id == R.id.nav_settings) {
//            Intent myIntent = new Intent(MobileMoneyActivity.this,
//                    SettingsActivity.class);
//            startActivity(myIntent);

        } else if (id == R.id.nav_feedback) {
            Intent myIntent = new Intent(MobileMoneyActivity.this,
                    FeedbackActivity.class);
            startActivity(myIntent);
        } else if (id == R.id.nav_securities) {
            Intent myIntent = new Intent(MobileMoneyActivity.this,
                    SecuritiesActivity.class);
            startActivity(myIntent);
        }
        else if (id == R.id.nav_branches) {
            Intent myIntent = new Intent(MobileMoneyActivity.this,
                    BranchActivity.class);
            startActivity(myIntent);
        } else if (id == R.id.nav_products) {
            Intent myIntent = new Intent(MobileMoneyActivity.this,
                    LoanProductActivity.class);
            startActivity(myIntent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
