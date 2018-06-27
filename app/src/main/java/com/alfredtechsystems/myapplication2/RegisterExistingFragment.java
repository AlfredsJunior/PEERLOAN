package com.alfredtechsystems.myapplication2;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.untu.fis.app.untumobile.DataClasses.dbLoans;
import com.untu.fis.app.untumobile.DataClasses.dbOperations;
import com.untu.fis.app.untumobile.DataClasses.dbSchedules;
import com.untu.fis.app.untumobile.DataClasses.dbUsers;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class RegisterExistingFragment extends Fragment {

    private EditText nameEditText;
    private EditText surnameEditText;
    private EditText idEditText;
    private EditText phoneEditText;
    private EditText pswdEditText;
    private EditText cpswdEditText;
    private RadioGroup radioGroup;
    private RadioButton maleRadioButton, femaleRadioButton;
    private dbUsers newUser;
    private dbSchedules newSchedule;
    private String mode;
    private dbLoans newLoan;
    private Button addButton;
    private long usrId;
    private dbOperations data;
    private boolean userExist;
    static final String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    static SecureRandom rnd = new SecureRandom();
    String url = configValues.url + "/addExisting";
    String url_schedule = configValues.url + "/getSchedule";
    String url_loans = configValues.url + "/addLoans";

    public RegisterExistingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_register_existing, container, false);

        newUser = new dbUsers();
        idEditText = (EditText) view.findViewById(R.id.signup_input_id);
        cpswdEditText = (EditText) view.findViewById(R.id.signup_confirm_password);
        pswdEditText = (EditText) view.findViewById(R.id.signup_input_password);
        addButton = (Button) view.findViewById(R.id.btn_signup);
        final TextInputLayout account = (TextInputLayout) view.findViewById(R.id.signup_input_layout_email);
        final TextInputLayout pin = (TextInputLayout) view.findViewById(R.id.signup_input_layout_password);
        TextInputLayout cpin = (TextInputLayout) view.findViewById(R.id.signup_input_confirm_password);
        data = new dbOperations(getContext());
        data.open();


        addButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if ((pswdEditText.getText().toString().equals(cpswdEditText.getText().toString()))&& !idEditText.getText().toString().trim().equalsIgnoreCase("") &&!pswdEditText.getText().toString().trim().equalsIgnoreCase("")) {
                    String appDate = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(Calendar.getInstance().getTime());
                    newUser.setIdNum(idEditText.getText().toString());
                    newUser.setPswd(pswdEditText.getText().toString());
                    newUser.setCreate_date(appDate);
                    String device_id = randomString(20);
                    newUser.setApp_id(device_id);
                    String manufacturer = Build.MANUFACTURER;
                    String model = Build.MODEL;
                    int version = Build.VERSION.SDK_INT;
                    String versionRelease = Build.VERSION.RELEASE;

                    JSONObject request = new JSONObject();
                    try {
                        request.put("device_id", device_id);
                        request.put("device_name", manufacturer + " " + model);
                        request.put("device_os", versionRelease);
                        request.put("id_num", idEditText.getText().toString());
                    } catch (Exception e) {
                        e.printStackTrace();

                    }
                    userExist = data.userCount();
                    if (userExist) {
                        RequestQueue queue = Volley.newRequestQueue(getContext());
                        JsonObjectRequest string_request = new JsonObjectRequest(Request.Method.POST, url, request, new Response.Listener<JSONObject>() {

                            @Override
                            public void onResponse(JSONObject response) {

                                String id = "";
                                String status = "";
                                try {
                                    id = response.getString("mambu_id");
                                    if (id.equals("0")) {
                                        Toast.makeText(getContext(), "Could Not Connect Please Retry", Toast.LENGTH_SHORT).show();
                                    } else {
                                        newUser.setFirstname((response.getString("f_name")));
                                        newUser.setLastname((response.getString("l_name")));
                                        newUser.setGender((response.getString("gender")));
                                        newUser.setMambu_id(((response.getString("mambu_id"))));
                                        newUser.setSent("1");
                                        data.addUsers(newUser);

                                        Toast.makeText(getContext(), "Saved Successfully", Toast.LENGTH_SHORT).show();
                                        Intent i = new Intent(getContext(), LoginActivity.class);
                                        startActivity(i);
                                    }

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                newUser.setSent("0");
                                Log.e("ERROR", error.toString());
                                Toast.makeText(getContext(), "Could Not Connect Please Retry", Toast.LENGTH_LONG).show();
                            }
                        }) {
                            @Override
                            public Map<String, String> getHeaders() throws AuthFailureError {
                                HashMap<String, String> headers = new HashMap<String, String>();
                                headers.put("Content-Type", "application/json; charset=utf-8");
                                return headers;
                            }
                        };
                        string_request.setRetryPolicy(new DefaultRetryPolicy(
                                5000,
                                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
                        queue.add(string_request);

                    } else {
                        Toast t = Toast.makeText(getContext(), "A User already exists please log in !", Toast.LENGTH_SHORT);
                        t.show();
                        Intent i = new Intent(getContext(), LoginActivity.class);
                        startActivity(i);
                    }
                }
                else if((cpswdEditText.getText().toString().length()+idEditText.getText().toString().length()+pswdEditText.getText().toString().length())<=0){
                    idEditText.setError("Account Number Cannot Be Blank");
                    pswdEditText.setError("PIN Cannot be Blank");
                    cpswdEditText.setError("Confirm PIN Cannot be Blank");
                }
                else if(idEditText.getText().toString().trim().equalsIgnoreCase("")){
                    idEditText.setError("Account Number Cannot Be Blank");
                }
                else if(pswdEditText.getText().toString().trim().equalsIgnoreCase("")){
                    pswdEditText.setText("");
                    pswdEditText.setError("PIN Cannot be Blank");
                }
                else if(cpswdEditText.getText().toString().trim().equalsIgnoreCase("")){
                    cpswdEditText.setText("");
                    cpswdEditText.setError("PIN Cannot be Blank");
                }
                else if (!(pswdEditText.getText().toString().equals(cpswdEditText.getText().toString()))){
                    cpswdEditText.setText("");
                    cpswdEditText.setError("PIN Does not Match");
                    pswdEditText.setText("");
                    pswdEditText.setError("PIN Does not Match");

                }
            }
        });

        return view;

    }


    String randomString( int len ){
        StringBuilder sb = new StringBuilder( len );
        for( int i = 0; i < len; i++ )
            sb.append( AB.charAt( rnd.nextInt(AB.length()) ) );
        return sb.toString();
    }

    public void getData( String id)
    {
        JSONObject request = new JSONObject();
        try
        {
            request.put("id",id);
        }
        catch(Exception e)
        {
            e.printStackTrace();

        }

        JSONArray request_array = new JSONArray();
        request_array.put(request);
            RequestQueue queue = Volley.newRequestQueue(getContext());
            JsonArrayRequest string_request = new JsonArrayRequest(Request.Method.POST, url_schedule,request_array,new Response.Listener<JSONArray>() {

                @Override
                public void onResponse(JSONArray response) {

                    // Process the JSON
                    try {
                        // Loop through the array elements
                        for (int i = 0; i < response.length(); i++) {
                            // Get current json object
                            JSONObject loan = response.getJSONObject(i);
                            String id = loan.getString("ln_id");
                            if (id.equals("0")) {
                                Toast.makeText(getContext(), "Error Submitting", Toast.LENGTH_SHORT).show();
                                // Save("PENDING",id);
                            } else {
                                newSchedule = new dbSchedules();
                                newSchedule.setAmount_due(loan.getString("amountDue"));
                                newSchedule.setAmount_paid(loan.getString("amountPaid"));
                                newSchedule.setDue_date(loan.getString("dueDate"));
                                newSchedule.setState(loan.getString("state"));
                                newSchedule.setLn_account(loan.getString("ln_account"));
                                data.addSchedule(newSchedule);
                            }
                        }
                        //Toast.makeText(getContext(), "Saved Successfully", Toast.LENGTH_SHORT).show();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
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

            Toast t = Toast.makeText(getContext(), "You have been added successfully !", Toast.LENGTH_SHORT);
            t.show();
            Intent i = new Intent(getContext(), StartActivity.class);
            startActivity(i);

    }

    public void getLoansData(String id)
    {
        JSONObject request = new JSONObject();
        try
        {
            request.put("id",id);
        }
        catch(Exception e)
        {
            e.printStackTrace();

        }

        JSONArray request_array = new JSONArray();
        request_array.put(request);
        RequestQueue queue = Volley.newRequestQueue(getContext());
        JsonArrayRequest string_request = new JsonArrayRequest(Request.Method.POST, url_loans,request_array,new Response.Listener<JSONArray>() {

            @Override
            public void onResponse(JSONArray response) {

                // Process the JSON
                try {
                    // Loop through the array elements
                    for (int i = 0; i < response.length(); i++) {
                        // Get current json object
                        JSONObject loan = response.getJSONObject(i);
                        String id = loan.getString("ln_id");
                        if (id.equals("0")) {
                            Toast.makeText(getContext(), "Error Submitting", Toast.LENGTH_SHORT).show();
                            // Save("PENDING",id);
                        } else {
                            newSchedule = new dbSchedules();
                            newSchedule.setAmount_due(loan.getString("amountDue"));
                            newSchedule.setAmount_paid(loan.getString("amountPaid"));
                            newSchedule.setDue_date(loan.getString("dueDate"));
                            newSchedule.setState(loan.getString("state"));
                            newSchedule.setLn_account(loan.getString("ln_account"));
                            data.addSchedule(newSchedule);
                        }
                    }
                    //Toast.makeText(getContext(), "Saved Successfully", Toast.LENGTH_SHORT).show();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
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

        Toast t = Toast.makeText(getContext(), "You have been added successfully !", Toast.LENGTH_SHORT);
        t.show();
        Intent i = new Intent(getContext(), StartActivity.class);
        startActivity(i);

    }
}
