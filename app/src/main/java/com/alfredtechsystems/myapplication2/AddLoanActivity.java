package com.alfredtechsystems.myapplication2;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.untu.fis.app.untumobile.DataClasses.dbLoans;
import com.untu.fis.app.untumobile.DataClasses.dbOperations;
import com.untu.fis.app.untumobile.DataClasses.dbUsers;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class AddLoanActivity extends AppCompatActivity  {

    private dbOperations data;
    private dbLoans newLoan;
    String url = configValues.url + "/addAccount";
    EditText acc_num;
    String account;
    Button submit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_loan);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        acc_num = (EditText) findViewById(R.id.editAccountNumber);
        submit = (Button) findViewById(R.id.buttonAddLoan);
        data = new dbOperations(this);
        data.open();

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                account = acc_num.getText().toString();
                String device_id = "";
                List<dbUsers> users = data.getAllUsers();
                for (dbUsers user :users) {
                    device_id = user.getApp_id();
                }

                JSONObject request = new JSONObject();
                try
                {
                    request.put("device_id",device_id);
                    request.put("account_id",account);
                }
                catch(Exception e)
                {
                    e.printStackTrace();

                }
                RequestQueue queue = Volley.newRequestQueue(AddLoanActivity.this);
                JsonObjectRequest string_request = new JsonObjectRequest(Request.Method.POST, url,request,new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {

                        String id ="";
                        String status ="";
                        try {
                            id=response.getString("ln_id");
                            if(id.equals("0")) {
                                Toast.makeText(AddLoanActivity.this, "Error Submitting", Toast.LENGTH_SHORT).show();
                                // Save("PENDING",id);
                            }
                            else{
                                newLoan = new dbLoans();
                                newLoan.setLn_int_paid((response.getString("ln_intpaid")));
                                newLoan.setLn_tenure((response.getString("ln_tenure")));
                                newLoan.setLn_pr_paid((response.getString("ln_prpaid")));
                                newLoan.setLn_int_rate((response.getString("ln_intrate")));
                                newLoan.setLn_fee_paid((response.getString("ln_feespaid")));
                                newLoan.setLn_balance((response.getString("ln_balance")));
                                newLoan.setLn_amount((response.getString("ln_amnt")));
                                newLoan.setLn_acc_num(response.getString("ln_account"));
                                newLoan.setLn_acc_sate(response.getString("ln_sett_acc"));
                                data.addLoan(newLoan);
                                Toast.makeText(AddLoanActivity.this, "Saved Successfully", Toast.LENGTH_SHORT).show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        Intent i = new Intent(AddLoanActivity.this, ViewLoansActivity.class);
                        startActivity(i);

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //Save("PENDING","0");
                        Toast.makeText(AddLoanActivity.this, "Error Connecting", Toast.LENGTH_SHORT).show();

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


}
