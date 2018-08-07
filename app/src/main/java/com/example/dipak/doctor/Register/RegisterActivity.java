package com.example.dipak.doctor.Register;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.dipak.doctor.MainActivity;
import com.example.dipak.doctor.SigninActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.example.dipak.doctor.R;
import com.example.dipak.doctor.Register.Artist;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "RegisterActivity";
    private EditText name, location, price, aviablity, phone, hospital;
    private Button insert;
    private Spinner speciality;
    FirebaseAuth mAuth;
    FirebaseDatabase mFirebaseDatabase;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private DatabaseReference myRef;
    Button btnSignOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        btnSignOut = findViewById(R.id.email_sign_out_button);
        mAuth = FirebaseAuth.getInstance();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        myRef = mFirebaseDatabase.getReference();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                    toastMessage("Successfully signed in with: " + user.getEmail());
                } else {
                    // User is signed out
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                    toastMessage("Successfully signed out.");
                    Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                    startActivity(intent);

                }

            }

        };
        btnSignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.signOut();
                toastMessage("Signing Out...");
            }
        });
        myRef = FirebaseDatabase.getInstance().getReference("details");
        init();
        setlistner();

    }


    @Override
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

    private void toastMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
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

}
