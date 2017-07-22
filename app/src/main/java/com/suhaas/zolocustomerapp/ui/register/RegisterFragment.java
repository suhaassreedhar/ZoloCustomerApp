package com.suhaas.zolocustomerapp.ui.register;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.chootdev.csnackbar.Duration;
import com.chootdev.csnackbar.Snackbar;
import com.chootdev.csnackbar.Type;
import com.suhaas.zolocustomerapp.R;
import com.suhaas.zolocustomerapp.data.model.Customer;
import com.suhaas.zolocustomerapp.di.component.ActivityComponent;
import com.suhaas.zolocustomerapp.ui.LoginActivity;
import com.suhaas.zolocustomerapp.ui.login.LoginFragmentPresenter;

import java.util.UUID;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.realm.Realm;

/**
 * A simple {@link Fragment} subclass.
 */
public class RegisterFragment extends Fragment implements View.OnClickListener, RegisterMvpView{

    @Inject
    RegisterPresenter mPresenter;
    ActivityComponent mActivityComponent;

    @BindView(R.id.et_phoneNum)
    EditText et_phoneNum;
    @BindView(R.id.et_emailId)
    EditText et_emailId;
    @BindView(R.id.et_name)
    EditText et_name;
    @BindView(R.id.et_password)
    EditText et_password;
    @BindView(R.id.btn_register)
    Button btn_register;
    @BindView(R.id.tv_logIn)
    TextView tv_logIn;
    private Unbinder unbinder;
    private static FragmentManager fragmentManager;
    private Realm realm;

    public RegisterFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_register, container, false);
        unbinder = ButterKnife.bind(this, view);
        fragmentManager = getActivity().getSupportFragmentManager();
        mActivityComponent.inject(this);
        setListeners();
        realm.init(getContext());
        realm = Realm.getDefaultInstance();

        et_phoneNum.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Long getPhoneNumber = Long.valueOf(et_phoneNum.getText().toString());
                try {
                    Customer owner = realm.where(Customer.class).equalTo("phone_number", getPhoneNumber).findFirst();
                    if (owner != null){
                        et_phoneNum.setError("Phone Number already exists");
                    }
                } finally {
//                    realm.close();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        return view;
    }

    public void setListeners() {
        btn_register.setOnClickListener(this);
        tv_logIn.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_register:
                checkValidation();
                break;

            case R.id.tv_logIn:
                new LoginActivity().replaceLoginFragment();
                break;
        }
    }

    public void checkValidation() {

        String getPhoneNumber = et_phoneNum.getText().toString();
        String getEmailId = et_emailId.getText().toString();
        String getName = et_name.getText().toString();
        String getPassword = et_password.getText().toString();

        if (getName.equals("") || getName.length() == 0
                || getEmailId.equals("") || getEmailId.length() == 0
                || getPhoneNumber.equals("") || getPhoneNumber.length() == 0
                || getPassword.equals("") || getPassword.length() == 0)

            Snackbar.with(getActivity(), null)
                    .type(Type.WARNING)
                    .message("All fields are required")
                    .duration(Duration.SHORT)
                    .show();
        else {
            realm.beginTransaction();
            Customer customer = realm.createObject(Customer.class);
            customer.setId(UUID.randomUUID().toString());
            customer.setPhone_number(Long.valueOf(getPhoneNumber));
            customer.setEmail_id(getEmailId);
            customer.setPassword(getPassword);
            realm.commitTransaction();

            Snackbar.with(getActivity(), null)
                    .type(Type.SUCCESS)
                    .message("User Created")
                    .duration(Duration.SHORT)
                    .show();
        }
    }
}
