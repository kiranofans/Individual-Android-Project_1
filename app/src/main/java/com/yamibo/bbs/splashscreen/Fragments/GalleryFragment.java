package com.yamibo.bbs.splashscreen.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.squareup.picasso.Picasso;
import com.yamibo.bbs.splashscreen.MainNavTabActivity;
import com.yamibo.bbs.splashscreen.R;
import Utils.VolleySingleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import Adapter.MyRecyclerAdapter;
import Adapter.SectionRecycleViewAdapter;
import Model.Base_Items_Model;
import Model.Image;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link GalleryFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class GalleryFragment extends Fragment implements MyRecyclerAdapter.OnItemClickListener{
    private OnFragmentInteractionListener mListener;
    private static RecyclerView recView;
    private static MyRecyclerAdapter recViewAdp;
    private static List<Base_Items_Model> picsList;
    private List<SectionRecycleViewAdapter.Sections> albumSecList;
    private String imgApiUrl="https://pixabay.com/api/?key=5303976-fd6581ad4ac165d1b75cc15b3&q=kitten&image_type=photo&pretty=true";

    public GalleryFragment() {/*Required empty public constructor*/}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ((MainNavTabActivity)getActivity()).fragsCustomToolbar("我的相冊");

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_gallery, container, false);
    }
    @Override
    public void onViewCreated(View v,Bundle savedInstanceState){
        recView=(RecyclerView)v.findViewById(R.id.gallery_rec);
        recView.setLayoutManager(new GridLayoutManager(getContext(),2));

        getImages();
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override//RecViewAdapter onItemClick function
    public void onItemClick(int position) {

    }

    /** This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * See the Android Training lesson
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * Communicating with Other Fragments for more information.*/
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
    private void getImages(){
        picsList=new ArrayList<>();
        JsonObjectRequest imgRequest=new JsonObjectRequest(Request.Method.GET, imgApiUrl, null,
                new com.android.volley.Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray hitsArr=response.getJSONArray("hits");
                            for(int i=0;i<hitsArr.length();i++){
                                JSONObject imgObj=hitsArr.getJSONObject(i);
                                String urls=imgObj.getString("webformatURL");
                                Image imgs=new Image(urls);
                                picsList.add(imgs);
                            }
                            recViewAdp=new MyRecyclerAdapter(getContext(),picsList);
                            recView.setAdapter(recViewAdp);
                            Log.d("T","Pics Size: "+picsList.size());
                            Picasso.with(getContext()).setLoggingEnabled(true);
                        } catch (JSONException je) {
                            Toast.makeText(getContext(),je.getMessage(),
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(),error.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
        VolleySingleton.getInstance(getContext()).addToRequestQueue(imgRequest);
    }

}
