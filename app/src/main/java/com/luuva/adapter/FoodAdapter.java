package com.luuva.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.luuva.model.Food;
import com.luuva.orderfood.R;

import java.util.List;

/**
 * Created by luuva on 3/24/2018.
 */

public class FoodAdapter extends ArrayAdapter<Food> {

    Activity context;
    int resource;
    List<Food> objects;
    public FoodAdapter(@NonNull Activity context, int resource, @NonNull List<Food> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.objects = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = this.context.getLayoutInflater();
        View row = inflater.inflate(R.layout.lv_item_food,null);


        ImageView imgFood = row.findViewById(R.id.imgFood);
        TextView txtNameFood = row.findViewById(R.id.txtNameFood);
        TextView txtAddress = row.findViewById(R.id.txtAddress);
        TextView txtDistance = row.findViewById(R.id.txtDistance);
        TextView txtPrice = row.findViewById(R.id.txtPrice);
        TextView txtTimeDelivery = row.findViewById(R.id.txtTimeDelivery);

        Food food = this.objects.get(position);

        imgFood.setImageResource(R.drawable.food);
        txtNameFood.setText(food.getNameFood());
        txtAddress.setText(food.getAddress());
        txtDistance.setText(">1km");  //Hiện tại chua có dữ liệu để set
        txtPrice.setText(food.getPrice()+"");
        txtTimeDelivery.setText("30'"); //Hiện tại chua có dữ liệu để set
        return row;
    }
}
