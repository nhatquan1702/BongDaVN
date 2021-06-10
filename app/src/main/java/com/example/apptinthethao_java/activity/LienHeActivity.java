package com.example.apptinthethao_java.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.apptinthethao_java.R;

public class LienHeActivity extends AppCompatActivity {
    EditText etSubject,etMessage;
    Button btnSend;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lien_he);
        etSubject=findViewById(R.id.edtTieuDe);
        etMessage=findViewById(R.id.edtNoiDung);
        btnSend=findViewById(R.id.btnGui);
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] TO = {"n17dccn074@student.ptithcm.edu.vn"};
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setData(Uri.parse("mailto:"));
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_EMAIL,TO);
                intent.putExtra(Intent.EXTRA_SUBJECT,etSubject.getText().toString());
                intent.putExtra(Intent.EXTRA_TEXT,etMessage.getText().toString());
                Log.d("mail2", etSubject.getText().toString()+ etMessage.getText().toString());
                startActivity(intent);
            }
        });
    }
}