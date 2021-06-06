package com.example.apptinthethao_java.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.apptinthethao_java.R;
import com.example.apptinthethao_java.adapter.DienBienSuKienAdapter;
import com.example.apptinthethao_java.adapter.ExpandableDoiHinhAdapter;
import com.example.apptinthethao_java.api.SimpleAPI;
import com.example.apptinthethao_java.model.CauLacBo;
import com.example.apptinthethao_java.model.CauThu_DoiHinh;
import com.example.apptinthethao_java.model.DienBienTranDau;
import com.example.apptinthethao_java.model.DoiHinh;
import com.example.apptinthethao_java.model.SuKienTrongTran;
import com.example.apptinthethao_java.util.Constants;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DienBienActivity extends AppCompatActivity {
    private String match_id;
    private SimpleAPI simpleAPI;
    private ArrayList<CauLacBo> cauLacBoArrayList;
    private ArrayList<DienBienTranDau> dienBienTranDauArrayList;
    private ImageView imgDoiNha, imgDoiKhach;
    private TextView tvTenDoiNha, tvTenDoiKhach, tvTiSo, tvNgayThiDau;
    private ExpandableListView expandableListView;
    private ExpandableListAdapter expandableListAdapter;
    private ArrayList<String> arrayListTitleDoiHinh;
    private HashMap<String, ArrayList<CauThu_DoiHinh>> hashMapChiTietDoiHinh;
    private ArrayList<DoiHinh> arrayListDoiHinh;
    private ListView listViewSuKien;
    private ArrayList<SuKienTrongTran> suKienTrongTranArrayList;
    private ShimmerFrameLayout shimmerFrameFB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dien_bien);

        imgDoiNha = findViewById(R.id.imgDoiNha);
        imgDoiKhach = findViewById(R.id.imgDoiKhach);
        tvTenDoiNha = findViewById(R.id.tvTenDoiNha);
        tvTenDoiKhach = findViewById(R.id.tvTenDoiKhach);
        tvTiSo = findViewById(R.id.tvTiSo);
        tvNgayThiDau = findViewById(R.id.tvNgayThiDau);
        expandableListView = findViewById(R.id.expandableDoiHinh);
        listViewSuKien = findViewById(R.id.listViewSuKien);
        shimmerFrameFB = findViewById(R.id.shimmerFrame);

        Intent intent = getIntent();
        match_id = intent.getStringExtra("match_id");
        dienBienTranDauArrayList = new ArrayList<>();

        LoadKetQuaTranDau(match_id);
        LoadDoiHinhTranDau(match_id);
        LoadDienBienTranDau(match_id);
