package com.alfredtechsystems.myapplication2.listAdapters;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.alfredtechsystems.myapplication2.dataclasses.dbSMS;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;

//import com.untu.fis.app.untumobile.DataClasses.dbSMS;
//import com.untu.fis.app.untumobile.R;

/**
 * Created by ashbel on 10/8/2017.
 */

public class CustomExpandableListAdapter extends BaseExpandableListAdapter {
    private Context context;
    private List<String> expandableListTitle;
    private HashMap<String, List<dbSMS>> expandableListDetail;
    private RecyclerView.ViewHolder mViewHolder;
    private List<dbSMS> mylist;

    public CustomExpandableListAdapter(Context context, List<String> expandableListTitle,
                                       HashMap<String, List<dbSMS>> expandableListDetail) {
        this.context = context;
        this.expandableListTitle = expandableListTitle;
        this.expandableListDetail = expandableListDetail;
    }

    @Override
    public Object getChild(int listPosition, int expandedListPosition) {
        return this.expandableListDetail.get(this.expandableListTitle.get(listPosition))
                .get(expandedListPosition);
    }

    @Override
    public long getChildId(int listPosition, int expandedListPosition) {
        return expandedListPosition;
    }

    @Override
    public View getChildView(int listPosition, final int expandedListPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {
        final dbSMS expandedListText = (dbSMS) getChild(listPosition, expandedListPosition);
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.list_item, null);
        }
        TextView expandedListTextView = (TextView) convertView
                .findViewById(R.id.expandedListItem);
        TextView expandedListTextView1 = (TextView) convertView
                .findViewById(R.id.expandedListItem1);
        TextView expandedListTextView2 = (TextView) convertView
                .findViewById(R.id.expandedListItem2);
        TextView tran_desc = (TextView) convertView
                .findViewById(R.id.tran_description);

        expandedListTextView.setText(expandedListText.getName().toUpperCase());
        NumberFormat formatter = new DecimalFormat("#0.00");
        String amnt = formatter.format(expandedListText.getAmount());
        expandedListTextView1.setText("$ "+amnt);
        tran_desc.setText(expandedListText.getTrantyp());
        SimpleDateFormat format = new SimpleDateFormat("dd-MMM-yyyy HH:mm");
        String datestr = format.format(expandedListText.getTrandate());
        expandedListTextView2.setText(datestr);
        return convertView;
    }

    @Override
    public int getChildrenCount(int listPosition) {
        return this.expandableListDetail.get(this.expandableListTitle.get(listPosition))
                .size();
    }

    @Override
    public Object getGroup(int listPosition) {
        return this.expandableListTitle.get(listPosition);
    }

    @Override
    public int getGroupCount() {
        return this.expandableListTitle.size();
    }

    @Override
    public long getGroupId(int listPosition) {
        return listPosition;
    }

    @Override
    public View getGroupView(int listPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        String listTitle = (String) getGroup(listPosition);
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.context.
                    getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.list_group, null);
        }
        TextView listTitleTextView = (TextView) convertView
                .findViewById(R.id.listTitle);
        listTitleTextView.setTypeface(null, Typeface.BOLD);
        listTitleTextView.setText(listTitle);
        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int listPosition, int expandedListPosition) {
        return true;
    }
}
