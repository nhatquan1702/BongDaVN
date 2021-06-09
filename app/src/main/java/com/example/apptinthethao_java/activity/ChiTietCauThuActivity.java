package com.example.apptinthethao_java.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.apptinthethao_java.R;
import com.example.apptinthethao_java.adapter.ViewPagerCauThuAdapter;
import com.example.apptinthethao_java.adapter.ViewPagerChiTietCLBAdapter;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class ChiTietCauThuActivity extends AppCompatActivity {
    private TabLayout tabLayoutCTCauThu;
    private ViewPager2 viewPager2CauThu;

    public String getPlayer_id() {
        return player_id;
    }

    private String player_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_cau_thu);
        Intent intent = getIntent();
        player_id = intent.getStringExtra("player_id");
        tabLayoutCTCauThu = findViewById(R.id.tabLayoutChiTietCauThu);
        viewPager2CauThu = findViewById(R.id.viewPagerChiTietCauThu);
        LoadTablayoutAndViewPager();
    }

    private void LoadTablayoutAndViewPager() {
        ViewPagerCauThuAdapter viewPagerAdapter = new ViewPagerCauThuAdapter(this);
        viewPager2CauThu.setAdapter(viewPagerAdapter);
        String[] titleTab = {"Tổng quan", "Phong độ"};
        new TabLayoutMediator(tabLayoutCTCauThu, viewPager2CauThu, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                tab.setText(titleTab[position]);
            }
        }).attach();
    }
}