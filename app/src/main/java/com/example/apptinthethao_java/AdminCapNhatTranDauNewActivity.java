package com.example.apptinthethao_java;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.apptinthethao_java.adapter.ChiTietCLBAdapter;
import com.example.apptinthethao_java.api.SimpleAPI;
import com.example.apptinthethao_java.model.CauThu_DoiHinh;
import com.example.apptinthethao_java.util.Constants;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdminCapNhatTranDauNewActivity extends AppCompatActivity {
    private Spinner spinnerChonCauThu, spinnerChonSuKien;
    private EditText editTextNhapPhut, editTextNhapTiSo;
    private CardView cardViewTao;
    private String match_id;
    private String home_name;
    protected String guess_name;
    private SimpleAPI simpleAPI;
    private ArrayList<CauThu_DoiHinh> cauThuDoiHinhs;
    private ArrayList<CauThu_DoiHinh> cauThu_doiHinhArrayList2;
    private ChiTietCLBAdapter tietCLBAdapter;
    private String suKien[]= {"Đội hình chính","Đội hình dự bị","Thẻ vàng","Thẻ đỏ","Ghi bàn", "Hỗ trợ"};
    private String suKienDcChon="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_cap_nhat_tran_dau_new);
        spinnerChonCauThu =  findViewById(R.id.spinnerChonCauThuAdNew);
        spinnerChonSuKien =  findViewById(R.id.spinnerChonSuKienAdNew);
        editTextNhapPhut = findViewById(R.id.edtNhapPhutAdNew);
        editTextNhapTiSo = findViewById(R.id.edtNhapTiSoAdNew);
        cardViewTao = findViewById(R.id.cvTao);
        Intent intent = getIntent();
        match_id = intent.getStringExtra("id_match_ad");
        home_name = intent.getStringExtra("home_name_ad");
        guess_name = intent.getStringExtra("guess_name_ad");
        cauThuDoiHinhs = new ArrayList<>();
        cauThu_doiHinhArrayList2 = new ArrayList<>();
        //LoadDSCauThu(home_name, guess_name);
        //LoadSuKienSpinner();

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
                        //arrayList.addAll(cauThu_doiHinhArrayList2);
                        tietCLBAdapter = new ChiTietCLBAdapter(cauThuDoiHinhs);
                        spinnerChonCauThu.setAdapter(tietCLBAdapter);
                        spinnerChonCauThu.setSelection(0);
                        spinnerChonCauThu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                Toast.makeText(AdminCapNhatTranDauNewActivity.this, arrayList.get(position).getIdCauThu(), Toast.LENGTH_SHORT).show();
                            }
                        });
                    }

                    @Override
                    public void onFailure(Call<ArrayList<CauThu_DoiHinh>> call, Throwable t) {
                        Toast.makeText(AdminCapNhatTranDauNewActivity.this, "chưa chọn", Toast.LENGTH_SHORT).show();
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
        ArrayAdapter adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, suKien);
        adapter.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
        spinnerChonSuKien.setAdapter(adapter);
        spinnerChonSuKien.setSelection(0);
        spinnerChonSuKien.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position==0){
                    suKienDcChon="main";
                    Toast.makeText(AdminCapNhatTranDauNewActivity.this, suKienDcChon, Toast.LENGTH_SHORT).show();
                }
                else if(position==1){
                    suKienDcChon="sub";
                    Toast.makeText(AdminCapNhatTranDauNewActivity.this, suKienDcChon, Toast.LENGTH_SHORT).show();
                }
                else if(position==2){
                    suKienDcChon="yellow";
                    Toast.makeText(AdminCapNhatTranDauNewActivity.this, suKienDcChon, Toast.LENGTH_SHORT).show();
                }
                else if(position==3){
                    suKienDcChon="red";
                    Toast.makeText(AdminCapNhatTranDauNewActivity.this, suKienDcChon, Toast.LENGTH_SHORT).show();
                }
                else if(position==4){
                    suKienDcChon="goal";
                    Toast.makeText(AdminCapNhatTranDauNewActivity.this, suKienDcChon, Toast.LENGTH_SHORT).show();
                }
                else if(position==5){
                    suKienDcChon="assist";
                    Toast.makeText(AdminCapNhatTranDauNewActivity.this, suKienDcChon, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}