package com.example.dipak.doctor.Recycler;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.dipak.doctor.R;

import java.util.List;


public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.ProductViewHolder> {

    Context mCtx;
    List<Product> productList;

    public ProductsAdapter(Context mCtx, List<Product> productList) {
        this.mCtx = mCtx;
        this.productList = productList;
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mCtx).inflate(R.layout.product_layout,
                parent, false);
        ProductViewHolder productViewHolder = new ProductViewHolder(view);
        return productViewHolder;
    }

    @Override
    public void onBindViewHolder(ProductViewHolder holder, int position) {
        Product product = productList.get(position);
        holder.textViewname.setText(product.getName());
        holder.textViewlocation.setText(product.getLocation());
        holder.textviewprice.setText(product.getPrice());
        String phone= String.valueOf(product.getPhone());
        holder.textviewphone.setText(phone);
        holder.textViewavail.setText(product.getAvailablity());
        holder.textviewhospital.setText(product.getHospital());


        holder.spinnerspeciality.setText(product.getSpeciality());

//        holder.imageView.setImageDrawable(mCtx.getResources().getDrawable(product.getImage(), null));
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    class ProductViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView textViewname, textViewlocation, textviewphone, textviewprice, textViewavail, textviewhospital,spinnerspeciality;


        public ProductViewHolder(View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imageView);
            textViewname = itemView.findViewById(R.id.textViewName);
            textViewlocation = itemView.findViewById(R.id.textViewLocation);
            textViewavail = itemView.findViewById(R.id.textViewAvail);
            textviewprice = itemView.findViewById(R.id.textViewPrice);
            textviewphone = itemView.findViewById(R.id.textViewPhone);
            textviewhospital = itemView.findViewById(R.id.textViewHospital);
            spinnerspeciality = itemView.findViewById(R.id.textViewSpinner);


        }
    }
}