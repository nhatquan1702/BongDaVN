package com.example.apptinthethao_java.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.apptinthethao_java.R;
import com.example.apptinthethao_java.api.JavaMailAPI;

public class GopYActivity extends AppCompatActivity {
    EditText etMessage, etSubject;
    TextView tvSubject;
    CardView btnSend;
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gop_y);
//        tvSubject=(TextView)findViewById(R.id.tvNhapEmail);
//        sharedPreferences = getSharedPreferences("dataLogin", MODE_PRIVATE);
//        String username=sharedPreferences.getString("email", "username");
//        Log.d("usera", username);
//        if(username.equalsIgnoreCase("username")){
//            tvSubject.setText("Ẩn danh");
//        }else{
//            tvSubject.setText(username);
//        }
//
        etMessage=findViewById(R.id.edtNoiDung);
        btnSend=findViewById(R.id.btnGui);
//        btnSend.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                sendMail();
//            }
//        });
        etSubject=findViewById(R.id.etSubject);
        etMessage=findViewById(R.id.edtNoiDung);
        btnSend=findViewById(R.id.btnGui);
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] TO = {"n17dccn135@student.ptithcm.edu.vn"};
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setData(Uri.parse("mailto:"));
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_EMAIL,TO);
                intent.putExtra(Intent.EXTRA_SUBJECT,etSubject.getText().toString());
                intent.putExtra(Intent.EXTRA_TEXT,etMessage.getText().toString());
                //Log.d("mail2", etSubject.getText().toString()+ etMessage.getText().toString());
                startActivity(intent);
                //sendMail();
            }
        });
    }

    private void sendMail() {
//        sharedPreferences = getSharedPreferences("dataLogin", MODE_PRIVATE);
//        String username=sharedPreferences.getString("email", "username");
//        Log.d("usera", username);
//        if(username.equalsIgnoreCase("username")){
//            tvSubject.setText("Ẩn danh");
//        }else{
//            tvSubject.setText(username);
//        }
        String mail = "phamnhatquan6905@gmail.com";
        String subject = "["+etSubject.getText().toString()+"] GÓP Ý ỨNG DỤNG";
        String message = etMessage.getText().toString();

        //Send mail
        JavaMailAPI javaMailAPI=new JavaMailAPI(GopYActivity.this,mail,subject,message);
        javaMailAPI.execute();
    }
}