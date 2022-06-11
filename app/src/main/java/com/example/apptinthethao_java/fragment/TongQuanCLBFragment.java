package com.example.apptinthethao_java.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.apptinthethao_java.R;
import com.example.apptinthethao_java.activity.CauLacBoActivity;
import com.example.apptinthethao_java.activity.ChiTietCLBActivity;
import com.example.apptinthethao_java.adapter.CauLacBoAdapter;
import com.example.apptinthethao_java.adapter.ChiTietCLBAdapter;
import com.example.apptinthethao_java.adapter.TranDauAdapter;
import com.example.apptinthethao_java.api.SimpleAPI;
import com.example.apptinthethao_java.model.CauLacBo;
import com.example.apptinthethao_java.model.CauThu_DoiHinh;
import com.example.apptinthethao_java.model.TranDau;
import com.example.apptinthethao_java.util.Constants;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
        loadLatestMatch(id_clb);
        loadUpcommingMatch(id_clb);

        return view;
    }

    private void loadIMG(String id_clb){
        simpleAPI = Constants.instance();
        simpleAPI.getCLB(id_clb).enqueue(new Callback<ArrayList<CauLacBo>>() {
            @Override
            public void onResponse(Call<ArrayList<CauLacBo>> call, Response<ArrayList<CauLacBo>> response) {
                Picasso.get()
                        .load(response.body().get(0).getLink())
                        .placeholder(R.drawable.gallery)
                        .error(R.drawable.gallery)
                        .into(imgCLB);
            }

            @Override
            public void onFailure(Call<ArrayList<CauLacBo>> call, Throwable t) {
                imgCLB.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.icon_team));
            }
        });



    }

    private void loadLatestMatch(String id_clb){
        simpleAPI = Constants.instance();
        simpleAPI.getLatestMatch(id_clb).enqueue(new Callback<ArrayList<TranDau>>() {
            @Override
            public void onResponse(Call<ArrayList<TranDau>> call, Response<ArrayList<TranDau>> response) {
                ArrayList<TranDau> res = response.body();
                //Log.d("quan", "res: " + res.toString());
                TranDauAdapter matchAdapter = new TranDauAdapter(res);
                ListView listLastest = view.findViewById(R.id.listViewLastMatches);
                listLastest.setAdapter(matchAdapter);
            }

            @Override
            public void onFailure(Call<ArrayList<TranDau>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    };

    private void loadUpcommingMatch(String id_clb){
        simpleAPI = Constants.instance();
        simpleAPI.getUpcommingtMatch(id_clb).enqueue(new Callback<ArrayList<TranDau>>() {
            @Override
            public void onResponse(Call<ArrayList<TranDau>> call, Response<ArrayList<TranDau>> response) {
                ArrayList<TranDau> res = response.body();
                //Log.d("quan", "res: " + res.toString());
                TranDauAdapter matchAdapter = new TranDauAdapter(res);
                ListView listLastest = view.findViewById(R.id.listViewUpcomming);
                listLastest.setAdapter(matchAdapter);
            }

            @Override
            public void onFailure(Call<ArrayList<TranDau>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    };

    private void createChart(String id_clb){
        List<PieEntry> entries = new ArrayList<>();
        ArrayList<Integer> chartColor = new ArrayList<>();

        // create info
        Map<String, Integer> resMap = new HashMap<>();
        simpleAPI = Constants.instance();
        simpleAPI.getMatchResult(id_clb).enqueue(new Callback<ArrayList<TranDau>>() {
            @Override
            public void onResponse(Call<ArrayList<TranDau>> call, Response<ArrayList<TranDau>> response) {
                ArrayList<TranDau> resultMatch = response.body();
                for(TranDau x : resultMatch){
                    String[] res = x.getMatch_result().split(":");

                    int home, guest;
                    try{
                        home = Integer.parseInt(res[0]);
                        guest = Integer.parseInt(res[1]);
                    }
                    catch(Exception e){
                        e.printStackTrace();
                        home = 0;
                        guest = 0;
                    }
                    boolean isHome = id_clb.equals(x.getClb_home_name());
                    int resInt = 0;
                    if((home > guest && isHome) || (home < guest && !isHome)){
                        resInt = 1;
                    }
                    else if (home == guest){
                        resInt = 2;
                    }


                    // tạo map kết quả
                    if(resInt == 1){
                        if(!resMap.containsKey("Thắng")){
                            resMap.put("Thắng", 0);
                        }
                        resMap.put("Thắng", resMap.get("Thắng") + 1);
                    }
                    else if(resInt == 0){
                        if(!resMap.containsKey("Thua")){
                            resMap.put("Thua", 0);
                        }
                        resMap.put("Thua", resMap.get("Thua") + 1);
                    }
                    else{
                        if(!resMap.containsKey("Hòa")){
                            resMap.put("Hòa", 0);
                        }
                        resMap.put("Hòa", resMap.get("Hòa") + 1);
                    }

                }
                if(resMap.containsKey("Thua")){
                    entries.add(new PieEntry(resMap.get("Thua"), "Thua"));
                    chartColor.add(ContextCompat.getColor(getContext(), R.color.colorLost));
                }
                if(resMap.containsKey("Hòa")){
                    entries.add(new PieEntry(resMap.get("Hòa"), "Hòa"));
                    chartColor.add(ContextCompat.getColor(getContext(), R.color.colorTie));
                }
                if(resMap.containsKey("Thắng")){
                    entries.add(new PieEntry(resMap.get("Thắng"), "Thắng"));
                    chartColor.add(ContextCompat.getColor(getContext(), R.color.colorWin));
                }

                PieDataSet dataSet = new PieDataSet(entries, "Thống Kê Các Trận Đấu");

                dataSet.setDrawIcons(false);
                dataSet.setSliceSpace(3f);
                dataSet.setSelectionShift(5f);
                dataSet.setColors(chartColor);
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
                chart.setData(data);
                chart.animateX(700);
                chart.getDescription().setEnabled(false);
                chart.setCenterText("Thống Kê Các Trận Đấu");
                chart.getLegend().setEnabled(false);


                chart.invalidate();
            }

            @Override
            public void onFailure(Call<ArrayList<TranDau>> call, Throwable t) {
                Log.d("quan", t.getMessage());
            }
        });






    }

}