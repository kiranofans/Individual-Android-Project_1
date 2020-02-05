package com.yamibo.bbs.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.yamibo.bbs.splashscreen.R;

import java.util.List;

public class ImgViewPagerAdapter extends PagerAdapter {
    private List<String> imgUrlsList;
    public Context context;
    private static ImageView imgView;
    private LayoutInflater inflater;
    public ImgViewPagerAdapter(Context context,List<String> imgUrlList) {
        this.context=context;
        this.inflater=(LayoutInflater)context.getSystemService
                (Context.LAYOUT_INFLATER_SERVICE);
        this.imgUrlsList=imgUrlList;
    }

    @Override
    public int getCount(){
        return imgUrlsList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;//View is logically equal to object
    }
    @Override
    public Object instantiateItem(ViewGroup container,int pos){
        inflater=(LayoutInflater)context.getSystemService
                (Context.LAYOUT_INFLATER_SERVICE);
        View v= inflater.inflate(R.layout.items_viewpager,null);
        imgView=(ImageView)v.findViewById(R.id.vpImgs);

        ViewPager vp=(ViewPager)container;
        try{
            String imgUrl=imgUrlsList.get(pos);
            Picasso.with(context).load(imgUrl)
                    .into(imgView);
        }catch (NumberFormatException ne){
            Log.d("VP",ne.getMessage());
        }
        container.addView(v,0);
        return v;
    }
    @Override
    public void destroyItem(ViewGroup container,int pos,Object object){
        ((ViewPager)container).removeView((View)(object));
    }
}
