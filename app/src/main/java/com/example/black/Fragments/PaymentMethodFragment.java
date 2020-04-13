package com.example.black.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.black.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PaymentMethodFragment extends Fragment implements View.OnClickListener {
    public static String paymentMethod;
    public static int paymentMethodImage;
    @BindView(R.id.payment_mastercard)
    RelativeLayout masterCard;
    @BindView(R.id.payment_visa)
    RelativeLayout visa;
    @BindView(R.id.payment_cod)
    RelativeLayout cod;

    /*@BindView(R.id.tv_payment_total_price)
    TextView tvTotalPrice;
    @BindView(R.id.tv_payment_product_num)
    TextView tvProductsNum;
    @BindView(R.id.tv_payment_product_price)
    TextView tvProductsPrice;*/
    public static TextView tvTotalPrice, tvProductsNum, tvProductsPrice;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_payment_method, container, false);
        ButterKnife.bind(this, view);
        masterCard.setOnClickListener(this);
        visa.setOnClickListener(this);
        cod.setOnClickListener(this);
        tvTotalPrice = view.findViewById(R.id.tv_payment_total_price);
        tvProductsPrice = view.findViewById(R.id.tv_payment_product_price);
        tvProductsNum = view.findViewById(R.id.tv_payment_product_num);

        tvProductsNum.setText(String.valueOf(ShoppingBagFragment.items) + " products");
        tvProductsPrice.setText(String.valueOf(ShoppingBagFragment.totalPrice) + " EGP");
        tvTotalPrice.setText(String.valueOf(ShoppingBagFragment.totalPrice) + " EGP");

        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.payment_cod:
                paymentMethod = "COD";
                paymentMethodImage = R.drawable.ic_cod;
                openNewFragment(new BuyFragment());
                break;
            case R.id.payment_mastercard:
                paymentMethod = "Master Card";
                paymentMethodImage = R.drawable.ic_mastercard;
                openNewFragment(new FillCardDataFragment());
                break;
            case R.id.payment_visa:
                paymentMethod = "visa";
                paymentMethodImage = R.drawable.ic_visa;
                openNewFragment(new FillCardDataFragment());
                break;
        }
    }

    private void openNewFragment(Fragment fragment) {
        getActivity().getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(R.anim.enter, R.anim.right_to_left, R.anim.enter, R.anim.right_to_left)
                .replace(R.id.payment_method_container, fragment).addToBackStack(null)
                .commit();
    }
}
