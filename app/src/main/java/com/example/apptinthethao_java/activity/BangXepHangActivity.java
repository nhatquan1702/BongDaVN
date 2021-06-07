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
        adapter = new BangXepHangAdapter(this,getList());
        rcvlBXH.setAdapter(adapter);
    }

        @NotNull
        private ArrayList<BXH_DoiBong> getList() {
        ArrayList<BXH_DoiBong> BXH = new ArrayList<>();
        getListKQ();
        getListCLB();
        Log.d("kq", String.valueOf(cauLacBoArrayList.size()));
        for (int i = 0; i <cauLacBoArrayList.size() ; i++) {
            BXH_DoiBong doibong = new BXH_DoiBong(cauLacBoArrayList.get(i).getTenCLB());
            BXH.add(doibong);
        }
        int indexWin, indexLose;
        for (int i = 0; i < kqTranDau.size(); i++) {
            if (kqTranDau.get(i).getTiSoHome() > kqTranDau.get(i).getTiSoGuess()) {
                indexWin = SearchDoiBong(BXH, kqTranDau.get(i).getClbHome());
                indexLose = SearchDoiBong(BXH, kqTranDau.get(i).getClbGuess());
                if (indexWin == -1 || indexLose == -1) {
                    continue;
                }
                BXH.get(indexWin).setThang(BXH.get(indexWin).getThang() + 1);
                BXH.get(indexLose).setThua(BXH.get(indexLose).getThua() + 1);
            } else if (kqTranDau.get(i).getTiSoHome() < kqTranDau.get(i).getTiSoGuess()) {
                indexLose = SearchDoiBong(BXH, kqTranDau.get(i).getClbHome());
                indexWin = SearchDoiBong(BXH, kqTranDau.get(i).getClbGuess());
                if (indexWin == -1 || indexLose == -1) {
                    continue;
                }
                BXH.get(indexWin).setThang(BXH.get(indexWin).getThang() + 1);
                BXH.get(indexLose).setThua(BXH.get(indexLose).getThua() + 1);
            } else {
                indexLose = SearchDoiBong(BXH, kqTranDau.get(i).getClbHome());
                indexWin = SearchDoiBong(BXH, kqTranDau.get(i).getClbGuess());
                if (indexWin == -1 || indexLose == -1) {
                    continue;
                }
                BXH.get(indexWin).setHoa(BXH.get(indexWin).getHoa() + 1);
                BXH.get(indexLose).setHoa(BXH.get(indexLose).getHoa() + 1);
            }
        }
        return BXH;
    }
    private void getListKQ() {
        Log.d("ketqua", "hihi1");
        simpleAPIkq = Constants.instance();
        Log.d("ketqua", "hihi2");
        simpleAPIkq.getListKetQuaTranDau("2021").enqueue(new Callback<ArrayList<KetQua_TranDau>>() {
            @Override
            public void onResponse(Call<ArrayList<KetQua_TranDau>> call, Response<ArrayList<KetQua_TranDau>> response) {
//                kqTranDau=response.body();
                Log.d("ketqua", "hihi3");
                Log.d("ketqua", String.valueOf(response.body()));
            }

            @Override
            public void onFailure(Call<ArrayList<KetQua_TranDau>> call, Throwable t) {
                Log.d("ketqua", "getListFail");
                Log.d("ketqua", t.getMessage());
            }
        });
    }
    private void getListCLB() {
        Log.d("qq", "1");
        simpleAPI = Constants.instance();
        Log.d("qq", "2");
        simpleAPI.getListCLB().enqueue(new Callback<ArrayList<CauLacBo>>() {
            @Override
            public void onResponse(Call<ArrayList<CauLacBo>> call, Response<ArrayList<CauLacBo>> response) {
                Log.d("qq", "3");
                ArrayList<CauLacBo> CLB_getAPI = new ArrayList<>();
                CLB_getAPI=response.body();
                for (int i = 0; i <CLB_getAPI.size() ; i++) {
                    CauLacBo clb = new CauLacBo();
                    clb.setTenCLB(CLB_getAPI.get(i).getTenCLB());
                    cauLacBoArrayList.add(clb);
                }
                Log.d("kq", String.valueOf(cauLacBoArrayList.size()));
            }

            @Override
            public void onFailure(Call<ArrayList<CauLacBo>> call, Throwable t) {
                Log.d("kq", "NhanCHiCLB");
            }
        });
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