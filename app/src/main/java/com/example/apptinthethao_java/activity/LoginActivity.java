package com.example.apptinthethao_java.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.apptinthethao_java.R;
import com.example.apptinthethao_java.adapter.CauLacBoAdapter;
import com.example.apptinthethao_java.api.SimpleAPI;
import com.example.apptinthethao_java.doQueryDBTask;
import com.example.apptinthethao_java.model.CauLacBo;
import com.example.apptinthethao_java.model.User;
import com.example.apptinthethao_java.util.Constants;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    private SimpleAPI simpleAPI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_login);
        // kết nối db
        RelativeLayout rLayoutDN = findViewById(R.id.rLayoutDN);
        RelativeLayout rLayoutAdd = findViewById(R.id.rLayoutAdd);
        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.uptodowndiagonal);
        rLayoutDN.setAnimation(animation);
        Animation animationx = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.tranlation_x);
        EditText edtEmailLogin = findViewById(R.id.edtEmailLogin);
        edtEmailLogin.setAnimation(animationx);
        TextView tvEmailDN = findViewById(R.id.tvEmailDN);
        tvEmailDN.setAnimation(animationx);
        EditText edtPassLogin = findViewById(R.id.edtPassLogin);
        edtPassLogin.setAnimation(animationx);
        TextView tvPassDN = findViewById(R.id.tvPassDN);
        tvPassDN.setAnimation(animationx);
        CheckBox checkShowPass = findViewById(R.id.checkShowPass);
        checkShowPass.setAnimation(animationx);
        Animation animationy = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.tranlation_y);
        rLayoutAdd.setAnimation(animationy);
        CardView cvDNLogin = findViewById(R.id.cvDNLogin);
        ImageView imgbtnDKTK = findViewById(R.id.imgbtnDKTK);


        checkShowPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkShowPass.isChecked())    {
                    edtPassLogin.setInputType(InputType.TYPE_CLASS_TEXT);
                } else {
                    edtPassLogin.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                }
                edtPassLogin.setSelection(edtPassLogin.getText().length());
            }
        });

        cvDNLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = edtEmailLogin.getText().toString().trim();
                String pass = edtPassLogin.getText().toString().trim();
                boolean isValid = true;
                if(email.isEmpty()){
                    edtEmailLogin.setError("Vui lòng kiểm tra lại tài khoản!");
                    edtEmailLogin.requestFocus();
                    isValid = false;
                }
                if(pass.isEmpty()){
                    edtPassLogin.setError("Vui lòng kiểm tra lại mật khẩu!");
                    edtPassLogin.requestFocus();
                    isValid = false;
                }

                if(isValid){
                    SharedPreferences sharedPreferences = getSharedPreferences("dataLogin", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();

                    simpleAPI = Constants.instance();
                    simpleAPI.getLoginResult(email, pass).enqueue(new Callback<ArrayList<User>>() {
                        @Override
                        public void onResponse(Call<ArrayList<User>> call, Response<ArrayList<User>> response) {
                            ArrayList<User> result = response.body();
                            if(result.size() < 1){
                                Toast.makeText(LoginActivity.this, "Sai tên đăng nhập hoặc mật khẩu!", Toast.LENGTH_SHORT).show();
                            }
                            else{
                                Intent intent;
                                editor.putString("email", email);
                                editor.putString("pass", pass);
                                editor.putString("role", String.valueOf(result.get(0).getRole()));
                                editor.commit();
                                Toast.makeText(LoginActivity.this, "Đăng nhập thành công!", Toast.LENGTH_SHORT).show();
                                intent = new Intent(LoginActivity.this, MainActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        }

                        @Override
                        public void onFailure(Call<ArrayList<User>> call, Throwable t) {
                            Toast.makeText(LoginActivity.this, "Đăng nhập thất bại!", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }

        });
        imgbtnDKTK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, DangKyActivity.class);
                startActivity(intent);
            }
        });
    }
}