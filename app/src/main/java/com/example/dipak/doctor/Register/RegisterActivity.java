/*Created By @ Dipendra Pant(2018)*/
package com.example.dipak.doctor.Register;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.dipak.doctor.MainActivity;
import com.example.dipak.doctor.SigninActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.example.dipak.doctor.R;
import com.example.dipak.doctor.Register.Artist;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.util.UUID;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "RegisterActivity";
    private EditText name, location, price, aviablity, phone, hospital,email,search,qualification;
    private Button insert;
    private Spinner speciality;
    FirebaseAuth mAuth;
    FirebaseDatabase mFirebaseDatabase;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private DatabaseReference myRef;
    Button btnSignOut;

 /*   // Image Insert
    private Uri filePath;
    private final int PICK_IMAGE_REQUEST=71;
    ImageView imgInsert;
    Button btn_choose,btn_upload;

    //firebase
    FirebaseStorage firebaseStorage;
    StorageReference storageReference;
*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
/*

        btn_choose=findViewById(R.id.button_choose);
        btn_upload=findViewById(R.id.button_upload);

        btn_choose.setOnClickListener(new View.OnClickListener() {
        //    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP_MR1)
            @Override
            public void onClick(View view) {
                chooseimage();
            }
        });

        btn_upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uploadImage();

            }
        });
*/
        btnSignOut = findViewById(R.id.email_sign_out_button);
        mAuth = FirebaseAuth.getInstance();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        myRef = mFirebaseDatabase.getReference();
//        firebaseStorage=FirebaseStorage.getInstance();
        //
  //      storageReference= firebaseStorage.getReference();

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

  /*  private void uploadImage() {

        if (filePath!=null)
        {
            final ProgressDialog progressDialog=new ProgressDialog(this);
            progressDialog.setTitle("Uploading");
            progressDialog.show();

            StorageReference reference= storageReference.child("images/"+ UUID.randomUUID().toString());


               reference.putFile(filePath)
                   .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                       @Override
                       public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                           progressDialog.dismiss();
                           Toast.makeText(RegisterActivity.this," Image Uploaded",Toast.LENGTH_LONG).show();
                       }
                   })
                       .addOnFailureListener(new OnFailureListener() {
                           @Override
                           public void onFailure(@NonNull Exception e) {
                               progressDialog.dismiss();
                               Toast.makeText(RegisterActivity.this,"Image Uploading Failed"+e.getMessage(),Toast.LENGTH_LONG).show();
                           }
                       })


        }
    }

    //@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP_MR1)
    private void chooseimage() {
        Intent intent=new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,"Select Image"),PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode==PICK_IMAGE_REQUEST && requestCode == RESULT_OK && data!=null && data.getData()!=null)
        {
            filePath=data.getData();
            try {
                Bitmap bitmap= MediaStore.Images.Media.getBitmap(getContentResolver(),filePath);
                imgInsert.setImageBitmap(bitmap);
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }

        }
    }
*/
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
       email.setText("");
       search.setText("");
        qualification.setText("");
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
        email = findViewById(R.id.edittext_Email);
        search=findViewById(R.id.edittext_Search);
        qualification=findViewById(R.id.edittext_Qualification);

        }

    private void setlistner() {
        insert.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            //Add  validation if left blank

            case R.id.button:
                if(name.getText().toString().isEmpty()||
                location.getText().toString().isEmpty()||
                        price.getText().toString().isEmpty()||
                        aviablity.getText().toString().isEmpty()||
                        speciality.getSelectedItem().toString().isEmpty()||
                        phone.getText().toString().isEmpty()|| hospital.getText().toString().isEmpty()||
                        email.getText().toString().isEmpty()||search.getText().toString().isEmpty()||
                        qualification.getText().toString().isEmpty())
                {
                    Toast.makeText(this,"Fields are empty !",Toast.LENGTH_LONG).show();
                }
                else
                {
                Artist artist = new Artist(name.getText().toString().trim(),
                        location.getText().toString().trim(),
                        price.getText().toString().trim(),
                        aviablity.getText().toString().trim(),
                        speciality.getSelectedItem().toString().trim(),
                        phone.getText().toString().trim(), hospital.getText().toString().trim(),
                        email.getText().toString().trim(),search.getText().toString().trim(),
                        qualification.getText().toString().trim());

                saveData(artist);
                Toast.makeText(this, "Inserted Successfully into Realtime Database", Toast.LENGTH_LONG).show();
                clearboxes();
                break;}
        }

    }

    private void saveData(Artist artist) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("details");
        myRef.push().setValue(artist);

    }

    //CLoses soft keyboard
    public void closekeyboard(View view) {

        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}
