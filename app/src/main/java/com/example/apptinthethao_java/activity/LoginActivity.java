package com.example.apptinthethao_java.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.apptinthethao_java.R;
import com.example.apptinthethao_java.doQueryDBTask;

public class LoginActivity extends AppCompatActivity {
    doQueryDBTask mDoQueryDBTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
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

//        mDoQueryDBTask = new doQueryDBTask(LoginActivity.this);
//        //Gọi hàm execute để kích hoạt tiến trình
//         mDoQueryDBTask.execute("select * from Account");

        checkShowPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if(checkShowPass.isChecked())    {
//                    edtPassLogin.transformationMethod = HideReturnsTransformationMethod.getInstance();
//                } else {
//                    edtPassLogin.transformationMethod = PasswordTransformationMethod.getInstance();
//                }
            }
        });

        cvDNLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = edtEmailLogin.getText().toString().trim();
                String pass = edtPassLogin.getText().toString().trim();

                if(email.isEmpty()){
                    edtEmailLogin.setError("Vui lòng kiểm tra lại tài khoản!");
                    edtEmailLogin.requestFocus();

                }
                if(pass.isEmpty()){
                    edtPassLogin.setError("Vui lòng kiểm tra lại mật khẩu!");
                    edtPassLogin.requestFocus();

                }
                SharedPreferences sharedPreferences = getSharedPreferences("dataLogin", Context.MODE_PRIVATE);
                 SharedPreferences.Editor editor = sharedPreferences.edit();
//                        if (!response.body().getAccessToken().equals(null)) {
//                            editor.apply {
//                                putString("email", email)
//                                putString("token", response.body()?.getAccessToken())
//                                putString("pass", pass)
//                                putString("username", response.body()?.getUserName())
//                            }.apply()
//                            editor.commit()
//                            Toast.makeText(
//                                    applicationContext,
//                                    "Đăng nhập thành công!",
//                                    Toast.LENGTH_SHORT
//                            ).show()
//                            val intent = Intent(applicationContext, MainActivity::class.java)
//                            startActivity(intent)
//                            onBackPressed()
//                        } else {
//                            Toast.makeText(
//                                    applicationContext,
//                                    "Đăng nhập thất bại!",
//                                    Toast.LENGTH_SHORT
//                            ).show()
//                        }
                    }

//                })
//            }
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