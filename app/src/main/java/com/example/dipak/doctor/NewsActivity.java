package com.example.dipak.doctor;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.content.Intent;
import android.net.Uri;

public class NewsActivity extends AppCompatActivity {

    ImageView imageView1,imageView2,imageView3,imageView4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        getSupportActionBar().setTitle("News Portal");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        imageView1=findViewById(R.id.news_img_1);
        imageView2=findViewById(R.id.news_img_2);
        imageView3=findViewById(R.id.news_img_3);
        imageView4=findViewById(R.id.news_img_4);

        imageView1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent browserIntent = new Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("http://gorkhapatraonline.com/"));
                startActivity(browserIntent);
            }
        });
        imageView2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent browserIntent = new Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("https://www.kantipurdaily.com/"));
                startActivity(browserIntent);
            }
        });
        imageView3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent browserIntent = new Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("http://annapurnapost.com/"));
                startActivity(browserIntent);
            }
        });
        imageView4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent browserIntent = new Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("http://dineshkhabar.com/"));
                startActivity(browserIntent);
            }
        });


    }
}
