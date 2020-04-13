package com.example.black.Adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.black.R;

import java.util.ArrayList;

public class BuyProductsAdapter extends RecyclerView.Adapter<BuyProductsAdapter.ViewHolder> {
    private static final String TAG = "BuyProductsAdapter";

    private Context context ;
    private ArrayList<Integer> image = new ArrayList<>();
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d(TAG, "onCreateViewHolder: called.");
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.selected_products_list, parent,false);
        return new ViewHolder(view);
    }

    public BuyProductsAdapter(Context context, ArrayList<Integer> image) {
        this.context = context;
        this.image = image;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder: called");
        holder.ivImage.setImageResource(image.get(position));
    }

    @Override
    public int getItemCount() {
        return image.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView ivImage ;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivImage = itemView.findViewById(R.id.iv_product_buy);
        }
    }
}