//        GetDoiHinhChinhDoiNha(match_id);
//        GetDoiHinhDuBiDoiNha(match_id);
//        GetDoiHinhChinhDoiKhach(match_id);
//        GetDoiHinhDuBiDoiKhach(match_id);
//        for(int i=0; i<arrayListHomeMain.size(); i++){
//            Toast.makeText(DienBienActivity.this,arrayListHomeMain.get(i).getTenCauThu(), Toast.LENGTH_SHORT).show();
//        }

    }

    private void LoadDienBienTranDau(String match_id) {
        simpleAPI = Constants.instance();
        simpleAPI.getSuKienTrongTran(match_id).enqueue(new Callback<ArrayList<SuKienTrongTran>>() {
            @Override
            public void onResponse(Call<ArrayList<SuKienTrongTran>> call, Response<ArrayList<SuKienTrongTran>> response) {
                suKienTrongTranArrayList = response.body();
                DienBienSuKienAdapter dienBienSuKienAdapter = new DienBienSuKienAdapter(DienBienActivity.this, suKienTrongTranArrayList);
                listViewSuKien.setAdapter(dienBienSuKienAdapter);
                shimmerFrameFB.stopShimmer();
                shimmerFrameFB.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<ArrayList<SuKienTrongTran>> call, Throwable t) {
                Toast.makeText(DienBienActivity.this, t.toString(), Toast.LENGTH_SHORT).show();
                t.printStackTrace();
            }
        });
    }
    @Override
    protected void onResume() {
        super.onResume();
        shimmerFrameFB.startShimmer();
    }
    private void LoadDoiHinhTranDau(String match_id){
        hashMapChiTietDoiHinh= new HashMap<>();
        arrayListTitleDoiHinh = new ArrayList<>();
        arrayListDoiHinh = new ArrayList<>();
        simpleAPI = Constants.instance();
        simpleAPI.getDoiHinh(match_id).enqueue(new Callback<ArrayList<DoiHinh>>() {
            @Override
            public void onResponse(Call<ArrayList<DoiHinh>> call, Response<ArrayList<DoiHinh>> response) {
                arrayListDoiHinh = response.body();
                hashMapChiTietDoiHinh.put("Đội hình chính đội nhà", arrayListDoiHinh.get(0).getArrayListHomeMain());
                hashMapChiTietDoiHinh.put("Đội hình dự bị đội nhà", arrayListDoiHinh.get(1).getArrayListHomeSub());
                hashMapChiTietDoiHinh.put("Đội hình chính đội khách", arrayListDoiHinh.get(2).getArrayListGuessMain());
                hashMapChiTietDoiHinh.put("Đội hình dự bị đội khách", arrayListDoiHinh.get(3).getArrayListGuessSub());
                arrayListTitleDoiHinh = new ArrayList<>(hashMapChiTietDoiHinh.keySet());
                expandableListAdapter = new ExpandableDoiHinhAdapter(DienBienActivity.this, arrayListTitleDoiHinh, hashMapChiTietDoiHinh);
                expandableListView.setAdapter(expandableListAdapter);
            }

            @Override
            public void onFailure(Call<ArrayList<DoiHinh>> call, Throwable t) {
                Toast.makeText(DienBienActivity.this, t.toString(), Toast.LENGTH_SHORT).show();
                t.printStackTrace();
            }
        });
    }

    private void LoadKetQuaTranDau(String match_id) {
        simpleAPI = Constants.instance();
        simpleAPI.getTranDau(match_id).enqueue(new Callback<ArrayList<DienBienTranDau>>() {
            @Override
            public void onResponse(Call<ArrayList<DienBienTranDau>> call, Response<ArrayList<DienBienTranDau>> response) {
                dienBienTranDauArrayList = response.body();
                tvTiSo.setText(dienBienTranDauArrayList.get(0).getKetQua());
                tvNgayThiDau.setText(dienBienTranDauArrayList.get(0).getThoiGian());
                GetChiTietCLBDoiNha(dienBienTranDauArrayList.get(0).getDoiNha());
                GetChiTietCLBDoiKhach(dienBienTranDauArrayList.get(0).getDoiKhach());
            }

            @Override
            public void onFailure(Call<ArrayList<DienBienTranDau>> call, Throwable t) {
                Toast.makeText(DienBienActivity.this, t.toString(), Toast.LENGTH_SHORT).show();
                t.printStackTrace();
            }
        });
    }

    private void GetChiTietCLBDoiNha(String id_clb){
        simpleAPI = Constants.instance();
        simpleAPI.getChiTietCLB(id_clb).enqueue(new Callback<ArrayList<CauLacBo>>() {
            @Override
            public void onResponse(Call<ArrayList<CauLacBo>> call, Response<ArrayList<CauLacBo>> response) {
                cauLacBoArrayList = response.body();
                tvTenDoiNha.setText(cauLacBoArrayList.get(0).getTenCLB());
                Picasso.get()
                        .load(cauLacBoArrayList.get(0).getLink())
                        .placeholder(R.drawable.galleryoo)
                        .error(R.drawable.galleryoo)
                        .into(imgDoiNha);
            }

            @Override
            public void onFailure(Call<ArrayList<CauLacBo>> call, Throwable t) {
                Toast.makeText(DienBienActivity.this, t.toString(), Toast.LENGTH_SHORT).show();
                t.printStackTrace();
            }
        });
    }
    private void GetChiTietCLBDoiKhach(String id_clb){
        simpleAPI = Constants.instance();
        simpleAPI.getChiTietCLB(id_clb).enqueue(new Callback<ArrayList<CauLacBo>>() {
            @Override
            public void onResponse(Call<ArrayList<CauLacBo>> call, Response<ArrayList<CauLacBo>> response) {
                cauLacBoArrayList = response.body();
                tvTenDoiKhach.setText(cauLacBoArrayList.get(0).getTenCLB());
                Picasso.get()
                        .load(cauLacBoArrayList.get(0).getLink())
                        .placeholder(R.drawable.galleryoo)
                        .error(R.drawable.galleryoo)
                        .into(imgDoiKhach);
            }

            @Override
            public void onFailure(Call<ArrayList<CauLacBo>> call, Throwable t) {
                Toast.makeText(DienBienActivity.this, t.toString(), Toast.LENGTH_SHORT).show();
                t.printStackTrace();
            }
        });
    }
}