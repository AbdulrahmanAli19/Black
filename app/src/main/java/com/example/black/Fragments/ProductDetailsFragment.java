package com.example.black.Fragments;

import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.black.Activities.MainActivity;
import com.example.black.Adapters.ImageAdapter;
import com.example.black.Classes.BagItems;
import com.example.black.R;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProductDetailsFragment extends Fragment implements View.OnClickListener {
    private static final String TAG = "ProductDetailsFragment";
    @BindView(R.id.product_d_name)
    TextView tvProductName;
    @BindView(R.id.product_d_price)
    TextView tvProductPrice;
    @BindView(R.id.product_d_sale_price)
    TextView tvProductSalePrice;
    /*@BindView(R.id.tv_product_d_bag_num)
    TextView tvBagNum;*/
    @BindView(R.id.iv_product_details_close)
    ImageView ivClose;
    @BindView(R.id.iv_product_d_bag)
    ImageView ivBag;
    @BindView(R.id.iv_product_d_save)
    ImageView ivSave;
    @BindView(R.id.iv_product_d_share)
    ImageView ivShare;
    @BindView(R.id.rl_product_add)
    RelativeLayout rlAdd;
    @BindView(R.id.rl_product_addd)
    RelativeLayout rlAddd;
    @BindView(R.id.product_viewpager)
    ViewPager viewPagerProduct;
    @BindView(R.id.product_tablayout)
    TabLayout tableLayout;

    public static TextView tvBagNum ;
    private FragmentProductDetailsListener fragmentProductDetailsListener ;

    private ImageAdapter imageAdapter;


    private ArrayList<Integer> productImages = new ArrayList<>();
    private   String productName = ProductsFragment.Names[MainActivity.position] ;
    private   int Price = ProductsFragment.Price[MainActivity.position];
    private   int salePrice = ProductsFragment.SalePrice[MainActivity.position];
    private   TypedArray Images ;

    private int imageUri;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_product_details, container, false);

        ButterKnife.bind(this, view);
        ivClose.setOnClickListener(this);
        ivBag.setOnClickListener(this);
        ivSave.setOnClickListener(this);
        ivShare.setOnClickListener(this);
        rlAdd.setOnClickListener(this);
        rlAddd.setOnClickListener(this);
        tvBagNum = view.findViewById(R.id.tv_product_d_bag_num);
        addImages();

        tvProductName.setText(productName);

        tvProductPrice.setText(String.valueOf(Price) + " EGP");

        tvProductPrice.setPaintFlags(tvProductPrice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

        tvProductSalePrice.setText(String.valueOf(salePrice) + " EGP");

        imageAdapter = new ImageAdapter(getContext(), productImages);

        viewPagerProduct.setAdapter(imageAdapter);

        tableLayout.setupWithViewPager(viewPagerProduct);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        tvBagNum.setText(String.valueOf(MainActivity.bagItemsNum));
    }

    public void addImages() {
        if (MainActivity.Title == "Men") {
            for (int i = 0; i <= 11 ; i++){
                if (MainActivity.position == i){
                    int []valuse = {R.array.men_1, R.array.men_2, R.array.men_3, R.array.men_4, R.array.men_5, R.array.men_6,
                            R.array.men_7, R.array.men_8, R.array.men_9, R.array.men_10, R.array.men_11, R.array.men_12};
                    Images = getResources().obtainTypedArray(valuse[i]);
                }
            }

        } else if (MainActivity.Title == "Women") {
            for (int i = 0; i <= 11 ; i++){
                if (MainActivity.position == i){
                    int []valuse = {R.array.women_1, R.array.women_2, R.array.women_3, R.array.women_4, R.array.women_5, R.array.women_6,
                            R.array.women_7, R.array.women_8, R.array.women_9, R.array.women_10, R.array.women_11, R.array.women_12};
                    Images = getResources().obtainTypedArray(valuse[i]);
                }
            }
        } else {
            for (int i = 0; i <= 11 ; i++){
                if (MainActivity.position == i){
                    int values [] = {R.array.kid_1, R.array.kid_2, R.array.kid_3, R.array.kid_4, R.array.kid_5, R.array.kid_6,
                            R.array.kid_7, R.array.kid_8, R.array.kid_9, R.array.kid_10, R.array.kid_11, R.array.kid_12};
                    Images = getResources().obtainTypedArray(values[i]);
                }
            }
        }

        for (int i = 0; i < Images.length(); i++) {
            productImages.add(Images.getResourceId(i, 0));
        }

    }



    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_product_details_close:
                getActivity().getSupportFragmentManager().popBackStack();
                break;
            case R.id.iv_product_d_bag:
                openNewFragment(new ShoppingBagFragment(), R.id.shopping_d_container);
                break;
            case R.id.iv_product_d_save:
                saveProduct();
                break;
            case R.id.iv_product_d_share:
                share();
                break;
            case R.id.rl_product_add:
                openNewFragment(new SelectSizeFragment(), R.id.size_container);
                break;
                case R.id.rl_product_addd:
                openNewFragment(new SelectSizeFragment(), R.id.size_container);
                break;
        }
    }

    public void updateProductData (String ProductName, int ProductImage,
                                   int ProductPrice, int ProductSalePrice,
                                   String Title, int Position) {
        tvProductName.setText(ProductName);
        tvProductPrice.setText(String.valueOf(ProductPrice)+" EGP");
        tvProductPrice.setPaintFlags(tvProductPrice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        tvProductSalePrice.setText(String.valueOf(ProductSalePrice)+" EGP");
    }

    private void share() {
        Intent i ;
    }

    private void saveProduct() {

    }



    private void openNewFragment(Fragment fragment, int container) {
        getActivity().getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(R.anim.slide_out_up, R.anim.slide_in_up, R.anim.slide_out_up, R.anim.slide_in_up)
                .replace(container, fragment).addToBackStack(null)
                .commit();
    }

    public interface FragmentProductDetailsListener {
        void getProductData(String ProductName, int ProductImage,
                            int ProductPrice, int ProductSalePrice,
                            String Title, int Position);
    }

   /* @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof FragmentProductDetailsListener){
            fragmentProductDetailsListener = (FragmentProductDetailsListener) context;
        }else {
            throw new RuntimeException(context.toString() +
                    "must implement FragmentProductDetailsListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        fragmentProductDetailsListener = null ;
    }*/

}
