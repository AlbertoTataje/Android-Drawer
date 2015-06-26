package pe.com.fragment;

import java.util.ArrayList;

import pe.com.Interfaces.Avanzadas.MainActivity;
import pe.com.Interfaces.Avanzadas.R;
import pe.com.data.CustomAdapter;
import pe.com.models.Room;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.internal.widget.AdapterViewCompat;
import android.support.v7.internal.widget.AdapterViewCompat.OnItemClickListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;


public class GridRoomFragment extends Fragment implements android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener,
OnItemClickListener{
	

	private SwipeRefreshLayout swipeRefreshLayout;
	private GridView grid;
	

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.fragment_room_grid,null);
		grid = (GridView)view.findViewById(R.id.grid_rooms);
		return view;
	}



	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
				
		swipeRefreshLayout = (SwipeRefreshLayout) getActivity().findViewById(R.id.swipe_container_grid);
		swipeRefreshLayout.setOnRefreshListener(this);
	    swipeRefreshLayout.setColorSchemeColors(R.color.color1,R.color.color2,R.color.color3);
	   	    	
		ArrayList<Room> rooms = new ArrayList<Room>();
		
		for (String room : getResources().getStringArray(R.array.array_rooms_standard)) {
			Room one_room = new Room(room,Room.STANDARD_ROOM);
			rooms.add(one_room);
		}
		for (String room : getResources().getStringArray(R.array.array_rooms_luxury)) {
			Room one_room = new Room(room,Room.LUXURY_ROOM);
			rooms.add(one_room);
		}
		
		CustomAdapter adapter =new CustomAdapter(getActivity(), rooms,false);
		grid.setAdapter(adapter);

	}



	@Override
	public void onRefresh() {
		new AsyncTask<Void,Void,Void>(){

			
			
			@Override
			protected void onPreExecute() {
				// TODO Auto-generated method stub
				super.onPreExecute();
				swipeRefreshLayout.setRefreshing(true);
			}

			@Override
			protected Void doInBackground(Void... params) {
				try {
					Thread.sleep(3000);
				} catch (Exception e) {
					// TODO: handle exception
				}
				return null;
			}

			@Override
			protected void onPostExecute(Void result) {
				// TODO Auto-generated method stub
				super.onPostExecute(result);
				swipeRefreshLayout.setRefreshing(false);
			}
			
			
		}.execute();
		
	}



	@Override
	public void onItemClick(AdapterViewCompat<?> arg0, View view, int position,
			long arg3) {
		Room clicked_room = (Room)grid.getItemAtPosition(position);
		Intent intent = new Intent(getActivity(),RoomListFragment.class);
		intent.putExtra(MainActivity.ROOM_TYPE, clicked_room.getRoomType());
		intent.putExtra(MainActivity.ROOM_NUMBER, clicked_room.getRoomNumber());
	    startActivity(intent);
		
	}


}
