package com.example.apptinthethao_java;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.apptinthethao_java.adapter.ListViewTranSapDauAdapter;
import com.example.apptinthethao_java.api.SimpleAPI;
import com.example.apptinthethao_java.model.TranDauSapDienRa;
import com.example.apptinthethao_java.util.Constants;
import com.facebook.shimmer.ShimmerFrameLayout;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TaoTranDauDaDienRaActivity extends AppCompatActivity {
    private ArrayList<TranDauSapDienRa> tranDauSapDienRaArrayList;
    private ListView listView;
    private ListViewTranSapDauAdapter listViewTranSapDauAdapter;
    private SimpleAPI simpleAPI;
    private ShimmerFrameLayout shimmerFrameFB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tao_tran_dau_da_dien_ra);
        listView = findViewById(R.id.listViewCapNhatTranDau);
        shimmerFrameFB = findViewById(R.id.shimmerFrame);
        LoadData();
    }
    @Override
    public void onResume() {
        super.onResume();
        shimmerFrameFB.startShimmer();
    }
    private void LoadData() {
        tranDauSapDienRaArrayList = new ArrayList<>();
        simpleAPI = Constants.instance();
        simpleAPI.getListTranSapDau().enqueue(new Callback<ArrayList<TranDauSapDienRa>>() {
            @Override
            public void onResponse(Call<ArrayList<TranDauSapDienRa>> call, Response<ArrayList<TranDauSapDienRa>> response) {
                tranDauSapDienRaArrayList = response.body();
                listViewTranSapDauAdapter = new ListViewTranSapDauAdapter(TaoTranDauDaDienRaActivity.this, tranDauSapDienRaArrayList);
                listView.setAdapter(listViewTranSapDauAdapter);
                shimmerFrameFB.stopShimmer();
                shimmerFrameFB.setVisibility(View.GONE);
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent intent = new Intent(TaoTranDauDaDienRaActivity.this, AdminCapNhatTranDauNewActivity.class);
                        intent.putExtra("id_match_ad", String.valueOf(tranDauSapDienRaArrayList.get(position).getIdTran()));
                        intent.putExtra("home_name_ad", String.valueOf(tranDauSapDienRaArrayList.get(position).getDoiNha()));
                        intent.putExtra("guess_name_ad", String.valueOf(tranDauSapDienRaArrayList.get(position).getDoiKhach()));
                        startActivity(intent);
                    }
                });

            }

            @Override
            public void onFailure(Call<ArrayList<TranDauSapDienRa>> call, Throwable t) {
                Toast.makeText(TaoTranDauDaDienRaActivity.this, t.toString(), Toast.LENGTH_SHORT).show();
                t.printStackTrace();
            }
        });
    }

}