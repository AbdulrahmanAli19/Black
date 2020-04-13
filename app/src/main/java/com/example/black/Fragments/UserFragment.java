package com.example.black.Fragments;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.black.Activities.MainActivity;
import com.example.black.R;
import com.google.firebase.auth.FirebaseAuth;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UserFragment extends Fragment implements View.OnClickListener {
    @BindView(R.id.tv_user_log_in)
    TextView tvLogin;
    @BindView(R.id.tv_user_help)
    TextView tvHelp;
    @BindView(R.id.tv_user_contact)
    TextView tvContact;
    @BindView(R.id.tv_user_settings)
    TextView tvSettings;
    @BindView(R.id.tv_user_rate_app)
    TextView tvRateApp;
    @BindView(R.id.tv_user_share_friend)
    TextView tvShare;
    @BindView(R.id.tv_user_email)
    TextView tvEmail;
    @BindView(R.id.iv_user_close)
    ImageView ivClose;
    @BindView(R.id.if_user_in)
    RelativeLayout userIn;
    @BindView(R.id.if_user_null)
    RelativeLayout userOut;
    @BindView(R.id.tv_sign_out)
    TextView signOut;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user, container, false);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getActivity().getWindow();
            View barIcons = getActivity().getWindow().getDecorView();
            barIcons.setSystemUiVisibility(barIcons.getSystemUiVisibility() & ~View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.BLACK);
        }
        ButterKnife.bind(this, view);
        ivClose.setOnClickListener(this);
        tvLogin.setOnClickListener(this);
        tvHelp.setOnClickListener(this);
        tvContact.setOnClickListener(this);
        tvRateApp.setOnClickListener(this);
        tvSettings.setOnClickListener(this);
        tvShare.setOnClickListener(this);
        signOut.setOnClickListener(this);
        userSituation();
        return view;
    }

    private void userSituation() {
        if (MainActivity.Email == null) {
            userOut.setVisibility(View.VISIBLE);
            userIn.setVisibility(View.GONE);

        } else {
            userOut.setVisibility(View.GONE);
            userIn.setVisibility(View.VISIBLE);
            if (FirebaseAuth.getInstance().getCurrentUser().getEmail() != null ){
            tvEmail.setText(FirebaseAuth.getInstance().getCurrentUser().getEmail());
            }
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_user_close:
                getActivity().getSupportFragmentManager().popBackStack();
                break;
            case R.id.tv_user_log_in:
                openNewFragment(new SignInFragment());
                break;
            case R.id.tv_user_contact:
                openNewFragment(new ContactFragment());
                break;
            case R.id.tv_sign_out:
                FirebaseAuth.getInstance().signOut();
                MainActivity.Email = null;
                Toast.makeText(getContext(), "Signing out", Toast.LENGTH_SHORT).show();
                getActivity().getSupportFragmentManager().popBackStack();
                break;
        }
    }

    private void openNewFragment(Fragment fragment) {
        getActivity().getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(R.anim.slide_out_up, R.anim.slide_in_up, R.anim.slide_out_up, R.anim.slide_in_up)
                .replace(R.id.login_frame, fragment).addToBackStack(null)
                .commit();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getActivity().getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.WHITE);
            View barIcons = getActivity().getWindow().getDecorView();
            barIcons.setSystemUiVisibility(barIcons.getSystemUiVisibility() | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
    }

}
