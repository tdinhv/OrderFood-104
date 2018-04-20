package com.luuva.orderfood;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.luuva.model.Cart;
import com.luuva.util.CheckConnectNetwork;
import com.luuva.util.Server;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ClientProfiles extends AppCompatActivity {
    EditText edtNameClient, edtPhoneClient, edtAddressClient;
    Button btnAccept, btnBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_profiles);
        AnhXa();
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        EvenButton();
        if (new CheckConnectNetwork().checkInternetConenction(getApplicationContext())==true) {
            EvenButton();
        } else {
            Toast.makeText(getApplicationContext(), "Bạn hãy kiểm tra lại kết nối", Toast.LENGTH_SHORT).show();
        }
    }

    private void EvenButton() {
        btnAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String nameClient = edtNameClient.getText().toString().trim();
                final String phoneClient = edtPhoneClient.getText().toString().trim();
                final String addressClient = edtAddressClient.getText().toString().trim();
                if (nameClient.length() > 0 && phoneClient.length() > 0 && addressClient.length() > 0) {
                    RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                    StringRequest stringRequest = new StringRequest(Request.Method.POST, Server.Duongdandonhang, new Response.Listener<String>() {
                        @Override
                        public void onResponse(final String madonhang) {
                            Log.d("ketqua",madonhang);
                            if(Integer.parseInt(madonhang)>0){
                                RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
                                StringRequest request = new StringRequest(Request.Method.POST, Server.Duongdanchitietdonhang, new Response.Listener<String>() {
                                    @Override
                                    public void onResponse(String response) {
                                        if(response.equals("1")){
                                            MainActivity.listCart.clear();
                                        Toast.makeText(getApplicationContext(), "Bạn đã thêm dữ liệu giỏ hàng thành công!", Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                                        startActivity(intent);
                                        Toast.makeText(getApplicationContext(), "Mời bạn tiếp tực mua hàng!", Toast.LENGTH_SHORT).show();
                                        }else{
                                            Toast.makeText(getApplicationContext(), "Dữ liệu giỏ hàng của bạn bị lỗi!", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                }, new ErrorListener() {
                                    @Override
                                    public void onErrorResponse(VolleyError error) {

                                    }
                                }){
                                    @Override
                                    protected Map<String, String> getParams() throws AuthFailureError {
                                        JSONArray jsonArray = new JSONArray();

                                        for(int i=0;i<MainActivity.listCart.size();i++){
                                            JSONObject jsonObject = new JSONObject();
                                            try {
                                                jsonObject.put("madonhang",madonhang);
                                                jsonObject.put("masanpham",MainActivity.listCart.get(i).getIdFood());
                                                jsonObject.put("tensanpham",MainActivity.listCart.get(i).getNameFood());
                                                jsonObject.put("giasanpham",MainActivity.listCart.get(i).getPrice());
                                                jsonObject.put("soluongsanpham",MainActivity.listCart.get(i).getQuantity());
                                            } catch (JSONException e) {
                                                e.printStackTrace();
                                            }
                                            jsonArray.put(jsonObject);
                                        }
                                        HashMap<String,String> hashMap = new HashMap<String, String>();
                                        hashMap.put("json",jsonArray.toString());
                                        return hashMap;
                                    }
                                };
                                queue.add(request);
                            }
                        }
                    }, new ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                        }
                    }){
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            HashMap<String,String> hashMap = new HashMap<String, String>();
                            hashMap.put("tenkhachhang",nameClient);
                            hashMap.put("sodienthoai",phoneClient);
                            hashMap.put("diachi",addressClient);
                            Log.d("ten+++++",nameClient);
                            return hashMap;
                        }
                    };
                    requestQueue.add(stringRequest);
                } else {
                    Toast.makeText(getApplicationContext(), "Bạn hãy kiểm tra lại dữ liệu", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void AnhXa() {
        edtNameClient = (EditText) findViewById(R.id.edtNameClient);
        edtPhoneClient = (EditText) findViewById(R.id.edtPhoneClient);
        edtAddressClient = (EditText) findViewById(R.id.edtAddressClient);
        btnAccept = (Button) findViewById(R.id.btnAccept);
        btnBack = (Button) findViewById(R.id.btnBack);
    }
}
