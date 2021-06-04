package com.example.apptinthethao_java.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.apptinthethao_java.R;
import com.example.apptinthethao_java.adapter.CauLacBoAdapter;
import com.example.apptinthethao_java.adapter.ChiTietCLBAdapter;
import com.example.apptinthethao_java.api.SimpleAPI;
import com.example.apptinthethao_java.model.CauThu_DoiHinh;
import com.example.apptinthethao_java.util.Constants;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChiTietCLBActivity extends AppCompatActivity {
    private ArrayList<CauThu_DoiHinh> cauThu_doiHinhArrayList;
    private ChiTietCLBAdapter chiTietCLBAdapter;
    ListView listViewDSCauThu;
    private SimpleAPI simpleAPI;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_c_l_b);
        listViewDSCauThu = findViewById(R.id.listViewDSCauThu);
        cauThu_doiHinhArrayList = new ArrayList<>();
        Intent intent = getIntent();
        String id_clb = intent.getStringExtra("clb_id");
        LoadDSCauThu(id_clb);
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
                        Toast.makeText(ChiTietCLBActivity.this, cauThu_doiHinhArrayList.get(position).getIdCauThu(), Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onFailure(Call<ArrayList<CauThu_DoiHinh>> call, Throwable t) {
                Toast.makeText(ChiTietCLBActivity.this, t.toString(), Toast.LENGTH_SHORT).show();
                t.printStackTrace();
            }
        });
    }
}