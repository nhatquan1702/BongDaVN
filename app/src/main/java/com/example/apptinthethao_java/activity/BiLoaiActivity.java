package com.example.apptinthethao_java.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.apptinthethao_java.R;
import com.example.apptinthethao_java.adapter.BaiVietAdapter;
import com.example.apptinthethao_java.adapter.ItemClickInterface;
import com.example.apptinthethao_java.api.SimpleAPI;
import com.example.apptinthethao_java.model.Post;
import com.example.apptinthethao_java.util.Constants;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BiLoaiActivity extends AppCompatActivity {
    private ArrayList<Post> mData;
    private SimpleAPI simpleAPI;
    private BaiVietAdapter adapter;
    private FloatingActionButton fab1;
    private Boolean fabCheck = true;
    private ProgressBar progressBar;
    RecyclerView mRecyclerView;
    SharedPreferences sharedPreferences;
    String mAuthor = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bi_loai);

        fab1 = (FloatingActionButton) findViewById(R.id.fab1);
        sharedPreferences = getSharedPreferences("dataLogin", MODE_PRIVATE);
        mAuthor = sharedPreferences.getString("email", "admin");

        fab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoadBaiViet();
                adapter = new BaiVietAdapter(getApplicationContext(),mData);
                mRecyclerView.setAdapter(adapter);
                mRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
            }
        });

        mRecyclerView = findViewById(R.id.rv_listbaiviet);
        progressBar = findViewById(R.id.progress_bar);
        progressBar.setVisibility(View.VISIBLE);
        mData = new ArrayList<>();

        LoadBaiViet();
        adapter = new BaiVietAdapter(getApplicationContext(),mData);
        adapter.setOnItemClickListener(new ItemClickInterface() {
            @Override
            public void onClick(View view, int position) {
                Toast.makeText(BiLoaiActivity.this, "Bài viết này đã bị loại bỏ!", Toast.LENGTH_SHORT).show();
            }
        });
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
    }
    public void LoadBaiViet() {
        progressBar.setVisibility(View.VISIBLE);
        simpleAPI = Constants.instance();
        simpleAPI.getBaiVietTheoTrangThaiCaNhan(-1, mAuthor).enqueue(new Callback<ArrayList<Post>>() {
            @Override
            public void onResponse(Call<ArrayList<Post>> call, Response<ArrayList<Post>> response) {
                mData = response.body();
                adapter.updateChange(mData);
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<ArrayList<Post>> call, Throwable t) {
                if(call.isCanceled()) {
                    Log.d("fail", "request was aborted");
                }else {
                    Log.d("fail", "Unable to submit post to API.");
                }
                progressBar.setVisibility(View.GONE);
            }
        });
    }
}