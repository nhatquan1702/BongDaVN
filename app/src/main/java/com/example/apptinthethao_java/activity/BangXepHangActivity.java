package com.example.apptinthethao_java.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.apptinthethao_java.R;
import com.example.apptinthethao_java.adapter.BangXepHangAdapter;
import com.example.apptinthethao_java.adapter.CauLacBoAdapter;
import com.example.apptinthethao_java.api.SimpleAPI;
import com.example.apptinthethao_java.model.BXH_DoiBong;
import com.example.apptinthethao_java.model.CauLacBo;
import com.example.apptinthethao_java.model.KetQua_TranDau;
import com.example.apptinthethao_java.util.Constants;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BangXepHangActivity extends AppCompatActivity {

    private RecyclerView rcvlBXH;
    private BangXepHangAdapter adapter;
    private SimpleAPI simpleAPI;
    private SimpleAPI simpleAPIkq;
    private ArrayList<KetQua_TranDau> kqTranDau=new ArrayList<>();
    private ArrayList<BXH_DoiBong> bxhDoiBong = new ArrayList<>();
    private ArrayList<CauLacBo> cauLacBoArrayList=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bang_xep_hang);
        rcvlBXH=(RecyclerView)findViewById(R.id.rcvBXH);
        setRecyclerView();
    }

    private void setRecyclerView() {
        rcvlBXH.setHasFixedSize(true);
        rcvlBXH.setLayoutManager(new LinearLayoutManager(this));
//        adapter = new BangXepHangAdapter(this,getList());
        rcvlBXH.setAdapter(adapter);
    }

    private int SearchDoiBong(ArrayList<BXH_DoiBong> arr, String tenDB) {
        for (int i = 0; i < arr.size(); i++) {
            if (arr.get(i).getTenDoiBong().equalsIgnoreCase(tenDB)) {
                return i;
            }
        }
        return -1;
    }
}