package com.example.black.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;

import com.example.black.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BookmarkFragment extends Fragment implements View.OnClickListener {
    private static final String TAG = "BookmarkFragment";

    @BindView(R.id.iv_bookmark_close)
    ImageView ivClose;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bookmark, container, false);
        ButterKnife.bind(this, view);

        ivClose.setOnClickListener(this);
        return view;
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.iv_bookmark_close:
                getActivity().getSupportFragmentManager().popBackStack();
                break;
        }
    }
}
