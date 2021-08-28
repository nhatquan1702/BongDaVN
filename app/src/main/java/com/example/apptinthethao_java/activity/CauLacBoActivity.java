package com.example.apptinthethao_java.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.apptinthethao_java.R;
import com.example.apptinthethao_java.adapter.CauLacBoAdapter;
import com.example.apptinthethao_java.api.SimpleAPI;
import com.example.apptinthethao_java.model.CauLacBo;
import com.example.apptinthethao_java.util.Constants;
import com.example.apptinthethao_java.view.LastView;
import com.facebook.shimmer.ShimmerFrameLayout;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CauLacBoActivity extends AppCompatActivity{
    private ArrayList<CauLacBo> cauLacBoArrayList;
    private CauLacBoAdapter cauLacBoAdapter;
    private ListView listViewCLB;
    private SimpleAPI simpleAPI;
    private ProgressBar mProgressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cau_lac_bo);
        listViewCLB = findViewById(R.id.listViewDSCLB);
        mProgressBar = findViewById(R.id.progress_bar);
        cauLacBoArrayList = new ArrayList<>();
        ShowLoading();
        LoadCLB();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void LoadCLB(){
        simpleAPI = Constants.instance();
        simpleAPI.getListCLB().enqueue(new Callback<ArrayList<CauLacBo>>() {
            @Override
            public void onResponse(Call<ArrayList<CauLacBo>> call, Response<ArrayList<CauLacBo>> response) {
                cauLacBoArrayList = response.body();
                cauLacBoAdapter = new CauLacBoAdapter(cauLacBoArrayList);
                listViewCLB.setAdapter(cauLacBoAdapter);
                listViewCLB.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent intent = new Intent(CauLacBoActivity.this, ChiTietCLBActivity.class);
                        intent.putExtra("clb_id", String.valueOf(cauLacBoArrayList.get(position).getTenCLB()));
                        startActivity(intent);
                    }
                });
                HideLoading();
            }

            @Override
            public void onFailure(Call<ArrayList<CauLacBo>> call, Throwable t) {
                Toast.makeText(CauLacBoActivity.this, t.toString(), Toast.LENGTH_SHORT).show();
                t.printStackTrace();
            }
        });
    }

    public void ShowLoading() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    public void HideLoading() {
        mProgressBar.setVisibility(View.GONE);
    }

}