package com.yamibo.bbs.Activities.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yamibo.bbs.Activities.MainNavTabActivity;
import com.yamibo.bbs.Activities.R;

public class SpaceFragment extends Fragment{
    public SpaceFragment(){}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_space,container,false);

        //空間名稱可換，可dynamic
        ((MainNavTabActivity)getActivity()).fragsCustomToolbar("我的空間");
        return v;
    }
    @Override
    public void onViewCreated(View v,Bundle savedInstanceState){

    }
}
