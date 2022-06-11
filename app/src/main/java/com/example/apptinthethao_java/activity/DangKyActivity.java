package com.example.apptinthethao_java.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.apptinthethao_java.R;
import com.example.apptinthethao_java.api.SimpleAPI;
import com.example.apptinthethao_java.model.Status;
import com.example.apptinthethao_java.model.User;
import com.example.apptinthethao_java.util.Constants;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DangKyActivity extends AppCompatActivity {
    private SimpleAPI simpleAPI;
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$", Pattern.CASE_INSENSITIVE);// cấu trúc 1 email thông thường
    //public static final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
    private static final Pattern PASS_PATTERN = Pattern.compile("^(?=.*[0-9])"
            + "(?=.*[a-z])(?=.*[A-Z])"
            + "(?=.*[.@#$%^&+=])"
            + "(?=\\S+$).{8,20}$", Pattern.CASE_INSENSITIVE);;
//            ^ represents starting character of the string.
//            (?=.*[0-9]) represents a digit must occur at least once.
//            (?=.*[a-z]) represents a lower case alphabet must occur at least once.
//            (?=.*[A-Z]) represents an upper case alphabet that must occur at least once.
//            (?=.*[@#$%^&-+=()] represents a special character that must occur at least once.
//            (?=\\S+$) white spaces don’t allowed in the entire string.
//            .{8, 20} represents at least 8 characters and at most 20 characters.
//            $ represents the end of the string.
    public static boolean validateEmail(String emailStr) {
        Matcher matcher = EMAIL_PATTERN.matcher(emailStr);
        return matcher.matches();
    }

    public static boolean validatePass(String pass) {
        Matcher matcher = PASS_PATTERN.matcher(pass);
        return matcher.matches();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_ky);
        RelativeLayout rLayoutDK = findViewById(R.id.rLayoutDK);
        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.uptodowndiagonal);
        rLayoutDK.setAnimation(animation);
        Animation animationx = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.tranlation_x);
        TextView tvNhapEmail, tvNhapPass1, tvNhapPass2;
        EditText edtNhapEmailDK,edtNhapPass1, edtNhapPass2, edtNhapUsernameDK;
        CheckBox checkShowPassDK = findViewById(R.id.checkShowPassDK);
        tvNhapEmail = findViewById(R.id.tvNhapEmail);
        tvNhapPass1 = findViewById(R.id.tvNhapPass1);
        tvNhapPass2 = findViewById(R.id.tvNhapPass2);
        edtNhapEmailDK = findViewById(R.id.edtNhapEmailDK);
        edtNhapPass1 = findViewById(R.id.edtNhapPass1);
        edtNhapPass2 = findViewById(R.id.edtNhapPass2);

        tvNhapEmail.setAnimation(animationx);
        edtNhapEmailDK.setAnimation(animationx);
        tvNhapPass1.setAnimation(animationx);
        edtNhapPass1.setAnimation(animationx);
        tvNhapPass2.setAnimation(animationx);
        edtNhapPass2.setAnimation(animationx);
        checkShowPassDK.setAnimation(animationx);
        checkShowPassDK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkShowPassDK.isChecked())    {
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
        CardView cvDKTK = findViewById(R.id.cvDKTK);
        cvDKTK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = edtNhapEmailDK.getText().toString().trim();
                String pass1 = edtNhapPass1.getText().toString().trim();
                String pass2 = edtNhapPass2.getText().toString().trim();
                boolean isValid = true;
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

                if(!validatePass(pass1)){
                    edtNhapPass1.setError("Mật khẩu không đúng định dạng!");
                    edtNhapPass1.requestFocus();
                    isValid = false;
                }

                if(!validateEmail(email)){
                    edtNhapEmailDK.setError("Email không hợp lệ!");
                    edtNhapEmailDK.requestFocus();
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
                if(isValid){
  //                  Toast.makeText(DangKyActivity.this, "Pass OK", Toast.LENGTH_SHORT).show();

                    simpleAPI = Constants.instance();
                    User newUser = new User(email, pass1, 0);
                    simpleAPI.postUser(newUser).enqueue(new Callback<Status>() {
                        @Override
                        public void onResponse(Call<Status> call, Response<Status> response) {
                            //Log.d("quan", "res: " + response.body().getStatus());
                            switch (response.body().getStatus()){
                                case 0:{
                                    Toast.makeText(DangKyActivity.this, "Đã có lỗi xảy ra, vui lòng thử lại sau!", Toast.LENGTH_SHORT).show();
                                    break;
                                }
                                case 1:{
                                    Toast.makeText(DangKyActivity.this, "Tài khoản đã tồn tại!", Toast.LENGTH_SHORT).show();
                                    break;
                                }
                                case 2:{
                                    //Toast.makeText(DangKyActivity.this, "Tài khoản OK!", Toast.LENGTH_SHORT).show();

                                    Intent intent;
                                    SharedPreferences sharedPreferences = getSharedPreferences("dataLogin", Context.MODE_PRIVATE);
                                    SharedPreferences.Editor editor = sharedPreferences.edit();
                                    editor.putString("email", email);
                                    editor.putString("pass", pass1);
                                    editor.putString("role", "0");
                                    editor.commit();

                                    Toast.makeText(DangKyActivity.this, "Tạo tài khoản thành công!", Toast.LENGTH_SHORT).show();
                                    intent = new Intent(DangKyActivity.this, MainActivity.class);
                                    startActivity(intent);
                                    finish();
                                    break;
                                }
                            }
                        }

                        @Override
                        public void onFailure(Call<Status> call, Throwable t) {
                            Toast.makeText(DangKyActivity.this, "Lỗi: "+t.toString(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }

            }
        });
    }
}