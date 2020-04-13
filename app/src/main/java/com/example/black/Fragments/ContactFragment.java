package com.example.black.Fragments;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.black.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ContactFragment extends Fragment implements View.OnClickListener {
    @BindView(R.id.iv_contact_close)
    ImageView ivCLose;
    @BindView(R.id.ll_contact_chat)
    LinearLayout llChat;
    @BindView(R.id.ll_contact_facebook)
    LinearLayout llFacebook;
    @BindView(R.id.ll_contact_twitter)
    LinearLayout llTwitter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_contact, container, false);
        ButterKnife.bind(this, view);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getActivity().getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.WHITE);
            View barIcons = getActivity().getWindow().getDecorView();
            barIcons.setSystemUiVisibility(barIcons.getSystemUiVisibility() | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
        ivCLose.setOnClickListener(this);
        llChat.setOnClickListener(this);
        llFacebook.setOnClickListener(this);
        llTwitter.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.iv_contact_close:
                getActivity().getSupportFragmentManager().popBackStack();
                break;
            case R.id.ll_contact_facebook:
                openWebpage("https://www.facebook.com/BlackCare/");
                break;
            case R.id.ll_contact_twitter:
                openWebpage("https://twitter.com/Black_care");
                break;
            case R.id.ll_contact_chat:
                Toast.makeText(getContext(), "this future is not available yet :(", Toast.LENGTH_SHORT).show();
                break;
        }
    }
    public void openWebpage ( String url){
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getActivity().getWindow();
            View barIcons = getActivity().getWindow().getDecorView();
            barIcons.setSystemUiVisibility(barIcons.getSystemUiVisibility() & ~View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.BLACK);
        }
    }
}
