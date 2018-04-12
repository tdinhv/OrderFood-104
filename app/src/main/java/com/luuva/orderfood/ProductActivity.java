package com.luuva.orderfood;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.luuva.adapter.FoodAdapter;
import com.luuva.background.CatDao;
import com.luuva.background.FoodDao;
import com.luuva.model.Category;
import com.luuva.model.Food;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

@SuppressLint("Registered")
public class ProductActivity extends AppCompatActivity {
    private ListView lvFood;
    private Button btnXem;
    private Spinner spinner;
    private FoodDao foodDao;
    private CatDao catDao;
    ArrayList<Food> arrFood;
    FoodAdapter productAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        lvFood = findViewById(R.id.lv_Product);
        arrFood = new ArrayList<>();
        getList();
       /* Food food1 = new Food(1, "Hủ tiếu", 12000, R.drawable.anh1, "Vinh Nam", 1, 1);
        Food food2 = new Food(2, "Hủ tiếu1", 13000, R.drawable.anh2, "Vinh Nam", 1, 1);
        Food food3 = new Food(3, "Hủ tiếu2", 14000, R.drawable.anh3, "Vinh Nam", 1, 1);
        Food food4 = new Food(4, "Hủ tiếu3", 15000, R.drawable.anh4, "Vinh Nam", 1, 1);
        Food food5 = new Food(5, "Hủ tiếu4", 16000, R.drawable.anh5, "Vinh Nam", 1, 1);

        arrFood.add(food1);
        arrFood.add(food2);
        arrFood.add(food3);
        arrFood.add(food4);
        arrFood.add(food5);*/


        productAdapter = new FoodAdapter(ProductActivity.this, R.layout.lv_item_food, arrFood);
        lvFood.setAdapter(productAdapter);

        lvFood.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long id) {
                Food food = arrFood.get(i);
                Intent intent = new Intent(ProductActivity.this, FoodDetail.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("food", food);
                intent.putExtra("packageFood", bundle);
                startActivity(intent);
            }
        });
    }

    public void getList() {

        final String url = "https://lebavy1611.000webhostapp.com/get_all_products.php";
        String tag_string_req = "getlistfood";
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                if (response != null) {
                    Food objFood;

                    try {
                        for (int i = 0; i < response.length(); i++) {
                            JSONObject jObj = response.getJSONObject(i);
                            objFood = new Food(jObj.getInt("id"), jObj.getString("name_food"), jObj.getInt("price"), "R.drawable." + jObj.getString("image"), jObj.getString("date_create"), jObj.getString("address"), jObj.getInt("shop_id"), jObj.getInt("id_cat"));
                            arrFood.add(objFood);
                        }
                        productAdapter.notifyDataSetChanged();

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                }
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //Log.e(TAG, "Login Error: " + error.getMessage());
                //toast("Unknown Error occurred");
                //progressDialog.hide();
            }
        });

        AndroidLoginController.getInstance().addToRequestQueue(jsonArrayRequest, tag_string_req);
    }
}

