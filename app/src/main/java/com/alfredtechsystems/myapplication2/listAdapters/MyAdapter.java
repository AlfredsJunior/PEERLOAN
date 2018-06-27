package com.alfredtechsystems.myapplication2.listAdapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.untu.fis.app.untumobile.DataClasses.dbApplications;
import com.untu.fis.app.untumobile.R;

import java.util.List;

/**
 * Created by ashbel on 3/8/2017.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private List<dbApplications> mDataset;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView title, year, genre, status;
        public ImageView img;
        public TextView mTextView;
        public ViewHolder(View v) {
            super(v);
            img = (ImageView) v.findViewById(R.id.imageView13);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public MyAdapter(List<dbApplications> myDataset) {
        mDataset = myDataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        // create a new view
        View v =  LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_layout, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        dbApplications application = mDataset.get(position);

        if (application.getAppStatus().equals("ERROR")) {
            holder.img.setImageResource(R.drawable.pending);
        } else if (application.getAppStatus().equals("SUBMITTED")) {
            holder.img.setImageResource(R.drawable.submitted);
        } else if (application.getAppStatus().equals("ASSESMENT")) {
            holder.img.setImageResource(R.drawable.approved);
        } else if (application.getAppStatus().equals("APPROVED")) {
            holder.img.setImageResource(R.drawable.approved);
        } else if (application.getAppStatus().equals("DISBURSED")) {
            holder.img.setImageResource(R.drawable.declined);
        }
        else{
            holder.img.setImageResource(R.drawable.submitted);
        }

        holder.title.setText(String.valueOf(application.getAppAmt()));
        holder.genre.setText(application.getAppInfo());
        holder.year.setText(application.getAppDate());
        holder.status.setText(application.getAppStatus());
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}
