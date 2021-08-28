package com.example.apptinthethao_java.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.apptinthethao_java.R;
import com.example.apptinthethao_java.adapter.BaiVietAdapter;
import com.example.apptinthethao_java.adapter.ItemClickInterface;
import com.example.apptinthethao_java.api.SimpleAPI;
import com.example.apptinthethao_java.model.Post;
import com.example.apptinthethao_java.model.Status;
import com.example.apptinthethao_java.model.TranDau;
import com.example.apptinthethao_java.util.Constants;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListBaiVietActivity extends AppCompatActivity {

    private ArrayList<Post> mData;
    private SimpleAPI simpleAPI;
    SharedPreferences sharedPreferences;
    private BaiVietAdapter adapter;
    String mAuthor = null;
    int requestCode = 2;
    RecyclerView mRecyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_bai_viet);

        Toolbar toolbar = findViewById(R.id.toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
         mRecyclerView = findViewById(R.id.rv_listbaiviet);
        mData = new ArrayList<>();
        sharedPreferences = getSharedPreferences("dataLogin", MODE_PRIVATE);
        mAuthor = sharedPreferences.getString("email", "admin");
        Log.d("author",mAuthor);
        LoadBaiViet();
        adapter = new BaiVietAdapter(getApplicationContext(),mData);
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListBaiVietActivity.this, BaiVietActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivityIfNeeded(intent, 0);
            }
        });

        ItemTouchHelper helper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull @NotNull RecyclerView recyclerView, @NonNull @NotNull RecyclerView.ViewHolder viewHolder, @NonNull @NotNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull @NotNull RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();
                Post mPost = adapter.getAtPosition(position);
                AlertDialog.Builder builder = new AlertDialog.Builder(ListBaiVietActivity.this);
                builder.setTitle("Xác nhận");
                builder.setMessage("Bạn có thực sự muốn xóa bài viết này?");
                builder.setPositiveButton("Xóa", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(), "Đã xóa" , Toast.LENGTH_SHORT).show();
                        //call delete
                        DeletePost(String.valueOf(mPost.getPost_id()));
                        LoadBaiViet();
                        adapter = new BaiVietAdapter(getApplicationContext(),mData);
                        mRecyclerView.setAdapter(adapter);
                        mRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    }
                });
                builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(), "Đã hủy thao tác" , Toast.LENGTH_SHORT).show();
                        LoadBaiViet();
                        adapter = new BaiVietAdapter(getApplicationContext(),mData);
                        mRecyclerView.setAdapter(adapter);
                        mRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

        helper.attachToRecyclerView(mRecyclerView);

        adapter.setOnItemClickListener(new ItemClickInterface() {
            @Override
            public void onClick(View view, int position) {
                Post mPost = adapter.getAtPosition(position);
                // call update
                Intent intent = new Intent(ListBaiVietActivity.this, SuaBaiVietActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("post_title", mPost.getPost_title());
                bundle.putString("post_content", mPost.getPost_content());
                bundle.putString("post_img", mPost.getPost_img());
                bundle.putInt("post_id", mPost.getPost_id());
                intent.putExtras(bundle);
                intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivityIfNeeded(intent, 0);
            }
        });
    }

    private void DeletePost(String post_id) {
        simpleAPI = Constants.instance();
        simpleAPI.deletePost(post_id).enqueue(new Callback<Status>() {
            @Override
            public void onResponse(Call<Status> call, Response<Status> response) {
                Status status = response.body();
                if(status.getStatus()==2){
                    Toast.makeText(ListBaiVietActivity.this, "Xóa bài viết thành công!", Toast.LENGTH_SHORT).show();
                    LoadBaiViet();
                    adapter = new BaiVietAdapter(getApplicationContext(),mData);
                    mRecyclerView.setAdapter(adapter);
                    mRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                }
                else {
                    Toast.makeText(ListBaiVietActivity.this, "Xóa bài viết không thành công!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Status> call, Throwable t) {
                Toast.makeText(ListBaiVietActivity.this, "Lỗi: "+t.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_list_bai_viet,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
            case android.R.id.home:{
                onBackPressed();
                return true;
            }
            case R.id.refresh:{
                LoadBaiViet();
                adapter = new BaiVietAdapter(getApplicationContext(),mData);
                mRecyclerView.setAdapter(adapter);
                mRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                return true;
            }
            case R.id.set_date:{
                Calendar now = Calendar.getInstance();
                DatePickerDialog datePicker = new DatePickerDialog(ListBaiVietActivity.this,(view, year, month, dayOfMonth) ->{
                    String strDate = "'" +year +"-"+ (month+1) +"-"+ dayOfMonth +"'";
                    mData = new ArrayList<>();
                    simpleAPI = Constants.instance();
                    simpleAPI.getBaiVietByAccountAndDate(mAuthor,strDate).enqueue(new Callback<ArrayList<Post>>() {
                        @Override
                        public void onResponse(Call<ArrayList<Post>> call, Response<ArrayList<Post>> response) {
                            mData = response.body();
                            Log.e("json", response.body().toString()+" " +strDate + " " + mAuthor + " " + String.valueOf(mData.size()));
                            if(mData.size() == 0)
                                Toast.makeText(ListBaiVietActivity.this,"không có bài viết nào vào ngày này",Toast.LENGTH_SHORT).show();
                            adapter.updateChange(mData);
                        }

                        @Override
                        public void onFailure(Call<ArrayList<Post>> call, Throwable t) {
                            if(call.isCanceled()) {
                                Log.d("fail", "request was aborted");
                            }else {
                                Log.d("fail", "Unable to submit post to API.");
                            }
                        }
                    });
                },now.get(Calendar.YEAR), now.get(Calendar.MONTH), now.get(Calendar.DAY_OF_MONTH));
                datePicker.show();
                return true;
            }
            default: {
                return super.onOptionsItemSelected(item);
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == requestCode) {
            if(resultCode == Activity.RESULT_OK){
                LoadBaiViet();
            }
            if (resultCode == Activity.RESULT_CANCELED) {
            }
        }
    }

    public void LoadBaiViet() {
        simpleAPI = Constants.instance();
        simpleAPI.getBaiVietBy(mAuthor).enqueue(new Callback<ArrayList<Post>>() {
            @Override
            public void onResponse(Call<ArrayList<Post>> call, Response<ArrayList<Post>> response) {
                Log.d("json", response.body().toString());
                mData = response.body();
                adapter.updateChange(mData);
            }

            @Override
            public void onFailure(Call<ArrayList<Post>> call, Throwable t) {
                if(call.isCanceled()) {
                    Log.d("fail", "request was aborted");
                }else {
                    Log.d("fail", "Unable to submit post to API.");
                }
            }
        });
    }
}