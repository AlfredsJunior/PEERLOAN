package com.alfredtechsystems.myapplication2;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.untu.fis.app.untumobile.DataClasses.dbOperations;
import com.untu.fis.app.untumobile.DataClasses.dbTransactions;
import com.untu.fis.app.untumobile.ListAdapters.TransactionListAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LoanTransactionFragment extends Fragment {

    SwipeRefreshLayout mSwipeRefreshLayout;
    private List<dbTransactions> myDataset = new ArrayList<>();
    private dbOperations dboperation;
    private dbTransactions newTransaction;
    String url_tran = configValues.url + "/getTransaction";

    public LoanTransactionFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_loan_transaction, container, false);

        Bundle b = this.getArguments();
        String ln_value = ""; // or other values
        if(b != null)
            ln_value = b.getString("loan_id");

        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.activity_main_swipe_refresh_layout);
        dboperation = new dbOperations(getContext());
        dboperation.open();
        final ListView listview = (ListView) view.findViewById(R.id.listview40);
        final String finalValue = ln_value;
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshContent();
            }
            private void refreshContent(){

                checkStatus(finalValue);
                myDataset = dboperation.getAllTransactions(finalValue);
                TransactionListAdapter adapter1 = new TransactionListAdapter(getContext(), myDataset);
                listview.setAdapter(adapter1);
                mSwipeRefreshLayout.setRefreshing(false);
            }

        });
       checkStatus(ln_value);
        myDataset = dboperation.getAllTransactions(ln_value);

        TransactionListAdapter adapter = new TransactionListAdapter(getContext(), myDataset);
        listview.setAdapter(adapter);
       // Log.i("Untu", String.valueOf(adapter));

        return view;
    }

    private void checkStatus(String loan_id) {

        List<dbTransactions> mylist = dboperation.getAllTransactions(loan_id);
        if (mylist.size() > 0) {
        } else {
            JSONObject request = new JSONObject();
            try {
                request.put("id", loan_id);
            } catch (Exception e) {
                e.printStackTrace();

            }

            JSONArray request_array = new JSONArray();
            request_array.put(request);

            RequestQueue queue = Volley.newRequestQueue(getContext());
            JsonArrayRequest string_request = new JsonArrayRequest(Request.Method.POST, url_tran, request_array, new Response.Listener<JSONArray>() {

                @Override
                public void onResponse(JSONArray response) {

                    // Process the JSON
                    try {
                        // Loop through the array elements
                        for (int i = 0; i < response.length(); i++) {
                            // Get current json object
                            JSONObject loan = response.getJSONObject(i);
                            String id = loan.getString("ln_account");
                            if (id.equals("0")) {
                                Toast.makeText(getContext(), "Error Submitting", Toast.LENGTH_SHORT).show();

                                // Save("PENDING",id);
                            } else {
                                if(dboperation.TransactionsCount(loan.getString("tran_id")))
                                {
                                    newTransaction = new dbTransactions();
                                    newTransaction.setTrans_id(loan.getString("tran_id"));
                                    newTransaction.setTran_balance(loan.getString("tran_balance"));
                                    newTransaction.setTran_account(loan.getString("ln_account"));
                                    newTransaction.setTran_type(loan.getString("tran_type"));
                                    newTransaction.setTran_date(loan.getString("tran_date"));
                                    newTransaction.setTran_amount(loan.getString("tran_amount"));
                                    dboperation.addTransaction(newTransaction);

                                }

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

        }
    }
}
