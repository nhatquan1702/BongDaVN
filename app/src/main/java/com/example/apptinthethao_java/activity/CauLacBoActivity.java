package com.example.apptinthethao_java.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.apptinthethao_java.R;
import com.example.apptinthethao_java.adapter.CauLacBoAdapter;
import com.example.apptinthethao_java.model.CauLacBo;

import java.util.ArrayList;

public class CauLacBoActivity extends AppCompatActivity {
    private ArrayList<CauLacBo> cauLacBoArrayList;
    private CauLacBoAdapter cauLacBoAdapter;
    ListView listViewCLB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cau_lac_bo);
        listViewCLB = findViewById(R.id.listViewDSCLB);

        cauLacBoArrayList = new ArrayList<>();
        CauLacBo cauLacBo;
        cauLacBo = new CauLacBo("https://firebasestorage.googleapis.com/v0/b/imgapi-144fe.appspot.com/o/img%2F1.jpg?alt=media&token=cdecafe8-9db0-4b02-8ecc-55cf40a96fb7", "Hoàng Anh Gia Lai");
        cauLacBoArrayList.add(cauLacBo);
        cauLacBoArrayList.add(cauLacBo);
        cauLacBoAdapter = new CauLacBoAdapter(cauLacBoArrayList);
        listViewCLB.setAdapter(cauLacBoAdapter);
        listViewCLB.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(CauLacBoActivity.this, cauLacBoArrayList.get(position).getTenCLB(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}