package com.example.apptinthethao_java.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.apptinthethao_java.R;
import com.example.apptinthethao_java.api.SimpleAPI;
import com.example.apptinthethao_java.model.DetailPost;
import com.example.apptinthethao_java.util.Constants;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailPostActivity extends AppCompatActivity {
    private ArrayList<DetailPost> detailPosts;
    private SimpleAPI simpleAPI;
    private ImageView imgDetailPost;
    private ShimmerFrameLayout shimmerFrameFB;
    private TextView tvTieuDeDeTail, tvNgayTao, tvNoiDung, tvNguoiTao, tvLuotView;
    private ConstraintLayout constraintLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_post);
        imgDetailPost = findViewById(R.id.imgDetailPost);
        tvTieuDeDeTail = findViewById(R.id.tvTieuDeDeTail);
        tvNgayTao = findViewById(R.id.tvNgayTao);
        tvNoiDung = findViewById(R.id.tvNoiDung);
        tvNguoiTao = findViewById(R.id.tvNguoiTao);
        tvLuotView = findViewById(R.id.tvLuotView);
        shimmerFrameFB = findViewById(R.id.shimmerFrame);
        constraintLayout = findViewById(R.id.constraintLayoutDetailPost);

        Intent intent = getIntent();
        String id = intent.getStringExtra("post_id");
        LoadDetailPost(id);
    }

    @Override
    protected void onResume() {
        super.onResume();
        constraintLayout.setVisibility(View.GONE);
        shimmerFrameFB.startShimmer();
    }

    private void LoadDetailPost(String post_id){
        simpleAPI = Constants.instance();
        simpleAPI.getDetailPost(post_id).enqueue(new Callback<ArrayList<DetailPost>>() {
            @Override
            public void onResponse(Call<ArrayList<DetailPost>> call, Response<ArrayList<DetailPost>> response) {
                detailPosts = response.body();
                Picasso.get()
                        .load(detailPosts.get(0).getPost_img())
                        .placeholder(R.drawable.gallery)
                        .error(R.drawable.gallery)
                        .into(imgDetailPost);
                tvTieuDeDeTail.setText(detailPosts.get(0).getPost_title());
                tvNgayTao.setText(detailPosts.get(0).getPost_create_time());
                tvNoiDung.setText(detailPosts.get(0).getPost_content());
                tvNguoiTao.setText(detailPosts.get(0).getPost_create_by());
                tvLuotView.setText(String.valueOf(detailPosts.get(0).getPost_view())+" lượt xem");
                shimmerFrameFB.stopShimmer();
                shimmerFrameFB.setVisibility(View.GONE);
                constraintLayout.setVisibility(View.VISIBLE);
            }

            @Override
            public void onFailure(Call<ArrayList<DetailPost>> call, Throwable t) {

            }
        });
    }
}