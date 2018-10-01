/*Created By @ Dipendra Pant(2018)*/
package com.example.dipak.doctor.Emergency;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dipak.doctor.R;


public class EmergencyActivity extends AppCompatActivity {

    int[] images = {R.drawable.ambulance,R.drawable.seti, R.drawable.mahakali, R.drawable.patan, R.drawable.accham, R.drawable.bajura, R.drawable.doti, R.drawable.dadeldhura,R.drawable.ambulance};//
    String[] name = {"Nepal Red Cross Society Ambulance","Seti Zonal Hospital", "Mahakali Zonal Hospital", "Patan Hospital Baitadi","Accham District Hospital","Bajura District Hospital","Doti District Hospital","Dadeldhura Hospital","All Nepal Ambulance Service"};
    String[] details = {"Dhangadhi,Kailali","Kailali,Dhangadhi", "Mahendranagar,Kanchanpur", "Baitadi,Patan","Accham,Mangalsen","Bajra,Martadi","Doti,Silgadi","Dadeldhura,Tufandada","Throughout Nepal"};
    String[] phone = {"091-521600","091-521271", "094-526645", "099-426645","091-521271", "094-526645", "099-426645","091-523312","112"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergency);

        getSupportActionBar().setTitle("Emergency Contact ");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ListView listView = findViewById(R.id.listview);
        CustomAdapter customAdapter = new CustomAdapter();
        listView.setAdapter(customAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
               // Toast.makeText(EmergencyActivity.this, "this item clicked" + position, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(EmergencyActivity.this, EdisplayActivity.class);
                intent.putExtra("image_name", images[position]);
                intent.putExtra("name", name[position]);
                intent.putExtra("detail", details[position]);
                intent.putExtra("phone", phone[position]);
                startActivity(intent);
            }
        });

    }


    class CustomAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return images.length;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {

            view = getLayoutInflater().inflate(R.layout.custom_layout, null);

            ImageView imageView =  view.findViewById(R.id.img_View);
            TextView name_tv =  view.findViewById(R.id.name_tv);
            TextView job_tv =  view.findViewById(R.id.job_tv);
            TextView phone_tv =  view.findViewById(R.id.phone_tv);
            imageView.setImageResource(images[i]);
            name_tv.setText(name[i]);
            job_tv.setText(details[i]);
            phone_tv.setText(phone[i]);
            return view;
        }
    }

}
