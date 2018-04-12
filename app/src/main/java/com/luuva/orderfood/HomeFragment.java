package com.luuva.orderfood;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TabHost;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.luuva.adapter.CategoryAdapter;
import com.luuva.adapter.FoodAdapter;
import com.luuva.background.CatDao;
import com.luuva.background.FoodDao;
import com.luuva.model.Category;
import com.luuva.model.Food;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by luuva on 3/9/2018.
 */

public class HomeFragment extends Fragment {

    GridView gvCategory;
    ArrayList<Category> categories;
    ArrayList<Food> user;
    CategoryAdapter adapter;
    CatDao catDao;
    FoodDao foodDao;
    ListView lvFood,lvNearMe;
    ArrayList<Food> foods,foodsNearMe;
    FoodAdapter foodAdapter,nearMeAdapter;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home,container,false);
        gvCategory = view.findViewById(R.id.gvCategory);

        /*categories = new ArrayList<>();
        categories.add(new Category(1,"Cơm",R.drawable.rice));
        categories.add(new Category(2,"Đồ ăn",R.drawable.food));
        categories.add(new Category(3,"Thức uống",R.drawable.drinks));
        categories.add(new Category(4,"Tráng miệng",R.drawable.fruit));
        categories.add(new Category(5,"Nhà làm",R.drawable.homefood));
        categories.add(new Category(6,"Đồ ăn nhẹ",R.drawable.snacks));
        Log.d("pic 1: ",getResources().getResourceName(R.drawable.rice)+"");
        catDao = new CatDao();*/
        foodDao = new FoodDao();

        user = foodDao.getList();
        categories = new ArrayList<>();
        getList(view);
        adapter = new CategoryAdapter(getActivity(),R.layout.gv_item,categories);
        gvCategory.setAdapter(adapter);


        //tab1
        lvFood = view.findViewById(R.id.lvPro);
        foods = new ArrayList<>();

        foods.add(new Food(1,"Hủ Tiếu",12000,R.drawable.anh1+"","Vinh Nam","Huế",1,1));
        foods.add(new Food(1,"Mì Quảng",22000,R.drawable.anh2+"","Vinh Nam","Huế",1,1));
        foods.add(new Food(1,"Bún Bò",32000,R.drawable.anh3+"","Vinh Nam","Huế",1,1));
        foods.add(new Food(1,"Mì Tôm",45000,R.drawable.anh4+"","Vinh Nam","Huế",1,1));
        foods.add(new Food(1,"Cơm Gà",21000,R.drawable.anh5+"","Vinh Nam","Huế",1,1));
        foodAdapter = new FoodAdapter(getActivity(),R.layout.lv_item_food,foods);
        lvFood.setAdapter(foodAdapter);

        //tab2
        lvNearMe = view.findViewById(R.id.lvNearMe);
        foodsNearMe = new ArrayList<>();
        foodsNearMe.add(new Food(1,"Hủ Tiếu",12000,R.drawable.anh1+"","Vinh Nam","Huế",1,1));
        foodsNearMe.add(new Food(1,"Mì Quảng",22000,R.drawable.anh2+"","Vinh Nam","Huế",1,1));
        foodsNearMe.add(new Food(1,"Bún Bò",32000,R.drawable.anh3+"","Vinh Nam","Huế",1,1));
        foodsNearMe.add(new Food(1,"Mì Tôm",45000,R.drawable.anh4+"","Vinh Nam","Huế",1,1));
        foodsNearMe.add(new Food(1,"Cơm Gà",21000,R.drawable.anh5+"","Vinh Nam","Huế",1,1));

        nearMeAdapter = new FoodAdapter(getActivity(),R.layout.lv_item_food,foodsNearMe);
        lvNearMe.setAdapter(nearMeAdapter);

        TabHost tabHost = view.findViewById(R.id.tabHost);
        tabHost.setup();

        TabHost.TabSpec tab1 = tabHost.newTabSpec("t1");
        tab1.setIndicator("Đặt nhiều");
        tab1.setContent(R.id.tab1);
        tabHost.addTab(tab1);

        TabHost.TabSpec tab2 = tabHost.newTabSpec("t2");
        tab2.setIndicator("Gần tôi");
        tab2.setContent(R.id.tab2);
        tabHost.addTab(tab2);
        lvFood.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Food food = foods.get(i);
                Intent intent = new Intent(getActivity(),ProductActivity.class);
/*
                Bundle bundle = new Bundle();
                bundle.putSerializable("food",food);*/
                startActivity(intent);
            }
        });
        gvCategory.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        });
        return view;
    }
    public void getList(View v){
        final ArrayList<Category> arrCat = new ArrayList<>();
        final String url="https://lebavy1611.000webhostapp.com/getlistcat.php";
        String tag_string_req = "getlistcart";

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET,url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                if (response != null) {
                    Category objCat;

                    try {
                        for (int i=0;i<response.length();i++) {
                            JSONObject jObj = response.getJSONObject(i);
                            objCat = new Category(jObj.getInt("id"), jObj.getString("name_cat"), "R.drawable." + jObj.getString("picture"));
                            categories.add(objCat);
                        }
                        adapter.notifyDataSetChanged();
                    }

                    catch (JSONException e) {
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

        //AndroidLoginController.getInstance().addToRequestQueue(jsonArrayRequest, tag_string_req);
        RequestQueue requestQueue = Volley.newRequestQueue(v.getContext());
        requestQueue.add(jsonArrayRequest);
    }




}
