package com.example.apptinthethao_java.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.apptinthethao_java.R;
import com.example.apptinthethao_java.adapter.BangXepHangAdapter;
import com.example.apptinthethao_java.adapter.CauLacBoAdapter;
import com.example.apptinthethao_java.api.SimpleAPI;
import com.example.apptinthethao_java.model.BXH_DoiBong;
import com.example.apptinthethao_java.model.CauLacBo;
import com.example.apptinthethao_java.model.KetQua_TranDau;
import com.example.apptinthethao_java.util.Constants;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BangXepHangActivity extends AppCompatActivity {

    private RecyclerView rcvlBXH;
    private BangXepHangAdapter adapter;
    private SimpleAPI simpleAPI;
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

//    private ArrayList<BXH_DoiBong> getList() {
//        ArrayList<BXH_DoiBong> BXH = new ArrayList<>();
//        BXH.add(new BXH_DoiBong("KHOA"));
//        BXH.add(new BXH_DoiBong("KHOA2"));
//        BXH.add(new BXH_DoiBong("KHOA3"));
//        BXH.add(new BXH_DoiBong("KHOA4"));
//        return BXH;
//    }

        private ArrayList<BXH_DoiBong> getList() {
        ArrayList<BXH_DoiBong> BXH = new ArrayList<>();
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
        simpleAPI = Constants.instance();
        simpleAPI.getListKetQuaTranDau().enqueue(new Callback<ArrayList<KetQua_TranDau>>() {
            @Override
            public void onResponse(Call<ArrayList<KetQua_TranDau>> call, Response<ArrayList<KetQua_TranDau>> response) {
                kqTranDau=response.body();
            }

            @Override
            public void onFailure(Call<ArrayList<KetQua_TranDau>> call, Throwable t) {
            }
        });
    }
    private void getListCLB() {
        simpleAPI = Constants.instance();
        simpleAPI.getListCLB().enqueue(new Callback<ArrayList<CauLacBo>>() {
            @Override
            public void onResponse(Call<ArrayList<CauLacBo>> call, Response<ArrayList<CauLacBo>> response) {
                cauLacBoArrayList = response.body();
            }

            @Override
            public void onFailure(Call<ArrayList<CauLacBo>> call, Throwable t) {

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