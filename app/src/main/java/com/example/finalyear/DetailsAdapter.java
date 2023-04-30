package com.example.finalyear;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.util.List;

public class DetailsAdapter extends ArrayAdapter<Details> implements ListAdapter {

    private Context context;
    private List<Details> detailsList;

    public DetailsAdapter(Context context, List<Details> detailsList) {
        super(context, 0, detailsList);
        this.context = context;
        this.detailsList = detailsList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.details_row, parent, false);
        }
        Details currentDetails = getItem(position);
        TextView name = convertView.findViewById(R.id.title);
        TextView status = convertView.findViewById(R.id.status);
        TextView width = convertView.findViewById(R.id.width);

        name.setText(currentDetails.getName());
        status.setText("Current Status:" +currentDetails.getStatus());
        width.setText("Width:" + currentDetails.getWidth() + " Meters");


        return convertView;
    }
}

