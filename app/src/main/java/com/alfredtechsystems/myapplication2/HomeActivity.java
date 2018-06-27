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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.untu.fis.app.untumobile.DataClasses.dbLoans;
import com.untu.fis.app.untumobile.DataClasses.dbOperations;
import com.untu.fis.app.untumobile.DataClasses.dbSchedules;
import com.untu.fis.app.untumobile.DataClasses.dbUsers;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private dbOperations userData;
    private List<dbUsers> users;
    private dbLoans loan;
    private dbSchedules schedule;
    public ImageView image;
    private Boolean ifLoan;
    private TextView balance,balanceText,accountStatus,amountDue,dueDate,amountDueText,dueDateText;
    FloatingActionMenu materialDesignFAM;
    FloatingActionButton floatingActionButton1, floatingActionButton2, floatingActionButton3, floatingActionButton4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        materialDesignFAM = (FloatingActionMenu) findViewById(R.id.material_design_android_floating_action_menu);
        floatingActionButton1 = (FloatingActionButton) findViewById(R.id.material_design_floating_action_menu_item1);
        floatingActionButton2 = (FloatingActionButton) findViewById(R.id.material_design_floating_action_menu_item2);
        floatingActionButton3 = (FloatingActionButton) findViewById(R.id.material_design_floating_action_menu_item3);
        floatingActionButton4 = (FloatingActionButton) findViewById(R.id.material_design_floating_action_menu_item4);
        //image = (ImageView) findViewById(R.id.imageView10);
        balance = (TextView) findViewById(R.id.textAmount);
        //balanceText = (TextView) findViewById(R.id.textLoanBalanceText);
        accountStatus = (TextView) findViewById(R.id.textStatusValue);
        amountDue = (TextView) findViewById(R.id.textInstallmentAmount);
        dueDate = (TextView) findViewById(R.id.textRepDate);
        amountDueText = (TextView) findViewById(R.id.textInstallment);
        dueDateText = (TextView) findViewById(R.id.textRepaymentDate);

        materialDesignFAM.setClosedOnTouchOutside(true);

        floatingActionButton1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent myIntent = new Intent(HomeActivity.this,
                      AddLoanActivity.class);
               startActivity(myIntent);
            }
        });
        floatingActionButton2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent myIntent = new Intent(HomeActivity.this,
                       LoanApplicationActivity.class);
                startActivity(myIntent);
            }
        });
        floatingActionButton3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent myIntent = new Intent(HomeActivity.this,
                        FeedbackActivity.class);
                startActivity(myIntent);
            }
       });
        floatingActionButton4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent myIntent = new Intent(HomeActivity.this,
                        SecuritiesActivity.class);
                startActivity(myIntent);
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        LinearLayout linear1 = (LinearLayout) findViewById(R.id.linear1);
        LinearLayout linear2 = (LinearLayout) findViewById(R.id.linear2);
        LinearLayout linear3 = (LinearLayout) findViewById(R.id.linear3);
        LinearLayout linear4 = (LinearLayout) findViewById(R.id.linear4);
        LinearLayout linear5 = (LinearLayout) findViewById(R.id.linear5);
        LinearLayout linear6 = (LinearLayout) findViewById(R.id.linear6);

        linear1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(HomeActivity.this,
                        LoanEmergencyActivity.class);
                startActivity(myIntent);
            }
        });
        linear2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(HomeActivity.this,
                        LoanTopupActivity.class);
                startActivity(myIntent);
            }
        });
        linear3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(HomeActivity.this,
                        LoanApplicationActivity.class);
                startActivity(myIntent);
            }
        });
        linear4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(HomeActivity.this,
                        ViewLoansActivity.class);
                startActivity(myIntent);
            }
        });
        linear5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(HomeActivity.this,
                        ViewApplicationsActivity.class);
                startActivity(myIntent);
            }
        });
        linear6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(HomeActivity.this,
                        LoanProductActivity.class);
                startActivity(myIntent);
            }
        });


        userData = new dbOperations(this);
        userData.open();
        TextView txtName = (TextView) findViewById(R.id.textView33);

        users = userData.getAllUsers();
        ifLoan = userData.loanCount();
        if(ifLoan)
        {
            loan = userData.getActiveLoan();
            if(userData.ScheduleCountAll(loan.getLn_acc_num())) {
                schedule = userData.getNextSchedule(loan.getLn_acc_num());
            }
            balance.setText("$"+ loan.getLn_balance());
            if(loan.getLn_acc_sate().equals("ACTIVE"))
            {
                if(userData.ScheduleCountAll(loan.getLn_acc_num())) {
                    amountDue.setText("$" + schedule.getAmount_due());
                }
                accountStatus.setText("Up to Date");
                amountDueText.setText("Next Installment Amount");
                dueDateText.setText("Next Payment Date");
            }
            else {
                amountDue.setText("$" + loan.getLn_amt_due());
                accountStatus.setText("In Arrears");
                amountDueText.setText("Amount Due");
                dueDate.setText("");
            }

            if(userData.ScheduleCountAll(loan.getLn_acc_num())) {
                DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
                String appDate = new SimpleDateFormat("dd-MM-yyyy").format(Calendar.getInstance().getTime());
                try {
                    Date startDate = df.parse(schedule.getDue_date());
                    Date currentDate = df.parse(appDate);
                    if(currentDate.compareTo(startDate)>0){
                        long diff = currentDate.getTime() - startDate.getTime();

                        accountStatus.setText("In Arrears");
                        dueDate.setText(""+TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS));
                        dueDateText.setText("Days In Arrears");
                        amountDueText.setText("Amount Due");
                        schedule.setState("LATE");
                        userData.updateSchedule(schedule);
                        loan.setLn_acc_sate("ACTIVE_IN_ARREARS");
                        userData.updateLoans(loan);
                    }
                    else{
                        dueDate.setText(schedule.getDue_date());
                    }


                } catch (ParseException e) {
                    e.printStackTrace();
                }

            }
        }
        else{
            balance.setText("$ 0.00");
            amountDue.setText("$ 0.00");
            amountDue.setText("$ 0.00");
            accountStatus.setText("No Loans");
            dueDate.setText(" - ");
        }
        for (dbUsers item: users ) {
            txtName.setText(item.getFirstname()+" "+item.getLastname());
        }

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

        if (id == R.id.nav_home) {
            Intent myIntent = new Intent(HomeActivity.this,
                    HomeActivity.class);
            startActivity(myIntent);
        }

        else  if (id == R.id.nav_new_loan) {
            Intent myIntent = new Intent(HomeActivity.this,
                    LoanApplicationActivity.class);
            startActivity(myIntent);

        } else if (id == R.id.nav_all_loans) {
            Intent myIntent = new Intent(HomeActivity.this,
                    ViewLoansActivity.class);
            startActivity(myIntent);

//        } else if (id == R.id.nav_mobile_money) {
//            Intent myIntent = new Intent(HomeActivity.this,
//                    MobileMoneyActivity.class);
//            startActivity(myIntent);

//        } else if (id == R.id.nav_settings) {
//            Intent myIntent = new Intent(HomeActivity.this,
//                    SettingsActivity.class);
//            startActivity(myIntent);

        } else if (id == R.id.nav_feedback) {
            Intent myIntent = new Intent(HomeActivity.this,
                    FeedbackActivity.class);
            startActivity(myIntent);
        } else if (id == R.id.nav_securities) {
            Intent myIntent = new Intent(HomeActivity.this,
                    SecuritiesActivity.class);
            startActivity(myIntent);
        }
        else if (id == R.id.nav_branches) {
            Intent myIntent = new Intent(HomeActivity.this,
                    BranchesActivity.class);
            startActivity(myIntent);
        } else if (id == R.id.nav_products) {
            Intent myIntent = new Intent(HomeActivity.this,
                    LoanProductActivity.class);
            startActivity(myIntent);
        }
//        }  else if (id == R.id.nav_payments) {
//            Intent myIntent = new Intent(LoanTopupActivity.this,
//                    PaymentsActivity.class);
//            startActivity(myIntent);
        else if (id == R.id.nav_send_feedback) {
            Intent myIntent = new Intent(HomeActivity.this,
                    AboutActivity.class);
            startActivity(myIntent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
