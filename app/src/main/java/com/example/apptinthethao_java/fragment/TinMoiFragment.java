package com.example.apptinthethao_java.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.apptinthethao_java.R;
import com.example.apptinthethao_java.activity.DetailPostActivity;
import com.example.apptinthethao_java.activity.LichThiDauActivity;
import com.example.apptinthethao_java.activity.MainActivity;
import com.example.apptinthethao_java.adapter.PostAdapter;
import com.example.apptinthethao_java.api.SimpleAPI;
import com.example.apptinthethao_java.model.Post;
import com.example.apptinthethao_java.util.Constants;
import com.facebook.shimmer.ShimmerFrameLayout;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class TinMoiFragment extends Fragment {
    private ArrayList<Post> postArrayList ;
    private ListView listViewTinMoi;
    private PostAdapter postAdapter;
    private SimpleAPI simpleAPI;
    private View view;
    private ShimmerFrameLayout shimmerFrameFB;
    private SwipeRefreshLayout mSwipeRefresh;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_tin_moi, container, false);
        listViewTinMoi = view.findViewById(R.id.listViewTinMoi);
        shimmerFrameFB = view.findViewById(R.id.shimmerFrame);
        postArrayList= new ArrayList<>();
        LoadDataTinMoi();
//        mSwipeRefresh.setOnRefreshListener(()->{
//            LoadDataTinMoi();
//            mSwipeRefresh.setRefreshing(false);
//        });

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        shimmerFrameFB.startShimmer();
    }

    private void LoadDataTinMoi() {
        simpleAPI = Constants.instance();
        simpleAPI.getListTinMoi().enqueue(new Callback<ArrayList<Post>>() {
            @Override
            public void onResponse(Call<ArrayList<Post>> call, Response<ArrayList<Post>> response) {
                postArrayList = response.body();
                postAdapter = new PostAdapter(getContext(), postArrayList);
                listViewTinMoi.setAdapter(postAdapter);
                listViewTinMoi.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent intent = new Intent(getContext(), DetailPostActivity.class);
                        intent.putExtra("post_id", String.valueOf(postArrayList.get(position).getPost_id()));
                        startActivity(intent);
                    }
                });
                shimmerFrameFB.stopShimmer();
                shimmerFrameFB.setVisibility(view.GONE);
            }

            @Override
            public void onFailure(Call<ArrayList<Post>> call, Throwable t) {
                Toast.makeText(getContext(), t.toString(), Toast.LENGTH_SHORT).show();
                t.printStackTrace();
            }
        });
    }
}