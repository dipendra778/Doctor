/*Created By @ Dipendra Pant(2018)*/
package com.example.dipak.doctor.Emergency;

import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dipak.doctor.R;

public class EdisplayActivity extends AppCompatActivity {

    ImageView imageView;
    TextView nametext, detaitext, phonetext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edisplay);

        getSupportActionBar().setTitle("Emergency Details");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        imageView=findViewById(R.id.imageView);
        nametext=findViewById(R.id.name);
        detaitext=findViewById(R.id.detail);
        phonetext=findViewById(R.id.phone);

        Intent intent = getIntent();
        if (intent.hasExtra("image_name")) {
            imageView.setImageResource(intent.getIntExtra("image_name", 0));
            nametext.setText(intent.getStringExtra("name"));
            detaitext.setText(intent.getStringExtra("detail"));
            phonetext.setText(intent.getStringExtra("phone"));
        }

        //make a phone call when clicked on textview
        phonetext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phonevalue = phonetext.getText().toString();
                Intent callintent = new Intent(Intent.ACTION_DIAL);
                callintent.setData(Uri.parse("tel:"+phonevalue));
                startActivity(callintent);
            }
        });
    }
}
