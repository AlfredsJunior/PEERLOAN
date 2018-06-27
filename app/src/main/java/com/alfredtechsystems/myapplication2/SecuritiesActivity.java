package com.alfredtechsystems.myapplication2;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
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
import com.untu.fis.app.untumobile.DataClasses.dbOperations;
import com.untu.fis.app.untumobile.DataClasses.dbSecurities;
import com.untu.fis.app.untumobile.DataClasses.dbUsers;
import com.untu.fis.app.untumobile.ListAdapters.SecurityListAdapter;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

public class SecuritiesActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, DatePickerDialog.OnDateSetListener {

    private dbOperations data;
    private dbSecurities newSecurity;
    String url = configValues.url + "/security";
    EditText sec_type, sec_details, sec_date;
    String security_type, security_details, security_date;
    TextView type,details,date_rq,date_sub,state;
    Button submit;
    final Context context = this;
    private Button button;
    private List<dbSecurities> myDataset = new ArrayList<>();
    Calendar calendar = Calendar.getInstance(TimeZone.getDefault());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_securities);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


//        submit = (Button) findViewById(R.id.btn_sec_submit);
        data = new dbOperations(this);
        data.open();
        final ListView listview = (ListView) findViewById(R.id.listview);
        myDataset = data.getAllSecurity();

        SecurityListAdapter adapter = new SecurityListAdapter(this, myDataset);
        listview.setAdapter(adapter);
        // Click event for single list row
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, final View view,
                                    int position, long id) {
                final dbSecurities item = (dbSecurities) parent.getItemAtPosition(position);
                final Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.securities_dialog_details);
                type = (TextView) dialog.findViewById(R.id.textView73);
                details = (TextView) dialog.findViewById(R.id.textView74);
                state = (TextView) dialog.findViewById(R.id.textView85);
                date_rq = (TextView) dialog.findViewById(R.id.textView75);
                date_sub = (TextView) dialog.findViewById(R.id.textView82);

                date_sub.setText("Submitted On: "+ item.getSec_create_date());
                date_rq.setText("Required On: "+ item.getSec_date());
                type.setText("Security Type: "+item.getSec_type());
                details.setText("Reason: "+item.getSec_details());
                state.setText("STATUS: " + item.getSec_state());
                dialog.show();
            }
        });
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.sec_fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // custom dialog
                final Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.security_dialog);
                Button dialogButton = (Button) dialog.findViewById(R.id.btn_sec_submit);

                 sec_type = (EditText) dialog.findViewById(R.id.security_type_input);
                 sec_details = (EditText) dialog.findViewById(R.id.security_reason_input);
                 sec_date = (EditText) dialog.findViewById(R.id.security_input_date);

                dialogButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        security_date = sec_date.getText().toString();
                        security_details = sec_details.getText().toString();
                        security_type = sec_type.getText().toString();
                        String device_id = "";
                        List<dbUsers> users = data.getAllUsers();
                        for (dbUsers user :users) {
                            device_id = user.getApp_id();
                        }

                        JSONObject request = new JSONObject();
                        try
                        {
                            request.put("device_id",device_id);
                            request.put("req_date",security_date);
                            request.put("security_str",security_details);
                            request.put("sec_type",security_type);
                        }
                        catch(Exception e)
                        {
                            e.printStackTrace();

                        }
                        Log.i("Request",request.toString());
                        RequestQueue queue = Volley.newRequestQueue(SecuritiesActivity.this);
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
                                    Toast.makeText(SecuritiesActivity.this, "Error Submitting", Toast.LENGTH_SHORT).show();
                                    Save("PENDING",id);
                                }
                                else{
                                    Toast.makeText(SecuritiesActivity.this, "Saved Successfully", Toast.LENGTH_SHORT).show();
                                    Save(status,id);
                                }
                                Intent i = new Intent(SecuritiesActivity.this, SecuritiesActivity.class);
                                startActivity(i);

                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                //Save("PENDING","0");
                                Log.i("Error",error.toString());
                                Toast.makeText(SecuritiesActivity.this, "Error Connecting", Toast.LENGTH_SHORT).show();

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
                        dialog.dismiss();
                    }
                });

                dialog.show();
            }
        });

    }


    public void showDatePickerDialog(View v) {

        DatePickerDialog dialog = new DatePickerDialog(this, this, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH));
        dialog.show();
    }



    public void Save(String status, String status_code) {
        Calendar c = Calendar.getInstance();
        String timeStamp = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
        newSecurity = new dbSecurities();
        newSecurity.setSec_create_date(timeStamp);
        newSecurity.setSec_state(status);
        newSecurity.setSec_details(security_details);
        newSecurity.setSec_date(security_date);
        newSecurity.setSec_type(security_type);
        data.addSecurity(newSecurity);
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
            Intent myIntent = new Intent(SecuritiesActivity.this,
                    HomeActivity.class);
            startActivity(myIntent);
        }

        if (id == R.id.nav_new_loan) {
            Intent myIntent = new Intent(SecuritiesActivity.this,
                    LoanApplicationActivity.class);
            startActivity(myIntent);

        } else if (id == R.id.nav_all_loans) {
            Intent myIntent = new Intent(SecuritiesActivity.this,
                    ViewLoansActivity.class);
            startActivity(myIntent);

//        } else if (id == R.id.nav_mobile_money) {
//            Intent myIntent = new Intent(SecuritiesActivity.this,
//                    MobileMoneyActivity.class);
//            startActivity(myIntent);

//        } else if (id == R.id.nav_settings) {
//            Intent myIntent = new Intent(SecuritiesActivity.this,
//                    SettingsActivity.class);
//            startActivity(myIntent);

        } else if (id == R.id.nav_feedback) {
            Intent myIntent = new Intent(SecuritiesActivity.this,
                    FeedbackActivity.class);
            startActivity(myIntent);
        } else if (id == R.id.nav_securities) {
            Intent myIntent = new Intent(SecuritiesActivity.this,
                    SecuritiesActivity.class);
            startActivity(myIntent);
        }
        else if (id == R.id.nav_branches) {
            Intent myIntent = new Intent(SecuritiesActivity.this,
                    BranchesActivity.class);
            startActivity(myIntent);
        } else if (id == R.id.nav_products) {
            Intent myIntent = new Intent(SecuritiesActivity.this,
                    LoanProductActivity.class);
            startActivity(myIntent);
        }

//        }  else if (id == R.id.nav_payments) {
//            Intent myIntent = new Intent(LoanTopupActivity.this,
//                    PaymentsActivity.class);
//            startActivity(myIntent);
        else if (id == R.id.nav_send_feedback) {
            Intent myIntent = new Intent(SecuritiesActivity.this,
                    AboutActivity.class);
            startActivity(myIntent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear,
                          int dayOfMonth) {
        // TODO Auto-generated method stub
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, monthOfYear);
        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        updateLabel();
    }

    private void updateLabel() {
        String myFormat = "dd-MM-yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        sec_date.setText(sdf.format(calendar.getTime()));
    }
}
