package com.example.black.Fragments;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.black.Activities.MainActivity;
import com.example.black.Adapters.ProductAdapter;
import com.example.black.Classes.BagItems;
import com.example.black.Classes.ProductItems;
import com.example.black.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProductsFragment extends Fragment implements View.OnClickListener {
    private static final String TAG = "ProductsFragment";
    private ArrayList<ProductItems> myItems = new ArrayList<>();

    private RecyclerView mRecyclerView;
    private ProductAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    static int []Price;
    static int []SalePrice;
    static String []Names;
    static TypedArray Images;

    @BindView(R.id.iv_product_back)
    ImageView btnBack;
    @BindView(R.id.tv_product_filter)
    TextView tvFilter;
    @BindView(R.id.tv_product_grid)
    TextView tvGridName;

    public static BagItems items ;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_products, container, false);

        ButterKnife.bind(this, view);
        Log.d(TAG, "onCreateView: Started");

        btnBack.setOnClickListener(this);
        addMenProducts();

        mRecyclerView = view.findViewById(R.id.product_recyclerview);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new GridLayoutManager(getContext(), 2);
        mAdapter = new ProductAdapter(myItems);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new ProductAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                MainActivity.position = position ;
               items = new BagItems(Images.getResourceId(position,1),
                        Names[position],
                        Price[position],
                        SalePrice[position]) ;
                openNewFragment(new ProductDetailsFragment());
            }
        });
        return view;
    }

    private void openNewFragment(Fragment fragment) {
        getActivity().getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(R.anim.slide_out_up, R.anim.slide_in_up, R.anim.slide_out_up, R.anim.slide_in_up)
                .replace(R.id.product_d_framelayout, fragment).addToBackStack(null)
                .commit();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.iv_product_back:
                getActivity().getSupportFragmentManager().popBackStack();
                break;
        }
    }


    private void addMenProducts() {
        if (MainActivity.Title=="Men"){

            Names = getResources().getStringArray(R.array.men_items_name);
            Price = getResources().getIntArray(R.array.men_items_price);
            SalePrice = getResources().getIntArray(R.array.men_items_sale_price);
            Images = getResources().obtainTypedArray(R.array.men_imgs);

        }else if (MainActivity.Title=="Women"){

            Names = getResources().getStringArray(R.array.women_items_name);
            Price = getResources().getIntArray(R.array.women_items_price);
            SalePrice = getResources().getIntArray(R.array.women_items_sale_price);
            Images = getResources().obtainTypedArray(R.array.women_imgs);

        }else {

            Names = getResources().getStringArray(R.array.kid_items_name);
            Price = getResources().getIntArray(R.array.kid_items_price);
            SalePrice = getResources().getIntArray(R.array.kid_items_sale_price);
            Images = getResources().obtainTypedArray(R.array.kid_imgs);

        }
        for (int i = 0; i<Images.length(); i++){

            myItems.add(new ProductItems(Images.getResourceId(i,0), Price[i], SalePrice[i],
                    Names[i]));

        }

    }

}
