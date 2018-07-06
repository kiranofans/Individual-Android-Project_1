package Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.yamibo.bbs.splashscreen.R;

public class ImgViewPagerAdapter extends PagerAdapter {
    private int[] imgs={R.drawable.bubble_kitty,R.drawable.arcer};
    public Context context;
    private LayoutInflater inflater;
    public ImgViewPagerAdapter(Context context) {
        this.context=context;
    }

    @Override
    public int getCount(){
        return imgs.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;//View is logically equal to object
    }
    @Override
    public Object instantiateItem(ViewGroup container,int pos){
        inflater=(LayoutInflater)context.getSystemService
                (Context.LAYOUT_INFLATER_SERVICE);
        View v= inflater.inflate(R.layout.viewpager_items,null);

        ImageView imgView=(ImageView)v.findViewById(R.id.vpImgs);
        imgView.setImageResource(imgs[pos]);
        ViewPager vp=(ViewPager)container;

        container.addView(v,0);

        imgView.setImageResource(imgs[pos]);
        return v;
    }
    @Override
    public void destroyItem(ViewGroup container,int pos,Object object){
        ((ViewPager)container).removeView((View)(object));
    }

}
