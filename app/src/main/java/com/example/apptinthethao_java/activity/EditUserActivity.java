package com.example.apptinthethao_java.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.apptinthethao_java.R;
import com.example.apptinthethao_java.api.SimpleAPI;
import com.example.apptinthethao_java.model.Status;
import com.example.apptinthethao_java.model.User;
import com.example.apptinthethao_java.util.Constants;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditUserActivity extends AppCompatActivity {
    RadioButton rAdmin;
    RadioButton rUser;
    Button btnDelete, btnUpdate;
    CheckBox cbShowPass;
    EditText edtNhapEmailDK,edtNhapPass1, edtNhapPass2;
    String user_id, role, pass, mode;
    private SimpleAPI simpleAPI;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user);
        rAdmin = findViewById(R.id.rAdmin);
        rUser = findViewById(R.id.rUser);
        btnDelete = findViewById(R.id.btnDelete);
        btnUpdate = findViewById(R.id.btnUpdate);
        cbShowPass = findViewById(R.id.checkShowPassDK);
        edtNhapPass1 = findViewById(R.id.edtNhapPass1);
        edtNhapPass2 = findViewById(R.id.edtNhapPass2);
        edtNhapEmailDK = findViewById(R.id.edtNhapEmailDK);

        loadButtonAction();
        Intent intent = getIntent();
        user_id = intent.getStringExtra("user_id");
        role = intent.getStringExtra("role");
        pass = intent.getStringExtra("pass");
        mode = intent.getStringExtra("mode");
        if(mode != null && mode.equals("selfEdit")){
            ((RadioGroup) findViewById(R.id.rGroup)).setVisibility(View.GONE);
            btnDelete.setVisibility(View.GONE);
        }
        loadInfoUser(user_id, role);
    }

    private void loadInfoUser(String user_id, String role){
        edtNhapEmailDK.setText(user_id);
        edtNhapEmailDK.setEnabled(false);
        if(role.equals("1")){
            rAdmin.setChecked(true);
        }
        else{
            rUser.setChecked(true);
        }
        //Toast.makeText(EditUserActivity.this, user_id, Toast.LENGTH_SHORT).show();
    }

    private void loadButtonAction(){
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isValid = true;
                String pass1 = edtNhapPass1.getText().toString().trim();
                String pass2 = edtNhapPass2.getText().toString().trim();
                // có sửa pass
                if (!(pass1.length() == 0 && pass2.length() == 0)) {
                    if (!pass1.equals(pass2)) {
                        edtNhapPass2.setError("Mật khẩu không khớp!");
                        edtNhapPass2.requestFocus();
                        isValid = false;
                    }
                }
                if (isValid) {
                    simpleAPI = Constants.instance();
                    if(rAdmin.isChecked()){
                        role = "1";
                    }
                    else{
                        role = "0";
                    }
                    User newUser = new User(user_id, pass, Integer.parseInt(role));
                    simpleAPI.putUser(newUser).enqueue(new Callback<Status>() {
                        @Override
                        public void onResponse(Call<Status> call, Response<Status> response) {
                            switch (response.body().getStatus()) {
                                case 0: {
                                    Toast.makeText(EditUserActivity.this, "Đã có lỗi xảy ra, vui lòng thử lại sau!", Toast.LENGTH_SHORT).show();
                                    break;
                                }
                                case 1: {
                                    Toast.makeText(EditUserActivity.this, "Tài khoản đã tồn tại!", Toast.LENGTH_SHORT).show();
                                    break;
                                }
                                case 2: {
                                    Intent intent;
                                    Toast.makeText(EditUserActivity.this, "Cập nhật tài khoản thành công!", Toast.LENGTH_SHORT).show();
                                    intent = new Intent(EditUserActivity.this, ListUserActivity.class);
                                    intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                                    startActivityIfNeeded(intent, 0);
                                    finish();
                                    break;
                                }
                            }
                        }

                        @Override
                        public void onFailure(Call<Status> call, Throwable t) {

                        }
                    });
                };
            }});

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                simpleAPI = Constants.instance();
                simpleAPI.delUser(user_id).enqueue(new Callback<Status>() {
                    @Override
                    public void onResponse(Call<Status> call, Response<Status> response) {
                        try{
                            switch (response.body().getStatus()) {
                                case 0: {
                                    Toast.makeText(EditUserActivity.this, "Đã có lỗi xảy ra, vui lòng thử lại sau!", Toast.LENGTH_SHORT).show();
                                    break;
                                }
                                case 1: {
                                    Toast.makeText(EditUserActivity.this, "Tài khoản không tồn tại!", Toast.LENGTH_SHORT).show();
                                    break;
                                }
                                case 2: {
                                    Intent intent;
                                    Toast.makeText(EditUserActivity.this, "Xóa tài khoản thành công!", Toast.LENGTH_SHORT).show();
                                    intent = new Intent(EditUserActivity.this, ListUserActivity.class);
                                    intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                                    startActivityIfNeeded(intent, 0);
                                    finish();
                                    break;
                                }
                            }
                        }catch(Exception e){
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(Call<Status> call, Throwable t) {
                        Toast.makeText(EditUserActivity.this, "Đã có lỗi xảy ra, vui lòng thử lại sau!", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        cbShowPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(cbShowPass.isChecked())    {
                    edtNhapPass1.setInputType(InputType.TYPE_CLASS_TEXT);
                    edtNhapPass2.setInputType(InputType.TYPE_CLASS_TEXT);
                } else {
                    edtNhapPass1.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    edtNhapPass2.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                }
                edtNhapPass1.setSelection(edtNhapPass1.getText().length());
                edtNhapPass2.setSelection(edtNhapPass2.getText().length());
            }
        });
    }
}