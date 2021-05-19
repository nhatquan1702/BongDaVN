package com.example.apptinthethao_java.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.apptinthethao_java.R;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;
import com.mxn.soul.flowingdrawer_core.FlowingDrawer;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    TabLayout tabLayout;
    SearchView searchView;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tabLayout = findViewById(R.id.tabLayout);

        //loadTabLayout();
        sharedPreferences = getSharedPreferences("dataLogin",MODE_PRIVATE);
        NavigationView navigationView = findViewById(R.id.nav_view);
        View headerView = navigationView.getHeaderView(0);
        TextView navUsername = headerView.findViewById(R.id.tvHoTenNa_Header);
        Button btnLoginHeader = headerView.findViewById(R.id.btnHeaderLogin);
        ImageView imgAVTHeader = headerView.findViewById(R.id.imgAVT);
        String check = sharedPreferences.getString("token", "-1");
        if(!check.equals("-1")){//đã đăng nhập
            //onBackPressed()
            navUsername.setText(sharedPreferences.getString("username", "username"));
            btnLoginHeader.setText("Đăng xuất");
            imgAVTHeader.setImageResource(R.drawable.deappool);
            btnLoginHeader.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this, MainActivity.class);
                    startActivity(intent);
                    editor = sharedPreferences.edit();

                        editor.remove("email");
                        editor.remove("pass");
                        editor.remove("token");
                        editor.remove("username");

                    editor.commit();
                    Toast.makeText(getApplicationContext(), "Đăng xuất thành công!", Toast.LENGTH_SHORT).show();
                }
            });
        }
        if(check.equals("-1")){//chưa đăng nhập
            navUsername.setText("username");
            btnLoginHeader.setText("Đăng nhập");
            imgAVTHeader.setImageResource(R.drawable.ic_launcher_foreground);
            btnLoginHeader.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                    startActivity(intent);
                }
            });
        }

        //search()
        TextView iconToolbarMenu = findViewById(R.id.iconToolbarMenu);
        FlowingDrawer drawerlayout = (FlowingDrawer) findViewById(R.id.drawerlayout);
        iconToolbarMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerlayout.toggleMenu();
            }
        });


        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.H: {
                        Toast.makeText(getApplicationContext(), "Home!", Toast.LENGTH_SHORT).show();
                        return true;

                    }
                    case R.id.CS : {
                        Toast.makeText(getApplicationContext(), "Chia sẻ!", Toast.LENGTH_SHORT).show();
                        return true;

                    }
                    case R.id.GY : {
                        Toast.makeText(getApplicationContext(), "Góp ý!", Toast.LENGTH_SHORT).show();
                        return true;

                    }
                    case R.id.CD : {
                        Toast.makeText(getApplicationContext(), "Cài đặt!", Toast.LENGTH_SHORT).show();
                        return true;

                    }
                    case R.id.HT : {
                        Toast.makeText(getApplicationContext(), "Hỗ trợ!", Toast.LENGTH_SHORT).show();
                        return true;

                    }
                    case R.id.DGUD : {
                        Toast.makeText(getApplicationContext(), "Đánh giá ứng dụng!", Toast.LENGTH_SHORT).show();
                        return true;

                    }
                    case R.id.DKSD : {
                        Toast.makeText(getApplicationContext(), "Điều khoản sử dụng!", Toast.LENGTH_SHORT).show();
                        return true;

                    }
                }
                return false;
            }
        });
    }
}