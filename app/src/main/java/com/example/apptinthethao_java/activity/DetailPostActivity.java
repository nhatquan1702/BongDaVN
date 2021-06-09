package com.example.apptinthethao_java.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.apptinthethao_java.R;
import com.example.apptinthethao_java.adapter.CmtPostAdapter;
import com.example.apptinthethao_java.adapter.ItemClickInterface;
import com.example.apptinthethao_java.api.SimpleAPI;
import com.example.apptinthethao_java.model.Cmt;
import com.example.apptinthethao_java.model.DetailPost;
import com.example.apptinthethao_java.model.Status;
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
    private TextView tvTieuDeDeTail, tvNgayTao, tvNoiDung, tvNguoiTao, tvLuotView, tvBinhLuan;
    private ShimmerFrameLayout shimmerFrameFB;
    private ConstraintLayout constraintLayout;
    private ArrayList<Cmt> cmtArrayList;
    private EditText edtNoiDungCmt;
    private Button btnGuiCmt;
    private ListView listView;
    private SharedPreferences sharedPreferences;
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
        //shimmerFrameFB = findViewById(R.id.shimmerFrame);
        constraintLayout = findViewById(R.id.constraintLayoutDetailPost);
        edtNoiDungCmt = findViewById(R.id.edtComment);
        btnGuiCmt = findViewById(R.id.btnCmt);
        listView = findViewById(R.id.listViewComment);
        tvBinhLuan = findViewById(R.id.tvBinhLuan);

        cmtArrayList = new ArrayList<>();
        Intent intent = getIntent();
        String id = intent.getStringExtra("post_id");
        LoadDetailPost(id);
        LoadListCmt(id);
        simpleAPI = Constants.instance();
        sharedPreferences = getSharedPreferences("dataLogin", MODE_PRIVATE);
        String check = sharedPreferences.getString("role", "0");
        String userName = sharedPreferences.getString("email", "username");
        btnGuiCmt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(check.equals("0")){
                    Toast.makeText(DetailPostActivity.this, "Vui lòng đăng nhập!", Toast.LENGTH_SHORT).show();
                }
                else{
                    if(edtNoiDungCmt.getText().toString().trim().equals("")){
                        Toast.makeText(DetailPostActivity.this, "Bạn chưa nhập bình luận!", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        simpleAPI.postCmt(userName, id, edtNoiDungCmt.getText().toString().trim()).enqueue(new Callback<Status>() {
                            @Override
                            public void onResponse(Call<Status> call, Response<Status> response) {
                                Status status = response.body();
                                if(status.getStatus()==1){
                                    LoadListCmt(id);
                                    edtNoiDungCmt.setText("");
                                    Toast.makeText(DetailPostActivity.this, "Bình luận thành công!", Toast.LENGTH_SHORT).show();
                                }
                                else {
                                    Toast.makeText(DetailPostActivity.this, "Bình luận không thành công!", Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<Status> call, Throwable t) {
                                Toast.makeText(DetailPostActivity.this, t.toString(), Toast.LENGTH_SHORT).show();
                                t.printStackTrace();
                            }
                        });
                    }
                }
            }
        });
    }


    private void LoadListCmt(String id) {
        simpleAPI = Constants.instance();
        simpleAPI.getListCmt(id).enqueue(new Callback<ArrayList<Cmt>>() {
            @Override
            public void onResponse(Call<ArrayList<Cmt>> call, Response<ArrayList<Cmt>> response) {
                cmtArrayList = response.body();
                if(cmtArrayList.size()>0){
                    tvBinhLuan.setText("Bình luận");
                    CmtPostAdapter cmtPostAdapter = new CmtPostAdapter(DetailPostActivity.this, cmtArrayList);
                    listView.setAdapter(cmtPostAdapter);
                    cmtPostAdapter.setOnClickItemListViewCmt(new ItemClickInterface() {
                        @Override
                        public void onClick(View view, int position) {
                            String check = sharedPreferences.getString("role", "0");
                            if(check.equals("0")){
                                Toast.makeText(DetailPostActivity.this, "Vui lòng đăng nhập!", Toast.LENGTH_SHORT).show();
                            }
                            else {
                                simpleAPI = Constants.instance();
                                simpleAPI.deleteCmt(cmtArrayList.get(position).getUserName(),
                                        cmtArrayList.get(position).getId_post(),
                                        cmtArrayList.get(position).getNgayCmt()).enqueue(new Callback<Status>() {
                                    @Override
                                    public void onResponse(Call<Status> call, Response<Status> response) {
                                        Status status = response.body();
                                        if(status.getStatus()==1){
                                            LoadListCmt(id);
                                            Toast.makeText(DetailPostActivity.this, "Xóa bình luận thành công!", Toast.LENGTH_SHORT).show();
                                        }
                                        else {
                                            Toast.makeText(DetailPostActivity.this, "Xóa bình luận thất bại!", Toast.LENGTH_SHORT).show();
                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<Status> call, Throwable t) {
                                        Toast.makeText(DetailPostActivity.this, t.toString(), Toast.LENGTH_SHORT).show();
                                        t.printStackTrace();
                                    }
                                });
                            }
                        }
                    });
                }
                else{
                    tvBinhLuan.setText("Chưa có bình luận nào!");
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Cmt>> call, Throwable t) {
                Toast.makeText(DetailPostActivity.this, t.toString(), Toast.LENGTH_SHORT).show();
                t.printStackTrace();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        constraintLayout.setVisibility(View.GONE);
        //shimmerFrameFB.startShimmer();
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
//                shimmerFrameFB.stopShimmer();
//                shimmerFrameFB.setVisibility(View.GONE);
                constraintLayout.setVisibility(View.VISIBLE);
            }

            @Override
            public void onFailure(Call<ArrayList<DetailPost>> call, Throwable t) {
                Toast.makeText(DetailPostActivity.this, t.toString(), Toast.LENGTH_SHORT).show();
                t.printStackTrace();
            }
        });
    }
}