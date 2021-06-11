package com.example.apptinthethao_java.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.example.apptinthethao_java.R;
import com.example.apptinthethao_java.activity.ChiTietCLBActivity;
import com.example.apptinthethao_java.activity.ChiTietCauThuActivity;
import com.example.apptinthethao_java.adapter.ChiTietCLBAdapter;
import com.example.apptinthethao_java.api.SimpleAPI;
import com.example.apptinthethao_java.model.CauLacBo;
import com.example.apptinthethao_java.model.CauThu_DoiHinh;
import com.example.apptinthethao_java.model.CauThu_Performance;
import com.example.apptinthethao_java.model.TranDau;
import com.example.apptinthethao_java.util.Constants;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PhongDoCauThuFragment extends Fragment {
    private View view;
    private LineChart lineChart;
    private SimpleAPI simpleAPI;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_phong_do_cau_thu, container, false);
        ChiTietCauThuActivity chiTietCauThuActivity = (ChiTietCauThuActivity) getActivity();
        String player_id = chiTietCauThuActivity.getPlayer_id();
        lineChart = (LineChart) view.findViewById(R.id.chartPhongDo);
        createChart(player_id);

        return view;
    }

    private void createChart(String player_id) {
        simpleAPI = Constants.instance();
        simpleAPI.getPhongDo(player_id).enqueue(new Callback<ArrayList<CauThu_Performance>>() {

            @Override
            public void onResponse(Call<ArrayList<CauThu_Performance>> call, Response<ArrayList<CauThu_Performance>> response) {
                ArrayList<CauThu_Performance> cauThu_performances = response.body();
                ArrayList lineEntries = new ArrayList<>();
                lineEntries.add(new Entry(0, 0));
                for( int i = 0; i < cauThu_performances.size(); i++) {
                    lineEntries.add(new Entry(i+1, cauThu_performances.get(i).getPhongDo()));
                }
                LineDataSet dataSet = new LineDataSet(lineEntries, "");
                dataSet.setLineWidth(5f);
                dataSet.setValueTextSize(11f);
                YAxis rightAxis = lineChart.getAxisRight();
                YAxis leftAxis = lineChart.getAxisLeft();
                rightAxis.setEnabled(false);
                leftAxis.setEnabled(false);
                XAxis xAxis = lineChart.getXAxis();
                xAxis.setValueFormatter(new IndexAxisValueFormatter() {
                    @Override
                    public String getFormattedValue(float value) {
                        if (value == (int) value) {
                            if (value == 0)
                                return "0";
                            else if (value > cauThu_performances.size())
                                return "";
                            else
                                return String.valueOf(cauThu_performances.get((int)(value - 1)).getThoiDiem().split(" ")[0]);
                        }
                        else return "";
                    }
                });
                xAxis.setTextColor(Color.parseColor("#333333"));
                xAxis.setTextSize(11f);
                xAxis.setAxisMinimum(0f);
                xAxis.setDrawAxisLine(true);
                xAxis.setDrawGridLines(false);
                xAxis.setDrawLabels(true);
                xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
//                xAxis.setLabelCount(lineEntries.size(), true);
                xAxis.setGranularity(1f);

                //Transparent legend
                Legend legend = lineChart.getLegend();
                legend.setForm(Legend.LegendForm.NONE);
                legend.setTextColor(Color.WHITE);

                //hidexAxis description
                Description description = new Description();
                description.setEnabled(false);
                lineChart.setDescription(description);


                LineData lineData = new LineData(dataSet);
                lineChart.setData(lineData);
                lineChart.setExtraRightOffset(40);
                lineChart.invalidate();
            }

            @Override
            public void onFailure(Call<ArrayList<CauThu_Performance>> call, Throwable t) {
                Log.d("error:", t.getMessage());
            }
        });
    }

}
