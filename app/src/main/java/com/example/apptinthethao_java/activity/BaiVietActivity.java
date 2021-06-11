package com.example.apptinthethao_java.activity;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.apptinthethao_java.R;
import com.example.apptinthethao_java.api.SimpleAPI;
import com.example.apptinthethao_java.model.Post;
import com.example.apptinthethao_java.util.Constants;
import com.example.apptinthethao_java.util.ImageUtil;
import com.squareup.picasso.Picasso;

import java.io.FileNotFoundException;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;

public class BaiVietActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView imgTitle;
    private ImageButton btnImg;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bai_viet);
        imgTitle = findViewById(R.id.imgDetailPost);
        btnImg = findViewById(R.id.button_uploadimg);
        edtTitle = findViewById(R.id.et_TieuDeDeTail);
        edtContent = findViewById(R.id.etNoiDung);
        author = findViewById(R.id.tv_nguoitao);

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
            Log.d("idPost",String.valueOf(postId));
//            if (postId != -1) {
//                MenuItem menuItem = (MenuItem) findViewById(R.id.action_save);
//                menuItem.setIcon(getResources().getDrawable(R.drawable.ic_edit));
//            }
        }

        Picasso.get()
                .load(imgReceive)
                .placeholder(R.drawable.galleryoo)
                .error(R.drawable.galleryoo)
                .into(imgTitle);
        sharedPreferences = getSharedPreferences("dataLogin", MODE_PRIVATE);
        String mAuthor = sharedPreferences.getString("email", "admin");
        author.setText(mAuthor);
        btnImg.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button_uploadimg:{
                WritePermission();
                ReadPermission();
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
                imgTitle.setImageBitmap(bitmap);
                encoded = ImageUtil.convert(bitmap);
                Log.d("showbitmap" , encoded);
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
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
                Post mPost = new Post();
                mPost.setPost_title(edtTitle.getText().toString());
                mPost.setPost_content(edtContent.getText().toString());
//                if(encoded != null)
//                mPost.setPost_img(encoded);
                mPost.setPost_create_by(sharedPreferences.getString("email", "admin"));
                // call api save or update
                if(postId == -1) {
                    upLoadPost(mPost);
                }
                else
                    UpdatePost(mPost);

                // chuyen ve man hinh cu
                Intent replyIntent = new Intent();
                if (checkSuccess) {
                    setResult(RESULT_OK, replyIntent);
                } else {
                    setResult(RESULT_CANCELED, replyIntent);
                }
                finish();
                return true;
            }
            case R.id.action_reset:{
                // reset
                imgTitle.setImageResource(R.drawable.galleryoo);
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
        simpleAPI.UpdateBaiViet(String.valueOf(postId),mPost).enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                if (response.isSuccessful()) {
                    Log.d("success", "post submitted to API." + response.body().toString());
                    result(true);
                }
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                if(call.isCanceled()) {
                    result(false);
                    Log.d("fail", "request was aborted");
                }else {
                    Log.d("fail", "Unable to submit post to API.");
                }
            }
        });
    }

    public void upLoadPost(Post mPost) {
        simpleAPI = Constants.instance();
        simpleAPI.postBaiViet(mPost).enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                if (response.isSuccessful()) {
                    Log.d("success", "post submitted to API." + response.body().toString());
                    result(true);
                }
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                if(call.isCanceled()) {
                    result(false);
                    Log.d("fail", "request was aborted");
                }else {
                    Log.d("fail", "Unable to submit post to API.");
                }
            }
        });

    }
    public void result(Boolean isSuccess) {
        this.checkSuccess = isSuccess;
    }

    // yêu cầu quyền truy cập
    public void WritePermission() {
        if (ContextCompat.checkSelfPermission(BaiVietActivity.this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            // Permission is not granted
            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(BaiVietActivity.this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
            } else {
                // No explanation needed; request the permission
                ActivityCompat.requestPermissions(BaiVietActivity.this,
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        REQUEST_CODE);

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        } else {
            // Permission has already been granted
        }
    }

    public void ReadPermission() {
        if (ContextCompat.checkSelfPermission(BaiVietActivity.this,
                Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(BaiVietActivity.this,
                    Manifest.permission.READ_EXTERNAL_STORAGE)) {
            } else {
                ActivityCompat.requestPermissions(BaiVietActivity.this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        REQUEST_CODE);
            }
        } else {
            // Permission has already been granted
        }
    }
}