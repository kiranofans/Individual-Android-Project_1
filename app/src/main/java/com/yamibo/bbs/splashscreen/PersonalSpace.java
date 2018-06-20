package com.yamibo.bbs.splashscreen;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class PersonalSpace extends Fragment implements View.OnClickListener{
    public PersonalSpace(){

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.activity_space,container,false);
        return v;
    }
    @Override
    public void onClick(View v) {

    }
}
