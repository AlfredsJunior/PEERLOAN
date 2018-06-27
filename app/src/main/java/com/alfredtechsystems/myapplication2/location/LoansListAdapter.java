package com.alfredtechsystems.myapplication2.location;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.untu.fis.app.untumobile.DataClasses.dbLoans;
import com.untu.fis.app.untumobile.R;

import java.util.List;

/**
 * Created by ashbel on 27/9/2017.
 */

public class LoansListAdapter extends ArrayAdapter<dbLoans> {
    private final Context context;
    //private final String[] values;
    private List<dbLoans> myDataset;
    public TextView account, balance,status,loan_amount;
    public ImageView img;

    public LoansListAdapter(Context context, List<dbLoans> myDataset) {
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
        dbLoans loans = myDataset.get(position);
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

            account.setText("A/C Number : "+loans.getLn_acc_num());
            balance.setText("Balance : $"+ loans.getLn_balance() );
            status.setText("A/C Status : " +loans.getLn_acc_sate());
            loan_amount.setText("Loan Amount : $"+String.valueOf(loans.getLn_amount()));

            if(loans.getLn_acc_sate().equals("ACTIVE")){
                status.setText("A/C Status : Active" );
            }
            else if(loans.getLn_acc_sate().equals("ACTIVE_IN_ARREARS")){
                status.setText("A/C Status : In Arrears" );
            }
            else if(loans.getLn_acc_sate().equals("CLOSED_REJECTED")){
                status.setText("A/C Status : Rejected" );
            }
            else if(loans.getLn_acc_sate().equals("CLOSED")){
                status.setText("A/C Status : Closed" );
            }

            // Change the icon for Windows and iPhone
            if (loans.getLn_acc_sate().equals("ACTIVE")) {
                img.setImageResource(R.drawable.active);
            } else if (loans.getLn_acc_sate().equals("ACTIVE_IN_ARREARS")) {
                img.setImageResource(R.drawable.declined);
            } else if (loans.getLn_acc_sate().equals("CLOSED")) {
                img.setImageResource(R.drawable.paidup);
            }
            else{
                img.setImageResource(R.drawable.submitted);
            }
            //convertView.setTag(viewHolder);
        } else {
            // View is being recycled, retrieve the viewHolder object from tag
            rowView = (View) convertView;
        }
        return rowView;
    }
}
