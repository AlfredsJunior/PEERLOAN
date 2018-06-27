package com.alfredtechsystems.myapplication2.location;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.untu.fis.app.untumobile.DataClasses.dbApplications;
import com.untu.fis.app.untumobile.R;

import java.util.List;

/**
 * Created by ashbel on 20/9/2017.
 */

public class CustomListAdapter extends ArrayAdapter<dbApplications> {
    private final Context context;
    //private final String[] values;
    private List<dbApplications> myDataset;
    public TextView amount, date, status;
    public ImageView img;

    public CustomListAdapter(Context context, List<dbApplications> myDataset) {
        super(context, R.layout.row_layout,myDataset);
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
        dbApplications application = myDataset.get(position);
        View rowView =null;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
             rowView = inflater.inflate(R.layout.row_layout, parent, false);
             amount = (TextView) rowView.findViewById(R.id.textAppAmnt);
             date = (TextView) rowView.findViewById(R.id.textAppDate);
             status = (TextView) rowView.findViewById(R.id.textAppStatus);
             img = (ImageView) rowView.findViewById(R.id.imageView13);

            amount.setText("Loan Amount : "+"$" + String.format("%.2f",application.getAppAmt()));
            date.setText("Date : "+application.getAppDate());
            status.setText("Status : "+application.getAppStatus());

            // Change the icon for Windows and iPhone
            if (application.getAppStatus().equals("ERROR")) {
                img.setImageResource(R.drawable.pending);
            } else if (application.getAppStatus().equals("SUBMITTED")) {
                img.setImageResource(R.drawable.submitted);
            } else if (application.getAppStatus().equals("ASSESMENT")) {
                img.setImageResource(R.drawable.approved);
            } else if (application.getAppStatus().equals("APPROVED")) {
                img.setImageResource(R.drawable.approved);
            } else if (application.getAppStatus().equals("DISBURSED")) {
                img.setImageResource(R.drawable.declined);
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
