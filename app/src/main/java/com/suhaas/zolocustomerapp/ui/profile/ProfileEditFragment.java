package com.suhaas.zolocustomerapp.ui.profile;


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
import com.suhaas.zolocustomerapp.ui.LoginActivity;

import java.util.UUID;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.realm.Realm;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileEditFragment extends Fragment implements View.OnClickListener{

    @BindView(R.id.et_phoneNum)
    EditText et_phoneNum;
    @BindView(R.id.et_emailId)
    EditText et_emailId;
    @BindView(R.id.et_name)
    EditText et_name;
    @BindView(R.id.btn_update)
    Button btn_update;
    private Unbinder unbinder;
    private static FragmentManager fragmentManager;
    private Realm realm;
    String id;

    public ProfileEditFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile_edit, container, false);
        unbinder = ButterKnife.bind(this, view);
        fragmentManager = getActivity().getSupportFragmentManager();
        setListeners();
        realm.init(getContext());
        realm = Realm.getDefaultInstance();

        Bundle getBundle = ((ProfileActivity)view.getContext()).getIntent().getExtras();

        if (getBundle != null) {
            id = getBundle.getString("id");
            String name = getBundle.getString("name");
            String emailId = getBundle.getString("email_id");
            Long mobileNum = getBundle.getLong("phone_number");

            et_name.setText(name);
            et_emailId.setText(emailId);
            et_phoneNum.setText(String.valueOf(mobileNum));
        }
        return view;
    }

    private void setListeners() {
        btn_update.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_update:
                checkValidation();
                break;
        }
    }

    private void checkValidation() {
        String getPhoneNumber = et_phoneNum.getText().toString();
        String getEmailId = et_emailId.getText().toString();
        String getName = et_name.getText().toString();

        if (getName.equals("") || getName.length() == 0
                || getEmailId.equals("") || getEmailId.length() == 0
                || getPhoneNumber.equals("") || getPhoneNumber.length() == 0)

            Snackbar.with(getActivity(), null)
                    .type(Type.WARNING)
                    .message("All fields are required")
                    .duration(Duration.SHORT)
                    .show();
        else {
            realm.beginTransaction();
            Customer editCustomerDetails = realm.where(Customer.class).equalTo("id", id).findFirst();
            editCustomerDetails.setPhone_number(Long.valueOf(getPhoneNumber));
            editCustomerDetails.setEmail_id(getEmailId);
            editCustomerDetails.setName(getName);
            realm.commitTransaction();

            Snackbar.with(getActivity(), null)
                    .type(Type.SUCCESS)
                    .message("User Updated")
                    .duration(Duration.SHORT)
                    .show();
        }
    }
}
