package com.example.apptinthethao_java.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.airbnb.lottie.LottieAnimationView;
import com.example.apptinthethao_java.R;

public class LoadingActivity extends AppCompatActivity {

    TextView tvUD, tvXTTT, tvContent;
    RelativeLayout relativeLayout;
    Animation txtAnimation,txtAnimation2,layoutAnimation;
    LottieAnimationView lottieAnimationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);
        txtAnimation= AnimationUtils.loadAnimation(LoadingActivity.this,R.anim.left_to_right);
        txtAnimation2= AnimationUtils.loadAnimation(LoadingActivity.this,R.anim.rotateout_in);
        layoutAnimation=AnimationUtils.loadAnimation(LoadingActivity.this,R.anim.bottom_to_top);
        lottieAnimationView=findViewById(R.id.lottieFootball);
        tvUD=findViewById(R.id.tvUD);
        tvXTTT=findViewById(R.id.tvXemTinTT);
        tvContent=findViewById(R.id.tvContent);
        relativeLayout=findViewById(R.id.relMain);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                relativeLayout.setVisibility(View.VISIBLE);
                relativeLayout.setAnimation(layoutAnimation);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        lottieAnimationView.setVisibility(View.VISIBLE);
                        tvUD.setVisibility(View.VISIBLE);
                        tvXTTT.setVisibility(View.VISIBLE);
                        tvUD.setAnimation(txtAnimation);
                        tvXTTT.setAnimation(txtAnimation2);
                    }
                },900);
            }
        },500);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(LoadingActivity.this,MainActivity.class);
                startActivity(intent);
            }
        },6000);
    }
}