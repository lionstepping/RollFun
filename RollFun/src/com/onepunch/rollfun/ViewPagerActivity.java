package com.onepunch.rollfun;

import java.util.ArrayList;

import android.app.Activity;
import android.app.LocalActivityManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

public class ViewPagerActivity extends Activity{

	private Context context = null;
	private LocalActivityManager manager = null;
	private ViewPager myViewPager = null;
	private FunPagerAdapter padapter;
	private ArrayList<View> vlist;

	@Override
	protected void onCreate(Bundle savedInstanceState){
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		context = this;
		setContentView(R.layout.viewpagerlayout);
		manager = new LocalActivityManager(this, true);
		manager.dispatchCreate(savedInstanceState);
		myViewPager = (ViewPager) findViewById(R.id.viewpager1);
		vlist = new ArrayList<View>();

		Intent intent1 = new Intent(context, RollFunActivity.class);
		Intent intent2 = new Intent(context, AddActivity.class);

		vlist.add(getView("Roll", intent1));
		vlist.add(getView("Add", intent2));
		padapter = new FunPagerAdapter(vlist);
		
		myViewPager.setAdapter(padapter);
		myViewPager.setCurrentItem(0);
		
	}

	private View getView(String id, Intent intent) {

		return manager.startActivity(id, intent).getDecorView();
		
	}
}
