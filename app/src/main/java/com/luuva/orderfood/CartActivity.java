package com.luuva.orderfood;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.luuva.adapter.CartAdapter;
import com.luuva.adapter.FoodAdapter;
import com.luuva.model.Cart;
import com.luuva.model.Food;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class CartActivity extends AppCompatActivity {

    ListView lvCart;
    static TextView txtTotalPrice;
    TextView txtThongBao;
    Button btnPlaceOrder, btnTiepTucMua;
    android.support.v7.widget.Toolbar toolbargiohang;
    CartAdapter cartAdapter;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        AnhXa();
        ActionToolBar();
        CheckData();
        EvenUltil();
        CatchOnItemListView();
        EventButton();
    }

    private void EventButton() {
        btnTiepTucMua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
        btnPlaceOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (MainActivity.listCart.size() > 0) {
                    Log.d("size+++++",MainActivity.listCart.size()+"");
                    Intent intent = new Intent(getApplicationContext(),ClientProfiles.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "Giỏ hàng của bạn chưa có hàng để đặt!", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    private void CatchOnItemListView() {
        lvCart.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int positon, long l) {
                AlertDialog.Builder builder = new AlertDialog.Builder(CartActivity.this);
                builder.setTitle("Xác nhận xóa sản phẩm");
                builder.setMessage("Bạn có chắc muốn xóa sản phẩm này?");
                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (MainActivity.listCart.size() <= 0) {
                            txtThongBao.setVisibility(View.VISIBLE);
                        } else {
                            MainActivity.listCart.remove(positon);
                            cartAdapter.notifyDataSetChanged();
                            EvenUltil();
                            if (MainActivity.listCart.size() <= 0) {
                                txtThongBao.setVisibility(View.VISIBLE);
                            } else {
                                txtThongBao.setVisibility(View.INVISIBLE);
                                cartAdapter.notifyDataSetChanged();
                                EvenUltil();
                            }
                        }
                    }
                });
                builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        cartAdapter.notifyDataSetChanged();
                        EvenUltil();
                    }
                });
                builder.show();
                return true;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menugiohang:
                Intent intent = new Intent(getApplicationContext(), CartActivity.class);
                startActivity(intent);

        }
        return super.onOptionsItemSelected(item);
    }

    public static void EvenUltil() {
        int tongTien = 0;
        for (int i = 0; i < MainActivity.listCart.size(); i++) {
            tongTien += MainActivity.listCart.get(i).getPrice();
        }
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        txtTotalPrice.setText(decimalFormat.format(tongTien) + " Đ");
    }

    private void ActionToolBar() {
        setSupportActionBar(toolbargiohang);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbargiohang.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });
    }

    private void CheckData() {
        if (MainActivity.listCart.size() <= 0) {
            cartAdapter.notifyDataSetChanged();
            txtThongBao.setVisibility(View.VISIBLE);
            lvCart.setVisibility(View.INVISIBLE);
        } else {
            cartAdapter.notifyDataSetChanged();
            txtThongBao.setVisibility(View.INVISIBLE);
            lvCart.setVisibility(View.VISIBLE);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void AnhXa() {
        lvCart = (ListView) findViewById(R.id.listCart);
        txtThongBao = (TextView) findViewById(R.id.txtThongBao);
        txtTotalPrice = (TextView) findViewById(R.id.txtTotalPrice);
        btnPlaceOrder = (Button) findViewById(R.id.btnPlaceOrder);
        btnTiepTucMua = (Button) findViewById(R.id.btnTiepTucMua);
        toolbargiohang = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbargiohang);
        cartAdapter = new CartAdapter(CartActivity.this, MainActivity.listCart);
        lvCart.setAdapter(cartAdapter);
    }
}
