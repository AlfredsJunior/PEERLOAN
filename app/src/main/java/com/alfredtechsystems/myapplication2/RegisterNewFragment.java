package com.alfredtechsystems.myapplication2;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.untu.fis.app.untumobile.DataClasses.dbOperations;
import com.untu.fis.app.untumobile.DataClasses.dbUsers;

import org.json.JSONException;
import org.json.JSONObject;

import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;


public class RegisterNewFragment extends Fragment {

    private EditText nameEditText;
    private EditText surnameEditText;
    private EditText idEditText;
    private EditText phoneEditText;
    private EditText pswdEditText;
    private EditText cpswdEditText;
    private RadioGroup radioGroup;
    private RadioButton maleRadioButton, femaleRadioButton;
    private dbUsers newUser;
    private dbUsers oldUser;
    private String mode;
    private Button addButton;
    private long usrId;
    private dbOperations userData;
    private boolean userExist;
    static final String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    static SecureRandom rnd = new SecureRandom();
    String url = configValues.url + "/register";


    public RegisterNewFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_register_new, container, false);

        newUser = new dbUsers();
        oldUser = new dbUsers();
        nameEditText = (EditText) view.findViewById(R.id.signup_input_fname);
        surnameEditText = (EditText) view.findViewById(R.id.signup_input_lname);
        idEditText = (EditText) view.findViewById(R.id.signup_input_id);
        radioGroup = (RadioGroup) view.findViewById(R.id.gender_radio);
        maleRadioButton = (RadioButton) view.findViewById(R.id.male_radio_btn);
        femaleRadioButton = (RadioButton) view.findViewById(R.id.female_radio_btn);
        phoneEditText = (EditText) view.findViewById(R.id.signup_input_phone);
        cpswdEditText = (EditText) view.findViewById(R.id.signup_confirm_password);
        pswdEditText = (EditText) view.findViewById(R.id.signup_input_password);
        addButton = (Button) view.findViewById(R.id.btn_signup);
        userData = new dbOperations(getContext());
        userData.open();

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // find which radio button is selected
                if (checkedId == R.id.male_radio_btn) {
                    newUser.setGender("M");
                } else if (checkedId == R.id.female_radio_btn) {
                    newUser.setGender("F");
                }
            }

        });


        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((pswdEditText.getText().toString().equals(cpswdEditText.getText().toString()))&& !idEditText.getText().toString().trim().equalsIgnoreCase("") &&!pswdEditText.getText().toString().trim().equalsIgnoreCase("")
                        && !nameEditText.getText().toString().trim().equalsIgnoreCase("") &&!surnameEditText.getText().toString().trim().equalsIgnoreCase("")&&!phoneEditText.getText().toString().trim().equalsIgnoreCase("")) {
                String appDate = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(Calendar.getInstance().getTime());
                newUser.setFirstname(nameEditText.getText().toString());
                newUser.setLastname(surnameEditText.getText().toString());
                newUser.setPhNum(phoneEditText.getText().toString());
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
                try
                {
                    request.put("device_id",device_id);
                    request.put("device_name",manufacturer +" "+ model);
                    request.put("device_os",versionRelease);
                    request.put("client_fname",nameEditText.getText().toString());
                    request.put("client_lname",surnameEditText.getText().toString());
                    request.put("phone_num",phoneEditText.getText().toString());
                    request.put("id_num",idEditText.getText().toString());
                }
                catch(Exception e)
                {
                    e.printStackTrace();

                }

                userExist = userData.userCount();
                if(userExist) {
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

                            newUser.setSent(id);
                            userData.addUsers(newUser);
                            Toast.makeText(getContext(), status, Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(getContext(), StartActivity.class);
                            startActivity(i);

                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            newUser.setSent("0");
                            userData.addUsers(newUser);
                            Toast.makeText(getContext(), "my error :" + error, Toast.LENGTH_LONG).show();

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
                    Intent i = new Intent(getContext(), LoginActivity.class);
                    startActivity(i);
                }
                else {
                    Toast t = Toast.makeText(getContext(), "A User already exists please log in !", Toast.LENGTH_SHORT);
                    t.show();
                    Intent i = new Intent(getContext(), LoginActivity.class);
                    startActivity(i);
                }
                }
                else if((cpswdEditText.getText().toString().length()+idEditText.getText().toString().length()+pswdEditText.getText().toString().length())<=0){
                    idEditText.setError("ID Number Cannot Be Blank");
                    pswdEditText.setError("PIN Cannot be Blank");
                    cpswdEditText.setError("Confirm PIN Cannot be Blank");
                    nameEditText.setError("Name Cannot Be Blank");
                    surnameEditText.setError("Surname Cannot be Blank");
                    phoneEditText.setError("Phone Number Cannot be Blank");
                }
                else if(idEditText.getText().toString().trim().equalsIgnoreCase("")){
                    idEditText.setError("ID Number Cannot Be Blank");
                }
                else if(pswdEditText.getText().toString().trim().equalsIgnoreCase("")){
                    pswdEditText.setText("");
                    pswdEditText.setError("PIN Cannot be Blank");
                }
                else if(cpswdEditText.getText().toString().trim().equalsIgnoreCase("")){
                    cpswdEditText.setText("");
                    cpswdEditText.setError("Confirm PIN Cannot be Blank");
                }
                else if(nameEditText.getText().toString().trim().equalsIgnoreCase("")){
                    nameEditText.setError("Name Cannot Be Blank");
                }
                else if(surnameEditText.getText().toString().trim().equalsIgnoreCase("")){
                    surnameEditText.setError("Surname Cannot be Blank");
                }
                else if(phoneEditText.getText().toString().trim().equalsIgnoreCase("")){
                    phoneEditText.setError("Phone Number Cannot be Blank");
                }
                else if (!(pswdEditText.getText().toString().equals(cpswdEditText.getText().toString()))){
                    cpswdEditText.setText("");
                    cpswdEditText.setError("PIN Does not Match");
                    pswdEditText.setText("");
                    pswdEditText.setError("PIN Does not Match");

                }
            }
        });


        return  view;
    }


    String randomString( int len ){
        StringBuilder sb = new StringBuilder( len );
        for( int i = 0; i < len; i++ )
            sb.append( AB.charAt( rnd.nextInt(AB.length()) ) );
        return sb.toString();
    }

}
