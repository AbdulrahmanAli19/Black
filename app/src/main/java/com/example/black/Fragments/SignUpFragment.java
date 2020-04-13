package com.example.black.Fragments;

import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.black.Classes.User;
import com.example.black.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SignUpFragment extends Fragment implements View.OnClickListener {
    private static final String TAG = "SignUpFragment";
    //binding the xml with java using ButterKnife
    @BindView(R.id.et_sign_up_address)
    TextInputLayout etAddress;
    @BindView(R.id.et_sign_up_email)
    TextInputLayout etEmail;
    @BindView(R.id.et_sign_up_info)
    TextInputLayout etInfo;
    @BindView(R.id.et_sign_up_name)
    TextInputLayout etName;
    @BindView(R.id.et_sign_up_surname_name)
    TextInputLayout etSurname;
    @BindView(R.id.et_sign_up_password)
    TextInputLayout etPassword;
    @BindView(R.id.et_sign_up_repeat_password)
    TextInputLayout etRepeatPassword;
    @BindView(R.id.et_sign_up_phone_number)
    TextInputLayout etPhoneNumber;
    @BindView(R.id.et_sign_up_phone_key)
    EditText etPhoneKey;
    @BindView(R.id.iv_sign_up_close)
    ImageView ivClose;
    @BindView(R.id.cb_sign_up_privacy)
    CheckBox cbPrivacy;
    @BindView(R.id.cb_sign_up_terms)
    CheckBox cbTerms;
    @BindView(R.id.switch_sign_up_information)
    Switch switchIformation;
    @BindView(R.id.btn_sing_up_save)
    Button btnSave;
    @BindView(R.id.progress_bar_signup)
    RelativeLayout progressPar;
    //
    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^" +
                    //"(?=.*[0-9])" +         //at least 1 digit
                    //"(?=.*[a-z])" +         //at least 1 lower case letter
                    //"(?=.*[A-Z])" +         //at least 1 upper case letter
                    "(?=.*[a-zA-Z])" +      //any letter
                    //"(?=.*[@#$%^&+=])" +    //at least 1 special character
                    "(?=\\S+$)" +           //no white spaces
                    ".{4,}" +               //at least 4 characters
                    "$");
    //Firebase
    private FirebaseAuth mAuth;

    //onCreateView method
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sign_up, container, false);

        ButterKnife.bind(this, view);

        ivClose.setOnClickListener(this);
        btnSave.setOnClickListener(this);

        mAuth = FirebaseAuth.getInstance();

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }

    private void updateUI(FirebaseUser currentUser) {

    }

    public void SignUp(String email, String password) {
        progressPar.setVisibility(View.VISIBLE);
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(Objects.requireNonNull(getActivity()), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            final String address = etAddress.getEditText().getText().toString();
                            final String moreIfno = etInfo.getEditText().getText().toString().trim();
                            final String phoneNumber = etPhoneKey.getText().toString().trim() +
                                    etPhoneNumber.getEditText().getText().toString().trim();
                            final String name = etName.getEditText().getText().toString().trim();
                            final String surName = etSurname.getEditText().getText().toString().trim();
                            final String password = etPassword.getEditText().getText().toString().trim();

                            User userData = new User(name, surName, address, moreIfno, phoneNumber, password);

                            // Write a userData to the database
                            FirebaseDatabase.getInstance().getReference("Users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(userData).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(getActivity(), "Successfully signed up", Toast.LENGTH_SHORT).show();
                                        getActivity().getSupportFragmentManager().popBackStack();                                    }
                                }
                            });

                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                            progressPar.setVisibility(View.GONE);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(getActivity(), "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            updateUI(null);
                            progressPar.setVisibility(View.GONE);

                        }
                    }
                });
    }


    //her we define what happened on the click event
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_sign_up_close:
                getActivity().getSupportFragmentManager().popBackStack();
                break;
            case R.id.btn_sing_up_save:
                if (!validationEmail() | !validationPassword() | !validationName() /*| !validationAddressAndIfno()*/ | !validationPhone()) {
                    break;
                }
                String email = Objects.requireNonNull(etEmail.getEditText()).getText().toString().trim();
                String password = Objects.requireNonNull(etPassword.getEditText()).getText().toString().trim();
                SignUp(email, password);

                break;

        }
    }


    private boolean validationEmail() {
        String email = etEmail.getEditText().getText().toString().trim();
        if (email.isEmpty()) {
            etEmail.setError("Filed can't be empty");
            return false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            etEmail.setError("Please enter a valid email address");
            return false;
        } else {
            etEmail.setError(null);
            return true;
        }
    }

    private boolean validationPhone() {
        String phone = etPhoneNumber.getEditText().getText().toString().trim();
        if (phone.isEmpty()) {
            etPhoneNumber.setError("Filed can't be empty");
            return false;
        } else if (phone.length() != 10) {
            etPhoneNumber.setError("Wrong phone number");
            return false;
        } else {
            etPhoneNumber.setError(null);
            return true;
        }
    }

/*
    private boolean validationAddressAndIfno() {
        String address = etAddress.getEditText().getText().toString().trim();
        String info = etInfo.getEditText().getText().toString().trim();

        if (address.isEmpty()) {
            etAddress.setError("Filed can't be empty");
            if (info.isEmpty()) {
                etInfo.setError("Filed can't be empty");
            }
            return false;
        }
        if (info.isEmpty()) {
            etInfo.setError("Filed can't be empty");
            if (address.isEmpty()) {
                etAddress.setError("Filed can't be empty");
            }
            return false;
        } else {
            etPhoneNumber.setError(null);
            return true;
        }
    }
*/

    private boolean validationName() {
        String name = etName.getEditText().getText().toString().trim();
        String surName = etSurname.getEditText().getText().toString().trim();

        if (name.isEmpty()) {
            etName.setError("Filed can't be empty");
            if (surName.isEmpty()) {
                etSurname.setError("Filed can't be empty");
            }
            return false;
        } else if (surName.isEmpty()) {
            etSurname.setError("Filed can't be empty");
            if (name.isEmpty()) {
                etName.setError("Filed can't be empty");
            }
            return false;
        } else {
            etName.setError(null);
            etSurname.setError(null);
            return true;
        }
    }

    public boolean validationPassword() {
        String password = etPassword.getEditText().getText().toString().trim();
        String passwordRe = etRepeatPassword.getEditText().getText().toString().trim();

        if (password.isEmpty()) {
            etPassword.setError("Filed can't be empty");
            if (passwordRe.isEmpty()) {
                etRepeatPassword.setError("Filed can't be empty");
            }
            return false;
        } else if (passwordRe.isEmpty()) {
            etRepeatPassword.setError("Filed can't be empty");
            if (password.isEmpty()) {
                etPassword.setError("Filed can't be empty");
            }
            return false;
        } else if (password.length() < 7) {
            etPassword.setError("Password should be have more than 7 chars");
            return false;
        } else if (!passwordRe.equals(password)) {
            etPassword.setError(null);
            etRepeatPassword.setError("passwords do not match");
            return false;
        } else if (!PASSWORD_PATTERN.matcher(password).matches()) {
            etPassword.setError("Password too weak");
            return false;
        } else {
            //(password == passwordRe & password != null & passwordRe != null)
            etPassword.setError(null);
            etRepeatPassword.setError(null);
            return true;
        }
    }


}