package com.luuva.orderfood;

import android.content.Intent;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.luuva.model.Cart;
import com.luuva.model.Food;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class FoodDetail extends AppCompatActivity {
    ListView lvFood;
    TextView food_name, food_price, food_description;
    ImageView food_image;
    CollapsingToolbarLayout collapsingToolbarLayout;
    FloatingActionButton btnCart;
    Spinner spinner;

    ArrayList<Cart> arrCart;

    int idSanpham=0;
    String TenChiTiet="";
    int Giachitiet=0;
    String HinhAnhChiTiet="";
    int IdShop=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_detail);
        Anhxa();
        //Init View
        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing);
        collapsingToolbarLayout.setExpandedTitleTextAppearance(R.style.ExpandedAppbar);
        collapsingToolbarLayout.setCollapsedTitleTextAppearance(R.style.CollapsedAppbar);

        GetInformation();
        CatchEventSpinner();
        //food_image.setImageResource(Integer.parseInt(food.getImage()));
        Eventbutton();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menugiohang:
                Intent intent = new Intent(getApplicationContext(),CartActivity.class);
                startActivity(intent);

        }
        return super.onOptionsItemSelected(item);
    }
    private void Eventbutton() {
        btnCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(MainActivity.listCart.size()>0){
                    int sl= Integer.parseInt(spinner.getSelectedItem().toString());
                    boolean exits = false;
                    for(int i=0;i<MainActivity.listCart.size();i++){
                        if(MainActivity.listCart.get(i).getIdFood()==idSanpham){
                            MainActivity.listCart.get(i).setQuantity(MainActivity.listCart.get(i).getQuantity()+sl);
                            if(MainActivity.listCart.get(i).getQuantity()>=20){
                                MainActivity.listCart.get(i).setQuantity(20);
                            }
                            MainActivity.listCart.get(i).setPrice(Giachitiet*MainActivity.listCart.get(i).getQuantity());
                            exits = true;
                        }
                    }
                    if(exits==false){
                        int soluong = Integer.parseInt(spinner.getSelectedItem().toString());
                        int Giamoi = soluong*Giachitiet;
                        MainActivity.listCart.add(new Cart(idSanpham,TenChiTiet,Giamoi,HinhAnhChiTiet,soluong,0));
                    }
                }else{
                    int soluong = Integer.parseInt(spinner.getSelectedItem().toString());
                    int Giamoi = soluong*Giachitiet;
                    MainActivity.listCart.add(new Cart(idSanpham,TenChiTiet,Giamoi,HinhAnhChiTiet,soluong,0));
                }
                Intent intent = new Intent(getApplicationContext(),CartActivity.class);
                startActivity(intent);
            }
        });
    }

    private void CatchEventSpinner() {
        Integer[] soluong =  new  Integer[]{1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20};
        ArrayAdapter<Integer> arrayAdapter = new ArrayAdapter<Integer>(this,android.R.layout.simple_spinner_dropdown_item,soluong);
        spinner.setAdapter(arrayAdapter);
    }
    private void GetInformation() {
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("packageFood");
        Food food = (Food) bundle.getSerializable("food");
        idSanpham= food.getId();
        TenChiTiet =food.getNameFood();
        Giachitiet = food.getPrice();
        HinhAnhChiTiet =food.getImage();
        IdShop = food.getShopId();
        food_name.setText(TenChiTiet);
        DecimalFormat decimalFormat = new DecimalFormat("####,###,###");
        food_price.setText(decimalFormat.format(Giachitiet));
    }

    private void Anhxa() {
        btnCart = (FloatingActionButton) findViewById(R.id.btnCart);
        spinner = (Spinner) findViewById(R.id.spinner);
        food_description = (TextView) findViewById(R.id.food_description);
        food_name = (TextView) findViewById(R.id.food_name);
        food_price = (TextView) findViewById(R.id.food_price);
        food_image = (ImageView) findViewById(R.id.foo_image);
    }

}
