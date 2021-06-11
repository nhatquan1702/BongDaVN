package com.example.apptinthethao_java.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.apptinthethao_java.AdminTranDauActivity;
import com.example.apptinthethao_java.R;
import com.example.apptinthethao_java.api.SimpleAPI;
import com.example.apptinthethao_java.model.Analysis;
import com.example.apptinthethao_java.util.Constants;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdminActivity extends AppCompatActivity implements View.OnClickListener {
    private SimpleAPI simpleAPI;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        Button btnTaiKhoan = (Button) findViewById(R.id.btnTaiKhoan);
        Button btnBaiViet = (Button)  findViewById(R.id.btnBaiViet);
        Button btnTranDau = (Button)  findViewById(R.id.btnTranDau);

//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//        ActionBar actionBar = getSupportActionBar();
//
//        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        btnTaiKhoan.setOnClickListener(this);
        btnBaiViet.setOnClickListener(this);
        btnTranDau.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnTaiKhoan:{
                Intent intent = new Intent(AdminActivity.this, ListUserActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivityIfNeeded(intent, 0);
                break;
            }
            case R.id.btnBaiViet:{
                Intent intent = new Intent(AdminActivity.this, ListBaiVietActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.btnTranDau:{
                Intent intent = new Intent(AdminActivity.this, AdminTranDauActivity.class);
                startActivity(intent);
            }
        }
    }

    private void updateInfo(){
        simpleAPI = Constants.instance();
        simpleAPI.getAnalysis().enqueue(new Callback<Analysis>() {
            @Override
            public void onResponse(Call<Analysis> call, Response<Analysis> response) {
                Analysis res = response.body();
                ((TextView) findViewById(R.id.txtCountUser)).setText(String.valueOf(res.getCount_account()));
                ((TextView) findViewById(R.id.txtCountPost)).setText(String.valueOf(res.getCount_post()));
                ((TextView) findViewById(R.id.txtCountMatch)).setText(String.valueOf(res.getCount_match()));
            }

            @Override
            public void onFailure(Call<Analysis> call, Throwable t) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}