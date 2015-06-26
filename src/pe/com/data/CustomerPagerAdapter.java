package pe.com.data;

import pe.com.fragment.GridRoomFragment;
import pe.com.fragment.MapaLugares;
import pe.com.fragment.PlacesMapFragment;
import pe.com.fragment.RoomListFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;

public class CustomerPagerAdapter extends FragmentStatePagerAdapter{

	
	private Fragment[] fragments;
	
	public CustomerPagerAdapter(FragmentManager fm) {
		super(fm);
		// TODO Auto-generated constructor stub
		fragments = new Fragment[]{new RoomListFragment(),
								   new GridRoomFragment(),
								   new MapaLugares()
								  };
	}

	@Override
	public Fragment getItem(int arg0) {
		// TODO Auto-generated method stub
		Log.d("getItemFragment","" +arg0);
		return fragments[arg0];
	} 

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return fragments.length;
	}

}
