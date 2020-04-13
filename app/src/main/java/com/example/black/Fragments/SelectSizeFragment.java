package com.example.black.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.black.Activities.MainActivity;
import com.example.black.Classes.BagItems;
import com.example.black.R;

public class SelectSizeFragment extends Fragment {
    private ListView listView ;
    String Sizes [] = { "S", "M", "L", "XL", "XXL"};
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_select_size, container,false);
        listView = view.findViewById(R.id.size_list);
        ArrayAdapter<String> adapter
                = new ArrayAdapter(getContext(),android.R.layout.simple_list_item_1,Sizes);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                BagItems bi = ProductsFragment.items;
                ShoppingBagFragment.bagItems.add(
                        new BagItems(bi.getmImage(),
                                    bi.getmProductName(),
                                    Sizes[position],
                                    bi.getmProductPrice(),
                                    bi.getmProductSalePrice()));
                BuyFragment.Images.add(bi.getmImage());
                MainActivity.bagItemsNum = ShoppingBagFragment.bagItems.size();
                Toast.makeText(getContext(), "you've successfully added a new piece", Toast.LENGTH_SHORT).show();
                MainActivity.tvBagItems.setText(String.valueOf(MainActivity.bagItemsNum));
                ProductDetailsFragment.tvBagNum.setText(String.valueOf(MainActivity.bagItemsNum));
                getActivity().getSupportFragmentManager().popBackStack();
            }
        });
        return view ;
    }
}
