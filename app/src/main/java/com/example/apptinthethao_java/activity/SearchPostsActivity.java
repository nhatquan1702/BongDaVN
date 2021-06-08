package com.example.apptinthethao_java.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.apptinthethao_java.R;
import com.example.apptinthethao_java.adapter.PostSearchAdapter;
import com.example.apptinthethao_java.api.SimpleAPI;
import com.example.apptinthethao_java.model.Post;
import com.example.apptinthethao_java.util.Constants;
import com.facebook.shimmer.ShimmerFrameLayout;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchPostsActivity extends AppCompatActivity {
    private ShimmerFrameLayout shimmerFrameLayout;
    private SimpleAPI simpleAPI;
    private ArrayList<Post> postArrayList;
    private PostSearchAdapter postAdapter;
    private ListView listViewSearch;
    private TextView textViewSearch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_posts);
        listViewSearch = findViewById(R.id.listViewSearch);
        Intent intent = getIntent();
        String searchText = intent.getStringExtra("searchText");
        shimmerFrameLayout = findViewById(R.id.shimmerFrameSearch);
        textViewSearch = findViewById(R.id.tvSearch);
        LoadResultSearch(searchText);
    }

    @Override
    protected void onResume() {
        super.onResume();
        shimmerFrameLayout.startShimmer();
    }

    private void LoadResultSearch(String searchText) {
        postArrayList = new ArrayList<>();
        simpleAPI = Constants.instance();
        simpleAPI.getListPostSearch(searchText).enqueue(new Callback<ArrayList<Post>>() {
            @Override
            public void onResponse(Call<ArrayList<Post>> call, Response<ArrayList<Post>> response) {
                postArrayList = response.body();
                if(postArrayList.size()>0){
                    textViewSearch.setText("Kết quả tìm kiếm");
                    postAdapter = new PostSearchAdapter(SearchPostsActivity.this, postArrayList);
                    listViewSearch.setAdapter(postAdapter);
                    listViewSearch.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            Intent intent = new Intent(SearchPostsActivity.this, DetailPostActivity.class);
                            intent.putExtra("post_id", String.valueOf(postArrayList.get(position).getPost_id()));
                            startActivity(intent);
                        }
                    });
                    shimmerFrameLayout.stopShimmer();
                    shimmerFrameLayout.setVisibility(View.GONE);
                }
                else {
                    textViewSearch.setText("Không có kết quả tìm kiếm");
                    Toast.makeText(SearchPostsActivity.this, "Không có kết quả tìm kiếm", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Post>> call, Throwable t) {
                Toast.makeText(SearchPostsActivity.this, t.toString(), Toast.LENGTH_SHORT).show();
                t.printStackTrace();
            }
        });
    }
}