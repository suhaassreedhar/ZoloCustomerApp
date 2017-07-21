package com.suhaas.zolocustomerapp.ui;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
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

import java.util.UUID;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.realm.Realm;

/**
 * A simple {@link Fragment} subclass.
 */
public class RegisterFragment extends Fragment implements View.OnClickListener {

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
        setListeners();
        realm.init(getContext());
        realm = Realm.getDefaultInstance();
        return view;
    }

    private void setListeners() {
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

    private void checkValidation() {

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
            customer.setId(Long.valueOf(UUID.randomUUID().toString()));
            customer.setPhone_number(Long.valueOf(getPhoneNumber));
            customer.setEmail_id(getEmailId);
            customer.setPassword(getPassword);
            realm.commitTransaction();
        }
    }
}
