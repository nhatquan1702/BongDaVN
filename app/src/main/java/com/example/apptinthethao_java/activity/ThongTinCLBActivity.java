package com.example.apptinthethao_java.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;

import com.example.apptinthethao_java.R;
import com.example.apptinthethao_java.adapter.ChiTietCLBAdapter;
import com.example.apptinthethao_java.model.CauThu_DoiHinh;
import com.example.apptinthethao_java.util.Constants;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.example.apptinthethao_java.api.SimpleAPI;
import com.squareup.picasso.Picasso;

import android.content.Intent;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ThongTinCLBActivity extends AppCompatActivity {
    ImageView imgCLB;
    private ArrayList<CauThu_DoiHinh> cauThu_doiHinhArrayList;
    private ChiTietCLBAdapter chiTietCLBAdapter;
    ListView listViewDSCauThu;
    private SimpleAPI simpleAPI;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_tin_c_l_b);
        NestedScrollView view = (NestedScrollView) findViewById(R.id.nestedScroll);
        view.setNestedScrollingEnabled(true);

        Intent intent = getIntent();
        String id_clb = intent.getStringExtra("clb_id");
        TextView txtTenCLB = findViewById(R.id.txtTenCLB);
        imgCLB = findViewById(R.id.imgViewCLB);
        cauThu_doiHinhArrayList = new ArrayList<>();
        listViewDSCauThu = findViewById(R.id.listViewDSCauThu);
        txtTenCLB.setText(id_clb);
        loadIMG(id_clb);
        createChart(id_clb);
        LoadDSCauThu(id_clb);
    }

    private void loadIMG(String id_clb){
        Picasso.get()
                .load("https://cdn-img.thethao247.vn/origin_865x0/storage/files/anhtuan/2021/06/05/syria-dat-mot-chan-den-nguong-cua-thien-duong-o-vong-loai-world-cup-2022-50945.jpg")
                .placeholder(R.drawable.gallery)
                .error(R.drawable.gallery)
                .into(imgCLB);
    }

    private void createChart(String id_clb){
        List<PieEntry> entries = new ArrayList<>();
        entries.add(new PieEntry(5, "Thắng"));
        entries.add(new PieEntry(5, "Hòa"));
        entries.add(new PieEntry(5, "Thua"));

        PieDataSet dataSet = new PieDataSet(entries, "Thống Kê Các Trận Đấu");

        dataSet.setDrawIcons(false);
        dataSet.setSliceSpace(3f);
        dataSet.setSelectionShift(5f);
        dataSet.setColors(new int[]{R.color.colorWin , R.color.colorTie, R.color.colorLost}, ThongTinCLBActivity.this);
        dataSet.setValueTextSize(15);
        dataSet.setValueTextColor(Color.WHITE);



        PieData data = new PieData(dataSet);


        PieChart chart = (PieChart) findViewById(R.id.chartCLB);

//        data.setValueFormatter(new PercentFormatter(chart));
//        chart.setUsePercentValues(true);
        chart.setData(data);
        chart.animateX(700);
        chart.getDescription().setEnabled(false);
        chart.setCenterText("Thống Kê Các Trận Đấu");
        chart.getLegend().setEnabled(false);


        chart.invalidate();
    }

    private void LoadDSCauThu(String id) {
        simpleAPI = Constants.instance();
        simpleAPI.getListCauThu(id).enqueue(new Callback<ArrayList<CauThu_DoiHinh>>() {
            @Override
            public void onResponse(Call<ArrayList<CauThu_DoiHinh>> call, Response<ArrayList<CauThu_DoiHinh>> response) {
                cauThu_doiHinhArrayList = response.body();

                chiTietCLBAdapter = new ChiTietCLBAdapter(cauThu_doiHinhArrayList);
                listViewDSCauThu.setAdapter(chiTietCLBAdapter);
                listViewDSCauThu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Toast.makeText(ThongTinCLBActivity.this, cauThu_doiHinhArrayList.get(position).getIdCauThu(), Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onFailure(Call<ArrayList<CauThu_DoiHinh>> call, Throwable t) {
                Toast.makeText(ThongTinCLBActivity.this, t.toString(), Toast.LENGTH_SHORT).show();
                t.printStackTrace();
            }
        });
    }
}


