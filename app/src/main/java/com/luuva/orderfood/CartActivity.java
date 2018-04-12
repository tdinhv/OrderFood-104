package com.luuva.orderfood;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.luuva.adapter.CartAdapter;
import com.luuva.adapter.FoodAdapter;
import com.luuva.model.Food;

import java.util.ArrayList;

public class CartActivity extends AppCompatActivity {

    ListView lvFood;
    TextView txtTotalPrice;
    Button btnPlaceOrder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        lvFood = findViewById(R.id.listCart);
        ArrayList<Food> arrFood = new ArrayList<>();

        Food food1 = new Food(1,"Hủ Tiếu",12000,R.drawable.anh1+"","Vinh Nam","Huế",1,1);
        Food food2 = new Food(1,"Mì Quảng",22000,R.drawable.anh2+"","Vinh Nam","Huế",1,1);
        Food food3 = new Food(1,"Bún Bò",32000,R.drawable.anh3+"","Vinh Nam","Huế",1,1);
        Food food4 = new Food(1,"Mì Tôm",45000,R.drawable.anh4+"","Vinh Nam","Huế",1,1);
        Food food5 = new Food(1,"Cơm Gà",21000,R.drawable.anh5+"","Vinh Nam","Huế",1,1);

        arrFood.add(food1);
        arrFood.add(food2);
        arrFood.add(food3);
        arrFood.add(food4);
        arrFood.add(food5);
        //Init

        txtTotalPrice = (TextView) findViewById(R.id.txtTotalPrice);
        btnPlaceOrder = (Button) findViewById(R.id.btnPlaceOrder);
        CartAdapter cartAdapter = new CartAdapter(CartActivity.this, R.layout.card_layout, arrFood);
        lvFood.setAdapter(cartAdapter);

    }
}
