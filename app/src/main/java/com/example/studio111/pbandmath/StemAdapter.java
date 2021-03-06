package com.example.studio111.pbandmath;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import static android.R.id.message;

/**
 * Created by rsteller on 12/2/2016.
 */

public class StemAdapter extends ArrayAdapter<Stem> {
    public StemAdapter(Context context, int resource, List<Stem> objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = ((Activity) getContext()).getLayoutInflater().inflate(R.layout.stem, parent, false);
        }

        TextView stemTextView = (TextView) convertView.findViewById(R.id.stemTextView);
        TextView idTextView = (TextView) convertView.findViewById(R.id.idTextView);
        TextView otherTextView = (TextView) convertView.findViewById(R.id.otherTextView);

        Stem stem = getItem(position);

        stemTextView.setText(stem.getStem());
        idTextView.setText(stem.getId());
        otherTextView.setText(stem.getCategory());

        return convertView;
    }
}