package com.example.apptinthethao_java.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.apptinthethao_java.R;
import com.example.apptinthethao_java.adapter.BaiVietAdapter;
import com.example.apptinthethao_java.adapter.CauThuAdapter;
import com.example.apptinthethao_java.adapter.UserAdapter;
import com.example.apptinthethao_java.api.SimpleAPI;
import com.example.apptinthethao_java.model.CauThuSimple;
import com.example.apptinthethao_java.model.Post;
import com.example.apptinthethao_java.model.User;
import com.example.apptinthethao_java.util.Constants;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListUserActivity extends AppCompatActivity {
    private UserAdapter userAdapter;
    private ListView listViewUser;
    private SimpleAPI simpleAPI;
    private FloatingActionButton fab1, fab2, fab3, fab4;
    private Boolean fabCheck = true;
    private ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_user);
        listViewUser = findViewById(R.id.listUser);
        fab1 = (FloatingActionButton) findViewById(R.id.fab1);
    //    fab2 = (FloatingActionButton) findViewById(R.id.fab2);
        fab3 = (FloatingActionButton) findViewById(R.id.fab3);
        fab4 = (FloatingActionButton) findViewById(R.id.fab4);
        LoadFab();
        progressBar = findViewById(R.id.progress_bar);
        progressBar.setVisibility(View.VISIBLE);
        loadUser();
    }
    private void FabVisible(){
     //   fab2.show();
        fab3.show();
        fab4.show();
    }
    private void FabHine(){
      //  fab2.hide();
        fab3.hide();
        fab4.hide();
    }
    private void LoadFab() {
        fab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(fabCheck){
                    FabVisible();
                    fab1.setImageResource(R.drawable.ic_close);
                    fabCheck = false;
                }
                else {
                    FabHine();
                    fab1.setImageResource(R.drawable.ic_add);
                    fabCheck = true;
                }
            }
        });
//        fab2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(ListUserActivity.this, "Tìm kiếm", Toast.LENGTH_SHORT).show();
//            }
//        });
        fab3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListUserActivity.this, AddUserActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivityIfNeeded(intent, 0);
            }
        });
        fab4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadUser();
            }
        });
    }

    private void loadUser(){
        simpleAPI = Constants.instance();
        simpleAPI.getListUsers().enqueue(new Callback<ArrayList<User>>() {
            @Override
            public void onResponse(Call<ArrayList<User>> call, Response<ArrayList<User>> response) {
                ArrayList<User> listUser = response.body();
                userAdapter = new UserAdapter(listUser);
                listViewUser.setAdapter(userAdapter);
                listViewUser.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent intent = new Intent(ListUserActivity.this, EditUserActivity.class);
                        intent.putExtra("user_id", listUser.get(position).getAccount_email());
                        intent.putExtra("role", String.valueOf(listUser.get(position).getRole()));
                        intent.putExtra("pass", listUser.get(position).getAccount_password());
                        intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                        startActivityIfNeeded(intent, 0);
                    }
                });
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<ArrayList<User>> call, Throwable t) {
                Toast.makeText(ListUserActivity.this, "Lỗi: "+t.toString(), Toast.LENGTH_SHORT).show();

            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        loadUser();
    }
}