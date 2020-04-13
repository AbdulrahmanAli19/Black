package com.example.black.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.black.Activities.MainActivity;
import com.example.black.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MenFragment extends Fragment implements View.OnClickListener {
    @BindView(R.id.men_view_btn)
    RelativeLayout btnView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_men, container, false);
        ButterKnife.bind(this,view);
        btnView.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.men_view_btn:
                MainActivity.Title = "Men";
                openNewFragment(new ProductsFragment());
                break;
        }
    }
    private void openNewFragment(Fragment fragment) {
        getActivity().getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(R.anim.slide_out_up, R.anim.slide_in_up, R.anim.slide_out_up, R.anim.slide_in_up)
                .replace(R.id.fragment_container, fragment).addToBackStack(null)
                .commit();
    }
}
