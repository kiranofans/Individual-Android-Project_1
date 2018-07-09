package com.yamibo.bbs.splashscreen.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.yamibo.bbs.splashscreen.MainNavTabActivity;
import com.yamibo.bbs.splashscreen.R;

import java.util.ArrayList;
import java.util.List;

import Adapter.MyRecyclerAdapter;
import Model.ApiInterface;
import Model.Base_Items_Model;
import Model.PostsListItems;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AnimeDiscussFragment extends Fragment {
    private static View v;
    private MyRecyclerAdapter recAdp;
    private RecyclerView mangaRecView;
    private List<PostsListItems> mangaDiscussList;
    public static Retrofit retrofit=null;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        v=inflater.inflate(R.layout.fragment_posts,container,false);
        ((MainNavTabActivity)getActivity()).fragsCustomToolbar("動漫區");
        return v;
    }
    @Override
    public void onViewCreated(View v,Bundle savedInstanceState){
        super.onViewCreated(v, savedInstanceState);

    }
}
