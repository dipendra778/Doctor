package com.example.dipak.doctor.Register;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.dipak.doctor.R;
import com.example.dipak.doctor.Register.Artist;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private EditText name, location, price, aviablity, phone, hospital;
    private Button insert;//signout;
    private Spinner speciality;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        reference = FirebaseDatabase.getInstance().getReference("details");
/*        signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.signOut();
                toastMessage("Signing Out...");
            }
        });
*/
        init();
        setlistner();



    }

    public void clearboxes() {
        name.setText("");
        location.setText("");
        price.setText("");
        aviablity.setText("");
        phone.setText("");
        hospital.setText("");
    }

    private void init() {
        name = findViewById(R.id.edittext_name);
        location = findViewById(R.id.edittext_location);
        price = findViewById(R.id.edittext_price);
        aviablity = findViewById(R.id.edittext_aviablity);
        speciality = findViewById(R.id.spinner_spe);
        insert = findViewById(R.id.button);
       // signout = findViewById(R.id.buttonsignout);
        phone = findViewById(R.id.edittext_phone);
        hospital = findViewById(R.id.edittext_Hospital);


    }

    private void setlistner() {
        insert.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button:
                Artist artist = new Artist(name.getText().toString().trim(), location.getText().toString().trim(), price.getText().toString().trim(), aviablity.getText().toString().trim(), speciality.getSelectedItem().toString().trim(), Integer.parseInt(phone.getText().toString().trim()), hospital.getText().toString().trim());
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

  /*  @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

    private void toastMessage(String message){
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }*/
}
