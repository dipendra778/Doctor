/*Created By @ Dipendra Pant(2018)*/
package com.example.dipak.doctor.Professional;

import android.Manifest;
import android.app.NotificationChannel;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.app.NotificationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dipak.doctor.R;
import com.example.dipak.doctor.SendMainActivity;
import com.google.firebase.messaging.FirebaseMessaging;

public class PostDetailActivity_p extends AppCompatActivity {

    TextView mTitleTv, mDetailTv, mspTv, mPriceTv, mEmailTv, mPhoneTv,mAvailTv; //,mPhoneTvmHospitalTv,,mHospitalTv,mEmailTv,mPriceTv;
    ImageView mImageTv;
    Button mSendMail_Btn;//


    /* <service
            android:name=".MyFirebaseMessagingService">
            <intent-filter>
                <action android:name="com.google.firebase.MESSAGING_EVENT"/>
            </intent-filter>
        </service>*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_detail_p);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Professional Details");
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);


/*  <Spinner
            android:id="@+id/spinner_location"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:dropDownWidth="wrap_content"
            android:entries="@array/location"
            android:padding="@dimen/dp_10"
            android:textColorHint="#000"
            android:background="#f0ffff"
            android:layout_margin="@dimen/dp_10" />
        <Button
            android:drawableLeft="@drawable/inquery"
            android:paddingLeft="@dimen/dp_10"
            android:id="@+id/InqueryTv"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:layout_marginLeft="20dp"
            android:layout_marginRight="20dp"
            android:text="@string/subscribe"
            android:textColor="#FF6C2737"
            android:textSize="30sp"
            android:background="@drawable/shape_btn"
            />*/
        mTitleTv = findViewById(R.id.TitleTv);
        mDetailTv = findViewById(R.id.DescriptionTv);
        mImageTv = findViewById(R.id.ImageView);
        mspTv = findViewById(R.id.SpecialityTv);
        //mHospitalTv = findViewById(R.id.HospitalTv);
        mPriceTv = findViewById(R.id.PriceTv);
        mEmailTv = findViewById(R.id.EmailTv);
        mPhoneTv = findViewById(R.id.PhoneTv);
        mAvailTv=findViewById(R.id.AvailTv);

        mSendMail_Btn = findViewById(R.id.MailTv);
      //  mInquery_Btn=findViewById(R.id.InqueryTv);
        //send mail to doctor

        mSendMail_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String emailvalue = mEmailTv.getText().toString();
                Intent intent = new Intent(PostDetailActivity_p.this, SendMainActivity.class);
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
       // String hospital=getIntent().getStringExtra("hospital");
        String price=getIntent().getStringExtra("price");
        String email=getIntent().getStringExtra("email");
        String phone=getIntent().getStringExtra("phone");
        String avail=getIntent().getStringExtra("availablity");
        Bitmap bmp= BitmapFactory.decodeByteArray(bytes,0,bytes.length);

        // set data to views
        mTitleTv.setText(title);
        mspTv.setText(spec);
        mDetailTv.setText(desc);
       // mHospitalTv.setText(hospital);
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
