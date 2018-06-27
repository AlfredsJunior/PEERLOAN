package com.alfredtechsystems.myapplication2;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
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
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.untu.fis.app.untumobile.DataClasses.dbApplications;
import com.untu.fis.app.untumobile.DataClasses.dbOperations;
import com.untu.fis.app.untumobile.DataClasses.dbUsers;
import com.untu.fis.app.untumobile.Location.GPSTracker;
import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LoanTopupActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    MaterialBetterSpinner materialBetterSpinner,collateral_spinner;
    private static final String TAG = "";
    private ProgressDialog pDialog;
    TextView content;
    EditText lamount, ltenure, length, lsector, other,address,income,expense,assets;
    String LoanAmount, LoanTenure, LengthOp, Sector, OtherInfo,Address,Income,Expense,Assets;
    Button submit;
    String url = configValues.url + "/trans";
    String url_register = configValues.url + "/register";
    private dbOperations userData;
    private dbApplications newApplication;
    private static final int REQUEST_CODE_PERMISSION = 2;
    String mPermission = Manifest.permission.ACCESS_FINE_LOCATION;
    Double latitude =0.00;
    Double longitude =0.00;
    GPSTracker gps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loan_topup);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        lamount = (EditText) findViewById(R.id.input_amount);
        ltenure = (EditText) findViewById(R.id.input_tenure);
        submit = (Button) findViewById(R.id.button13);
        userData = new dbOperations(this);
        userData.open();

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoanAmount = lamount.getText().toString();
                LoanTenure = ltenure.getText().toString();
                String appDate = new SimpleDateFormat("dd-MM-yyyy").format(Calendar.getInstance().getTime());
                String status = "";
                String client_name="";
                String client_phone="";
                String id_num="";
                Long id = Long.valueOf(0);
                String manufacturer = Build.MANUFACTURER;
                String model = Build.MODEL;
                int version = Build.VERSION.SDK_INT;
                String versionRelease = Build.VERSION.RELEASE;
                String device_id = "";
                List<dbUsers> users = userData.getAllUsers();
                for (dbUsers user :users) {
                    status = user.getSent();
                    device_id = user.getApp_id();
                    id = user.getUsrId();
                    client_name = user.getFirstname();
                    client_phone = user.getPhNum();
                    id_num = user.getIdNum();
                }


                if (status.equals("0"))
                {
                    JSONObject register = new JSONObject();
                    try
                    {
                        register.put("device_id",device_id);
                        register.put("device_name",manufacturer +" "+ model);
                        register.put("device_os",versionRelease);
                        register.put("client_name",client_name);
                        register.put("phone_num",client_phone);
                        register.put("id_num",id_num);
                    }
                    catch(Exception e)
                    {
                        e.printStackTrace();

                    }
                    final dbUsers editUser = userData.getUsers(id);
                    RequestQueue queue = Volley.newRequestQueue(LoanTopupActivity.this);
                    JsonObjectRequest string_request = new JsonObjectRequest(Request.Method.POST, url_register,register,new Response.Listener<JSONObject>() {

                        @Override
                        public void onResponse(JSONObject response) {

                            String id ="";
                            String status ="";
                            try {
                                id = response.getString("id");
                                status = response.getString("status");
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            editUser.setSent("1");
                            userData.updateUsers(editUser);
                            Intent i = new Intent(LoanTopupActivity.this, ViewApplicationsActivity.class);
                            startActivity(i);

                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                        }
                    }) {
                        @Override
                        public Map<String, String> getHeaders() throws AuthFailureError {
                            HashMap<String, String> headers = new HashMap<String, String>();
                            return headers;
                        }
                    };
                    string_request.setRetryPolicy(new DefaultRetryPolicy(
                            5000,
                            DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                            DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
                    queue.add(string_request);

                }

                JSONObject request = new JSONObject();
                try
                {
                    request.put("id",device_id);
                    request.put("date",appDate);
                    request.put("amount",LoanAmount);
                    request.put("tenure",LoanTenure);
                    request.put("time","");
                    request.put("narration","Loan Top Up");
                    request.put("latitude",latitude);
                    request.put("longitude",longitude);
                    request.put("type","Top Up");
                    request.put("income","0");
                    request.put("expense","0");
                    request.put("assets","0");
                    request.put("address","");
                }
                catch(Exception e)
                {
                    e.printStackTrace();

                }
                RequestQueue queue = Volley.newRequestQueue(LoanTopupActivity.this);
                JsonObjectRequest string_request = new JsonObjectRequest(Request.Method.POST, url,request,new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {

                        String id ="";
                        String status ="";
                        try {
                            id = response.getString("id");
                            status = response.getString("status");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                        if(id.equals("0")) {
                            Toast.makeText(LoanTopupActivity.this, "Error Submitting", Toast.LENGTH_SHORT).show();
                            insertApplication("PENDING",id);
                        }
                        else{
                            Toast.makeText(LoanTopupActivity.this, "Saved Successfully", Toast.LENGTH_SHORT).show();
                            insertApplication(status,id);
                        }
                        Intent i = new Intent(LoanTopupActivity.this, ViewLoansActivity.class);
                        startActivity(i);

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        insertApplication("PENDING","0");
                        Toast.makeText(LoanTopupActivity.this, "Error Connecting", Toast.LENGTH_SHORT).show();

                    }
                }) {
                    @Override
                    public Map<String, String> getHeaders() throws AuthFailureError {
                        HashMap<String, String> headers = new HashMap<String, String>();
                        return headers;
                    }
                };
                string_request.setRetryPolicy(new DefaultRetryPolicy(
                        5000,
                        DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                        DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
                queue.add(string_request);

            }
        });


    }

    public void insertApplication(String status, String status_code) {
        Calendar c = Calendar.getInstance();
        String timeStamp = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
        newApplication = new dbApplications();

        newApplication.setAppSector("Test");
        newApplication.setAppStatus(status);
        newApplication.setAppInfo("Loan Top Up");
        newApplication.setAppTenure(LoanTenure);
        newApplication.setAppAmt(Double.valueOf(LoanAmount));
        newApplication.setAppDate(timeStamp);
        newApplication.setApplcnId(status_code);
        newApplication.setApplcn_address(Address);
        newApplication.setApplcn_assets(Assets);
        newApplication.setApplcn_expense(Expense);
        newApplication.setApplcn_income(Income);
        newApplication.setApplcnType("Top Up");
        newApplication.setOps_length(LengthOp);
        userData.addApplication(newApplication);
    }

    private String showJSON(String json){
        ParseJSON pj = new ParseJSON(json);
        return pj.parseJSON();
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
            Intent myIntent = new Intent(LoanTopupActivity.this,
                    HomeActivity.class);
            startActivity(myIntent);
        }
        else  if (id == R.id.nav_new_loan) {
            Intent myIntent = new Intent(LoanTopupActivity.this,
                    LoanApplicationActivity.class);
            startActivity(myIntent);

        } else if (id == R.id.nav_all_loans) {
            Intent myIntent = new Intent(LoanTopupActivity.this,
                    ViewLoansActivity.class);
            startActivity(myIntent);

//        } else if (id == R.id.nav_mobile_money) {
//            Intent myIntent = new Intent(LoanTopupActivity.this,
//                    MobileMoneyActivity.class);
//            startActivity(myIntent);

//        } else if (id == R.id.nav_settings) {
//            Intent myIntent = new Intent(LoanTopupActivity.this,
//                    SettingsActivity.class);
//            startActivity(myIntent);

        } else if (id == R.id.nav_feedback) {
            Intent myIntent = new Intent(LoanTopupActivity.this,
                    FeedbackActivity.class);
            startActivity(myIntent);
        } else if (id == R.id.nav_securities) {
            Intent myIntent = new Intent(LoanTopupActivity.this,
                    SecuritiesActivity.class);
            startActivity(myIntent);
        }
        else if (id == R.id.nav_branches) {
            Intent myIntent = new Intent(LoanTopupActivity.this,
                    BranchesActivity.class);
            startActivity(myIntent);
        } else if (id == R.id.nav_products) {
            Intent myIntent = new Intent(LoanTopupActivity.this,
                    LoanProductActivity.class);
            startActivity(myIntent);

        }
//        }  else if (id == R.id.nav_payments) {
//            Intent myIntent = new Intent(LoanTopupActivity.this,
//                    PaymentsActivity.class);
//            startActivity(myIntent);
        else if (id == R.id.nav_send_feedback) {
            Intent myIntent = new Intent(LoanTopupActivity.this,
                    AboutActivity.class);
            startActivity(myIntent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
