package com.example.apptinthethao_java.activity;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.apptinthethao_java.R;
import com.example.apptinthethao_java.adapter.ViewPagerChiTietCLBAdapter;
import com.example.apptinthethao_java.adapter.ViewPagerLichDau;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class LichThiDauActivity extends AppCompatActivity {

    private TabLayout tabLayoutLichDau;
    private ViewPager2 viewPager2LichDau;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lich_thi_dau);

        tabLayoutLichDau = findViewById(R.id.tabLayout_lichthidau);
        viewPager2LichDau= findViewById(R.id.viewPager_lichthidau);
        LoadTablayoutAndViewPager();
    }

    private void LoadTablayoutAndViewPager() {
        ViewPagerLichDau viewPagerAdapter = new ViewPagerLichDau(this);
        viewPager2LichDau.setAdapter(viewPagerAdapter);
        String[] titleTab = {"Sắp diễn ra", "Đã diễn ra"};
        new TabLayoutMediator(tabLayoutLichDau, viewPager2LichDau, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                tab.setText(titleTab[position]);
            }
        }).attach();
    }

    public void GoToMatch(String id_match){
        Intent intent = new Intent(LichThiDauActivity.this, DienBienActivity.class);
        intent.putExtra("match_id", id_match);
        startActivity(intent);
    }
}