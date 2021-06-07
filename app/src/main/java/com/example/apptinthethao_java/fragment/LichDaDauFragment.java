package com.example.apptinthethao_java.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.apptinthethao_java.R;
import com.example.apptinthethao_java.adapter.TranDauAdapter;
import com.example.apptinthethao_java.api.SimpleAPI;
import com.example.apptinthethao_java.model.NgayThiDau;
import com.example.apptinthethao_java.model.TranDau;
import com.example.apptinthethao_java.util.Constants;
import com.example.apptinthethao_java.view.LastView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LichDaDauFragment extends Fragment implements LastView {

    private SwipeRefreshLayout mSwipeRefresh;
    private ProgressBar mProgressBar;
    private SimpleAPI simpleAPI;
    private ArrayList<NgayThiDau> ngayThiDauArrayList;
    private ArrayList<Date> ngayDauArrayList;
    private ArrayList<TranDau> tranDauArrayList;

    public LichDaDauFragment() {
        // Required empty public constructor
    }

    public static LichDaDauFragment newInstance() {
        LichDaDauFragment fragment = new LichDaDauFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_lich_sap_dau, container, false);
        RecyclerView mRecyclerView = rootView.findViewById(R.id.rv_next_match);
        mSwipeRefresh = rootView.findViewById(R.id.swipe_refresh);
        mProgressBar = rootView.findViewById(R.id.progress_bar);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        LoadDataLichDau();
//        if(ngayThiDauArrayList.size() != 0)
//        {
//            Log.d("listngay", String.valueOf(ngayThiDauArrayList.size()));
//            TranDauAdapter adapter = new TranDauAdapter(ngayThiDauArrayList);
//            mRecyclerView.setAdapter(adapter);
//
//        }
        return rootView;
    }

    private void LoadDataLichDau(){
        //set date current
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String strDate = "'" + formatter.format(date) + "'";
        simpleAPI = Constants.instance();
        simpleAPI.getNgayDaDau(strDate).enqueue(new Callback<ArrayList<String>>() {
            @Override
            public void onResponse(Call<ArrayList<String>> call, Response<ArrayList<String>> response) {
                ArrayList<String> listdata = new ArrayList<String>();
                listdata = response.body();
//                ngayDauArrayList = response.body();
                Log.d("ngaydau",listdata.toString());
            }

            @Override
            public void onFailure(Call<ArrayList<String>> call, Throwable t) {
                Toast.makeText(getContext(), t.toString(), Toast.LENGTH_SHORT).show();
                Log.d("failCall",t.toString());
                t.printStackTrace();
            }
        });
        //lay list tran dau cua ngay do

    }

    @Override
    public void ShowLoading() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void HideLoading() {
        mProgressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void ShowLastList() {

    }
}