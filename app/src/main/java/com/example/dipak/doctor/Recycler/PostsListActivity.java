package com.example.dipak.doctor.Recycler;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dipak.doctor.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.io.ByteArrayOutputStream;

public class PostsListActivity extends AppCompatActivity {

    RecyclerView mRecyclerView;
    FirebaseDatabase mFirebasedatabase;
    DatabaseReference myRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_posts_list);


        // Internet Not Avilable Notification Display
        if(!isConnected(PostsListActivity.this)) buildDialog(PostsListActivity.this).show();
        else {
       //     Toast.makeText(PostsListActivity.this,"Welcome", Toast.LENGTH_LONG).show();
            setContentView(R.layout.activity_posts_list);
        }

        //actionbar
        ActionBar actionBar = getSupportActionBar();
        //settitle
        actionBar.setTitle("Doctors List");

        //Recycler vieew
        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        //set layout linear
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        //send query to firebase database
        mFirebasedatabase = FirebaseDatabase.getInstance();
        myRef = mFirebasedatabase.getReference("details");
    }

    //Internet Connection Notifier

    public boolean isConnected(Context context) {

        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netinfo = cm.getActiveNetworkInfo();

        if (netinfo != null && netinfo.isConnectedOrConnecting()) {
            android.net.NetworkInfo wifi = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            android.net.NetworkInfo mobile = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

            if ((mobile != null && mobile.isConnectedOrConnecting()) || (wifi != null && wifi.isConnectedOrConnecting()))
                return true;
            else return false;
        } else
            return false;
    }

    // Alert Dialog messege
    public AlertDialog.Builder buildDialog(Context c) {

        AlertDialog.Builder builder = new AlertDialog.Builder(c);
        builder.setTitle("No Internet Connection!");
        builder.setMessage("You need to have Mobile Data or wifi to View Doctor Details. Press ok to Exit");

        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

                finish();
            }
        });

        return builder;
    }

    //search data
    private void firebaseSearch(String searchText) {

        // COnvert to lowercase letters
        String query = searchText.toLowerCase();
        Query firebaseSearchQuery = myRef.orderByChild("search").startAt(query).endAt(query + "\uf8ff");
        FirebaseRecyclerAdapter<Model, ViewHolder> firebaseRecyclerAdapter = new
                FirebaseRecyclerAdapter<Model, ViewHolder>(
                        Model.class,
                        R.layout.row,
                        ViewHolder.class, firebaseSearchQuery
                ) {
                    @Override
                    protected void populateViewHolder(ViewHolder viewHolder, Model model, int position) {
                        viewHolder.setDetails(getApplicationContext(), model.getName(), model.getSpeciality(), model.getLocation(), model.getImage(), model.getHospital(), model.getPrice(), model.getEmail(), model.getPhone(),model.getAvailablity());//, model.getPhone(), model.getHospital(), model.getEmail(), model.getPrice()
                    }

                    @Override
                    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                        ViewHolder viewHolder = super.onCreateViewHolder(parent, viewType);

                        viewHolder.setOnClickListener(new ViewHolder.ClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {
                                // view bala
                                TextView mTitleTv = view.findViewById(R.id.rTitleTv);
                                TextView mspTv = view.findViewById(R.id.rSpecialityTv);
                                TextView mDescTv = view.findViewById(R.id.rDescriptionTv);
                                ImageView mImageView = view.findViewById(R.id.rImageView);
                                TextView mHospitalTv = view.findViewById(R.id.rHospitalTv);
                                TextView mPriceTv = view.findViewById(R.id.rPriceTv);
                                TextView mEmailTv = view.findViewById(R.id.rEmailTv);
                                TextView mPhoneTv = view.findViewById(R.id.rPhoneTv);
                                TextView mAvailTv=view.findViewById(R.id.rAvailTv);
                                // TextView mPhoneTv = view.findViewById(R.id.rPhoneTv);
                                //TextView mHospitalTv = view.findViewById(R.id.rHospitalTv);
                                // TextView mEmailTv = view.findViewById(R.id.rEmailTv);
                                //TextView mPriceTv = view.findViewById(R.id.rPriceTv);
                                // to get data from views

                                String mTitle = mTitleTv.getText().toString();
                                String msp = mspTv.getText().toString();
                                String mDesc = mDescTv.getText().toString();
                              /*  String mPhone = mPhoneTv.getText().toString();
                                String mHospital = mHospitalTv.getText().toString();
                                String mEmail = mEmailTv.getText().toString();
                                String mPrice = mPriceTv.getText().toString();
                                */
                                Drawable mDrawable = mImageView.getDrawable();
                                Bitmap mBitmap = ((BitmapDrawable) mDrawable).getBitmap();
                                String mHospital = mHospitalTv.getText().toString();
                                String mPrice = mPriceTv.getText().toString();
                                String mEmail = mEmailTv.getText().toString();
                                String mPhone = mPhoneTv.getText().toString();
                                String mAvail =mAvailTv.getText().toString();
                                //pass this data to new activity
                                Intent intent = new Intent(view.getContext(), PostDetailActivity.class);
                                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                                mBitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
                                byte[] bytes = stream.toByteArray();
                                intent.putExtra("image", bytes);
                                intent.putExtra("speciality", msp);
                                intent.putExtra("name", mTitle);
                                intent.putExtra("location", mDesc);
                                intent.putExtra("hospital", mHospital);
                                intent.putExtra("price", mPrice);
                                intent.putExtra("email", mEmail);
                                intent.putExtra("phone", mPhone);
                                intent.putExtra("availablity",mAvail);
                                //  intent.putExtra("phone", mPhone);
                                //intent.putExtra("hospital", mHospital);
                                //intent.putExtra("email", mEmail);
                                //intent.putExtra("price", mPrice);
                                startActivity(intent);
                            }

                            @Override
                            public void onItemLongClick(View view, int position) {

                            }
                        });

                        return viewHolder;
                    }

                };
        //set adapter to recycler view
        mRecyclerView.setAdapter(firebaseRecyclerAdapter);


    }

    //load data into recycler view

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseRecyclerAdapter<Model, ViewHolder> firebaseRecyclerAdapter = new
                FirebaseRecyclerAdapter<Model, ViewHolder>(

                        Model.class, R.layout.row, ViewHolder.class, myRef
                ) {
                    @Override
                    protected void populateViewHolder(ViewHolder viewHolder, Model model, int position) {

                        viewHolder.setDetails(getApplicationContext(), model.getName(), model.getSpeciality(), model.getLocation(), model.getImage(), model.getHospital(), model.getPrice(), model.getEmail(), model.getPhone(),model.getAvailablity());//, model.getPhone(), model.getHospital(), model.getEmail(), model.getPrice());
                    }

                    @Override
                    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                        ViewHolder viewHolder = super.onCreateViewHolder(parent, viewType);

                        viewHolder.setOnClickListener(new ViewHolder.ClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {
                                // view bala
                                TextView mTitleTv = view.findViewById(R.id.rTitleTv);
                                TextView mspTv = view.findViewById(R.id.rSpecialityTv);
                                TextView mDescTv = view.findViewById(R.id.rDescriptionTv);
                                ImageView mImageView = view.findViewById(R.id.rImageView);
                                TextView mHospitalTv = view.findViewById(R.id.rHospitalTv);
                                TextView mPriceTv = view.findViewById(R.id.rPriceTv);
                                TextView mEmailTv = view.findViewById(R.id.rEmailTv);
                                TextView mPhoneTv = view.findViewById(R.id.rPhoneTv);
                                TextView mAvailTv=view.findViewById(R.id.rAvailTv);
                                //TextView mPhoneTv = view.findViewById(R.id.rPhoneTv);
                                // TextView mHospitalTv = view.findViewById(R.id.rHospitalTv);
                                // TextView mEmailTv = view.findViewById(R.id.rEmailTv);
                                // TextView mPriceTv = view.findViewById(R.id.rPriceTv);
                                // to get data from views

                                String mTitle = mTitleTv.getText().toString();
                                String msp = mspTv.getText().toString();
                                String mDesc = mDescTv.getText().toString();

                                //String mPhone = mPhoneTv.getText().toString();
                                // String mHospital = mHospitalTv.getText().toString();
                                // String mEmail = mEmailTv.getText().toString();
                                // String mPrice = mPriceTv.getText().toString();
                                Drawable mDrawable = mImageView.getDrawable();
                                Bitmap mBitmap = ((BitmapDrawable) mDrawable).getBitmap();

                                String mHospital = mHospitalTv.getText().toString();
                                String mPrice = mPriceTv.getText().toString();
                                String mEmail = mEmailTv.getText().toString();
                                String mPhone = mPhoneTv.getText().toString();
                                String mAvail =mAvailTv.getText().toString();

                                //pass this data to new activity
                                Intent intent = new Intent(view.getContext(), PostDetailActivity.class);
                                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                                mBitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
                                byte[] bytes = stream.toByteArray();
                                intent.putExtra("image", bytes);
                                intent.putExtra("speciality", msp);
                                intent.putExtra("name", mTitle);
                                intent.putExtra("location", mDesc);
                                intent.putExtra("hospital", mHospital);
                                intent.putExtra("price", mPrice);
                                intent.putExtra("email", mEmail);
                                intent.putExtra("phone", mPhone);
                                intent.putExtra("availablity",mAvail);
                                // intent.putExtra("phone", mPhone);
                                // intent.putExtra("hospital", mHospital);
                                //intent.putExtra("email", mEmail);
                                //intent.putExtra("price", mPrice);
                                startActivity(intent);
                            }

                            @Override
                            public void onItemLongClick(View view, int position) {

                            }
                        });

                        return viewHolder;
                    }
                };
        //set adapter to recycler view
        mRecyclerView.setAdapter(firebaseRecyclerAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //inflate menu this add items to actionbar if present
        getMenuInflater().inflate(R.menu.menu_s, menu);
        MenuItem item = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(item);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                firebaseSearch(query);  //s==query
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //filter as you type
                firebaseSearch(newText);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
       /* int id = item.getItemId();
        handle action bar click here
        if (id == R.id.action_setting) {
            //Baki part
            <item
            android:id="@+id/action_setting"
            android:title="Setting"
                    />
            return true;
        } */
        return super.onOptionsItemSelected(item);
    }
}
