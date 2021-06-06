package com.example.apptinthethao_java.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.apptinthethao_java.R;
import com.example.apptinthethao_java.adapter.ExpandableDoiHinhAdapter;
import com.example.apptinthethao_java.api.SimpleAPI;
import com.example.apptinthethao_java.model.CauLacBo;
import com.example.apptinthethao_java.model.CauThu_DoiHinh;
import com.example.apptinthethao_java.model.DienBienTranDau;
import com.example.apptinthethao_java.util.Constants;
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
    private ArrayList<CauThu_DoiHinh> arrayListHomeMain;
    private ArrayList<CauThu_DoiHinh> arrayListHomeSub;
    private ArrayList<CauThu_DoiHinh> arrayListGuessMain;
    private ArrayList<CauThu_DoiHinh> arrayListGuessSub;

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

        Intent intent = getIntent();
        match_id = intent.getStringExtra("match_id");
        dienBienTranDauArrayList = new ArrayList<>();

        LoadDienBienTranDau(match_id);
        LoadDoiHinhTranDau(match_id);
//        GetDoiHinhChinhDoiNha(match_id);
//        GetDoiHinhDuBiDoiNha(match_id);
//        GetDoiHinhChinhDoiKhach(match_id);
//        GetDoiHinhDuBiDoiKhach(match_id);
//        for(int i=0; i<arrayListHomeMain.size(); i++){
//            Toast.makeText(DienBienActivity.this,arrayListHomeMain.get(i).getTenCauThu(), Toast.LENGTH_SHORT).show();
//        }

    }
    private void LoadDoiHinhTranDau(String match_id){
        hashMapChiTietDoiHinh= new HashMap<>();
        arrayListHomeMain = new ArrayList<>();
        arrayListHomeSub = new ArrayList<>();
        arrayListGuessMain = new ArrayList<>();
        arrayListGuessSub = new ArrayList<>();
        arrayListTitleDoiHinh = new ArrayList<>();
        simpleAPI = Constants.instance();
        simpleAPI.getDoiHinhChinhDoiNha(match_id).enqueue(new Callback<ArrayList<CauThu_DoiHinh>>() {
            @Override
            public void onResponse(Call<ArrayList<CauThu_DoiHinh>> call, Response<ArrayList<CauThu_DoiHinh>> response) {
                arrayListHomeMain = response.body();
                hashMapChiTietDoiHinh.put("Đội hình chính Đội nhà", arrayListHomeMain);
                arrayListTitleDoiHinh = new ArrayList<String>(hashMapChiTietDoiHinh.keySet());
                expandableListAdapter = new ExpandableDoiHinhAdapter(DienBienActivity.this, arrayListTitleDoiHinh, hashMapChiTietDoiHinh);
                expandableListView.setAdapter(expandableListAdapter);
                //arrayListTitleDoiHinh.add("Đội hình chính (Đội nhà)");
            }

            @Override
            public void onFailure(Call<ArrayList<CauThu_DoiHinh>> call, Throwable t) {
                Toast.makeText(DienBienActivity.this, t.toString(), Toast.LENGTH_SHORT).show();
                t.printStackTrace();
            }
        });
        simpleAPI.getDoiHinhDuBiDoiNha(match_id).enqueue(new Callback<ArrayList<CauThu_DoiHinh>>() {
            @Override
            public void onResponse(Call<ArrayList<CauThu_DoiHinh>> call, Response<ArrayList<CauThu_DoiHinh>> response) {
                arrayListHomeSub = response.body();
                hashMapChiTietDoiHinh.put("Đội hình dự bị Đội nhà", arrayListHomeSub);

                //arrayListTitleDoiHinh.add("Đội hình dự bị (Đội nhà)");
            }

            @Override
            public void onFailure(Call<ArrayList<CauThu_DoiHinh>> call, Throwable t) {
                Toast.makeText(DienBienActivity.this, t.toString(), Toast.LENGTH_SHORT).show();
                t.printStackTrace();
            }
        });
        simpleAPI.getDoiHinhChinhDoiKhach(match_id).enqueue(new Callback<ArrayList<CauThu_DoiHinh>>() {
            @Override
            public void onResponse(Call<ArrayList<CauThu_DoiHinh>> call, Response<ArrayList<CauThu_DoiHinh>> response) {
                arrayListGuessMain = response.body();
                hashMapChiTietDoiHinh.put("Đội hình chính Đội khách", arrayListGuessMain);
                //arrayListTitleDoiHinh.add("Đội hình chính (Đội khách)");
            }

            @Override
            public void onFailure(Call<ArrayList<CauThu_DoiHinh>> call, Throwable t) {
                Toast.makeText(DienBienActivity.this, t.toString(), Toast.LENGTH_SHORT).show();
                t.printStackTrace();
            }
        });
        simpleAPI.getDoiHinhDuBiDoiKhach(match_id).enqueue(new Callback<ArrayList<CauThu_DoiHinh>>() {
            @Override
            public void onResponse(Call<ArrayList<CauThu_DoiHinh>> call, Response<ArrayList<CauThu_DoiHinh>> response) {
                arrayListGuessSub = response.body();
                hashMapChiTietDoiHinh.put("Đội hình dự bị Đội khách", arrayListGuessSub);
                //arrayListTitleDoiHinh.add("Đội hình dự bị (Đội khách)");
            }

            @Override
            public void onFailure(Call<ArrayList<CauThu_DoiHinh>> call, Throwable t) {
                Toast.makeText(DienBienActivity.this, t.toString(), Toast.LENGTH_SHORT).show();
                t.printStackTrace();
            }
        });
        for(int i=0; i<arrayListGuessSub.size(); i++){
            Toast.makeText(DienBienActivity.this,arrayListGuessSub.get(i).getTenCauThu(), Toast.LENGTH_SHORT).show();
        }

    }

    private void LoadDienBienTranDau(String match_id) {
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