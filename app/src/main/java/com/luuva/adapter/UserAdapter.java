package com.luuva.adapter;


import android.app.Activity;
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
import com.luuva.model.User;
import com.luuva.model.UserOption;
import com.luuva.orderfood.R;

import java.util.ArrayList;
import java.util.List;

public class UserAdapter extends ArrayAdapter<UserOption> {

    Activity context;
    int resource;
    List<UserOption> objects;
    public UserAdapter(@NonNull Activity context, int resource, @NonNull List<UserOption> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.objects = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = this.context.getLayoutInflater();
        View row = inflater.inflate(R.layout.user_item,null);


        ImageView imgIcon = row.findViewById(R.id.image_icon);
        TextView name = row.findViewById(R.id.ten);


        UserOption user = this.objects.get(position);

        imgIcon.setImageResource(user.getPicture());
        name.setText(user.getName());
        return row;
    }



    /*Activity context;
    int resource;
    ArrayList<User> objects;


    public UserAdapter(Activity context, int resource, ArrayList<User> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.objects = objects;
    }

    @NonNull
    @Override

    public View getView(int position, @Nullable View convertView, @Nullable ViewGroup parent) {
        LayoutInflater inflater = this.context.getLayoutInflater();
        View row = inflater.inflate(R.layout.user_item, null);

        ImageView image_icon = row.findViewById(R.id.image_icon);
        TextView ten = row.findViewById(R.id.ten);
//        TextView Next = row.findViewById(R.id.Next);

        User user = this.objects.get(position);
        image_icon.setImageResource(user.getImage_view());
        ten.setText(user.getTen());

        return row;
    }*/
}
