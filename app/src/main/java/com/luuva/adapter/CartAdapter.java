package com.luuva.adapter;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.luuva.model.Food;
import com.luuva.orderfood.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by My PC on 3/31/2018.
 */

public class CartAdapter extends ArrayAdapter<Food> {

    Activity context;
    int resource;
    List<Food> objects;
    public CartAdapter(@NonNull Activity context, int resource, @NonNull ArrayList<Food> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.objects = objects;
    }

    public View getView(int position,View convertView,ViewGroup parent) {
        LayoutInflater inflater = this.context.getLayoutInflater();
        View row = inflater.inflate(R.layout.lv_food_cart, null);
        Food product = this.objects.get(position); // lấy đối tượng từ List

        TextView tvProductNameCart =(TextView) row.findViewById(R.id.tvProductNameCart);
        TextView tvProductPriceCart =(TextView) row.findViewById(R.id.tvProductPriceCart);

        tvProductNameCart.setText(product.getNameFood());
        tvProductPriceCart.setText(product.getPrice()+"");
        return row;
    }
}
