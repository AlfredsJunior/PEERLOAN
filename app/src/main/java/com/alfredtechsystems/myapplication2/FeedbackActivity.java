package com.alfredtechsystems.myapplication2;

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
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
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
import com.untu.fis.app.untumobile.DataClasses.dbFeedback;
import com.untu.fis.app.untumobile.DataClasses.dbOperations;
import com.untu.fis.app.untumobile.DataClasses.dbUsers;
import com.untu.fis.app.untumobile.ListAdapters.FeedBackListAdapter;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FeedbackActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private dbOperations data;
    private dbFeedback newFeedback;
    String url = configValues.url + "/feedback";
    EditText fd_details,fd_title;
    TextView textFeedTitle,textFeed,textStatus, txtDate;
    String fdbk_details, fdbk_title;
    Button submit;
    final Context context = this;
    private Button button;
    private List<dbFeedback> myDataset = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

//        fd_details = (EditText) findViewById(R.id.feedback_details_input);
//        submit = (Button) findViewById(R.id.btn_fd_submit);
        data = new dbOperations(this);
        data.open();
        final ListView listview = (ListView) findViewById(R.id.listview);
        myDataset = data.getAllFeedback();

        FeedBackListAdapter adapter = new FeedBackListAdapter(this, myDataset);
        listview.setAdapter(adapter);
        // Click event for single list row
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, final View view,
                                    int position, long id) {
                final dbFeedback item = (dbFeedback) parent.getItemAtPosition(position);
                // custom dialog
                final Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.feedback_dialog_details);
                textFeedTitle = (TextView) dialog.findViewById(R.id.textFeedTitle);
                textFeed = (TextView) dialog.findViewById(R.id.textFeed);
                textStatus = (TextView) dialog.findViewById(R.id.textStatus);
                txtDate = (TextView) dialog.findViewById(R.id.textDate);

                txtDate.setText("Date: "+ item.getFd_date());
                textFeedTitle.setText(item.getFd_title());
                textFeed.setText(item.getFd_details());
                textStatus.setText("STATUS: " + item.getFd_state());
                dialog.show();
            }
        });
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.feed_fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // custom dialog
                final Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.feedback_dialog);
                fd_details = (EditText) dialog.findViewById(R.id.feedback_details_input);
                fd_title = (EditText) dialog.findViewById(R.id.feedback_title_input);
                Button dialogButton = (Button) dialog.findViewById(R.id.btn_fd_submit);
                // if button is clicked, close the custom dialog
                dialogButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        fdbk_details = fd_details.getText().toString();
                        fdbk_title = fd_title.getText().toString();
                        String device_id = "";
                        List<dbUsers> users = data.getAllUsers();
                        for (dbUsers user :users) {
                            device_id = user.getApp_id();
                        }

                        JSONObject request = new JSONObject();
                        try
                        {
                            request.put("device_id",device_id);
                            request.put("feedback_str",fdbk_details);
                        }
                        catch(Exception e)
                        {
                            e.printStackTrace();

                        }
                        RequestQueue queue = Volley.newRequestQueue(FeedbackActivity.this);
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
                                    Toast.makeText(FeedbackActivity.this, "Error Submitting", Toast.LENGTH_SHORT).show();
                                    Save("PENDING",id);
                                }
                                else{
                                    Toast.makeText(FeedbackActivity.this, "Saved Successfully", Toast.LENGTH_SHORT).show();
                                    Save(status,id);
                                }
                                Intent i = new Intent(FeedbackActivity.this, FeedbackActivity.class);
                                startActivity(i);

                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Save("PENDING","0");
                                Toast.makeText(FeedbackActivity.this, "Error Connecting", Toast.LENGTH_SHORT).show();


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

    public void Save(String status, String status_code) {
        Calendar c = Calendar.getInstance();
        String timeStamp = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
        newFeedback = new dbFeedback();
        newFeedback.setFd_date(timeStamp);
        newFeedback.setFd_state("SAVED");
        newFeedback.setFd_details(fdbk_details);
        newFeedback.setFd_title(fdbk_title);
        data.addFeedback(newFeedback);
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
            Intent myIntent = new Intent(FeedbackActivity.this,
                    HomeActivity.class);
            startActivity(myIntent);
        }

        else  if (id == R.id.nav_new_loan) {
            Intent myIntent = new Intent(FeedbackActivity.this,
                    LoanApplicationActivity.class);
            startActivity(myIntent);

        } else if (id == R.id.nav_all_loans) {
            Intent myIntent = new Intent(FeedbackActivity.this,
                    ViewLoansActivity.class);
            startActivity(myIntent);

//        } else if (id == R.id.nav_mobile_money) {
//            Intent myIntent = new Intent(FeedbackActivity.this,
//                    MobileMoneyActivity.class);
//            startActivity(myIntent);

//        } else if (id == R.id.nav_settings) {
//            Intent myIntent = new Intent(FeedbackActivity.this,
//                    SettingsActivity.class);
//            startActivity(myIntent);

        } else if (id == R.id.nav_feedback) {
            Intent myIntent = new Intent(FeedbackActivity.this,
                    FeedbackActivity.class);
            startActivity(myIntent);
        } else if (id == R.id.nav_securities) {
            Intent myIntent = new Intent(FeedbackActivity.this,
                    SecuritiesActivity.class);
            startActivity(myIntent);
        }
        else if (id == R.id.nav_branches) {
            Intent myIntent = new Intent(FeedbackActivity.this,
                    BranchesActivity.class);
            startActivity(myIntent);
        } else if (id == R.id.nav_products) {
            Intent myIntent = new Intent(FeedbackActivity.this,
                    LoanProductActivity.class);
            startActivity(myIntent);
        }
//        }  else if (id == R.id.nav_payments) {
//            Intent myIntent = new Intent(LoanTopupActivity.this,
//                    PaymentsActivity.class);
//            startActivity(myIntent);
        else if (id == R.id.nav_send_feedback) {
            Intent myIntent = new Intent(FeedbackActivity.this,
                    AboutActivity.class);
            startActivity(myIntent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
