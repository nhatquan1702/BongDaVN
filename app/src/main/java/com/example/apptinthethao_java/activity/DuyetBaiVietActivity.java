package com.example.apptinthethao_java.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.cloudinary.android.MediaManager;
import com.cloudinary.android.callback.ErrorInfo;
import com.cloudinary.android.callback.UploadCallback;
import com.example.apptinthethao_java.R;
import com.example.apptinthethao_java.api.SimpleAPI;
import com.example.apptinthethao_java.model.Status;
import com.example.apptinthethao_java.util.Constants;
import com.squareup.picasso.Picasso;
import com.example.apptinthethao_java.R;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DuyetBaiVietActivity extends AppCompatActivity {
    private EditText edtTitle;
    private EditText edtContent;
    private SimpleAPI simpleAPI;
    private String encoded = null; // encoded img bitmap to base64
    private int postId = -1;
    private String imgReceive = null;
    SharedPreferences sharedPreferences;

    private ImageView mProfile;
    private ImageButton mImageAdd;
    private CardView mBtnUpload, btnCapNhat;
    private TextView mText, tvCapNhat, tvRefreshPost;

    private static final int PERMISSION_CODE =1;
    private static final int PICK_IMAGE=1;
    private  boolean checkInit = true;// chưa
    String filePath;
    private void configCloudinary() {
        Map config = new HashMap();
        config.put("cloud_name", "dmfrvd4tl");
        config.put("api_key", "258945955129684");
        config.put("api_secret", "taQ7f4rtk6nM2DzRGo9Crzj3WVs");
        config.put("secure", true);
        MediaManager.init(DuyetBaiVietActivity.this,null, config);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_duyet_bai_viet);

        edtTitle = findViewById(R.id.et_TieuDeDeTail);
        edtContent = findViewById(R.id.etNoiDung);
        mProfile = findViewById(R.id.imgProfile);
        mImageAdd = findViewById(R.id.imgAdd);
        mBtnUpload = findViewById(R.id.btnUpload);
        btnCapNhat = findViewById(R.id.btnCapNhat);
        mText = findViewById(R.id.txt);

        tvCapNhat = findViewById(R.id.tvCapNhat);
        tvRefreshPost = findViewById(R.id.tvLamMoi);
        tvCapNhat.setText("Cập nhật");
        mBtnUpload.setVisibility(View.GONE);
        sharedPreferences = getSharedPreferences("dataLogin", MODE_PRIVATE);

        if(checkInit){
            try {
                configCloudinary();
            }
            catch (Exception e){

            }

            checkInit=false;
        }
        //when click mImageAdd request the permission to access the gallery
        LoadButton();

        // receive update
        Bundle bundle = getIntent().getExtras();
        if(bundle!= null) {
            edtTitle.setText(bundle.getString("post_title", ""));
            edtContent.setText(bundle.getString("post_content", ""));
            postId = bundle.getInt("post_id", 0);
            imgReceive = bundle.getString("post_img", null);
            Picasso.get()
                    .load(imgReceive)
                    .placeholder(R.drawable.galleryoo)
                    .error(R.drawable.galleryoo)
                    .into(mProfile);
            mBtnUpload.setVisibility(View.GONE);
            mText.setText(imgReceive);
        }
    }
    private void LoadButton() {
        tvRefreshPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mProfile.setImageResource(R.drawable.galleryoo);
                edtTitle.getText().clear();
                edtContent.getText().clear();
                mText.setText("");
            }
        });
        mImageAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mText.setText("");
                mBtnUpload.setVisibility(View.VISIBLE);
                //request permission to access external storage
                requestPermission();

            }
        });
        mBtnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(filePath.isEmpty() || filePath.toString().trim().equals("")){
                    Toast.makeText(DuyetBaiVietActivity.this, "Vui lòng chọn ảnh!", Toast.LENGTH_SHORT).show();
                }
                else
                    uploadToCloudinary(filePath);
            }
        });

        btnCapNhat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String post_title = edtTitle.getText().toString().trim();
                if(post_title.isEmpty()){
                    Toast.makeText(DuyetBaiVietActivity.this, "Tiêu đề không được bỏ trống!", Toast.LENGTH_SHORT).show();
                }
                String post_content = edtContent.getText().toString().trim();
                if(post_content.isEmpty()){
                    Toast.makeText(DuyetBaiVietActivity.this, "Nội dung không được bỏ trống!", Toast.LENGTH_SHORT).show();
                }
                String post_img = mText.getText().toString().trim();
                if(post_img.isEmpty()){
                    Toast.makeText(DuyetBaiVietActivity.this, "Hình ảnh không được bỏ trống!", Toast.LENGTH_SHORT).show();
                }
                else{
                    try {
                        EditPostNew(String.valueOf(postId), post_title, post_content, post_img);
                        DuyetBaiViet(String.valueOf(postId));
                    }
                    catch (Exception e){

                    }
                }

            }
        });
    }

    private void requestPermission(){
        if(ContextCompat.checkSelfPermission
                (DuyetBaiVietActivity.this,
                        Manifest.permission.READ_EXTERNAL_STORAGE
                ) == PackageManager.PERMISSION_GRANTED
        ){
            accessTheGallery();
        } else {
            ActivityCompat.requestPermissions(
                    DuyetBaiVietActivity.this,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                    PERMISSION_CODE
            );
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                accessTheGallery();
            } else {
                Toast.makeText(DuyetBaiVietActivity.this, "Không có quyền truy cập vào thư viện", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void accessTheGallery(){
        Intent i = new Intent(
                Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        );
        i.setType("image/*");
        startActivityForResult(i, PICK_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //get the image's file location
        filePath = getRealPathFromUri(data.getData(), DuyetBaiVietActivity.this);

        if(requestCode==PICK_IMAGE && resultCode==RESULT_OK){
            try {
                //set picked image to the mProfile
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), data.getData());
                mProfile.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private String getRealPathFromUri(Uri imageUri, Activity activity){
        Cursor cursor = activity.getContentResolver().query(imageUri, null, null, null, null);

        if(cursor==null) {
            return imageUri.getPath();
        }else{
            cursor.moveToFirst();
            int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
            return cursor.getString(idx);
        }
    }
    private void uploadToCloudinary(String filePath) {
        mText.setText("");
        MediaManager.get().upload(filePath).callback(new UploadCallback() {
            @Override
            public void onStart(String requestId) {
                mText.setText("Bắt đầu tải lên");
            }

            @Override
            public void onProgress(String requestId, long bytes, long totalBytes) {
                mText.setText("Vui đòng đợi... ");
            }

            @Override
            public void onSuccess(String requestId, Map resultData) {
                mText.setText(resultData.get("url").toString());
                mBtnUpload.setVisibility(View.GONE);
            }

            @Override
            public void onError(String requestId, ErrorInfo error) {
                mText.setText("Lỗi "+ error.getDescription());
            }

            @Override
            public void onReschedule(String requestId, ErrorInfo error) {
                mText.setText("Reshedule "+error.getDescription());
            }
        }).dispatch();
    }

    public void EditPostNew(String post_id, String post_title,
                            String post_content, String post_img) {
        simpleAPI = Constants.instance();
        simpleAPI.editPostNew(post_id, post_title, post_content, post_img).enqueue(new Callback<Status>() {
            @Override
            public void onResponse(Call<Status> call, Response<Status> response) {
                Status status = response.body();
                if(status.getStatus()==4){
                    Toast.makeText(DuyetBaiVietActivity.this, "Cập nhật thành công!", Toast.LENGTH_SHORT).show();
                    //Intent intenta = new Intent(SuaBaiVietActivity.this, ListBaiVietActivity.class);
                    Intent intent;
                    intent = new Intent(DuyetBaiVietActivity.this, ListBaiVietActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                    startActivityIfNeeded(intent, 0);
                    finish();
                }
                else {
                    Toast.makeText(DuyetBaiVietActivity.this, "Cập nhật không thành công!", Toast.LENGTH_SHORT).show();
                    Intent intent;
                    intent = new Intent(DuyetBaiVietActivity.this, ListTinChuaDuyetActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                    startActivityIfNeeded(intent, 0);
                    finish();
                }
            }

            @Override
            public void onFailure(Call<Status> call, Throwable t) {
                Toast.makeText(DuyetBaiVietActivity.this, "Lỗi: "+t.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    private  void  DuyetBaiViet(String id){
        simpleAPI = Constants.instance();
        simpleAPI.DuyetBaiViet(id).enqueue(new Callback<Status>() {
            @Override
            public void onResponse(Call<Status> call, Response<Status> response) {
                Status status = response.body();
                if(status.getStatus()==1){
                    Toast.makeText(DuyetBaiVietActivity.this, "Duyệt bài thành công!", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(DuyetBaiVietActivity.this, "Duyệt bài không thành công!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Status> call, Throwable t) {
                Toast.makeText(DuyetBaiVietActivity.this, "Lỗi: "+t.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}