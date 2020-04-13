package com.example.black.Adapters;

import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.black.Classes.ProductItems;
import com.example.black.R;

import java.util.ArrayList;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {
    private ArrayList<ProductItems> mProductItems ;
    private OnItemClickListener mListener;

    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        mListener = listener;
    }
    public static class ProductViewHolder extends RecyclerView.ViewHolder{

        public ImageView ivProductImage;
        public TextView tvProductPrice, tvProductSale, tvProductName;

        public ProductViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);
            ivProductImage = itemView.findViewById(R.id.iv_product_image);
            tvProductName = itemView.findViewById(R.id.tv_product_name);
            tvProductPrice = itemView.findViewById(R.id.tv_product_price);
            tvProductSale = itemView.findViewById(R.id.tv_product_sale_price);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener != null){
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION){
                            listener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }

    public ProductAdapter (ArrayList<ProductItems> productItems){
        mProductItems = productItems ;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_item, parent, false);
        ProductViewHolder pvh = new ProductViewHolder(v, mListener);
        return pvh;
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        ProductItems productItems = mProductItems.get(position);
        holder.ivProductImage.setImageResource(productItems.getmProductImage());
        holder.tvProductName.setText(productItems.getmProductName());
        holder.tvProductSale.setText(productItems.getmProductSalePrice()+" EGP");
        holder.tvProductPrice.setText(productItems.getmProductPrice()+" EGP");
        if (productItems.getmProductSalePrice() != 0) {
            holder.tvProductPrice.setPaintFlags(holder.tvProductPrice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        }else {
            holder.tvProductSale.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return mProductItems.size();
    }
}
