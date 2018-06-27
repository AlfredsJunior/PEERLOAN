package com.alfredtechsystems.myapplication2.location;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.untu.fis.app.untumobile.DataClasses.dbFeedback;
import com.untu.fis.app.untumobile.R;

import java.util.List;

/**
 * Created by ashbel on 20/10/2017.
 */

public class FeedBackListAdapter extends ArrayAdapter<dbFeedback> {
    private final Context context;
    private List<dbFeedback> myDataset;
    public TextView account, balance,status,loan_amount;
    public ImageView img;

    public FeedBackListAdapter(Context context, List<dbFeedback> myDataset) {
        super(context, R.layout.row_layout_account,myDataset);
        this.context = context;
        this.myDataset = myDataset;
    }

    public int getCount() {
        return myDataset.size();
    }

    @Override
    public int getViewTypeCount() {
        if(myDataset.size()==0){
            return 1;
        }
        return myDataset.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        dbFeedback feedback = myDataset.get(position);
        View rowView =null;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            rowView = inflater.inflate(R.layout.row_layout_account, parent, false);
            account = (TextView) rowView.findViewById(R.id.textAccount);
            balance = (TextView) rowView.findViewById(R.id.textBalance);
            status = (TextView) rowView.findViewById(R.id.textStatus);
            loan_amount = (TextView) rowView.findViewById(R.id.textAmount);
            img = (ImageView) rowView.findViewById(R.id.imageView13);

            //convertView.setTag(viewHolder);
            account.setText("Title: "+feedback.getFd_title());
            balance.setText("");
            status.setText("Status: "+feedback.getFd_state());
            loan_amount.setText("Date: "+feedback.getFd_date());
        } else {
            // View is being recycled, retrieve the viewHolder object from tag
            rowView = (View) convertView;
        }
        return rowView;
    }
}
