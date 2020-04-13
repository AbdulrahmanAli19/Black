package com.example.black.Activities;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.black.Adapters.ViewPagerAdapter;
import com.example.black.Fragments.BookmarkFragment;
import com.example.black.Fragments.SignInFragment;
import com.example.black.Fragments.MenuFragment;
import com.example.black.Fragments.SearchFragment;
import com.example.black.Fragments.ShoppingBagFragment;
import com.example.black.Fragments.UserFragment;
import com.example.black.R;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "MainActivity";
    @BindView(R.id.iv_search)
    ImageView ivSearch;
    @BindView(R.id.iv_bookmark)
    ImageView ivBookmark;
    @BindView(R.id.iv_user_info)
    ImageView ivUser;
    @BindView(R.id.iv_shopping)
    ImageView ivShopping;
    @BindView(R.id.tv_menu)
    TextView tvMenu;
    @BindView(R.id.main_tabs)
    TabLayout tabLayout;
    @BindView(R.id.viewpager)
    ViewPager viewPager;
    public static TextView tvBagItems ;
    private ViewPagerAdapter viewPagerAdapter;

    public static String Email;
    public static String Title;
    public static int position;
    public static int bagItemsNum = 0;

    private  SharedPreferences mPreferences;
    private  SharedPreferences.Editor mEditor;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mPreferences = getSharedPreferences("UserData", Context.MODE_PRIVATE);
        mEditor = mPreferences.edit();

        mAuth = FirebaseAuth.getInstance();

        tvBagItems = findViewById(R.id.tv_shopping_num);

        ButterKnife.bind(this);
        ivSearch.setOnClickListener(this);
        ivBookmark.setOnClickListener(this);
        ivUser.setOnClickListener(this);
        ivShopping.setOnClickListener(this);
        tvMenu.setOnClickListener(this);
        tvBagItems.setText(String.valueOf(bagItemsNum));
        //viewPager = findViewById(R.id.viewpager);
        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(viewPagerAdapter);

        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setText("Men");
        tabLayout.getTabAt(1).setText("Women");
        tabLayout.getTabAt(2).setText("Kids");
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }

    @Override
    protected void onResume() {
        super.onResume();
        tvBagItems.setText(String.valueOf(bagItemsNum));

    }

    private void updateUI(FirebaseUser user) {
        //hideProgressDialog();
        if (user != null) {
          //  Toast.makeText(this, "we're in"+user.getEmail(), Toast.LENGTH_SHORT).show();
            Email = "1" ;
        } else {
           // Toast.makeText(this, "we're not in", Toast.LENGTH_SHORT).show();
            Email = null ;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_search:
                openNewFragment(new SearchFragment());

                break;
            case R.id.iv_bookmark:
                updateUI(mAuth.getCurrentUser());
                if (Email == null ) {
                    openNewFragment(new SignInFragment());
                    Toast.makeText(this, "You need to login first", Toast.LENGTH_SHORT).show();
                } else
                    openNewFragment(new BookmarkFragment());

                break;
            case R.id.tv_menu:

                openNewFragment(new MenuFragment());

                break;
            case R.id.iv_shopping:

                openNewFragment(new ShoppingBagFragment());

                break;
            case R.id.iv_user_info:

                openNewFragment(new UserFragment());

                break;
        }
    }


    private void openNewFragment(Fragment fragment) {
        this.getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(R.anim.slide_out_up, R.anim.slide_in_up, R.anim.slide_out_up, R.anim.slide_in_up)
                .replace(R.id.fragment_container, fragment).addToBackStack(null)
                .commit();
    }

}
