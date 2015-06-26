package pe.com.fragment;

import pe.com.Interfaces.Avanzadas.R;
import pe.com.data.CustomerPagerAdapter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBar.Tab;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class MainFragment extends Fragment 
							implements ViewPager.OnPageChangeListener,
										ActionBar.TabListener{

	private ViewPager view_pager;
	private CustomerPagerAdapter adapter;
	ActionBar bar;
	

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.fragment_main, null);
		view_pager = (ViewPager)view.findViewById(R.id.pager);
		
		return view;
	}


	@Override
	public void  onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		
		
		adapter= new CustomerPagerAdapter(getActivity().getSupportFragmentManager());
		view_pager.setAdapter(adapter);
		view_pager.setOnPageChangeListener(this);
		
		 bar =((ActionBarActivity) getActivity()).getSupportActionBar();
		 bar.removeAllTabs();
		 bar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		 bar.addTab(bar.newTab().setText("Lista").setTabListener(this));
		 bar.addTab(bar.newTab().setText("Grid").setTabListener(this));
		 bar.addTab(bar.newTab().setText("Mapa").setTabListener(this));
		 
	}


	@Override
	public void onPageScrollStateChanged(int arg0) {
		
	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onPageSelected(int position) {
		// TODO Auto-generated method stub
		bar.setSelectedNavigationItem(position);
		Log.d("OnPageSelectes", ""+position);
	}

	@Override
	public void onTabReselected(Tab arg0, FragmentTransaction arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTabSelected(Tab tab, FragmentTransaction arg1) {
		// TODO Auto-generated method stub
		view_pager.setCurrentItem(tab.getPosition());
		Log.d("OnTabSelectes", ""+tab.getPosition());
	}

	@Override
	public void onTabUnselected(Tab arg0, FragmentTransaction arg1) {
		// TODO Auto-generated method stub
		
	}

}
