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

    private EditText name, location, price, aviablity;
    private Button insert;
    private Spinner speciality;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        reference = FirebaseDatabase.getInstance().getReference("details");

        init();
        setlistner();
    }

    private void init() {
        name = (EditText) findViewById(R.id.edittext_name);
        location = (EditText) findViewById(R.id.edittext_location);
        price = (EditText) findViewById(R.id.edittext_price);
        aviablity = (EditText) findViewById(R.id.edittext_aviablity);
        speciality = (Spinner) findViewById(R.id.spinner_spe);
        insert = (Button) findViewById(R.id.button);


    }

    private void setlistner() {
        insert.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.button:
                artist ar=new artist(name.getText().toString().trim(),location.getText().toString().trim(),Integer.parseInt(price.getText().toString().trim()), aviablity.getText().toString().trim(),speciality.getSelectedItem().toString().trim());
                saveData(ar);
                Toast.makeText(this,"Inserted Successfully",Toast.LENGTH_LONG).show();
                break;
        }

    }

    private void saveData(artist ar) {
        FirebaseDatabase database=FirebaseDatabase.getInstance();
        DatabaseReference myRef=database.getReference("details");
        myRef.push().setValue(ar);

    }
}
