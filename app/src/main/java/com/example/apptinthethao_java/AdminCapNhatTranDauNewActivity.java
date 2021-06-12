package com.example.apptinthethao_java;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.apptinthethao_java.adapter.ChiTietCLBAdapter;
import com.example.apptinthethao_java.api.SimpleAPI;
import com.example.apptinthethao_java.model.CauThu_DoiHinh;
import com.example.apptinthethao_java.model.Status;
import com.example.apptinthethao_java.util.Constants;

import java.time.LocalDateTime;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdminCapNhatTranDauNewActivity extends AppCompatActivity {
    private Spinner spinnerChonCauThu, spinnerChonSuKien;
    private EditText editTextNhapPhut, editTextNhapTiSo, edtNhapTiSoDoiKhach;
    private CardView cardViewTaoEvent, cardViewTaoTiSo;
    private String match_id;
    private String home_name;
    protected String guess_name;
    private SimpleAPI simpleAPI;
    private ArrayList<CauThu_DoiHinh> cauThuDoiHinhs;
    private ArrayList<CauThu_DoiHinh> cauThu_doiHinhArrayList2;
    private ChiTietCLBAdapter tietCLBAdapter;
    private String suKien[]= {"Đội hình chính","Đội hình dự bị","Thẻ vàng","Thẻ đỏ","Ghi bàn", "Hỗ trợ"};
    private String suKienDcChon;
    private String soPhut;
    String id_CauThu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_cap_nhat_tran_dau_new);
        spinnerChonCauThu =  findViewById(R.id.spinnerChonCauThuAdNew);
        spinnerChonSuKien =  findViewById(R.id.spinnerChonSuKienAdNew);
        editTextNhapPhut = findViewById(R.id.edtNhapPhutAdNew);
        editTextNhapTiSo = findViewById(R.id.edtNhapTiSoAdNew);
        edtNhapTiSoDoiKhach = findViewById(R.id.edtNhapTiSoDoiKhach);
        cardViewTaoEvent = findViewById(R.id.cvTaoAdNewEvent);
        cardViewTaoTiSo = findViewById(R.id.cvTaoAdNewTiSo);
        Intent intent = getIntent();
        match_id = intent.getStringExtra("id_match_ad");
        home_name = intent.getStringExtra("home_name_ad");
        guess_name = intent.getStringExtra("guess_name_ad");
        cauThuDoiHinhs = new ArrayList<>();
        cauThu_doiHinhArrayList2 = new ArrayList<>();
        soPhut = editTextNhapPhut.getText().toString().trim();
        LoadSuKienSpinner();
        LoadDSCauThu(home_name, guess_name);

        cardViewTaoTiSo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tiSoNha = editTextNhapTiSo.getText().toString();
                String tiSoKhach = edtNhapTiSoDoiKhach.getText().toString();
                Log.e("tiso",tiSoNha + " " + tiSoKhach);
                if(editTextNhapTiSo.getText().toString().isEmpty() || editTextNhapPhut.getText().toString().isEmpty()){
                    Toast.makeText(AdminCapNhatTranDauNewActivity.this, "Kiểm tra lại tỉ số", Toast.LENGTH_SHORT).show();
                }
