package com.example.apptinthethao_java.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.apptinthethao_java.R;
import com.example.apptinthethao_java.adapter.CauLacBoAdapter;
import com.example.apptinthethao_java.api.SimpleAPI;
import com.example.apptinthethao_java.model.CauLacBo;
import com.example.apptinthethao_java.util.Constants;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CauLacBoActivity extends AppCompatActivity {
    private ArrayList<CauLacBo> cauLacBoArrayList;
    private CauLacBoAdapter cauLacBoAdapter;
    private ListView listViewCLB;
    private SimpleAPI simpleAPI;
    private String id_CLB = "Binh Dinh FC";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cau_lac_bo);
        listViewCLB = findViewById(R.id.listViewDSCLB);
        cauLacBoArrayList = new ArrayList<>();
        LoadCLB();

    }
    private void LoadCLB(){
        simpleAPI = Constants.instance();
        simpleAPI.getListCLB().enqueue(new Callback<ArrayList<CauLacBo>>() {
            @Override
            public void onResponse(Call<ArrayList<CauLacBo>> call, Response<ArrayList<CauLacBo>> response) {
                cauLacBoArrayList = response.body();
                cauLacBoAdapter = new CauLacBoAdapter(cauLacBoArrayList);
                listViewCLB.setAdapter(cauLacBoAdapter);
                listViewCLB.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        id_CLB = String.valueOf(cauLacBoArrayList.get(position).getTenCLB());
                        Intent intent = new Intent(CauLacBoActivity.this, ChiTietCLBActivity.class);
                        intent.putExtra("clb_id", String.valueOf(cauLacBoArrayList.get(position).getTenCLB()));
                        startActivity(intent);
                    }
                });
            }

            @Override
            public void onFailure(Call<ArrayList<CauLacBo>> call, Throwable t) {
                Toast.makeText(CauLacBoActivity.this, t.toString(), Toast.LENGTH_SHORT).show();
                t.printStackTrace();
            }
        });
    }
    public String getId_CLB() {
        return id_CLB;
    }
}