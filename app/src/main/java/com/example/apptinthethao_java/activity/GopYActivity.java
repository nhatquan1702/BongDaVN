package com.example.apptinthethao_java.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.apptinthethao_java.R;
import com.example.apptinthethao_java.api.JavaMailAPI;

public class GopYActivity extends AppCompatActivity {
    EditText etMessage;
    TextView tvSubject;
    Button btnSend;
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gop_y);
        tvSubject=(TextView)findViewById(R.id.tvTieuDe);
        sharedPreferences = getSharedPreferences("dataLogin", MODE_PRIVATE);
        String username=sharedPreferences.getString("email", "username");
        Log.d("usera", username);
        if(username.equalsIgnoreCase("username")){
            tvSubject.setText("Ẩn danh");
        }else{
            tvSubject.setText(username);
        }

        etMessage=findViewById(R.id.edtNoiDung);
        btnSend=findViewById(R.id.btnGui);
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMail();
            }
        });
    }

    private void sendMail() {
        sharedPreferences = getSharedPreferences("dataLogin", MODE_PRIVATE);
        String username=sharedPreferences.getString("email", "username");
        Log.d("usera", username);
        if(username.equalsIgnoreCase("username")){
            tvSubject.setText("Ẩn danh");
        }else{
            tvSubject.setText(username);
        }
        String mail = "ankhoa18599.edu@gmail.com";
        String subject = "["+tvSubject.getText().toString()+"] GÓP Ý ỨNG DỤNG";
        String message = etMessage.getText().toString();

        //Send mail
        JavaMailAPI javaMailAPI=new JavaMailAPI(GopYActivity.this,mail,subject,message);
        javaMailAPI.execute();
    }
}