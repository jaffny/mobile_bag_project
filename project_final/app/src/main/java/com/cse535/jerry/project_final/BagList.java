package com.cse535.jerry.project_final;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Yanzhu on 2016/11/7.
 */

public class BagList extends ArrayAdapter<String> {
    private final Activity context;
    private final String[] title;
    private final String[] price;
    private final Integer[] imageId;
    public BagList(Activity context,
                      String[] title, String[] price,Integer[] imageId) {
        super(context, R.layout.list_onebag, title);
        this.context = context;
        this.title = title;
        this.price = price;
        this.imageId = imageId;

    }
    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView= inflater.inflate(R.layout.list_onebag, null, true);
        TextView txtTitle = (TextView) rowView.findViewById(R.id.title_10);
        TextView txtPrice = (TextView) rowView.findViewById(R.id.price_10);

        ImageView imageView = (ImageView) rowView.findViewById(R.id.img_10);
        txtTitle.setText(title[position]);
        txtPrice.setText(price[position]);

        imageView.setImageResource(imageId[position]);
        return rowView;
    }
}
