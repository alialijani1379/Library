package com.example.library.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.example.library.R;
import com.example.library.activity.RegisterActivity;
import com.example.library.databinding.FragmentProfileBinding;

public class ProfileFragment extends Fragment implements View.OnClickListener {
    //<editor-fold desc="--Declaration--">
    private FragmentProfileBinding profileBinding;
    private Button btnLogout;
    private TextView txtName, txtLastName, txtEmail, txtPassword;
    private String name, lastName, email, pass;
    //</editor-fold>
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        profileBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_profile, container, false);
        bindViews(profileBinding);
        receivedSharedPref();
        btnLogout.setOnClickListener(this);

        return profileBinding.getRoot();
    }

    private void receivedSharedPref() {
        SharedPreferences preferences = getActivity().getSharedPreferences("register", Context.MODE_PRIVATE);

        name = preferences.getString("name", null);
        lastName = preferences.getString("lastName", null);
        email = preferences.getString("email", null);
        pass = preferences.getString("pass", null);

        txtName.setText(name);
        txtLastName.setText(lastName);
        txtEmail.setText(email);
        txtPassword.setText(pass);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.btn_logout:
                SharedPreferences preferences = getActivity().getSharedPreferences("register", Context.MODE_PRIVATE);
                Editor editor = preferences.edit();
                editor.clear();
                editor.apply();
                Intent intent = new Intent(getActivity(), RegisterActivity.class);
                startActivity(intent);
                getActivity().finish();
                break;
        }
    }

    private void bindViews(FragmentProfileBinding profileBinding) {
        btnLogout = profileBinding.btnLogout;
        txtName = profileBinding.txtName;
        txtLastName = profileBinding.txtLastName;
        txtEmail = profileBinding.txtEmail;
        txtPassword = profileBinding.txtPassword;
    }

}