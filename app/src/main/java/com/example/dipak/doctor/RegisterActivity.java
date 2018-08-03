package com.example.dipak.doctor;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {


    /*
    <Spinner
        android:id="@+id/spinner_spe"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:entries="@array/speciality"
        android:dropDownWidth="wrap_content"
        android:layout_weight="1"
        />*/
    private EditText name, location, price, aviablity;
    private Button insert;
   // private Spinner speciality;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        reference = FirebaseDatabase.getInstance().getReference("details");

        init();
        setlistner();


    }

    public void clearboxes()
    {
        name.setText("");
        location.setText("");
        price.setText("");
        aviablity.setText("");
    }
    private void init() {
        name = (EditText) findViewById(R.id.edittext_name);
        location = (EditText) findViewById(R.id.edittext_location);
        price = (EditText) findViewById(R.id.edittext_price);
        aviablity = (EditText) findViewById(R.id.edittext_aviablity);
     //   speciality = (Spinner) findViewById(R.id.spinner_spe);
        insert = (Button) findViewById(R.id.button);


    }

    private void setlistner() {
        insert.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button:
                Artist artist = new Artist(name.getText().toString().trim(), location.getText().toString().trim(), price.getText().toString().trim(), aviablity.getText().toString().trim());
//, speciality.getSelectedItem().toString().trim()
                saveData(artist);
                Toast.makeText(this, "Inserted Successfully", Toast.LENGTH_LONG).show();
                clearboxes();
                break;
        }

    }

    private void saveData(Artist artist) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("details");
        myRef.push().setValue(artist);

    }
}