//                if(Integer.parseInt(editTextNhapTiSo.getText().toString())<0 ||Integer.parseInt(editTextNhapTiSo.getText().toString())>100){
//                    Toast.makeText(AdminCapNhatTranDauNewActivity.this, "Kiểm tra lại tỉ số", Toast.LENGTH_SHORT).show();
//                }
//                if(Integer.parseInt(edtNhapTiSoDoiKhach.getText().toString())<0 ||Integer.parseInt(edtNhapTiSoDoiKhach.getText().toString())>100){
//                    Toast.makeText(AdminCapNhatTranDauNewActivity.this, "Kiểm tra lại tỉ số", Toast.LENGTH_SHORT).show();
//                }
                else {
                    Log.d("quan", editTextNhapTiSo.getText().toString()+":"+edtNhapTiSoDoiKhach.getText().toString());
                    LoadPostTiSo(match_id, editTextNhapTiSo.getText().toString()+":"+edtNhapTiSoDoiKhach.getText().toString());
                }
            }
        });

    }

    private void LoadPostTiSo(String match_id, String tiSo) {
        simpleAPI = Constants.instance();
        simpleAPI.postTiSo(match_id, tiSo).enqueue(new Callback<Status>() {
            @Override
            public void onResponse(Call<Status> call, Response<Status> response) {
                Status status = response.body();
                if(status.getStatus()==1){
                    Toast.makeText(AdminCapNhatTranDauNewActivity.this, "Tạo thành công", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(AdminCapNhatTranDauNewActivity.this, "Thất bại: "+String.valueOf(status.getStatus()), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Status> call, Throwable t) {
                Toast.makeText(AdminCapNhatTranDauNewActivity.this, "Thất bại: "+t.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void PostMatchEvent(String id, String id_cauthu, String suKien, String tgian){
        simpleAPI = Constants.instance();
        simpleAPI.postMatchEvent(id, id_cauthu, suKien, tgian).enqueue(new Callback<Status>() {
            @Override
            public void onResponse(Call<Status> call, Response<Status> response) {
                Status status = response.body();
                if(status.getStatus() == 1){
                    Toast.makeText(AdminCapNhatTranDauNewActivity.this, "Tạo thành công", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(AdminCapNhatTranDauNewActivity.this, "Thất bại: "+String.valueOf(status.getStatus()), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Status> call, Throwable t) {
                Toast.makeText(AdminCapNhatTranDauNewActivity.this, t.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void LoadDSCauThu(String id_clb, String id2) {
        ArrayList<CauThu_DoiHinh> arrayList = new ArrayList<>();
        simpleAPI = Constants.instance();
        simpleAPI.getListCauThu(id_clb).enqueue(new Callback<ArrayList<CauThu_DoiHinh>>() {
            @Override
            public void onResponse(Call<ArrayList<CauThu_DoiHinh>> call, Response<ArrayList<CauThu_DoiHinh>> response) {
                cauThuDoiHinhs = response.body();
                arrayList.addAll(cauThuDoiHinhs);

                simpleAPI.getListCauThu(id2).enqueue(new Callback<ArrayList<CauThu_DoiHinh>>() {
                    @Override
                    public void onResponse(Call<ArrayList<CauThu_DoiHinh>> call, Response<ArrayList<CauThu_DoiHinh>> response) {
                        cauThu_doiHinhArrayList2=response.body();
                        arrayList.addAll(cauThu_doiHinhArrayList2);
                        tietCLBAdapter = new ChiTietCLBAdapter(arrayList);
                        spinnerChonCauThu.setAdapter(tietCLBAdapter);
                        spinnerChonCauThu.setSelection(0);
                        spinnerChonCauThu.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                Toast.makeText(AdminCapNhatTranDauNewActivity.this, arrayList.get(position).getIdCauThu(), Toast.LENGTH_SHORT).show();
                                id_CauThu = arrayList.get(position).getIdCauThu();
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {
                                Toast.makeText(AdminCapNhatTranDauNewActivity.this, "chưa chọn", Toast.LENGTH_SHORT).show();
                            }
                        });
                        cardViewTaoEvent.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Log.d("quan", "onClick: "+match_id);
                                Log.d("quan", "onClick: "+id_CauThu);
                                Log.d("quan", "onClick: "+suKienDcChon);
                                Log.d("quan", "onClick: "+editTextNhapPhut.getText().toString());
                                if(id_CauThu.equals("")){
                                    Toast.makeText(AdminCapNhatTranDauNewActivity.this, "Chọn cầu thủ", Toast.LENGTH_SHORT).show();
                                }
                                if(suKienDcChon.equals("")){
                                    Toast.makeText(AdminCapNhatTranDauNewActivity.this, "Chọn sự kiện", Toast.LENGTH_SHORT).show();
                                }
                                if(editTextNhapPhut.getText().toString().isEmpty()){
                                    Toast.makeText(AdminCapNhatTranDauNewActivity.this, "nhập phút", Toast.LENGTH_SHORT).show();
                                }
                                else{
                                    PostMatchEvent(match_id, id_CauThu, suKienDcChon, editTextNhapPhut.getText().toString());
                                }
                            }
                        });

                    }

                    @Override
                    public void onFailure(Call<ArrayList<CauThu_DoiHinh>> call, Throwable t) {
                        Toast.makeText(AdminCapNhatTranDauNewActivity.this, t.toString(), Toast.LENGTH_SHORT).show();
                    }
                });

            }

            @Override
            public void onFailure(Call<ArrayList<CauThu_DoiHinh>> call, Throwable t) {
                Toast.makeText(AdminCapNhatTranDauNewActivity.this, t.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void LoadSuKienSpinner(){
        ArrayAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, suKien);
        adapter.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
        spinnerChonSuKien.setAdapter(adapter);
        spinnerChonSuKien.setSelection(0);
        spinnerChonSuKien.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position==0){
                    suKienDcChon="main";
                    //Toast.makeText(AdminCapNhatTranDauNewActivity.this, suKienDcChon, Toast.LENGTH_SHORT).show();
                }
                else if(position==1){
                    suKienDcChon="sub";
                    // Toast.makeText(AdminCapNhatTranDauNewActivity.this, suKienDcChon, Toast.LENGTH_SHORT).show();
                }
                else if(position==2){
                    suKienDcChon="yellow";
                    // Toast.makeText(AdminCapNhatTranDauNewActivity.this, suKienDcChon, Toast.LENGTH_SHORT).show();
                }
                else if(position==3){
                    suKienDcChon="red";
                    // Toast.makeText(AdminCapNhatTranDauNewActivity.this, suKienDcChon, Toast.LENGTH_SHORT).show();
                }
                else if(position==4){
                    suKienDcChon="goal";
                    //Toast.makeText(AdminCapNhatTranDauNewActivity.this, suKienDcChon, Toast.LENGTH_SHORT).show();
                }
                else if(position==5){
                    suKienDcChon="assist";
                    //Toast.makeText(AdminCapNhatTranDauNewActivity.this, suKienDcChon, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(AdminCapNhatTranDauNewActivity.this, "Chưa chọn", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
