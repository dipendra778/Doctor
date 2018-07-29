package com.example.dipak.doctor;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText speciality, location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        speciality = (EditText) findViewById(R.id.et_spec);
        location = (EditText) findViewById(R.id.et_loca);
        getSupportActionBar().setTitle("Doctor");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Button Search = (Button) findViewById(R.id.btn_srch);
        Button Clear = (Button) findViewById(R.id.btn_clr);


        Clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                speciality.setText("");
                location.setText("");
            }
        });

        Search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, RecyclerActivity.class);
                startActivity(intent);

            }
        });

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

            case R.id.about_id:
                Toast.makeText(this, "About Clicked", Toast.LENGTH_SHORT).show();
                newactivity();

            case R.id.admin_id:
                Toast.makeText(this, "Register Clicked", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(intent);
        }

        return true;
    }

    public void newactivity() {
        Intent intent = new Intent(MainActivity.this, AboutActivity.class);
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
