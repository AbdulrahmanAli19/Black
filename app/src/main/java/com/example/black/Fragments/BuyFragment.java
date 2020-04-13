package com.example.black.Fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.black.Activities.MainActivity;
import com.example.black.Adapters.BuyProductsAdapter;
import com.example.black.Classes.BagItems;
import com.example.black.Classes.Order;
import com.example.black.Classes.User;
import com.example.black.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BuyFragment extends Fragment implements View.OnClickListener {
    private static final String TAG = "BuyFragment";

    @BindView(R.id.grid_view)
    RecyclerView gvProducts;
    @BindView(R.id.tv_buy_payment_method)
    TextView tvPaymentMethod;
    @BindView(R.id.iv_buy_payment_method)
    ImageView ivPaymentMethod;
    @BindView(R.id.tv_buy_name)
    TextView tvBuyerName;
    @BindView(R.id.tv_buy_address)
    TextView tvBuyerAddress;
    @BindView(R.id.tv_buy_phone_number)
    TextView tvBuyerPhoneNumber;
    @BindView(R.id.tv_buy_product_num)
    TextView tvProductNum;
    @BindView(R.id.buy_progress_bar)
    ProgressBar progressBar;
    @BindView(R.id.btn_confirm)
    Button btnBuy;
    User gUser = new User();
    private DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.DEFAULT,
            new Locale("eng", "EG"));
    private String date = dateFormat.format(new Date());


    public static ArrayList<Integer> Images = new ArrayList<>();

    private FirebaseAuth mAuth;
    private FirebaseDatabase database;
    private DatabaseReference myRef;

    private String userId;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_buy, container, false);

        ButterKnife.bind(this, view);

        mAuth = FirebaseAuth.getInstance();
        userId = mAuth.getUid();

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Users").child(userId);

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                progressBar.setVisibility(View.GONE);
                getDataFromDB(dataSnapshot);

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });

        btnBuy.setOnClickListener(this);

        initRecyclerView();

        return view;
    }

    private void getDataFromDB(DataSnapshot dataSnapshot) {
        User user = new User();
        //WORKING CODE
        user.setAddress(dataSnapshot.child("address").getValue().toString());
        user.setName(dataSnapshot.child("name").getValue().toString());
        user.setSurName(dataSnapshot.child("surName").getValue().toString());
        user.setPhoneNumber(dataSnapshot.child("phoneNumber").getValue().toString());
       /* for (DataSnapshot ds : dataSnapshot.getChildren()) {
            user.setAddress(ds.child(userId).getValue(User.class).getAddress());
            user.setName(ds.child(userId).getValue(User.class).getName());
            user.setPhoneNumber(ds.child(userId).getValue(User.class).getPhoneNumber());
        }*/
        tvBuyerName.setText(user.getName() + " " + user.getSurName());
        tvBuyerPhoneNumber.setText(user.getPhoneNumber());
        tvBuyerAddress.setText(user.getAddress());
        gUser = user ;
    }

    private void submitOrder() {
        ArrayList<BagItems> b = new ArrayList<>();
        for (int i = 0; i < ShoppingBagFragment.bagItems.size(); i++) {
            b.add(ShoppingBagFragment.bagItems.get(i));
        }

        Order order = new Order(b, gUser);
        FirebaseDatabase.getInstance().getReference("Orders")
                .child("Order " + date + " " + FirebaseAuth.getInstance().getCurrentUser().getUid())
                .setValue(order).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(getContext(), "successful operation", Toast.LENGTH_SHORT).show();
                    ShoppingBagFragment.bagItems.clear();
                    Images.clear();
                    MainActivity.bagItemsNum = 0 ;
                    MainActivity.tvBagItems.setText("0");
                    ProductDetailsFragment.tvBagNum.setText("0");
                    ShoppingBagFragment.tvPrice.setText("0 EGP");
                    PaymentMethodFragment.tvProductsNum.setText("0 products");
                    PaymentMethodFragment.tvProductsPrice.setText("0 EGP");
                    PaymentMethodFragment.tvTotalPrice.setText("0 EGP");
                    getActivity().getSupportFragmentManager().popBackStack();
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_confirm:
                submitOrder();
                break;
        }
    }

    private void initRecyclerView() {
        Log.d(TAG, "initRecyclerView: called.");
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        gvProducts.setLayoutManager(layoutManager);
        BuyProductsAdapter adapter = new BuyProductsAdapter(getContext(), Images);
        gvProducts.setAdapter(adapter);

        tvPaymentMethod.setText(PaymentMethodFragment.paymentMethod);
        tvProductNum.setText(Images.size() + " Products");

        ivPaymentMethod.setImageResource(PaymentMethodFragment.paymentMethodImage);
    }
}
