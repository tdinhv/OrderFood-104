package com.luuva.orderfood;

import android.content.Intent;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.luuva.model.Food;

import java.util.ArrayList;

public class FoodDetail extends AppCompatActivity {
    ListView lvFood;
    TextView food_name, food_price, food_description;
    ImageView food_image;
    CollapsingToolbarLayout collapsingToolbarLayout;
    FloatingActionButton btnCart;
    ElegantNumberButton numberButton;

    ArrayList<Food> arrFood;

    String foodId = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_detail);
        /*ArrayList<Food> arrFood = new ArrayList<>();

        Food food1 = new Food(1,"Hủ Tiếu",12000,R.drawable.anh1+"","Vinh Nam","Huế",1,1);
        Food food2 = new Food(1,"Mì Quảng",22000,R.drawable.anh2+"","Vinh Nam","Huế",1,1);
        Food food3 = new Food(1,"Bún Bò",32000,R.drawable.anh3+"","Vinh Nam","Huế",1,1);
        Food food4 = new Food(1,"Mì Tôm",45000,R.drawable.anh4+"","Vinh Nam","Huế",1,1);
        Food food5 = new Food(1,"Cơm Gà",21000,R.drawable.anh5+"","Vinh Nam","Huế",1,1);

        arrFood.add(food1);
        arrFood.add(food2);
        arrFood.add(food3);
        arrFood.add(food4);
        arrFood.add(food5);*/


        //Init View
        numberButton = (ElegantNumberButton) findViewById(R.id.number_button);
        btnCart = (FloatingActionButton) findViewById(R.id.btnCart);

        food_description = (TextView) findViewById(R.id.food_description);
        food_name = (TextView) findViewById(R.id.food_name);
        food_price = (TextView) findViewById(R.id.food_price);
        food_image = (ImageView) findViewById(R.id.foo_image);

        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing);
        collapsingToolbarLayout.setExpandedTitleTextAppearance(R.style.ExpandedAppbar);
        collapsingToolbarLayout.setCollapsedTitleTextAppearance(R.style.CollapsedAppbar);

        Intent intent =getIntent();
        Bundle bundle = intent.getBundleExtra("packageFood");
        Food food = (Food) bundle.getSerializable("food");
        food_name.setText(food.getNameFood());
        food_price.setText(food.getPrice()+"");
        //food_image.setImageResource(Integer.parseInt(food.getImage()));
        btnCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FoodDetail.this, CartActivity.class);
                startActivity(intent);
            }
        });

    }

}
