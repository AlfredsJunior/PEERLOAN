package com.alfredtechsystems.myapplication2;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
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


public class PersonalLoanFragment extends Fragment {
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

    public PersonalLoanFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_personal_loan, container, false);
        collateral_spinner = (MaterialBetterSpinner) view.findViewById(R.id.android_material_collateral_spinner);
        ArrayAdapter<CharSequence> adapter_collateral = ArrayAdapter.createFromResource(getContext(),R.array.collateral_array, android.R.layout.simple_spinner_item);
        collateral_spinner.setAdapter(adapter_collateral);
        // Edit Text
        lamount = (EditText) view.findViewById(R.id.input_amount);
        ltenure = (EditText) view.findViewById(R.id.input_tenure);
        length = (EditText) view.findViewById(R.id.input_length);
        other = (EditText) view.findViewById(R.id.input_description);
        address = (EditText) view.findViewById(R.id.input_place);
        income = (EditText) view.findViewById(R.id.input_sales);
        expense = (EditText) view.findViewById(R.id.input_expenses);
        assets = (EditText) view.findViewById(R.id.input_total_assets);
        submit = (Button) view.findViewById(R.id.button13);
        userData = new dbOperations(getContext());
        userData.open();

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoanAmount = lamount.getText().toString();
                LoanTenure = ltenure.getText().toString();
                LengthOp = length.getText().toString();
                OtherInfo = other.getText().toString();
                Address = address.getText().toString();
                Income = income.getText().toString();
                Expense = expense.getText().toString();
                Assets = assets.getText().toString();
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
                    RequestQueue queue = Volley.newRequestQueue(getContext());
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
                            Intent i = new Intent(getContext(), ViewApplicationsActivity.class);
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
                    request.put("time",LengthOp);
                    request.put("narration",OtherInfo);
                    request.put("latitude",latitude);
                    request.put("longitude",longitude);
                    request.put("type","Personal");
                    request.put("income",Income);
                    request.put("expense",Expense);
                    request.put("assets",Assets);
                    request.put("address",Address);
                }
                catch(Exception e)
                {
                    e.printStackTrace();

                }
                RequestQueue queue = Volley.newRequestQueue(getContext());
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
                            Toast.makeText(getContext(), "Error Submitting", Toast.LENGTH_SHORT).show();
                            insertApplication("PENDING",id);
                        }
                        else{
                            Toast.makeText(getContext(), "Saved Successfully", Toast.LENGTH_SHORT).show();
                            insertApplication(status,id);
                        }
                        Intent i = new Intent(getContext(), ViewLoansActivity.class);
                        startActivity(i);

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        insertApplication("PENDING","0");
                        Toast.makeText(getContext(), "Error Connecting", Toast.LENGTH_SHORT).show();

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

        return view;
    }

    public void insertApplication(String status, String status_code) {
        Calendar c = Calendar.getInstance();
        String timeStamp = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
        newApplication = new dbApplications();

        newApplication.setAppSector("Test");
        newApplication.setAppStatus(status);
        newApplication.setAppInfo(OtherInfo);
        newApplication.setAppTenure(LoanTenure);
        newApplication.setAppAmt(Double.valueOf(LoanAmount));
        newApplication.setAppDate(timeStamp);
        newApplication.setApplcnId(status_code);
        newApplication.setApplcn_address(Address);
        newApplication.setApplcn_assets(Assets);
        newApplication.setApplcn_expense(Expense);
        newApplication.setApplcn_income(Income);
        newApplication.setApplcnType("Personal");
        newApplication.setOps_length(LengthOp);
        userData.addApplication(newApplication);
    }

    private String showJSON(String json){
        ParseJSON pj = new ParseJSON(json);
        return pj.parseJSON();
    }

}
