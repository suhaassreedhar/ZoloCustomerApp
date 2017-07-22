package com.suhaas.zolocustomerapp.ui.login;

import android.content.Intent;
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
import com.suhaas.zolocustomerapp.di.component.ActivityComponent;
import com.suhaas.zolocustomerapp.ui.forgotpassword.ForgotPasswordFragment;
import com.suhaas.zolocustomerapp.ui.register.RegisterFragment;
import com.suhaas.zolocustomerapp.ui.profile.ProfileActivity;
import com.suhaas.zolocustomerapp.utils.Util;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.realm.Realm;

/**
 * A placeholder fragment containing a simple view.
 */
public class LoginActivityFragment extends Fragment implements View.OnClickListener, LoginFragmentMvpView{

    @Inject
    LoginFragmentPresenter mPresenter;
    ActivityComponent mActivityComponent;

    @BindView(R.id.et_phoneNum)
    EditText et_phoneNum;
    @BindView(R.id.et_password)
    EditText et_password;
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
        mActivityComponent.inject(this);
        fragmentManager = getActivity().getSupportFragmentManager();
        setListeners();
        realm.init(getContext());
        realm = Realm.getDefaultInstance();
        return view;
    }

    public void setListeners() {
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

    public void checkValidation() {
        Long getPhoneNumber = Long.valueOf(et_phoneNum.getText().toString());
        String getPassword = et_password.getText().toString();

        if (getPhoneNumber.equals("") || getPhoneNumber.toString().length() == 0
                || getPassword.equals("") || getPassword.length() == 0)

            Snackbar.with(getActivity(), null)
                    .type(Type.WARNING)
                    .message("All fields are required")
                    .duration(Duration.SHORT)
                    .show();
        else {
            Customer owner = realm.where(Customer.class).equalTo("phone_number", getPhoneNumber).findFirst();
            if (owner != null){
                Intent intent = new Intent(getActivity(), ProfileActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("id", owner.getId());
                bundle.putLong("phone_number", owner.getPhone_number());
                bundle.putString("email_id", owner.getEmail_id());
                bundle.putString("name", owner.getName());
                intent.putExtras(bundle);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);

            }
        }
    }
}
