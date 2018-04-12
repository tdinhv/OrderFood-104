package com.luuva.orderfood;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainUser extends AppCompatActivity {

    String[] mobileArray = {"Lịch sử","Yêu thích","PayNow","Địa chỉ","Hóa đơn","Mời bạn bè","Góp ý","Chính sách quy định",
            "Cài đặt", "Đăng xuất"};

    ListView lv;
    ArrayList<String> data;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        ArrayAdapter adapter = new ArrayAdapter<String>(this, R.layout.activity_main_user, mobileArray);
//
//        ListView listView = (ListView) findViewById(R.id.mobile_list);
//        listView.setAdapter(adapter);
    }
}
