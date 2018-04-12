package com.luuva.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.luuva.model.Category;
import com.luuva.orderfood.R;

import java.util.List;

/**
 * Created by luuva on 3/9/2018.
 */

public class CategoryAdapter extends ArrayAdapter<Category> {
    Activity context;
    int resource;
    List<Category> objects;
    public CategoryAdapter(@NonNull Activity context, int resource, @NonNull List<Category> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.objects = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = this.context.getLayoutInflater();
        View row = inflater.inflate(R.layout.gv_item,null);

        ImageButton btnCategory = row.findViewById(R.id.btnCategory);
        TextView txtCategory = row.findViewById(R.id.txtCategory);

        Category category = this.objects.get(position); // lấy đối tượng từ List
        btnCategory.setImageResource(this.objects.get(position).getPic());
        txtCategory.setText(category.getName_cat());
        return row;
    }
}
