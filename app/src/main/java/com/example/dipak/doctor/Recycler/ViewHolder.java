package com.example.dipak.doctor.Recycler;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dipak.doctor.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ViewHolder extends RecyclerView.ViewHolder {

    View mView;
    List<Model> productList;


    public ViewHolder(@NonNull View itemView) {
        super(itemView);

        mView = itemView;

        //item click garda
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mClickListener.onItemClick(view,getAdapterPosition());
            }
        });
//itemLOng CLick bala
        itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                mClickListener.onItemLongClick(view,getAdapterPosition());
                return true;
            }
        });

    }
        public void setDetails(Context ctx, String name, String speciality, String location,
                               String image, String hospital, String price,
                               String email,String phone,String availablity,String qualification)// ,String phone , String hospital, String email, String price
        {

            TextView mTitleTv=mView.findViewById(R.id.rTitleTv);
            TextView mspTv=mView.findViewById(R.id.rSpecialityTv);
            ImageView mImageTv=mView.findViewById(R.id.rImageView);
            TextView mDetailTv=mView.findViewById(R.id.rDescriptionTv);
            TextView mHospitalTv=mView.findViewById(R.id.rHospitalTv);
            TextView mPriceTv=mView.findViewById(R.id.rPriceTv);
            TextView mEmailTv=mView.findViewById(R.id.rEmailTv);
            TextView mPhoneTv=mView.findViewById(R.id.rPhoneTv);
            TextView mAvailTv=mView.findViewById(R.id.rAvailTv);
            TextView mQualTv=mView.findViewById(R.id.rQualificationTv);




            //set data
            mTitleTv.setText(name);
            mspTv.setText(speciality);
            mDetailTv.setText(location);
            mHospitalTv.setText(hospital);
            mPriceTv.setText(price);
            mEmailTv.setText(email);
            mPhoneTv.setText(phone);
            mAvailTv.setText(availablity);
            mQualTv.setText(qualification);

            Picasso.get().load(image).into(mImageTv);
        }
        private ViewHolder.ClickListener mClickListener;
//interface
       public interface ClickListener
       {
           void onItemClick(View view, int position);
           void onItemLongClick(View view, int position);
       }
        public void setOnClickListener(ViewHolder.ClickListener clickListener)
        {
            mClickListener=clickListener;
        }

}
