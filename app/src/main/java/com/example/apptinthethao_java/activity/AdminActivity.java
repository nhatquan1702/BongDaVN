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
    private TextView tvSLTK, tvSLBV, tvSLDB;
    private ArrayList<Analysis> analysisArrayList;
    private ArrayList<Post> postArrayList;
    private ArrayList<Post> postArrayList2;
    private SwipeRefreshLayout swipeRefreshLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        Button btnTaiKhoan = (Button) findViewById(R.id.btnTaiKhoan);
        Button btnBaiViet = (Button)  findViewById(R.id.btnBaiViet);
        Button btnDuyetBai = (Button)  findViewById(R.id.btnDuyetBai);

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
        btnDuyetBai.setOnClickListener(this);

        tvSLTK = findViewById(R.id.tvSLTK);
        tvSLBV = findViewById(R.id.tvSLBV);
        tvSLDB = findViewById(R.id.tvSLDB);

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
                intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivityIfNeeded(intent, 0);
                break;
            }
            case R.id.btnDuyetBai:{
                Intent intent = new Intent(AdminActivity.this, ListTinChuaDuyetActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivityIfNeeded(intent, 0);
                break;
            }
        }
    }

    private void updateInfo(){
        postArrayList = new ArrayList<>();
        postArrayList2=new ArrayList<>();
        simpleAPI = Constants.instance();
        simpleAPI.getAnalysis().enqueue(new Callback<ArrayList<Analysis>>() {
            @Override
            public void onResponse(Call<ArrayList<Analysis>> call, Response<ArrayList<Analysis>> response) {
                analysisArrayList = new ArrayList<>();
                analysisArrayList = response.body();
                tvSLTK.setText(String.valueOf(analysisArrayList.get(0).getCount_account()));

                simpleAPI.getListTinDaDuyet().enqueue(new Callback<ArrayList<Post>>() {
                    @Override
                    public void onResponse(Call<ArrayList<Post>> call, Response<ArrayList<Post>> response) {
                        postArrayList2 = response.body();
                        if(postArrayList2.size()<0 || postArrayList2.size()==0){
                            tvSLBV.setText(String.valueOf(0));
                        }
                        else {
                            tvSLBV.setText(String.valueOf(postArrayList2.size()));
                        }
                    }

                    @Override
                    public void onFailure(Call<ArrayList<Post>> call, Throwable t) {
                        Toast.makeText(AdminActivity.this, "Lỗi: "+t.toString(), Toast.LENGTH_SHORT).show();
                    }
                });
                simpleAPI.getListTinChuaDuyet().enqueue(new Callback<ArrayList<Post>>() {
                    @Override
                    public void onResponse(Call<ArrayList<Post>> call, Response<ArrayList<Post>> response) {
                        postArrayList = response.body();
                        if(postArrayList.size()<0 || postArrayList.size()==0){
                            tvSLDB.setText(String.valueOf(0));
                        }
                        else {
                            tvSLDB.setText(String.valueOf(postArrayList.size()));
                        }
                    }

                    @Override
                    public void onFailure(Call<ArrayList<Post>> call, Throwable t) {
                        Toast.makeText(AdminActivity.this, "Lỗi: "+t.toString(), Toast.LENGTH_SHORT).show();
                    }
                });
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