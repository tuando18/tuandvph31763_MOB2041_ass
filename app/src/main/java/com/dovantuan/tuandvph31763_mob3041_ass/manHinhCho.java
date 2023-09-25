package com.dovantuan.tuandvph31763_mob3041_ass;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class manHinhCho extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_man_hinh_cho);

        ImageView ivlogo = findViewById(R.id.ivlogo);
        Glide.with(this).load(R.mipmap.book2).into(ivlogo);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(manHinhCho.this, LoginActivity.class);
                startActivity(intent);
            }
        }, 3000);
    }
}