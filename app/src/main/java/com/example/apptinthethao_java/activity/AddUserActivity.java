package com.example.apptinthethao_java.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.apptinthethao_java.R;
import com.example.apptinthethao_java.api.SimpleAPI;
import com.example.apptinthethao_java.model.Status;
import com.example.apptinthethao_java.model.User;
import com.example.apptinthethao_java.util.Constants;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddUserActivity extends AppCompatActivity {
    RadioButton rAdmin;
    RadioButton rUser;
    Button btnDelete, btnUpdate;
    CheckBox cbShowPass;
    EditText edtNhapEmailDK,edtNhapPass1, edtNhapPass2;
    String role;
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
        btnDelete.setVisibility(View.GONE);
        btnUpdate.setText("Tạo Tài Khoản");
        rUser.setChecked(true);
        loadButtonAction();
    }

    private void loadButtonAction(){
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isValid = true;
                String pass1 = edtNhapPass1.getText().toString().trim();
                String pass2 = edtNhapPass2.getText().toString().trim();
                String email = edtNhapEmailDK.getText().toString().trim();
                // có sửa pass

                if(email.isEmpty()){
                    edtNhapEmailDK.setError("Vui lòng kiểm tra lại email!");
                    edtNhapEmailDK.requestFocus();
                    isValid = false;
                }

                if(pass1.isEmpty()){
                    edtNhapPass1.setError("Vui lòng kiểm tra lại mật khẩu!");
                    edtNhapPass1.requestFocus();
                    isValid = false;
                }

                if(pass2.isEmpty()){
                    edtNhapPass2.setError("Vui lòng kiểm tra lại mật khẩu!");
                    edtNhapPass2.requestFocus();
                    isValid = false;
                }
                if(!pass1.equals(pass2) || !pass2.equals(pass1)){
                    edtNhapPass2.setError("Mật khẩu chưa trùng khớp!");
                    isValid = false;
                }
                if (isValid) {
                    simpleAPI = Constants.instance();
                    if(rAdmin.isChecked()){
                        role = "1";
                    }
                    else{
                        role = "0";
                    }
                    User newUser = new User(email, pass1, Integer.parseInt(role));
                    simpleAPI.postUser(newUser).enqueue(new Callback<Status>() {
                        @Override
                        public void onResponse(Call<Status> call, Response<Status> response) {
                            switch (response.body().getStatus()) {
                                case 0: {
                                    Toast.makeText(AddUserActivity.this, "Đã có lỗi xảy ra, vui lòng thử lại sau!", Toast.LENGTH_SHORT).show();
                                    break;
                                }
                                case 1: {
                                    Toast.makeText(AddUserActivity.this, "Tài khoản đã tồn tại!", Toast.LENGTH_SHORT).show();
                                    break;
                                }
                                case 2: {
                                    Intent intent;
                                    Toast.makeText(AddUserActivity.this, "Tạo tài khoản thành công!", Toast.LENGTH_SHORT).show();
                                    intent = new Intent(AddUserActivity.this, ListUserActivity.class);
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