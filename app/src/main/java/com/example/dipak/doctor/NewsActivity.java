/*Created By @ Dipendra Pant(2018)*/
package com.example.dipak.doctor;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.content.Intent;
import android.net.Uri;

public class NewsActivity extends AppCompatActivity {

    ImageView imageView1, imageView2, imageView3, imageView4, imageView5, imageView6, imageView7, imageView8;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        getSupportActionBar().setTitle("News Portal");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        imageView1 = findViewById(R.id.news_image_a_1);
        imageView2 = findViewById(R.id.news_image_a_2);
        imageView3 = findViewById(R.id.news_image_b_1);
        imageView4 = findViewById(R.id.news_image_b_2);
        imageView5 = findViewById(R.id.news_image_c_1);
        imageView6 = findViewById(R.id.news_image_c_2);
        imageView7 = findViewById(R.id.news_image_d_1);
        imageView8 = findViewById(R.id.news_image_d_2);

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
        imageView5.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent browserIntent = new Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("http://ratopati.com/"));
                startActivity(browserIntent);
            }
        });
        imageView6.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent browserIntent = new Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("https://setopati.com/"));
                startActivity(browserIntent);
            }
        });
        imageView7.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent browserIntent = new Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("https://www.onlinekhabar.com/"));
                startActivity(browserIntent);
            }
        });
        imageView8.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent browserIntent = new Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("http://www.paschimtoday.com/"));
                startActivity(browserIntent);
            }
        });


    }
}
