package com.example.dipak.doctor.Professional;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dipak.doctor.R;
import com.example.dipak.doctor.Recycler.Model;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ViewHolder_p extends RecyclerView.ViewHolder {

    View pView;
    List<Model> productList;


    public ViewHolder_p(@NonNull View itepView) {
        super(itepView);

        pView = itepView;

        //item click garda
        itepView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mClickListener.onItemClick(view,getAdapterPosition());
            }
        });
//itemLOng CLick bala
        itepView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                mClickListener.onItemLongClick(view,getAdapterPosition());
                return true;
            }
        });

    }
    public void setDetails(Context ctx, String name, String speciality, String location, String image, String price,String email,String phone,String availablity)// String hospital,,String phone , String hospital, String email, String price
    {

        TextView mTitleTv=pView.findViewById(R.id.rTitleTv);
        TextView mspTv=pView.findViewById(R.id.rSpecialityTv);
        ImageView mImageTv=pView.findViewById(R.id.rImageView);
        TextView mDetailTv=pView.findViewById(R.id.rDescriptionTv);
       // TextView mHospitalTv=pView.findViewById(R.id.rHospitalTv);
        TextView mPriceTv=pView.findViewById(R.id.rPriceTv);
        TextView mEmailTv=pView.findViewById(R.id.rEmailTv);
        TextView mPhoneTv=pView.findViewById(R.id.rPhoneTv);
        TextView mAvailTv=pView.findViewById(R.id.rAvailTv);


          /*  TextView  mPhoneTv=pView.findViewById(R.id.PhoneTv);
            TextView  mHospitalTv=pView.findViewById(R.id.HospitalTv);
            TextView   mEmailTv=pView.findViewById(R.id.EmailTv);
            TextView mPriceTv=pView.findViewById(R.id.PriceTv);
*/

        //set data
        mTitleTv.setText(name);
        mspTv.setText(speciality);
        mDetailTv.setText(location);
        //mHospitalTv.setText(hospital);
        mPriceTv.setText(price);
        mEmailTv.setText(email);
        mPhoneTv.setText(phone);
        mAvailTv.setText(availablity);
        //  mPhoneTv.setText(phone);
        //           mHospitalTv.setText(hospital);
        //         mEmailTv.setText(email);
        //       mPriceTv.setText(price);
        Picasso.get().load(image).into(mImageTv);
    }
    private ViewHolder_p.ClickListener mClickListener;
    //interface
    public interface ClickListener
    {
        void onItemClick(View view, int position);
        void onItemLongClick(View view, int position);
    }
    public void setOnClickListener(ViewHolder_p.ClickListener clickListener)
    {
        mClickListener=clickListener;
    }

}
