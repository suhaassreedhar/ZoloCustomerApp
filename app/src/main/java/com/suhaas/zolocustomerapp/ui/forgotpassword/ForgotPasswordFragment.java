package com.suhaas.zolocustomerapp.ui.forgotpassword;


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
import com.creativityapps.gmailbackgroundlibrary.BackgroundMail;
import com.suhaas.zolocustomerapp.R;
import com.suhaas.zolocustomerapp.di.component.ActivityComponent;
import com.suhaas.zolocustomerapp.ui.LoginActivity;
import com.suhaas.zolocustomerapp.ui.login.LoginFragmentPresenter;

import org.apache.commons.lang3.RandomStringUtils;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class ForgotPasswordFragment extends Fragment implements View.OnClickListener, ForgotPasswordMvpView{

    @Inject
    ForgotPasswordPresenter mPresenter;
    ActivityComponent mActivityComponent;

    @BindView(R.id.et_emailId)
    EditText et_emailId;
    @BindView(R.id.btn_reset_password)
    Button btn_resetPassword;
    @BindView(R.id.tv_logIn)
    TextView tv_logIn;
    private Unbinder unbinder;
    private static FragmentManager fragmentManager;

    public ForgotPasswordFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_forgot_password, container, false);
        unbinder = ButterKnife.bind(this, view);
        mActivityComponent.inject(this);
        fragmentManager = getActivity().getSupportFragmentManager();
        setListeners();
        return view;
    }

    public void setListeners() {
        btn_resetPassword.setOnClickListener(this);
        tv_logIn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_reset_password:
                sendMail();
                break;

            case R.id.tv_logIn:
                new LoginActivity().replaceLoginFragment();
                break;
        }
    }

    public void sendMail() {

        String getEmail = et_emailId.getText().toString();

        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        String pwd = RandomStringUtils.random(7, characters );
        System.out.println( pwd );

        BackgroundMail.newBuilder(getContext())
                .withUsername("username@gmail.com")
                .withPassword("password12345")
                .withMailto(getEmail)
                .withType(BackgroundMail.TYPE_PLAIN)
                .withSubject("Your New Password")
                .withBody(pwd)
                .withOnSuccessCallback(new BackgroundMail.OnSuccessCallback() {
                    @Override
                    public void onSuccess() {
                        Snackbar.with(getActivity(), null)
                                .type(Type.SUCCESS)
                                .message("password sent to your mailId")
                                .duration(Duration.SHORT)
                                .show();                    }
                })
                .withOnFailCallback(new BackgroundMail.OnFailCallback() {
                    @Override
                    public void onFail() {
                        Snackbar.with(getActivity(), null)
                                .type(Type.ERROR)
                                .message("Something went wrong")
                                .duration(Duration.SHORT)
                                .show();
                    }
                })
                .send();
    }
}
