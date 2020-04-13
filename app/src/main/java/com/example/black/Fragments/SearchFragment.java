package com.example.black.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.black.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class SearchFragment extends Fragment implements View.OnClickListener {
    @BindView(R.id.iv_search_close)
    ImageView ivClose ;
    @BindView(R.id.iv_search_stores)
    ImageView ivStores ;
    @BindView(R.id.iv_search_barcode)
    ImageView ivBarcode ;
    @BindView(R.id.et_search)
    EditText etSearch ;
    @BindView(R.id.tv_search_dress)
    TextView tvDress;
    @BindView(R.id.tv_search_coat)
    TextView tvCoat;
    @BindView(R.id.tv_search_jacket)
    TextView tvJacket;
    @BindView(R.id.tv_search_sale)
    TextView tvSale;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_srearch, container, false);
        ButterKnife.bind(this,view);
        ivClose.setOnClickListener(this);
        tvCoat.setOnClickListener(this);
        tvDress.setOnClickListener(this);
        tvJacket.setOnClickListener(this);
        tvSale.setOnClickListener(this);
        ivBarcode.setOnClickListener(this);
        ivStores.setOnClickListener(this);
        return view;
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.iv_search_close:
                getActivity().getSupportFragmentManager().popBackStack();
                break;
        }
    }

    public void Search (){
        String text = etSearch.getText().toString();
    }
}
