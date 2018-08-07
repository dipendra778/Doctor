package com.example.dipak.doctor;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.example.dipak.doctor.Recycler.Product;
import com.example.dipak.doctor.Recycler.RecyclerActivity;
import com.example.dipak.doctor.Register.RegisterActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawerLayout;
    Toolbar toolbar;
    ActionBarDrawerToggle actionBarDrawerToggle;
    AutoCompleteTextView speciality, location;
    private List<Product> productList;
    ViewFlipper viewFlipper;


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


        int images[] = {R.drawable.flipper1, R.drawable.flipper2, R.drawable.flipper3,
                R.drawable.flipper4, R.drawable.flipper5, R.drawable.flipper6};

      /* for(int i=0; i< image.length; i++){
             filpperImages(image[i]);
        }*/
        for (int image : images) {
            flipperImages(image);
        }
        //Navigation items ko lai
        NavigationView navigationView = findViewById(R.id.navigationview);
        navigationView.setNavigationItemSelectedListener(this);


        speciality = findViewById(R.id.Specialist);
        location = findViewById(R.id.location);

        //   getSupportActionBar().setTitle("Doctor");
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Button btnSearch = findViewById(R.id.search);
        Button btnClear = findViewById(R.id.clear);

        setUpToolbar();
        productList = new ArrayList<>();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("details");

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                if (dataSnapshot.exists()) {

                    for (DataSnapshot productSnapshot : dataSnapshot.getChildren()) {
                        Product p = productSnapshot.getValue(Product.class);
                        productList.add(p);
                    }
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        Gson gson = new Gson();

        RecyclerActivity.start(this, gson.toJson(productList));

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                speciality.setText("");
                location.setText("");
            }
        });

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, RecyclerActivity.class);
                startActivity(intent);
            }
        });

    }

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
            case R.id.home_id:
                Toast.makeText(this, "Home Clicked", Toast.LENGTH_SHORT).show();
                newactivity();
                break;

            case R.id.view_id:
                Toast.makeText(this, "View Details", Toast.LENGTH_SHORT).show();
                viewactivity();
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

    public void viewactivity() {
        Intent intent = new Intent(MainActivity.this, RecyclerActivity.class);
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
        } else if (id == R.id.viewdetail) {
            Intent intent = new Intent(this, RecyclerActivity.class);
            startActivity(intent);
            Toast.makeText(this, "Details", Toast.LENGTH_SHORT);
        }
        else if (id == R.id.signin) {
            Intent intent = new Intent(this, SigninActivity.class);
            startActivity(intent);
            Toast.makeText(this, "Sign In ", Toast.LENGTH_SHORT);
        }
        else if (id == R.id.news) {
            Intent intent = new Intent(this, NewsActivity.class);
            startActivity(intent);
            Toast.makeText(this, "News! ", Toast.LENGTH_SHORT);
        }


        return false;
    }
}
