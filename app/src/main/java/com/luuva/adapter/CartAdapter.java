package com.luuva.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.luuva.model.Cart;
import com.luuva.model.Food;
import com.luuva.orderfood.CartActivity;
import com.luuva.orderfood.MainActivity;
import com.luuva.orderfood.R;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by My PC on 3/31/2018.
 */

public class CartAdapter extends BaseAdapter {
    Context context;
    ArrayList<Cart> arrayCart;
    ViewHolder viewHolder;

    public CartAdapter(Context context, ArrayList<Cart> arrayCart) {
        this.context = context;
        this.arrayCart = arrayCart;
    }

    @Override
    public int getCount() {
        return arrayCart.size();
    }

    @Override
    public Object getItem(int i) {
        return arrayCart.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    public class ViewHolder {
        public TextView card_item_name, card_item_price;
        public Button btnMinus, btnValues, btnPlus;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        if (view == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.card_layout, null);
            viewHolder.card_item_name = view.findViewById(R.id.card_item_name);
            viewHolder.card_item_price = view.findViewById(R.id.card_item_price);
            viewHolder.btnMinus = view.findViewById(R.id.btnMinus);
            viewHolder.btnValues = view.findViewById(R.id.btnValues);
            viewHolder.btnPlus = view.findViewById(R.id.btnPlus);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        final Button btnPlus = viewHolder.btnPlus;
        final Button btnValues = viewHolder.btnValues;
        final Button btnMinus = viewHolder.btnMinus;
        final TextView txtPrice = viewHolder.card_item_price;
        Cart cart = (Cart) getItem(i);
        viewHolder.card_item_name.setText(cart.getNameFood());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        txtPrice.setText(decimalFormat.format(cart.getPrice()) + " Đ");
        btnValues.setText(cart.getQuantity() + "");
        int sl = Integer.parseInt(btnValues.getText().toString());
        if (sl >= 20) {
            btnPlus.setVisibility(View.INVISIBLE);
            btnMinus.setVisibility(View.VISIBLE);
        } else if (sl <= 1) {
            btnMinus.setVisibility(View.INVISIBLE);
        } else if (sl >= 1) {
            btnMinus.setVisibility(View.VISIBLE);
            btnPlus.setVisibility(View.VISIBLE);
        }
        btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int slmoi = Integer.parseInt(btnValues.getText().toString()) + 1;
                int slht = MainActivity.listCart.get(i).getQuantity();
                int giaht = MainActivity.listCart.get(i).getPrice();
                MainActivity.listCart.get(i).setQuantity(slmoi);
                int giamoi = (giaht * slmoi) / slht;
                MainActivity.listCart.get(i).setPrice(giamoi);
                DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
                txtPrice.setText(decimalFormat.format(giamoi) + " Đ");
                CartActivity.EvenUltil();
                if (slmoi > 19) {
                    btnPlus.setVisibility(View.INVISIBLE);
                    btnMinus.setVisibility(View.VISIBLE);
                    btnValues.setText(String.valueOf(slmoi));
                } else {
                    btnPlus.setVisibility(View.VISIBLE);
                    btnMinus.setVisibility(View.VISIBLE);
                    btnValues.setText(String.valueOf(slmoi));
                }
            }
        });
        btnMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int slmoi = Integer.parseInt(btnValues.getText().toString()) - 1;
                int slht = MainActivity.listCart.get(i).getQuantity();
                int giaht = MainActivity.listCart.get(i).getPrice();
                MainActivity.listCart.get(i).setQuantity(slmoi);
                int giamoi = (giaht * slmoi) / slht;
                MainActivity.listCart.get(i).setPrice(giamoi);
                DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
                txtPrice.setText(decimalFormat.format(giamoi) + " Đ");
                CartActivity.EvenUltil();
                if (slmoi < 2) {
                    btnMinus.setVisibility(View.INVISIBLE);
                    btnPlus.setVisibility(View.VISIBLE);
                    btnValues.setText(String.valueOf(slmoi));
                } else {
                    btnMinus.setVisibility(View.VISIBLE);
                    btnPlus.setVisibility(View.VISIBLE);
                    btnValues.setText(String.valueOf(slmoi));
                }
            }
        });
        return view;
    }
}
