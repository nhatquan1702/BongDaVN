package com.example.apptinthethao_java.activity;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.apptinthethao_java.R;

public class LichThiDauActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lich_thi_dau);
        TextView textView = findViewById(R.id.textView);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GoToMatch("2021_BECAMEX Binh Duong FC_DUOC NAM HA Nam Dinh FC");
            }
        });
    }
    public void GoToMatch(String id_match){
        Intent intent = new Intent(LichThiDauActivity.this, DienBienActivity.class);
        intent.putExtra("match_id", id_match);
        startActivity(intent);
    }
}