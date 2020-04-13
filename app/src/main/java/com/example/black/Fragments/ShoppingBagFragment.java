package com.example.black.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.black.Activities.MainActivity;
import com.example.black.Adapters.ShoppingBagAdapter;
import com.example.black.Classes.BagItems;
import com.example.black.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;


public class ShoppingBagFragment extends Fragment implements View.OnClickListener {
    public static ArrayList<BagItems> bagItems = new ArrayList<>();

    public static int items;
    public static int totalPrice;

    private RecyclerView mRecyclerView;
    private ShoppingBagAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    public static TextView tvPrice ;

    @BindView(R.id.iv_shopping_close)
    ImageView ivClose ;
    @BindView(R.id.if_list_is_empty)
    RelativeLayout emptyList ;
    @BindView(R.id.if_list_is_not_empty)
    RelativeLayout fullList ;
    @BindView(R.id.btn_buy)
    Button btnBuy;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_shopping_bag, container, false);
        ButterKnife.bind(this, view);
        tvPrice = view.findViewById(R.id.tv_shopping_price);
        ivClose.setOnClickListener(this);
        btnBuy.setOnClickListener(this);

        if (bagItems.size()==0){
            emptyList.setVisibility(View.VISIBLE);
            fullList.setVisibility(View.GONE);
        }else {
            fullList.setVisibility(View.VISIBLE);
            emptyList.setVisibility(View.GONE);
            mRecyclerView = view.findViewById(R.id.bag_recyclerview);
            mRecyclerView.setHasFixedSize(true);
            mLayoutManager = new LinearLayoutManager(getContext());
            mAdapter = new ShoppingBagAdapter(bagItems);
            mRecyclerView.setLayoutManager(mLayoutManager);
            mRecyclerView.setAdapter(mAdapter);
        }
        int price = 0  ;
        for (int i = 0; i<bagItems.size(); i++){
            price += bagItems.get(i).getmProductSalePrice();
        }
        totalPrice = price ;
        items = bagItems.size();
        tvPrice.setText("Total: "+price+" EGP");
        return view;
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.iv_shopping_close:

                getActivity().getSupportFragmentManager().popBackStack();

                break;
            case R.id.btn_buy:
                String Email = MainActivity.Email;
                if (Email== null){
                    openNewFragment(new SignInFragment());
                    Toast.makeText(getContext(), "You need to login first", Toast.LENGTH_SHORT).show();
                }else
                    openNewFragment(new PaymentMethodFragment());

                break;
        }
    }

    private void openNewFragment(Fragment fragment) {
        getActivity().getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(R.anim.slide_out_up, R.anim.slide_in_up, R.anim.slide_out_up, R.anim.slide_in_up)
                .replace(R.id.payment_method, fragment).addToBackStack(null)
                .commit();
    }
}
