package com.example.black.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.black.Classes.BagItems;
import com.example.black.R;

import java.util.ArrayList;

public class ShoppingBagAdapter extends RecyclerView.Adapter<ShoppingBagAdapter.ProductViewHolder> {
    private ArrayList<BagItems> mProductItems ;
    private OnItemClickListener mListener;

    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        mListener = listener;
    }

    public static class ProductViewHolder extends RecyclerView.ViewHolder{

        public ImageView ivProductImage;
        public TextView tvProductPrice, tvProductName, tvProductSize;


        public ProductViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);
            ivProductImage = itemView.findViewById(R.id.bag_item_image);
            tvProductName = itemView.findViewById(R.id.bag_item_name);
            tvProductPrice = itemView.findViewById(R.id.bag_item_price);
            tvProductSize = itemView.findViewById(R.id.bag_item_size);
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

    public ShoppingBagAdapter(ArrayList<BagItems> productItems){
        mProductItems = productItems ;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.bag_item, parent, false);
        ProductViewHolder pvh = new ProductViewHolder(v, mListener);
        return pvh;
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        BagItems bagItems = mProductItems.get(position);
        holder.ivProductImage.setImageResource(bagItems.getmImage());
        holder.tvProductName.setText(bagItems.getmProductName());
        holder.tvProductSize.setText(bagItems.getmSize());
        if (bagItems.getmProductSalePrice() == 0 )
            holder.tvProductPrice.setText(bagItems.getmProductPrice()+" EGP");
        else
            holder.tvProductPrice.setText(bagItems.getmProductSalePrice()+" EGP");


    }

    @Override
    public int getItemCount() {
        return mProductItems.size();
    }
}
