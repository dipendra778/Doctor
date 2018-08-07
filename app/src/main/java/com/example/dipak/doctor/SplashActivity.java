package com.example.dipak.doctor;


import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.widget.ImageView;
import android.widget.TextView;

import com.example.dipak.doctor.Recycler.Product;

import java.util.List;

public class SplashActivity extends AppCompatActivity {

    private final int SplashTime = 2000;
    private List<Product> productList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ImageView imageView = findViewById(R.id.imageView);
        TextView textView1 = findViewById(R.id.textView1);
        TextView textView2=findViewById(R.id.textView2);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                SplashActivity.this.startActivity(intent);
                SplashActivity.this.finish();
            }
        }, SplashTime);
    }
}
