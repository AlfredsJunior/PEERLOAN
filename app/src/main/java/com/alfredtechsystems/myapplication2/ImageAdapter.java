package com.alfredtechsystems.myapplication2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by ashbel on 19/9/2017.
 */

public class ImageAdapter extends BaseAdapter {
    private Context mContext;

    private final String[] mobileValues;

    public ImageAdapter(Context context, String[] mobileValues) {
        this.mContext = context;
        this.mobileValues = mobileValues;
    }

    public int getCount() {
        return mobileValues.length;
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        View gridView = null;
        LayoutInflater inflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            // if it's not recycled, initialize some attributes
           /* imageView = new ImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams(85, 85));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(8, 8, 8, 8);*/
            gridView = new View(mContext);

            // get layout from mobile.xml
            gridView = inflater.inflate(R.layout.grid_menu, null);

            // set value into textview
            TextView textView = (TextView) gridView
                    .findViewById(R.id.grid_item_label);
            textView.setText(mobileValues[position]);

            // set image based on selected text
            imageView = (ImageView) gridView
                    .findViewById(R.id.grid_item_image);

            String mobile = mobileValues[position];

            if (mobile.equals("Windows")) {
                imageView.setImageResource(R.drawable.dev_one);
            } else if (mobile.equals("iOS")) {
                imageView.setImageResource(R.drawable.dev_two);
            } else if (mobile.equals("Blackberry")) {
                imageView.setImageResource(R.drawable.dev_three);
            } else if (mobile.equals("Volvo")) {
                imageView.setImageResource(R.drawable.dev_four);
            } else if (mobile.equals("Audi")) {
                imageView.setImageResource(R.drawable.dev_five);
            } else if (mobile.equals("Bmw")) {
                imageView.setImageResource(R.drawable.dev_six);
            } else if (mobile.equals("Toyota")) {
                imageView.setImageResource(R.drawable.dev_seven);
            } else if (mobile.equals("Cadillac")) {
                imageView.setImageResource(R.drawable.dev_eight);
            } else if (mobile.equals("Pontiac")) {
                imageView.setImageResource(R.drawable.dev_three);
            } else {
                imageView.setImageResource(R.drawable.dev_two);
            }
        } else {
            gridView = (View) convertView;
        }

       // imageView.setImageResource(mThumbIds[position]);
        return gridView;
    }

    // references to our images
    private Integer[] mThumbIds = {
            R.drawable.dev_one, R.drawable.dev_two,
            R.drawable.dev_three, R.drawable.dev_four,
            R.drawable.dev_five, R.drawable.dev_six,
            R.drawable.dev_seven, R.drawable.dev_eight,
            R.drawable.coins, R.drawable.hand_money,
            R.drawable.loans, R.drawable.mobile_money,
            R.drawable.new_loan, R.drawable.product,
            R.drawable.wallet, R.drawable.view_loans,
            R.drawable.loans, R.drawable.mobile_money,
            R.drawable.new_loan, R.drawable.product,
            R.drawable.wallet, R.drawable.view_loans
    };
}
