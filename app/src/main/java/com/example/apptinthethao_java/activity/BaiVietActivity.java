package com.example.apptinthethao_java.activity;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.cloudinary.android.MediaManager;
import com.cloudinary.android.callback.ErrorInfo;
import com.cloudinary.android.callback.UploadCallback;
import com.example.apptinthethao_java.R;
import com.example.apptinthethao_java.api.SimpleAPI;
import com.example.apptinthethao_java.model.Post;
import com.example.apptinthethao_java.util.Constants;
import com.example.apptinthethao_java.util.ImageUtil;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;

public class BaiVietActivity extends AppCompatActivity {
    private EditText edtTitle;
    private EditText edtContent;
    private TextView author;
    private SimpleAPI simpleAPI;
    private Boolean checkSuccess = false;
    private String encoded = null; // encoded img bitmap to base64
    private int postId = -1;
    private String imgReceive = null;
    SharedPreferences sharedPreferences;
    int REQUEST_CODE = 1;

    private ImageView mProfile;
    private ImageView mImageAdd;
    private Button mBtnUpload;
    private TextView mText;

    private static final int PERMISSION_CODE =1;
    private static final int PICK_IMAGE=1;

    String filePath;
    Map config = new HashMap();

    private void configCloudinary() {
        config.put("cloud_name", "dmfrvd4tl");
        config.put("api_key", "258945955129684");
        config.put("api_secret", "taQ7f4rtk6nM2DzRGo9Crzj3WVs");
        MediaManager.init(BaiVietActivity.this, config);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bai_viet);

        edtTitle = findViewById(R.id.et_TieuDeDeTail);
        edtContent = findViewById(R.id.etNoiDung);
        author = findViewById(R.id.tv_nguoitao);
        mProfile = findViewById(R.id.imgProfile);
        mImageAdd = findViewById(R.id.imgAdd);
        mBtnUpload = findViewById(R.id.btnUpload);
        mText = findViewById(R.id.txt);

        configCloudinary();
        //when click mImageAdd request the permission to access the gallery
        mImageAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //request permission to access external storage
                requestPermission();
            }
        });
        mBtnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uploadToCloudinary(filePath);
            }
        });

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        // receive update
        Bundle bundle = getIntent().getExtras();
        if(bundle!= null) {
            edtTitle.setText(bundle.getString("post_title", ""));
            edtContent.setText(bundle.getString("post_content", ""));
            postId = bundle.getInt("post_id", 0);
            imgReceive = bundle.getString("post_img", null);
        }
    }

    private void requestPermission(){
        if(ContextCompat.checkSelfPermission
                (BaiVietActivity.this,
                        Manifest.permission.READ_EXTERNAL_STORAGE
                ) == PackageManager.PERMISSION_GRANTED
        ){
            accessTheGallery();
        } else {
            ActivityCompat.requestPermissions(
                    BaiVietActivity.this,
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
                Toast.makeText(BaiVietActivity.this, "Không có quyền truy cập vào thư viện", Toast.LENGTH_SHORT).show();
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
        filePath = getRealPathFromUri(data.getData(), BaiVietActivity.this);

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
                mText.setText("URL: "+resultData.get("url").toString());
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_bai_viet,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home: {
                onBackPressed();
                return true;
            }
            case R.id.action_save:{

            }
            case R.id.action_reset:{
                // reset
                mProfile.setImageResource(R.drawable.galleryoo);
                edtTitle.getText().clear();
                edtContent.getText().clear();
                return true;
            }
            default:{
                return super.onOptionsItemSelected(item);
            }
        }
    }

    private void UpdatePost(Post mPost) {
        simpleAPI = Constants.instance();
        simpleAPI.UpdateBaiViet(String.valueOf(postId),mPost).enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {
                if (response.isSuccessful()) {
                    Log.d("success", "post submitted to API." + response.body().toString());
                    result(true);
                }
            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {
                if(call.isCanceled()) {

                    Log.d("fail", "request was aborted");
                }else {
                    Log.d("fail", "Unable to submit post to API.");
                }
                result(false);
            }
        });
    }

    public void upLoadPost(Post mPost) {
        simpleAPI = Constants.instance();
        simpleAPI.postBaiViet(mPost).enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(BaiVietActivity.this, "success", Toast.LENGTH_SHORT).show();
                    Log.d("success", "post submitted to API." + response.body().toString());
                    result(true);
                }
            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {
                if(call.isCanceled()) {
                    Log.d("fail", "request was aborted");
                }else {
                    Log.d("fail", "Unable to submit post to API.");
                }
                result(false);
            }
        });

    }
    public void result(Boolean isSuccess) {
        this.checkSuccess = isSuccess;
    }


}