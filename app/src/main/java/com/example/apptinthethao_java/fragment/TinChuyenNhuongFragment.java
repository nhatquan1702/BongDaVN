package com.example.apptinthethao_java.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.apptinthethao_java.R;
import com.example.apptinthethao_java.activity.DetailPostActivity;
import com.example.apptinthethao_java.adapter.PostAdapter;
import com.example.apptinthethao_java.api.SimpleAPI;
import com.example.apptinthethao_java.model.Post;
import com.example.apptinthethao_java.util.Constants;
import com.facebook.shimmer.ShimmerFrameLayout;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class TinChuyenNhuongFragment extends Fragment {
    private ArrayList<Post> postArrayList;
    private ListView listViewTinChuyenNhuong;
    private PostAdapter postAdapter;
    private SimpleAPI simpleAPI;
    private View view;
    private ShimmerFrameLayout shimmerFrameFB;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.fragment_tin_chuyen_nhuong, container, false);
        listViewTinChuyenNhuong = view.findViewById(R.id.listViewTinChuyenNhuong);
        shimmerFrameFB = view.findViewById(R.id.shimmerFrame);
        LoadDataTinChuyenNhuong();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        shimmerFrameFB.startShimmer();
    }

    private void LoadDataTinChuyenNhuong() {
        simpleAPI = Constants.instance();
        simpleAPI.getListTinChuyenNhuong().enqueue(new Callback<ArrayList<Post>>() {
            @Override
            public void onResponse(Call<ArrayList<Post>> call, Response<ArrayList<Post>> response) {
                postArrayList = response.body();
                postAdapter = new PostAdapter(getContext(), postArrayList);
                listViewTinChuyenNhuong.setAdapter(postAdapter);
                listViewTinChuyenNhuong.setOnItemClickListener(new AdapterView.OnItemClickListener() {
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