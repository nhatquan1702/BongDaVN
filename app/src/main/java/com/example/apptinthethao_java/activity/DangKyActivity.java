package com.example.apptinthethao_java.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.apptinthethao_java.R;

public class DangKyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_ky);
        RelativeLayout rLayoutDK = findViewById(R.id.rLayoutDK);
        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.uptodowndiagonal);
        rLayoutDK.setAnimation(animation);
        Animation animationx = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.tranlation_x);
        TextView tvNhapEmail,tvNhapUserName, tvNhapPass1, tvNhapPass2;
        EditText edtNhapEmailDK,edtNhapPass1, edtNhapPass2, edtNhapUsernameDK;
        CheckBox checkShowPassDK = findViewById(R.id.checkShowPassDK);
        tvNhapEmail = findViewById(R.id.tvNhapEmail);
        tvNhapUserName = findViewById(R.id.tvNhapUserName);
        tvNhapPass1 = findViewById(R.id.tvNhapPass1);
        tvNhapPass2 = findViewById(R.id.tvNhapPass2);
        edtNhapEmailDK = findViewById(R.id.edtNhapEmailDK);
        edtNhapUsernameDK = findViewById(R.id.edtNhapUsernameDK);
        edtNhapPass1 = findViewById(R.id.edtNhapPass1);
        edtNhapPass2 = findViewById(R.id.edtNhapPass2);

        tvNhapEmail.setAnimation(animationx);
        edtNhapEmailDK.setAnimation(animationx);
        tvNhapUserName.setAnimation(animationx);
        edtNhapUsernameDK.setAnimation(animationx);
        tvNhapPass1.setAnimation(animationx);
        edtNhapPass1.setAnimation(animationx);
        tvNhapPass2.setAnimation(animationx);
        edtNhapPass2.setAnimation(animationx);
        checkShowPassDK.setAnimation(animationx);
        checkShowPassDK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if(checkShowPassDK.isChecked())    {
//                    edtNhapPass1.transformationMethod = HideReturnsTransformationMethod.getInstance()
//                    edtNhapPass2.transformationMethod = HideReturnsTransformationMethod.getInstance()
//                } else {
//                    edtNhapPass1.transformationMethod = PasswordTransformationMethod.getInstance()
//                    edtNhapPass2.transformationMethod = PasswordTransformationMethod.getInstance()
//                }
            }
        });
        CardView cvDKTK = findViewById(R.id.cvDKTK);
        cvDKTK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = edtNhapUsernameDK.getText().toString().trim();
                String email = edtNhapEmailDK.getText().toString().trim();
                String pass1 = edtNhapPass1.getText().toString().trim();
                String pass2 = edtNhapPass2.getText().toString().trim();

                if(username.isEmpty()){
                    edtNhapUsernameDK.setError("Vui lòng kiểm tra lại tên người dùng!");
                    edtNhapUsernameDK.requestFocus();

                }
                if(email.isEmpty()){
                    edtNhapEmailDK.setError("Vui lòng kiểm tra lại email!");
                    edtNhapEmailDK.requestFocus();

                }

                if(pass1.isEmpty()){
                    edtNhapPass1.setError("Vui lòng kiểm tra lại mật khẩu!");
                    edtNhapPass1.requestFocus();

                }

                if(pass2.isEmpty()){
                    edtNhapPass2.setError("Vui lòng kiểm tra lại mật khẩu!");
                    edtNhapPass2.requestFocus();
                }
                if(!pass1.equals(pass2) || !pass2.equals(pass1))
                    edtNhapPass2.setError("Mật khẩu chưa trùng khớp!");
                //else dangKyTaiKhoan(username, email, pass1)
            }
        });
    }
}