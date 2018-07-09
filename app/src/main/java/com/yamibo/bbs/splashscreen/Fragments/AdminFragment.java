package com.yamibo.bbs.splashscreen.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yamibo.bbs.splashscreen.MainNavTabActivity;
import com.yamibo.bbs.splashscreen.R;
public class AdminFragment extends Fragment {
    public AdminFragment() {/*Required empty public constructor*/}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ((MainNavTabActivity)getActivity()).fragsCustomToolbar("管理版");

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_admin, container, false);
    }
    @Override
    public void onViewCreated(View v,Bundle savedInstanceState){

    }

}
