package com.yamibo.bbs.Activities.Fragments;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.yamibo.bbs.Activities.MainNavTabActivity;
import com.yamibo.bbs.Activities.R;
import Managers.SessionManager;

import static Utils.AppConstants.PREF_FILE_GLOBAL;

/**
 * A simple {@link Fragment} subclass.
 */
public class SettingsFragment extends Fragment {
    SessionManager sessionManager;
    private Button logoutBtn;

    public SettingsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_settings, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ((MainNavTabActivity) getActivity()).fragsCustomToolbar("Settings");

        sessionManager = new SessionManager(view.getContext(),PREF_FILE_GLOBAL);
        logoutBtn = view.findViewById(R.id.btn_logout);
        setBtnTabs();
    }

    private void setBtnTabs(){
        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sessionManager.logoutUser();
                getActivity().finish();
            }
        });
    }

}
