package com.example.apptinthethao_java.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.apptinthethao_java.R;
import com.example.apptinthethao_java.util.ImageUtil;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;

public class BaiVietActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView imgTitle;
    private Button btnImg;
    private EditText edtTitle;
    private EditText edtContent;
    private TextView author;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bai_viet);
        imgTitle = findViewById(R.id.img);
        btnImg = findViewById(R.id.button_uploadimg);
        edtTitle = findViewById(R.id.et_TieuDeDeTail);
        edtContent = findViewById(R.id.etNoiDung);
        author = findViewById(R.id.tv_nguoitao);

        btnImg.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button_uploadimg:{
                Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, 0);
                break;
            }
            default:
                break;
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK){
            Uri targetUri = data.getData();
            Bitmap bitmap;
            try {
                bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(targetUri));
                String encoded = ImageUtil.convert(bitmap);
                Log.d("showbitmap" , encoded);
                imgTitle.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}