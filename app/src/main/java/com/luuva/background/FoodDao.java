package com.luuva.background;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.luuva.adapter.FoodAdapter;
import com.luuva.model.Category;
import com.luuva.model.Food;
import com.luuva.orderfood.AndroidLoginController;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class FoodDao {
    private ArrayList<Food> arrFood;

    public ArrayList<Food> getList(){
        arrFood = new ArrayList<Food>();
        final String url="https://lebavy1611.000webhostapp.com/get_all_products.php";
        String tag_string_req = "getlistfood";
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET,url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                if (response != null) {
                    Food objFood;
                    for (int i=0;i<response.length();i++){
                        try {
                            JSONObject jObj = response.getJSONObject(i);
                            objFood = new Food(jObj.getInt("id"),jObj.getString("name_food"),jObj.getInt("price"),"R.drawable."+jObj.getString("image"),jObj.getString("date_create"),jObj.getString("address"),jObj.getInt("shop_id"),jObj.getInt("id_cat"));
                            arrFood.add(objFood);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
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
        Log.d("length: ", arrFood.size()+"");
        return arrFood;
    }

}
