package com.luuva.orderfood;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.widget.GridView;
import android.widget.Toast;

import com.luuva.adapter.CategoryAdapter;
import com.luuva.background.UserSession;
import com.luuva.model.Category;
import com.luuva.model.User;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    GridView gvCategory;
    ArrayList<Category> categories;
    CategoryAdapter adapter;
    HomeFragment homeFragment;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        UserSession session = new UserSession(getApplication());
        final User userLogin = session.getUserLogin();
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment =null;
                switch (item.getItemId()){
                    case R.id.action_home:
                        fragment = new HomeFragment();
                        break;
                    case R.id.action_noti:
                        fragment = new NotificationsFragment();
                        break;
                    case R.id.action_acc:
                        if(userLogin==null){
                            Intent intent = new Intent(MainActivity.this, MainLoginActivity.class);
                            startActivity(intent);
                        }else{
                            fragment = new UserFragment();
                        }
                        break;

                }
                return loadFragment(fragment);
            }
        });
        loadFragment(new HomeFragment());
//        addControll();
//        addEvent();

    }

    private boolean loadFragment(Fragment fragment){
        if (fragment!=null){
            getFragmentManager().beginTransaction().replace(R.id.fragment_container,fragment).commit();
            return true;
        }
        return false;
    }


}
