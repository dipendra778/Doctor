package com.example.dipak.doctor.Recycler;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dipak.doctor.R;
import com.example.dipak.doctor.SendMainActivity;

public class PostDetailActivity extends AppCompatActivity {

    TextView mTitleTv, mDetailTv, mspTv, mHospitalTv, mPriceTv, mEmailTv, mPhoneTv,mAvailTv; //,mPhoneTv,mHospitalTv,mEmailTv,mPriceTv;
    ImageView mImageTv;
    Button mSendMail_Btn,mInquery_Btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_detail);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Doctor Details");
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

        mTitleTv = findViewById(R.id.TitleTv);
        mDetailTv = findViewById(R.id.DescriptionTv);
        mImageTv = findViewById(R.id.ImageView);
        mspTv = findViewById(R.id.SpecialityTv);
        mHospitalTv = findViewById(R.id.HospitalTv);
        mPriceTv = findViewById(R.id.PriceTv);
        mEmailTv = findViewById(R.id.EmailTv);
        mPhoneTv = findViewById(R.id.PhoneTv);
        mAvailTv=findViewById(R.id.AvailTv);

        mSendMail_Btn = findViewById(R.id.MailTv);
        mInquery_Btn=findViewById(R.id.InqueryTv);
        //send mail to doctor

        mSendMail_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String emailvalue = mEmailTv.getText().toString();
                Intent intent = new Intent(PostDetailActivity.this, SendMainActivity.class);
                intent.putExtra("email", emailvalue);
                startActivity(intent);
            }
        });

        //open new activity fot gmail login
        //
        //


        //make a phone call when clicked on textview


        mPhoneTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phonevalue = mPhoneTv.getText().toString();
                Intent callintent = new Intent(Intent.ACTION_DIAL);
                callintent.setData(Uri.parse("tel:"+phonevalue));
                startActivity(callintent);
            }
        });

        //get data from intent
        byte[] bytes=getIntent().getByteArrayExtra("image");
        String title=getIntent().getStringExtra("name");
        String spec=getIntent().getStringExtra("speciality");
        String desc=getIntent().getStringExtra("location");
        String hospital=getIntent().getStringExtra("hospital");
        String price=getIntent().getStringExtra("price");
        String email=getIntent().getStringExtra("email");
        String phone=getIntent().getStringExtra("phone");
        String avail=getIntent().getStringExtra("availablity");
        Bitmap bmp= BitmapFactory.decodeByteArray(bytes,0,bytes.length);

        // set data to views
        mTitleTv.setText(title);
        mspTv.setText(spec);
        mDetailTv.setText(desc);
        mHospitalTv.setText(hospital);
        mPriceTv.setText(price);
        mEmailTv.setText(email);
        mPhoneTv.setText(phone);
        mAvailTv.setText(avail);

        mImageTv.setImageBitmap(bmp);

        }

    @Override
    public boolean onSupportNavigateUp() {

        onBackPressed();
        return true;
    }

}
