package com.example.apptinthethao_java.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.apptinthethao_java.R;
import com.example.apptinthethao_java.adapter.CauThuAdapter;
import com.example.apptinthethao_java.adapter.UserAdapter;
import com.example.apptinthethao_java.api.SimpleAPI;
import com.example.apptinthethao_java.model.CauThuSimple;
import com.example.apptinthethao_java.model.User;
import com.example.apptinthethao_java.util.Constants;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListUserActivity extends AppCompatActivity {
    private UserAdapter userAdapter;
    private ListView listViewUser;
    private SimpleAPI simpleAPI;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_user);
        listViewUser = findViewById(R.id.listUser);
        Button addUser = findViewById(R.id.btnAddUser);
        addUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListUserActivity.this, AddUserActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivityIfNeeded(intent, 0);
            }
        });
        loadUser();
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
            }

            @Override
            public void onFailure(Call<ArrayList<User>> call, Throwable t) {

            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        loadUser();
    }
}