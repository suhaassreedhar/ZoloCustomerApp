package com.suhaas.zolocustomerapp.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.suhaas.zolocustomerapp.R;
import com.suhaas.zolocustomerapp.utils.Util;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.realm.Realm;

/**
 * A placeholder fragment containing a simple view.
 */
public class LoginActivityFragment extends Fragment implements View.OnClickListener{

    @BindView(R.id.btn_login)
    Button btn_logIn;
    @BindView(R.id.btn_create_account)
    Button btn_createAccount;
    @BindView(R.id.tv_forgot_pw)
    TextView tv_forgotPassword;
    private Unbinder unbinder;
    private static FragmentManager fragmentManager;

    private Realm realm;

    public LoginActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        unbinder = ButterKnife.bind(this, view);
        fragmentManager = getActivity().getSupportFragmentManager();
        setListeners();
        realm.init(getContext());
        realm = Realm.getDefaultInstance();
        return view;
    }

    private void setListeners() {
        btn_logIn.setOnClickListener(this);
        tv_forgotPassword.setOnClickListener(this);
        btn_createAccount.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                checkValidation();
                break;

            case R.id.tv_forgot_pw:

                fragmentManager
                        .beginTransaction()
                        .setCustomAnimations(R.anim.right_enter, R.anim.left_out)
                        .replace(R.id.frameContainer,
                                new ForgotPasswordFragment(),
                                Util.ForgotPassword_Fragment).commit();
                break;
            case R.id.btn_create_account:

                fragmentManager
                        .beginTransaction()
                        .setCustomAnimations(R.anim.right_enter, R.anim.left_out)
                        .replace(R.id.frameContainer, new RegisterFragment(),
                                Util.SignUp_Fragment).commit();
                break;
        }
    }

    private void checkValidation() {

    }
}
