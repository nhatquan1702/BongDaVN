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
import com.example.apptinthethao_java.adapter.CauThuAdapter;
import com.example.apptinthethao_java.api.SimpleAPI;
import com.example.apptinthethao_java.model.CauLacBo;
import com.example.apptinthethao_java.model.CauThuSimple;
import com.example.apptinthethao_java.util.Constants;
import com.facebook.shimmer.ShimmerFrameLayout;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListCauThuActivity extends AppCompatActivity {
    private ArrayList<CauThuSimple> cauThuSimpleArrayList;
    private CauThuAdapter cauThuAdapter;
    private ListView listViewCauThu;
    private SimpleAPI simpleAPI;
    private ShimmerFrameLayout shimmerFrameLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_cau_thu);
        listViewCauThu = findViewById(R.id.listViewDSCauThu);
        shimmerFrameLayout = findViewById(R.id.shimmerFrame);
        cauThuSimpleArrayList = new ArrayList<>();
        LoadCLB();
    }

    @Override
    protected void onResume() {
        super.onResume();
        shimmerFrameLayout.startShimmer();
    }

    private void LoadCLB(){
        simpleAPI = Constants.instance();
        simpleAPI.getListAllPlayer().enqueue(new Callback<ArrayList<CauThuSimple>>() {
            @Override
            public void onResponse(Call<ArrayList<CauThuSimple>> call, Response<ArrayList<CauThuSimple>> response) {
                cauThuSimpleArrayList = response.body();
                cauThuAdapter = new CauThuAdapter(cauThuSimpleArrayList);
                listViewCauThu.setAdapter(cauThuAdapter);
                listViewCauThu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent intent = new Intent(ListCauThuActivity.this, ChiTietCauThuActivity.class);
                        intent.putExtra("player_id", String.valueOf(cauThuSimpleArrayList.get(position).getIdCauThu()));
                        startActivity(intent);
                    }
                });
                shimmerFrameLayout.stopShimmer();
                shimmerFrameLayout.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<ArrayList<CauThuSimple>> call, Throwable t) {
                Toast.makeText(ListCauThuActivity.this, t.toString(), Toast.LENGTH_SHORT).show();
                t.printStackTrace();
            }
        });
    }
}