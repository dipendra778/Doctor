package com.example.dipak.doctor;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ViewFlipper;

public class AboutActivity extends AppCompatActivity {

    ViewFlipper viewFlipper;
  // ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
      getSupportActionBar().setTitle("About");
      getSupportActionBar().setDisplayHomeAsUpEnabled(true);

      //flipper images
        viewFlipper = findViewById(R.id.photo_fliper);

        int images_a[] = {R.drawable.dipendra, R.drawable.roshan, R.drawable.renuka,
                R.drawable.upendra, R.drawable.bhawana, R.drawable.flipper6};

        for (int image : images_a) {
            flipperImages(image);
        }

    }

    private void flipperImages(int image_a) {
        ImageView imageView = new ImageView(this);
        imageView.setBackgroundResource(image_a);

        viewFlipper.addView(imageView);
        viewFlipper.setFlipInterval(4000);
        viewFlipper.setAutoStart(true);

        //Animation
        viewFlipper.setInAnimation(this, android.R.anim.slide_in_left);
        viewFlipper.setOutAnimation(this, android.R.anim.slide_out_right);
    }
}
