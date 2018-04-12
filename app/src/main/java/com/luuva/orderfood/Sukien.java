package com.luuva.orderfood;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

/**
 * Created by NhuongPH on 3/24/2018.
 */

public class Sukien extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_information);
    }
    public void onClick(View view) {
        Toast.makeText(Sukien.this, "Blog ThangCoder.com xin chào!", Toast.LENGTH_SHORT).show();
    }
}
