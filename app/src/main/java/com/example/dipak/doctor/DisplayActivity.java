/*Created By @ Dipendra Pant(2018)*/
package com.example.dipak.doctor;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class DisplayActivity extends AppCompatActivity {

    ImageView imageView;
    TextView name, location, hospital, phone, availablity, price, speciality;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        imageView = findViewById(R.id.imageView);
        name = findViewById(R.id.name);
        location = findViewById(R.id.Speciality);
        hospital = findViewById(R.id.hospital);
        phone = findViewById(R.id.phone);
        availablity = findViewById(R.id.Availablity);
        price = findViewById(R.id.price);
        speciality = findViewById(R.id.Speciality);
        Intent intent = getIntent();


    }
}
