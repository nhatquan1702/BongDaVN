package com.example.apptinthethao_java.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.apptinthethao_java.R;
import com.example.apptinthethao_java.adapter.ViewPagerAdapter;
import com.example.apptinthethao_java.adapter.ViewPagerChiTietCLBAdapter;
import com.example.apptinthethao_java.fragment.ListCauThuFragment;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class ChiTietCLBActivity extends AppCompatActivity {
    private TabLayout tabLayoutCTCLB;
    private ViewPager2 viewPager2CTCLB;
    private TextView tvTenCLBCTCLB;

    public String getId_clb() {
        return id_clb;
    }

    private String id_clb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_clb);
        tvTenCLBCTCLB = findViewById(R.id.tvTenCLB);
        Intent intent = getIntent();
        id_clb = intent.getStringExtra("clb_id");
        tvTenCLBCTCLB.setText(id_clb);
        tabLayoutCTCLB = findViewById(R.id.tabLayoutChiTietCLB);
        viewPager2CTCLB = findViewById(R.id.viewPagerChiTietCLB);
        LoadTablayoutAndViewPager();
    }

    private void LoadTablayoutAndViewPager() {
        ViewPagerChiTietCLBAdapter viewPagerAdapter = new ViewPagerChiTietCLBAdapter(this);
        viewPager2CTCLB.setAdapter(viewPagerAdapter);
        String[] titleTab = {"Cầu thủ", "Tổng quan"};
        new TabLayoutMediator(tabLayoutCTCLB, viewPager2CTCLB, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                tab.setText(titleTab[position]);
            }
        }).attach();
    }
}