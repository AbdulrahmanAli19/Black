package com.example.black.Fragments;

import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.black.Activities.MainActivity;
import com.example.black.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SignInFragment extends Fragment implements View.OnClickListener {
    private static final String TAG = "LogInFragment";

    @BindView(R.id.et_login_email)
    TextInputLayout etEmail;
    @BindView(R.id.et_login_password)
    TextInputLayout etPassword;
    @BindView(R.id.btn_create_account)
    TextView btnCreateAccount;
    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.tv_forget_password)
    TextView tvForgetPassword;
    @BindView(R.id.iv_close_login_btn)
    ImageView ivClose;
    @BindView(R.id.progress_bar_login)
    RelativeLayout ProgressBar;

    private MainActivity activity;

    private FirebaseAuth mAuth;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sign_in, container, false);
        fromBlackNavToWhiteNav();

        mAuth = FirebaseAuth.getInstance();

        ButterKnife.bind(this, view);
        btnCreateAccount.setOnClickListener(this);
        btnLogin.setOnClickListener(this);
        ivClose.setOnClickListener(this);
        tvForgetPassword.setOnClickListener(this);

        return view;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_close_login_btn:
                getActivity().getSupportFragmentManager().popBackStack();
                break;
            case R.id.btn_create_account:
                openNewFragment(new SignUpFragment());
                break;
            case R.id.btn_login:
                if (!validationEmail() | !validationPassword()) {
                    break;
                }
                String email = Objects.requireNonNull(etEmail.getEditText()).getText().toString().trim();
                String password = Objects.requireNonNull(etPassword.getEditText()).getText().toString().trim();
                SignIn(email, password);
                break;
            case R.id.tv_forget_password:
                break;
        }
    }


    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
    }

    public void SignIn(String email, String password) {
        ProgressBar.setVisibility(View.VISIBLE);
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(Objects.requireNonNull(getActivity()), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            ProgressBar.setVisibility(View.GONE);

                            Toast.makeText(activity, "Successfully signed in", Toast.LENGTH_SHORT).show();
                            MainActivity.Email = "1";
                            getActivity().getSupportFragmentManager().popBackStack();
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(activity, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            ProgressBar.setVisibility(View.GONE);
                            MainActivity.Email = null;

                        }
                    }
                });
    }

    private void openNewFragment(Fragment fragment) {
        getActivity().getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(R.anim.enter, R.anim.right_to_left, R.anim.enter, R.anim.right_to_left)
                .replace(R.id.sign_up_frame, fragment).addToBackStack(null)
                .commit();
    }


    private boolean validationPassword() {
        String Password = etPassword.getEditText().getText().toString().trim();
        if (Password.isEmpty()) {
            etPassword.setError("Filed can't be empty");
            return false;
        } else {
            etPassword.setError(null);
            return true;
        }
    }

    private boolean validationEmail() {
        String Email = etEmail.getEditText().getText().toString().trim();
        if (Email.isEmpty()) {
            etEmail.setError("Filed can't be empty");
            return false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(Email).matches()) {
            etEmail.setError("Please enter a valid email address");
            return false;
        } else {
            etEmail.setError(null);
            return true;
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.activity = (MainActivity) activity;
    }

    // @onDestroy this method calls only when the user destroy the fragment
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

    private void fromBlackNavToWhiteNav() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getActivity().getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.WHITE);
            View barIcons = getActivity().getWindow().getDecorView();
            barIcons.setSystemUiVisibility(barIcons.getSystemUiVisibility() | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
    }

}
