package com.example.apptinthethao_java.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.apptinthethao_java.R;
import com.example.apptinthethao_java.adapter.BangXepHangAdapter;
import com.example.apptinthethao_java.api.SimpleAPI;
import com.example.apptinthethao_java.model.BXH_DoiBong;
import com.example.apptinthethao_java.util.Constants;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BangXepHangActivity extends AppCompatActivity {

    private ListView listViewlBXH;
    private BangXepHangAdapter adapterBXH;
    private SimpleAPI simpleAPI;
    private ArrayList<BXH_DoiBong> bxhDoiBong;
    private Spinner bxhSpinner;
    private String yearList[]= {"2017","2018","2019","2020","2021"};
    private TextView tvbxhTrong;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bang_xep_hang);
        bxhDoiBong = new ArrayList<>();
        listViewlBXH = findViewById(R.id.listViewBXH);
        bxhSpinner=(Spinner) findViewById(R.id.spnBXH);
        tvbxhTrong=findViewById(R.id.bxhTrong);
        setEventSpiner();
    }
    @Override
    public void onResume() {
        super.onResume();
        bxhSpinner.setSelection(4);
    }
    private void setEventSpiner() {
        ArrayAdapter adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,yearList);
        adapter.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
        bxhSpinner.setAdapter(adapter);
        bxhSpinner.setSelection(4);
        bxhSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                Toast.makeText(getApplication(), "Bảng xếp hạng năm: "+yearList[arg2], Toast.LENGTH_SHORT).show();
                LoadBXH(yearList[arg2]);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        });
    }
    private void LoadBXH(String year) {
        simpleAPI = Constants.instance();
        simpleAPI.getBXH_year(year).enqueue(new Callback<ArrayList<BXH_DoiBong>>() {
            @Override
            public void onResponse(Call<ArrayList<BXH_DoiBong>> call, Response<ArrayList<BXH_DoiBong>> response) {
                bxhDoiBong=response.body();
                if(bxhDoiBong.size()==0){
                    tvbxhTrong.setText("BẢNG XẾP HẠNG TRỐNG");
                }else{
                    tvbxhTrong.setText("");
                }
                adapterBXH = new BangXepHangAdapter(BangXepHangActivity.this,bxhDoiBong);
                listViewlBXH.setAdapter(adapterBXH);
                listViewlBXH.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent intent = new Intent(BangXepHangActivity.this, ChiTietBXHActivity.class);
                        intent.putExtra("clb_name", String.valueOf(bxhDoiBong.get(position).getTenDoiBong()));
                        startActivity(intent);
                    }
                });
            }

            @Override
            public void onFailure(Call<ArrayList<BXH_DoiBong>> call, Throwable t) {
                Toast.makeText(getApplication(), "Lỗi: "+t.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private int SearchDoiBong(ArrayList<BXH_DoiBong> arr, String tenDB) {
        for (int i = 0; i < arr.size(); i++) {
            if (arr.get(i).getTenDoiBong().equalsIgnoreCase(tenDB)) {
                return i;
            }
        }
        return -1;
    }
}