package com.onepunch.rollfun;

import java.util.ArrayList;
import java.util.List;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

public class FunPagerAdapter extends PagerAdapter {
	
	private ArrayList<View> vlist = new ArrayList<View>();
	
	public FunPagerAdapter(ArrayList vlist){
		this.vlist = vlist;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return vlist.size();
	}

	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		// TODO Auto-generated method stub
		return arg0==arg1;
	}
	
	
	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		ViewPager vp = (ViewPager)container;
		vp.addView(vlist.get(position));
		
		return vlist.get(position);
	}

	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		// TODO Auto-generated method stub
		 container.removeView(vlist.get(position));
	}
}
