package com.example.apptinthethao_java.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.apptinthethao_java.AdminTranDauActivity;
import com.example.apptinthethao_java.R;
import com.example.apptinthethao_java.api.SimpleAPI;
import com.example.apptinthethao_java.model.Analysis;
import com.example.apptinthethao_java.model.Post;
import com.example.apptinthethao_java.util.Constants;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdminActivity extends AppCompatActivity implements View.OnClickListener {
    private SimpleAPI simpleAPI;
    private TextView tvSLTK, tvSLBV;
    private ArrayList<Analysis> analysisArrayList;
    private SwipeRefreshLayout swipeRefreshLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        Button btnTaiKhoan = (Button) findViewById(R.id.btnTaiKhoan);
        Button btnBaiViet = (Button)  findViewById(R.id.btnBaiViet);
//        swipeRefreshLayout = findViewById(R.id.swipe_refreshAd);
//        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//            @Override
//            public void onRefresh() {
//                updateInfo();
//                swipeRefreshLayout.setRefreshing(false);
//            }
//        });
        btnTaiKhoan.setOnClickListener(this);
        btnBaiViet.setOnClickListener(this);

        tvSLTK = findViewById(R.id.tvSLTK);
        tvSLBV = findViewById(R.id.tvSLBV);

        updateInfo();
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateInfo();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnTaiKhoan:{
                Intent intent = new Intent(AdminActivity.this, ListUserActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivityIfNeeded(intent, 0);
                break;
            }
            case R.id.btnBaiViet:{
                Intent intent = new Intent(AdminActivity.this, ListBaiVietActivity.class);
                startActivity(intent);
                break;
            }
        }
    }

    private void updateInfo(){
        simpleAPI = Constants.instance();
        simpleAPI.getAnalysis().enqueue(new Callback<ArrayList<Analysis>>() {
            @Override
            public void onResponse(Call<ArrayList<Analysis>> call, Response<ArrayList<Analysis>> response) {
                analysisArrayList = new ArrayList<>();
                analysisArrayList = response.body();
                tvSLTK.setText(String.valueOf(analysisArrayList.get(0).getCount_account()));
                tvSLBV.setText(String.valueOf(analysisArrayList.get(0).getCount_post()));
            }

            @Override
            public void onFailure(Call<ArrayList<Analysis>> call, Throwable t) {
                Toast.makeText(AdminActivity.this, "Lỗi: "+t.toString(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}