package com.alfredtechsystems.myapplication2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.untu.fis.app.untumobile.DataClasses.dbLoans;
import com.untu.fis.app.untumobile.DataClasses.dbOperations;
import com.untu.fis.app.untumobile.DataClasses.dbUsers;
import com.untu.fis.app.untumobile.ListAdapters.LoansListAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class LoanAccountsFragment extends Fragment {

    private dbOperations dboperation;
    private dbLoans newLoan;
    SwipeRefreshLayout mSwipeRefreshLayout;
    private List<dbLoans> myDataset = new ArrayList<>();
    String url = configValues.url + "/addLoans";
    FloatingActionMenu materialDesignFAM;
    FloatingActionButton floatingActionButton1;

    public LoanAccountsFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_loan_accounts, container, false);
        materialDesignFAM = (FloatingActionMenu) view.findViewById(R.id.loan_floating_action_menu);
        floatingActionButton1 = (FloatingActionButton) view.findViewById(R.id.loan_floating_action_menu_item1);
        materialDesignFAM.setClosedOnTouchOutside(true);
        floatingActionButton1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent myIntent = new Intent(getContext(),
                        AddLoanActivity.class);
                startActivity(myIntent);
            }
        });
        dboperation = new dbOperations(getContext());
        dboperation.open();
        final List<dbLoans> mylist = dboperation.getAllLoans();
        final ListView listview = (ListView) view.findViewById(R.id.listview4);
        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.refresh_accounts_overview);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshContent();
            }
            private void refreshContent(){
                checkStatus();
                myDataset = dboperation.getAllLoans();
                LoansListAdapter adapter = new LoansListAdapter(getContext(), myDataset);
                listview.setAdapter(adapter);
                mSwipeRefreshLayout.setRefreshing(false);
            }

        });
        myDataset = dboperation.getAllLoans();
        checkStatus();
        LoansListAdapter adapter = new LoansListAdapter(getContext(), myDataset);
        listview.setAdapter(adapter);
        // Click event for single list row
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, final View view,
                                    int position, long id) {
                final dbLoans item = (dbLoans) parent.getItemAtPosition(position);
                Intent intent = new Intent(getContext(),
                        ViewLoanActivity.class);
                Bundle b = new Bundle();
                b.putLong("id", item.getLn_id()); //Your id
                b.putString("loan_id",item.getLn_acc_num());
                intent.putExtras(b); //Put your id to your next Intent
                startActivity(intent);
                //Toast.makeText(ViewApplicationsActivity.this, item.getAppInfo(), Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }

    private void checkStatus() {

        dbUsers user = dboperation.getUsers(1);
       // Log.i("mambu id",user.getMambu_id());
        JSONObject request = new JSONObject();
        try
        {
            request.put("id_num",user.getMambu_id());
        }
        catch(Exception e)
        {
            e.printStackTrace();

        }

        JSONArray request_array = new JSONArray();
        request_array.put(request);
        RequestQueue queue = Volley.newRequestQueue(getContext());
        JsonArrayRequest string_request = new JsonArrayRequest(Request.Method.POST, url,request_array,new Response.Listener<JSONArray>() {

            @Override
            public void onResponse(JSONArray response) {
                try {

                    for (int i = 0; i < response.length(); i++) {

                        JSONObject loan = response.getJSONObject(i);
                        String id = loan.getString("ln_id");
                        Log.i("Loan ID",id);
                        if (id.equals("0")) {

                        } else {
                            if(dboperation.getLoan(loan.getString("ln_account"))!=null) {
                                newLoan = dboperation.getLoan(loan.getString("ln_account"));
                                newLoan.setLn_int_paid((loan.getString("ln_intpaid")));
                                newLoan.setLn_tenure((loan.getString("ln_tenure")));
                                newLoan.setLn_pr_paid((loan.getString("ln_prpaid")));
                                newLoan.setLn_int_rate((loan.getString("ln_intrate")));
                                newLoan.setLn_fee_paid((loan.getString("ln_feespaid")));
                                newLoan.setLn_balance((loan.getString("ln_balance")));
                                newLoan.setLn_amount((loan.getString("ln_amnt")));
                                newLoan.setLn_acc_num(loan.getString("ln_account"));
                                newLoan.setLn_set_num(loan.getString("ln_sett_acc"));
                                newLoan.setLn_acc_sate(loan.getString("ln_state"));
                                newLoan.setLn_amt_due(loan.getString("ln_amnt_due"));
                                dboperation.updateLoans(newLoan);
                            }
                            else {
                                newLoan = new dbLoans();
                                newLoan.setLn_int_paid((loan.getString("ln_intpaid")));
                                newLoan.setLn_tenure((loan.getString("ln_tenure")));
                                newLoan.setLn_pr_paid((loan.getString("ln_prpaid")));
                                newLoan.setLn_int_rate((loan.getString("ln_intrate")));
                                newLoan.setLn_fee_paid((loan.getString("ln_feespaid")));
                                newLoan.setLn_balance((loan.getString("ln_balance")));
                                newLoan.setLn_amount((loan.getString("ln_amnt")));
                                newLoan.setLn_acc_num(loan.getString("ln_account"));
                                newLoan.setLn_set_num(loan.getString("ln_sett_acc"));
                                newLoan.setLn_acc_sate(loan.getString("ln_state"));
                                newLoan.setLn_amt_due(loan.getString("ln_amnt_due"));
                                dboperation.addLoan(newLoan);
                            }
                        }
                    }
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
    }

}
