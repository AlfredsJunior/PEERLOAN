package com.alfredtechsystems.myapplication2.location;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.untu.fis.app.untumobile.DataClasses.dbTransactions;
import com.untu.fis.app.untumobile.R;

import java.util.List;

/**
 * Created by ashbel on 5/10/2017.
 */

public class TransactionListAdapter extends ArrayAdapter<dbTransactions> {
    private final Context context;
    //private final String[] values;
    private List<dbTransactions> myDataset;
    public TextView date, amount,type,balance;
    public ImageView img;

    public TransactionListAdapter(Context context, List<dbTransactions> myDataset) {
        super(context, R.layout.row_layout_account,myDataset);
        this.context = context;
        this.myDataset = myDataset;
    }
    @Override
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

        dbTransactions transactions = myDataset.get(position);
        View rowView =null;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            rowView = inflater.inflate(R.layout.row_layout_schedule, parent, false);
            type = (TextView) rowView.findViewById(R.id.textAmtDue);
            amount = (TextView) rowView.findViewById(R.id.textPaid);
            balance = (TextView) rowView.findViewById(R.id.textStatus);
            date = (TextView) rowView.findViewById(R.id.textDueDate);

            date.setText(transactions.getTran_date());
            type.setText(transactions.getTran_type());
            amount.setText(transactions.getTran_amount());
            balance.setText(transactions.getTran_balance());
            //convertView.setTag(viewHolder);
        } else {
            // View is being recycled, retrieve the viewHolder object from tag
            rowView = (View) convertView;
        }
        return rowView;
    }
}
