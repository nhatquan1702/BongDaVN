package com.example.apptinthethao_java.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.apptinthethao_java.R;
import com.example.apptinthethao_java.api.SimpleAPI;
import com.example.apptinthethao_java.model.Analysis;
import com.example.apptinthethao_java.model.Post;
import com.example.apptinthethao_java.util.Constants;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class NhanVienActivity extends AppCompatActivity {
    private SimpleAPI simpleAPI;
    private TextView tvSLTK, tvSLBV, tvSLDB;
    private ArrayList<Analysis> analysisArrayList;
    private ArrayList<Post> postArrayList1;
    private ArrayList<Post> postArrayList2;
    private ArrayList<Post> postArrayList3;
    private SharedPreferences sharedPreferences;
    private String mAuthor=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nhan_vien);

        sharedPreferences = getSharedPreferences("dataLogin", MODE_PRIVATE);
        mAuthor = sharedPreferences.getString("email", "admin");

        Button btnTaiKhoan = (Button) findViewById(R.id.btnTaiKhoan);
        Button btnBaiViet = (Button)  findViewById(R.id.btnBaiViet);
        Button btnDuyetBai = (Button)  findViewById(R.id.btnDuyetBai);
        postArrayList1 = new ArrayList<>();
        postArrayList2 = new ArrayList<>();
        postArrayList3 = new ArrayList<>();

        btnTaiKhoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NhanVienActivity.this, DangChoDuyetActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivityIfNeeded(intent, 0);
            }
        });
        btnBaiViet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NhanVienActivity.this, DaDuyetActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivityIfNeeded(intent, 0);
            }
        });
        btnDuyetBai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NhanVienActivity.this, BiLoaiActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivityIfNeeded(intent, 0);
            }
        });

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


    private void updateInfo(){
        simpleAPI = Constants.instance();
        simpleAPI.getBaiVietTheoTrangThaiCaNhan(-2, mAuthor).enqueue(new Callback<ArrayList<Post>>() {
            @Override
            public void onResponse(Call<ArrayList<Post>> call, Response<ArrayList<Post>> response) {
                postArrayList1 = response.body();
                if(postArrayList1.size()<0 || postArrayList1.size()==0){
                    tvSLTK.setText(String.valueOf(0));
                }
                else {
                    tvSLTK.setText(String.valueOf(postArrayList1.size()));
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Post>> call, Throwable t) {
                Toast.makeText(NhanVienActivity.this, "Lỗi: "+t.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        simpleAPI.getBaiVietDaDuyetCaNhan(mAuthor).enqueue(new Callback<ArrayList<Post>>() {
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
                Toast.makeText(NhanVienActivity.this, "Lỗi: "+t.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        simpleAPI.getBaiVietTheoTrangThaiCaNhan(-1, mAuthor).enqueue(new Callback<ArrayList<Post>>() {
            @Override
            public void onResponse(Call<ArrayList<Post>> call, Response<ArrayList<Post>> response) {
                postArrayList3 = response.body();
                if(postArrayList3.size()<0 || postArrayList3.size()==0){
                    tvSLDB.setText(String.valueOf(0));
                }
                else {
                    tvSLDB.setText(String.valueOf(postArrayList3.size()));
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Post>> call, Throwable t) {
                Toast.makeText(NhanVienActivity.this, "Lỗi: "+t.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}