package com.example.apptinthethao_java;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class AdminTranDauActivity extends AppCompatActivity {
    private CardView cardViewSap, cardViewDa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_tran_dau);
        cardViewSap = findViewById(R.id.cvSapDienRa);
        cardViewDa = findViewById(R.id.cvDaDienRa);

        cardViewSap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminTranDauActivity.this, TaoTranDauSapDienRaActivity.class);
                startActivity(intent);
            }
        });

        cardViewDa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminTranDauActivity.this, TaoTranDauDaDienRaActivity.class);
                startActivity(intent);
            }
        });
    }

}