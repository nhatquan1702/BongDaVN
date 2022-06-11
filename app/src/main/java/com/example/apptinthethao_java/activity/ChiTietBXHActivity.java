package com.example.apptinthethao_java.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.apptinthethao_java.R;
import com.example.apptinthethao_java.api.SimpleAPI;
import com.example.apptinthethao_java.model.BXH_DoiBong;
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

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChiTietBXHActivity extends AppCompatActivity {
    private LineChart lineChartT,lineChartH,lineChartB;
    private SimpleAPI simpleAPI;
    private String clb_name;
    public String clnbName() {
        return clb_name;
    }
    TextView titlectBXH;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_bxh);
        Intent intent = getIntent();
        clb_name = intent.getStringExtra("clb_name");
        lineChartT = (LineChart) findViewById(R.id.chartThang);
        lineChartH = (LineChart) findViewById(R.id.charHoa);
        lineChartB = (LineChart) findViewById(R.id.chartBai);
        titlectBXH=findViewById(R.id.titlectBXH);
        titlectBXH.setText(clb_name);
        LoadThang(clb_name);
        LoadHoa(clb_name);
        LoadBai(clb_name);
    }

    private void LoadThang(String clb_name) {
        simpleAPI = Constants.instance();
        simpleAPI.getBXH_clb(clb_name).enqueue(new Callback<ArrayList<BXH_DoiBong>>() {

            @Override
            public void onResponse(Call<ArrayList<BXH_DoiBong>> call, Response<ArrayList<BXH_DoiBong>> response) {
                ArrayList<BXH_DoiBong> thongke_Thang = response.body();
                ArrayList lineEntries = new ArrayList<>();
                lineEntries.add(new Entry(0, 0));
                for( int i = 0; i < thongke_Thang.size(); i++) {
                    lineEntries.add(new Entry(i+1, thongke_Thang.get(i).getThang()));
                }
                LineDataSet dataSet = new LineDataSet(lineEntries, "");
                dataSet.setLineWidth(5f);
                dataSet.setValueTextSize(11f);
                YAxis rightAxis = lineChartT.getAxisRight();
                YAxis leftAxis = lineChartT.getAxisLeft();
                rightAxis.setEnabled(false);
                leftAxis.setEnabled(false);
                XAxis xAxis = lineChartT.getXAxis();
                xAxis.setValueFormatter(new IndexAxisValueFormatter() {
                    @Override
                    public String getFormattedValue(float value) {
                        if (value == (int) value) {
                            if (value == 0)
                                return "0";
                            else if (value > thongke_Thang.size())
                                return "";
                            else
                                return String.valueOf(thongke_Thang.get((int)(value - 1)).getYear());
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
                Legend legend = lineChartT.getLegend();
                legend.setForm(Legend.LegendForm.NONE);
                legend.setTextColor(Color.WHITE);

                //hidexAxis description
                Description description = new Description();
                description.setEnabled(false);
                lineChartT.setDescription(description);


                LineData lineData = new LineData(dataSet);
                lineChartT.setData(lineData);
                lineChartT.setExtraRightOffset(40);
                lineChartT.invalidate();
            }

            @Override
            public void onFailure(Call<ArrayList<BXH_DoiBong>> call, Throwable t) {
                Log.d("error:", t.getMessage());
            }
        });
    }

    private void LoadHoa(String clb_name) {
        simpleAPI = Constants.instance();
        simpleAPI.getBXH_clb(clb_name).enqueue(new Callback<ArrayList<BXH_DoiBong>>() {

            @Override
            public void onResponse(Call<ArrayList<BXH_DoiBong>> call, Response<ArrayList<BXH_DoiBong>> response) {
                ArrayList<BXH_DoiBong> thongke_Hoa = response.body();
                ArrayList lineEntries = new ArrayList<>();
                lineEntries.add(new Entry(0, 0));
                for( int i = 0; i < thongke_Hoa.size(); i++) {
                    lineEntries.add(new Entry(i+1, thongke_Hoa.get(i).getHoa()));
                }
                LineDataSet dataSet = new LineDataSet(lineEntries, "");
                dataSet.setLineWidth(5f);
                dataSet.setValueTextSize(11f);
                YAxis rightAxis = lineChartH.getAxisRight();
                YAxis leftAxis = lineChartH.getAxisLeft();
                rightAxis.setEnabled(false);
                leftAxis.setEnabled(false);
                XAxis xAxis = lineChartH.getXAxis();
                xAxis.setValueFormatter(new IndexAxisValueFormatter() {
                    @Override
                    public String getFormattedValue(float value) {
                        if (value == (int) value) {
                            if (value == 0)
                                return "0";
                            else if (value > thongke_Hoa.size())
                                return "";
                            else
                                return String.valueOf(thongke_Hoa.get((int)(value - 1)).getYear());
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
                Legend legend = lineChartH.getLegend();
                legend.setForm(Legend.LegendForm.NONE);
                legend.setTextColor(Color.WHITE);

                //hidexAxis description
                Description description = new Description();
                description.setEnabled(false);
                lineChartH.setDescription(description);


                LineData lineData = new LineData(dataSet);
                lineChartH.setData(lineData);
                lineChartH.setExtraRightOffset(40);
                lineChartH.invalidate();
            }

            @Override
            public void onFailure(Call<ArrayList<BXH_DoiBong>> call, Throwable t) {
                Log.d("error:", t.getMessage());
            }
        });
    }

    private void LoadBai(String clb_name) {
        simpleAPI = Constants.instance();
        simpleAPI.getBXH_clb(clb_name).enqueue(new Callback<ArrayList<BXH_DoiBong>>() {

            @Override
            public void onResponse(Call<ArrayList<BXH_DoiBong>> call, Response<ArrayList<BXH_DoiBong>> response) {
                ArrayList<BXH_DoiBong> thongke_Bai = response.body();
                ArrayList lineEntries = new ArrayList<>();
                lineEntries.add(new Entry(0, 0));
                for( int i = 0; i < thongke_Bai.size(); i++) {
                    lineEntries.add(new Entry(i+1, thongke_Bai.get(i).getThua()));
                }
                LineDataSet dataSet = new LineDataSet(lineEntries, "");
                dataSet.setLineWidth(5f);
                dataSet.setValueTextSize(11f);
                YAxis rightAxis = lineChartB.getAxisRight();
                YAxis leftAxis = lineChartB.getAxisLeft();
                rightAxis.setEnabled(false);
                leftAxis.setEnabled(false);
                XAxis xAxis = lineChartB.getXAxis();
                xAxis.setValueFormatter(new IndexAxisValueFormatter() {
                    @Override
                    public String getFormattedValue(float value) {
                        if (value == (int) value) {
                            if (value == 0)
                                return "0";
                            else if (value > thongke_Bai.size())
                                return "";
                            else
                                return String.valueOf(thongke_Bai.get((int)(value - 1)).getYear());
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
                Legend legend = lineChartB.getLegend();
                legend.setForm(Legend.LegendForm.NONE);
                legend.setTextColor(Color.WHITE);

                //hidexAxis description
                Description description = new Description();
                description.setEnabled(false);
                lineChartB.setDescription(description);


                LineData lineData = new LineData(dataSet);
                lineChartB.setData(lineData);
                lineChartB.setExtraRightOffset(40);
                lineChartB.invalidate();
            }

            @Override
            public void onFailure(Call<ArrayList<BXH_DoiBong>> call, Throwable t) {
                Log.d("error:", t.getMessage());
            }
        });
    }
}