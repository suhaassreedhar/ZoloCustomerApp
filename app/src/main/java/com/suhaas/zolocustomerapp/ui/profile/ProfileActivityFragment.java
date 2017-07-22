package com.suhaas.zolocustomerapp.ui.profile;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.suhaas.zolocustomerapp.R;
import com.suhaas.zolocustomerapp.data.model.Customer;
import com.suhaas.zolocustomerapp.utils.Util;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.realm.Realm;

import static android.R.attr.id;

/**
 * A placeholder fragment containing a simple view.
 */
public class ProfileActivityFragment extends Fragment implements View.OnClickListener{

    @BindView(R.id.iv_editProfile)
    ImageView iv_editProfile;
    @BindView(R.id.tv_name)
    TextView tv_name;
    @BindView(R.id.tv_emailId)
    TextView tv_emailId;
    @BindView(R.id.tv_phoneNumber)
    TextView tv_phoneNumber;
    private Unbinder unbinder;
    private static FragmentManager fragmentManager;

    private Realm realm;
    String id;

    public ProfileActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
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
//
//            tv_name.setText(name);
//            tv_emailId.setText(emailId);
//            tv_phoneNumber.setText(String.valueOf(mobileNum));
        }

        realm.beginTransaction();
        Customer customerDetails = realm.where(Customer.class).equalTo("id", id).findFirst();
        tv_name.setText(customerDetails.getName());
        tv_emailId.setText(customerDetails.getEmail_id());
        tv_phoneNumber.setText(String.valueOf(customerDetails.getPhone_number()));
        realm.commitTransaction();

        return view;
    }

    private void setListeners() {
        iv_editProfile.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_editProfile:
                fragmentManager
                        .beginTransaction()
                        .setCustomAnimations(R.anim.right_enter, R.anim.left_out)
                        .replace(R.id.frameContainer,
                                new ProfileEditFragment(),
                                Util.ProfileEdit_Fragment).commit();
                break;
        }
    }
}
