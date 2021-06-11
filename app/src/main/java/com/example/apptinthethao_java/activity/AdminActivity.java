package com.example.apptinthethao_java.activity;

import androidx.appcompat.app.AppCompatActivity;
import com.example.apptinthethao_java.R;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AdminActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        Button btnTaiKhoan = findViewById(R.id.btnTaiKhoan);
        Button btnBaiViet = findViewById(R.id.btnBaiViet);
        Button btnCauThu = findViewById(R.id.btnCauThu);
        Button btnTranDau = findViewById(R.id.btnTranDau);
        Button btnDoiBong = findViewById(R.id.btnDoiBong);

        btnTaiKhoan.setOnClickListener(this);
        btnBaiViet.setOnClickListener(this);
        btnCauThu.setOnClickListener(this);
        btnTranDau.setOnClickListener(this);
        btnDoiBong.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnTaiKhoan:{
                // chuyen acti
            }
            case R.id.btnBaiViet:{
                // chuyen acti
            }
            case R.id.btnCauThu:{
                // chuyen acti
            }
            case R.id.btnTranDau:{
                // chuyen acti
            }
            case R.id.btnDoiBong:{
                // chuyen acti
            }
        }
    }
}