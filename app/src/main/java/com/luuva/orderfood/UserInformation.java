package com.luuva.orderfood;


import android.content.Intent;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import android.view.View;

import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.luuva.background.UserSession;
import com.luuva.model.User;

/**
 * Created by NhuongPH on 3/24/2018.
 */

public class UserInformation extends AppCompatActivity {
    TextView ttB,fullName;
    EditText edtUsername, edtName, edtEmail, edtBirthday, edtPhone, edtAddress;
    RadioButton rdNam, rdNu;
    RadioGroup rdgroup;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_information);
        edtUsername = findViewById(R.id.edtUsername);
        edtName = findViewById(R.id.edtName);
        edtEmail = findViewById(R.id.edtEmail);
        edtBirthday = findViewById(R.id.edtBirthday);
        edtPhone = findViewById(R.id.edtPhone);
        edtAddress = findViewById(R.id.edtAddress);
        rdgroup = findViewById(R.id.rdgroup);
        rdNam = findViewById(R.id.radioButtonNam);
        rdNu = findViewById(R.id.radioButtonNu);



        fullName = findViewById(R.id.fullName);

        final UserSession session = new UserSession(this.getApplication());
        final User userLogin = session.getUserLogin();

        fullName.setText(userLogin.getFullname());
        //edtName.setText("adad");
        edtUsername.setText(userLogin.getUsername());
        edtName.setText(userLogin.getFullname());
        edtEmail.setText(userLogin.getEmail());
        edtBirthday.setText(String.valueOf(userLogin.getDate_of_brith()));
        edtPhone.setText(userLogin.getPhone());
        edtAddress.setText(userLogin.getAddress());
        if(userLogin.getGender()==1){
            rdNam.setChecked(true);
        }else rdNu.setChecked(true);
        ttB = findViewById(R.id.tvBack);
        ttB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

}

