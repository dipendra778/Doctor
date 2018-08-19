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
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.example.dipak.doctor.Emergency.EmergencyActivity;
import com.example.dipak.doctor.Recycler.Product;
import com.example.dipak.doctor.Recycler.RecyclerActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    FirebaseDatabase mFirebaseDatabase;
    DrawerLayout drawerLayout;
    Toolbar toolbar;
    ActionBarDrawerToggle actionBarDrawerToggle;
    AutoCompleteTextView speciality, location;
    private List<Product> productList;
    ViewFlipper viewFlipper;
    private ArrayList<String> listLocation;
    private ArrayList<String> listSpeciality;
    private String filterLocation, filterSpeciality;


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

        listLocation = new ArrayList<>();
        listSpeciality = new ArrayList<>();
        int images[] = {R.drawable.flipper1, R.drawable.flipper2, R.drawable.flipper3,
                R.drawable.flipper4, R.drawable.flipper5, R.drawable.flipper6};

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
        final DatabaseReference reference = FirebaseDatabase.getInstance().getReference("details");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                if (dataSnapshot.exists()) {
                    for (DataSnapshot productSnapshot : dataSnapshot.getChildren()) {
                        Product p = productSnapshot.getValue(Product.class);
                        String location = p.getLocation();
                        listLocation.add(location);
                        String speciality = p.getSpeciality();
                        listSpeciality.add(speciality);
                    }
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        Gson gson = new Gson();

        ArrayAdapter<String> adapter = new ArrayAdapter<String>
                (this, android.R.layout.select_dialog_item, listLocation);
        location.setThreshold(1); //will start working from first character
        location.setAdapter(adapter);
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>
                (this, android.R.layout.select_dialog_item, listSpeciality);
        speciality.setThreshold(1); //will start working from first character
        speciality.setAdapter(adapter1);

        speciality.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Log.i("", "SELECTED speciality TEXT WAS------->" + listSpeciality.get(i));
                filterSpeciality = listSpeciality.get(i);
            }
        });
        location.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                filterLocation = listLocation.get(i);
                Log.i("", "SELECTED location TEXT WAS------->" + listLocation.get(i));
            }
        });


//        RecyclerActivity.start(this, gson.toJson(productList));


        //Button Search and Clear Work

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
                RecyclerActivity.start(MainActivity.this, filterLocation,filterSpeciality);
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

            case R.id.sendmail:
                Toast.makeText(this, "Send Mail", Toast.LENGTH_SHORT).show();
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
        } else if (id == R.id.signin) {
            Intent intent = new Intent(this, SigninActivity.class);
            startActivity(intent);
            Toast.makeText(this, "Sign In ", Toast.LENGTH_SHORT);
        } else if (id == R.id.news) {
            Intent intent = new Intent(this, NewsActivity.class);
            startActivity(intent);
            Toast.makeText(this, "News! ", Toast.LENGTH_SHORT);
        }
        else if (id == R.id.emergency) {
            Intent intent = new Intent(this, EmergencyActivity.class);
            startActivity(intent);
            Toast.makeText(this, "Emergency! ", Toast.LENGTH_SHORT);
        }


        return false;
    }
}
