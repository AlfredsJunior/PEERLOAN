package com.alfredtechsystems.myapplication2.listAdapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.untu.fis.app.untumobile.DataClasses.dbSchedules;
import com.untu.fis.app.untumobile.R;

import java.util.List;

/**
 * Created by ashbel on 2/10/2017.
 */

public class ScheduleListAdapter extends ArrayAdapter<dbSchedules> {
    private final Context context;
    //private final String[] values;
    private List<dbSchedules> myDataset;
    public TextView date, due,paid,status;
    public ImageView img;

    public ScheduleListAdapter(Context context, List<dbSchedules> myDataset) {
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
        dbSchedules loans = myDataset.get(position);
        View rowView =null;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            rowView = inflater.inflate(R.layout.row_layout_schedule, parent, false);
            due = (TextView) rowView.findViewById(R.id.textAmtDue);
            paid = (TextView) rowView.findViewById(R.id.textPaid);
            status = (TextView) rowView.findViewById(R.id.textStatus);
            date = (TextView) rowView.findViewById(R.id.textDueDate);

            due.setText(loans.getAmount_due());
            paid.setText(loans.getAmount_paid()) ;
            status.setText(loans.getState());
            date.setText(loans.getDue_date());

            //convertView.setTag(viewHolder);
        } else {
            // View is being recycled, retrieve the viewHolder object from tag
            rowView = (View) convertView;
        }
        return rowView;
    }
}
