package com.example.dipak.doctor;

import android.content.Context;
import android.content.Intent;
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
import android.widget.EditText;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;
    Toolbar toolbar;
    ActionBarDrawerToggle actionBarDrawerToggle;
    AutoCompleteTextView speciality, location;
    ViewFlipper ViewFlipper;
    private List<Product> productList;

    public static void start(Context context, String productList) {
        Intent mainIntent = new Intent(context, MainActivity.class);
        mainIntent.putExtra("details", productList);
        context.startActivity(mainIntent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewFlipper = findViewById(R.id.photo_fliper);

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
                newactivity();
                break;

            case R.id.admin_id:
                Toast.makeText(this, "Register Clicked", Toast.LENGTH_SHORT).show();
                registeractivity();
                break;
        }

        return true;
    }

    public void newactivity() {
        Intent intent = new Intent(MainActivity.this, AboutActivity.class);
        startActivity(intent);
    }

    public void gameactivity() {
        Intent intent = new Intent(MainActivity.this, GameActivity.class);
        startActivity(intent);
    }

    public void registeractivity() {
        Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
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

    public void closekeyboard(View view) {

        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }


}
