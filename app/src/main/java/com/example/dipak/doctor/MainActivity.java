/*Created By @ Dipendra Pant(2018)*/
package com.example.dipak.doctor;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.example.dipak.doctor.Emergency.EmergencyActivity;
import com.example.dipak.doctor.Professional.Constants;
import com.example.dipak.doctor.Professional.PostDetailActivity_p;
import com.example.dipak.doctor.Recycler.PostsListActivity;
import com.example.dipak.doctor.Professional.PostsListActivity_p;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import ir.sohreco.circularpulsingbutton.CircularPulsingButton;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    //Button mInquery_Btn;
    //Spinner spinnerLocation;
    DrawerLayout drawerLayout;
    Toolbar toolbar;
    ActionBarDrawerToggle actionBarDrawerToggle;

    ViewFlipper viewFlipper;

    //android:background="@drawable/main1"


    public static void start(Context context, String productList) {
        Intent mainIntent = new Intent(context, MainActivity.class);
        mainIntent.putExtra("details", productList);
        context.startActivity(mainIntent);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Imagae at top slideshow
        viewFlipper = findViewById(R.id.photo_fliper);

        //   listLocation = new ArrayList<>();
        // listSpeciality = new ArrayList<>();
        int images[] = {R.drawable.flipper1, R.drawable.doctor1, R.drawable.doctor2,
                R.drawable.flipper4, R.drawable.flipper5, R.drawable.flipper6};

        for (int image : images) {
            flipperImages(image);
        }
        //Navigation items ko lai
        NavigationView navigationView = findViewById(R.id.navigationview);
        navigationView.setNavigationItemSelectedListener(this);


        Button btnSearch = findViewById(R.id.search);

        Button btnProfessional = findViewById(R.id.search_professional);

        setUpToolbar();

        //  spinnerLocation=findViewById(R.id.spinner_location);
        // spinnerLocation.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) this);
        //mInquery_Btn=findViewById(R.id.InqueryTv);

        //       createNotificationChannel();

       /* mInquery_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String location=spinnerLocation.getSelectedItem().toString();
                FirebaseMessaging.getInstance().subscribeToTopic(location);
                Toast.makeText(getApplicationContext(),"Subscribed",Toast.LENGTH_LONG).show();
            }
        });
*/






          /* <Button
                    android:id="@+id/clear"
                    android:layout_width="match_parent"
                    android:layout_height="100dp"
                    android:layout_below="@id/photo_fliper"
                    android:layout_marginLeft="20dp"
                    android:layout_marginRight="20dp"
                    android:layout_marginTop="@dimen/dp_60"
                    android:background="@drawable/shape_btn"
                    android:text="@string/searchhospital"
                    android:textColor="#FF6C2737"
                    android:textSize="30sp" />
         //
         */

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, PostsListActivity.class);
                startActivity(intent);
            }
        });

        btnProfessional.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent inten = new Intent(MainActivity.this, PostsListActivity_p.class);
                startActivity(inten);
            }
        });


    }

 /*   public void onItemSelected(AdapterView<?> parent, View view, int pos,long id) {
        Toast.makeText(parent.getContext(),
                "OnItemSelectedListener : " + parent.getItemAtPosition(pos).toString(),
                Toast.LENGTH_SHORT).show();
    }
*/

/*
    private void createNotificationChannel()
    {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationManager mNotificationManager =
                    (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel mChannel = new NotificationChannel(Constants.CHANNEL_ID, Constants.CHANNEL_NAME, importance);
            mChannel.setDescription(Constants.CHANNEL_DESCRIPTION);
            mChannel.enableLights(true);
            mChannel.setLightColor(Color.RED);
            mChannel.enableVibration(true);
            mChannel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
            mNotificationManager.createNotificationChannel(mChannel);
        }
    }  */

    public void flipperImages(int images) {
        ImageView imageView = new ImageView(this);
        imageView.setBackgroundResource(images);

        viewFlipper.addView(imageView);
        viewFlipper.setFlipInterval(4000);
        viewFlipper.setAutoStart(true);

        //Animation
        viewFlipper.setInAnimation(this, android.R.anim.slide_in_left);
        viewFlipper.setOutAnimation(this, android.R.anim.slide_out_right);
    }


    private void setUpToolbar() {
        drawerLayout = findViewById(R.id.drawerLayout);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.app_name, R.string.app_name);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    //Left SIde 3 dot menu
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            /*case R.id.home_id:
                Toast.makeText(this, "Home Clicked", Toast.LENGTH_SHORT).show();
                newactivity();
                break; */

            case R.id.sendmail:
                Toast.makeText(this, "Send Mail", Toast.LENGTH_SHORT).show();
                mailactivity();
                break;

            case R.id.game_id:
                Toast.makeText(this, "Let's Play Game!", Toast.LENGTH_SHORT).show();
                gameactivity();
                break;

            case R.id.about_id:
                Toast.makeText(this, "About Clicked", Toast.LENGTH_SHORT).show();
                aboutactivity();
                break;

        }

        return true;
    }

    //Intents for activity
    public void newactivity() {
        Intent intent = new Intent(MainActivity.this, MainActivity.class);
        startActivity(intent);
    }

    public void gameactivity() {
        Intent intent = new Intent(MainActivity.this, GameActivity.class);
        startActivity(intent);
    }

    public void aboutactivity() {
        Intent intent = new Intent(MainActivity.this, AboutActivity.class);
        startActivity(intent);
    }

    public void mailactivity() {
        Intent intent = new Intent(MainActivity.this, SendMainActivity.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    //CLoses soft keyboard
    public void closekeyboard(View view) {

        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    /*else if (id == R.id.viewdetail) {
                Intent intent = new Intent(this, PostsListActivity.class);
                startActivity(intent);
                Toast.makeText(this, "Details", Toast.LENGTH_SHORT);
            } */
    //Navigation bar item selection and intent
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.home) {
            Intent intent = new Intent(MainActivity.this, MainActivity.class);
            startActivity(intent);
            Toast.makeText(this, "You are in Home", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.game_id) {
            Intent intent = new Intent(this, GameActivity.class);
            startActivity(intent);
            Toast.makeText(this, "This is Game", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.aboutus) {
            Intent intent = new Intent(this, AboutActivity.class);
            startActivity(intent);
            Toast.makeText(this, "About Us", Toast.LENGTH_SHORT);
        } else if (id == R.id.signin) {
            Intent intent = new Intent(this, SigninActivity.class);
            startActivity(intent);
            Toast.makeText(this, "Sign In ", Toast.LENGTH_SHORT);
        } else if (id == R.id.news) {
            Intent intent = new Intent(this, NewsActivity.class);
            startActivity(intent);
            Toast.makeText(this, "News! ", Toast.LENGTH_SHORT);
        } else if (id == R.id.emergency) {
            Intent intent = new Intent(this, EmergencyActivity.class);
            startActivity(intent);
            Toast.makeText(this, "Emergency! ", Toast.LENGTH_SHORT);
        } else if (id == R.id.share) {
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("text/plain");
            String shareBody = "You can download our app";
            String shareSub = "Link Here";
            intent.putExtra(Intent.EXTRA_SUBJECT, shareSub);
            intent.putExtra(Intent.EXTRA_TEXT, shareBody);
            startActivity(Intent.createChooser(intent, "Share Using"));
        }


        return false;
    }
}
