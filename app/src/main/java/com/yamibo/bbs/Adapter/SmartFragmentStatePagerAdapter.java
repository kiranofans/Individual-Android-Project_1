package com.yamibo.bbs.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.SparseArray;
import android.view.ViewGroup;
/**
This ViewPager adpater is for undetermined number of ViewPager tabs
*/
public abstract class SmartFragmentStatePagerAdapter extends FragmentStatePagerAdapter{
    private SparseArray<Fragment> addedFrags=new SparseArray<Fragment>();

    public SmartFragmentStatePagerAdapter(FragmentManager fragMg){
        super(fragMg);//Default constructor

    }
    @Override
    public Object instantiateItem(ViewGroup container, int pos){
        // Register or add the fragment when the item is instantiated
        Fragment fragment=(Fragment)super.instantiateItem(container,pos);
        addedFrags.append(pos,fragment);
        return fragment;
    }
    @Override
    public void destroyItem(ViewGroup container,int pos,Object obj){
        //Unregister or remove fragment when the item is inactive
        addedFrags.remove(pos);
        super.destroyItem(container,pos,obj);
    }
    public Fragment getAddedFragment(int position){
        return addedFrags.get(position);
    }
}
