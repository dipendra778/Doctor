package com.example.dipak.doctor.Recycler;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.dipak.doctor.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class RecyclerActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    ProductsAdapter adapter;
    List<Product> productList;
    private Gson gson;
    private Product product;
    private String speciality, location;

    public static void start(Context context, String filteredLocation, String filterSpeciality) {
        Intent intent = new Intent();
        intent.setClass(context, RecyclerActivity.class);
        intent.putExtra("location", filteredLocation);
        intent.putExtra("speciality", filterSpeciality);
        context.startActivity(intent);
    }

    // Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);

        getSupportActionBar().setTitle("Doctors Details");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        gson = new Gson();
        productList = new ArrayList<>();

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("details");
        Query query = reference.child("details").orderByChild("speciality").startAt(speciality).endAt(speciality);
        if (getIntent().getExtras() != null) {
            Bundle extras = getIntent().getExtras();
            location = extras.getString("location");
            speciality = extras.getString("speciality");
        }

        reference.addValueEventListener(new ValueEventListener()
         {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                if (dataSnapshot.exists()) {

                    for (DataSnapshot productSnapshot : dataSnapshot.getChildren()) {
                        Product p = productSnapshot.getValue(Product.class);
                        productList.add(p);
                    }

                    adapter = new ProductsAdapter(RecyclerActivity.this, productList);
                    recyclerView.setAdapter(adapter);

                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }



}

