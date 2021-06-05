package com.example.apptinthethao_java.fragment;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.apptinthethao_java.R;
import com.example.apptinthethao_java.activity.ChiTietCLBActivity;
import com.example.apptinthethao_java.adapter.ChiTietCLBAdapter;
import com.example.apptinthethao_java.api.SimpleAPI;
import com.example.apptinthethao_java.model.CauThu_DoiHinh;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.utils.ViewPortHandler;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class TongQuanCLBFragment extends Fragment {
    private View view;
    private ImageView imgCLB;
    private ArrayList<CauThu_DoiHinh> cauThu_doiHinhArrayList;
    private ChiTietCLBAdapter chiTietCLBAdapter;
    private ListView listViewDSCauThu;
    private SimpleAPI simpleAPI;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_tong_quan_clb, container, false);

        ChiTietCLBActivity chiTietCLBActivity = (ChiTietCLBActivity) getActivity();
        String id_clb = chiTietCLBActivity.getId_clb();
        TextView txtTenCLB = view.findViewById(R.id.txtTenCLB);
        imgCLB = view.findViewById(R.id.imgViewCLB);

        txtTenCLB.setText(id_clb);
        loadIMG(id_clb);
        createChart(id_clb);

        return view;
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
        //sample data
        entries.add(new PieEntry(5, "Thua"));
        entries.add(new PieEntry(3, "Hòa"));
        entries.add(new PieEntry(15, "Thắng"));

        PieDataSet dataSet = new PieDataSet(entries, "Thống Kê Các Trận Đấu");

        dataSet.setDrawIcons(false);
        dataSet.setSliceSpace(3f);
        dataSet.setSelectionShift(5f);
        dataSet.setColors(new int[]{R.color.colorLost,  R.color.colorTie, R.color.colorWin}, getActivity());
        dataSet.setValueTextSize(15);
        dataSet.setValueTextColor(Color.WHITE);
        dataSet.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                return String.valueOf((int) value);
            }
        });



        PieData data = new PieData(dataSet);


        PieChart chart = (PieChart) view.findViewById(R.id.chartCLB);

//        data.setValueFormatter(new PercentFormatter(chart));
//        chart.setUsePercentValues(true);
        chart.setData(data);
        chart.animateX(700);
        chart.getDescription().setEnabled(false);
        chart.setCenterText("Thống Kê Các Trận Đấu");
        chart.getLegend().setEnabled(false);


        chart.invalidate();
    }
}