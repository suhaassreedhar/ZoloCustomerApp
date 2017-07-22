package com.suhaas.zolocustomerapp.ui.profile;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.suhaas.zolocustomerapp.R;
import com.suhaas.zolocustomerapp.utils.Util;

public class ProfileActivity extends AppCompatActivity {

    private static FragmentManager fragmentManager;
    public String id, email_id, name;
    public Long mobile_number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            id = bundle.getString("id");
            email_id = bundle.getString("email_id");
            mobile_number = bundle.getLong("phone_number");
            name = bundle.getString("name");
        }

        fragmentManager = getSupportFragmentManager();

        if (savedInstanceState == null) {
            fragmentManager
                    .beginTransaction()
                    .replace(R.id.frameContainer, new ProfileActivityFragment(),
                            Util.Profile_Fragment).commit();
        }
    }

    protected void replaceLoginFragment() {
        fragmentManager
                .beginTransaction()
                .setCustomAnimations(R.anim.left_enter, R.anim.right_out)
                .replace(R.id.frameContainer, new ProfileActivityFragment(),
                        Util.Profile_Fragment).commit();
    }

    @Override
    public void onBackPressed() {

        Fragment ProfileEdit_Fragment = fragmentManager
                .findFragmentByTag(Util.ProfileEdit_Fragment);

        if (ProfileEdit_Fragment != null)
            replaceLoginFragment();
        else
            super.onBackPressed();
    }

}
