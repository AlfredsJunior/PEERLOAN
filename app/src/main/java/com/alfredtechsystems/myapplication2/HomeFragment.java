package com.alfredtechsystems.myapplication2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class HomeFragment  extends Fragment {

    public HomeFragment() {
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
        View myView = inflater.inflate(R.layout.fragment_home, container, false);
        Button login = (Button) myView.findViewById(R.id.buttonLogin);
        Button register = (Button) myView.findViewById(R.id.buttonRegister);

        login.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Start NewActivity.class
                Intent myIntent = new Intent(getActivity(),
                        LoginActivity.class);
                startActivity(myIntent);
            }

        });
        register.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Start NewActivity.class
                Intent myIntent = new Intent(getActivity(),
                        RegisterActivity.class);
                startActivity(myIntent);
            }

        });

        return myView;
    }

}
