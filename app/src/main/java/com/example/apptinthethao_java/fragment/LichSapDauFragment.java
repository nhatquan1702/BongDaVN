package com.example.apptinthethao_java.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.example.apptinthethao_java.R;
import com.example.apptinthethao_java.api.SimpleAPI;
import com.example.apptinthethao_java.model.NgayThiDau;
import com.example.apptinthethao_java.model.TranDau;
import com.example.apptinthethao_java.util.Constants;
import com.example.apptinthethao_java.view.NextView;

import java.util.ArrayList;

public class LichSapDauFragment extends Fragment implements NextView {

    private SwipeRefreshLayout mSwipeRefresh;
    private ProgressBar mProgressBar;
    private SimpleAPI simpleAPI;
    private ArrayList<NgayThiDau> ngayThiDauArrayList;
    private ArrayList<TranDau> tranDauArrayList;

    public LichSapDauFragment() {
        // Required empty public constructor
    }

    public static LichSapDauFragment newInstance() {
        LichSapDauFragment fragment = new LichSapDauFragment();
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
        return rootView;
    }

    private void LoadDataLichDau(){
        simpleAPI = Constants.instance();
        // lay list ngay sap dau
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
    public void ShowNextList() {

    }
}