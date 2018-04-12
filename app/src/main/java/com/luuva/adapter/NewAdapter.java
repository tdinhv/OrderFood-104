package com.luuva.adapter;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.luuva.model.New;
import com.luuva.orderfood.R;

import java.util.List;

/**
 * Created by My PC on 3/10/2018.
 */

public class NewAdapter extends ArrayAdapter<New> {
    Activity context;
    int resource;
    List<New> objects;

    public NewAdapter(@NonNull Activity context, int resource, @NonNull List<New> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.objects = objects;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = this.context.getLayoutInflater();
        View view = inflater.inflate(R.layout.list_new, null);
        New news = this.objects.get(position);
        ImageView imageView = (ImageView) view.findViewById(R.id.imageViewProduct);
        imageView.setImageResource(news.getColor());

        TextView textViewName = (TextView) view.findViewById(R.id.tvNewName);
        textViewName.setText(news.getNewName());

        TextView textViewPrice = (TextView) view.findViewById(R.id.tvPreview);
        textViewPrice.setText(news.getPreview());

        return view;
    }
}
