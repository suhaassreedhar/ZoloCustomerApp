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

import com.suhaas.zolocustomerapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class ForgotPasswordFragment extends Fragment implements View.OnClickListener{

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
        fragmentManager = getActivity().getSupportFragmentManager();
        setListeners();
        return view;
    }

    private void setListeners() {
        btn_resetPassword.setOnClickListener(this);
        tv_logIn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_reset_password:
                break;

            case R.id.tv_logIn:
                new LoginActivity().replaceLoginFragment();
                break;
        }
    }
}
