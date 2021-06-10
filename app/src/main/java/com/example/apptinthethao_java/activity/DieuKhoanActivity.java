package com.example.apptinthethao_java.activity;

import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.apptinthethao_java.R;

public class DieuKhoanActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dieu_khoan);
        Animation animationx = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.tranlation_x);
        TextView tvNoiDungDieuKhoan = findViewById(R.id.tvNoiDungDieuKhoan);
        tvNoiDungDieuKhoan.setAnimation(animationx);
        LinearLayout linearLayout;
        linearLayout=findViewById(R.id.lnDN);
        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.uptodowndiagonal);
        linearLayout.setAnimation(animation);
    }
}