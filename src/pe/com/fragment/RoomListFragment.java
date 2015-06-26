package pe.com.fragment;



import java.util.ArrayList;

import pe.com.Interfaces.Avanzadas.MainActivity;
import pe.com.Interfaces.Avanzadas.R;
import pe.com.Interfaces.Avanzadas.R.array;
import pe.com.Interfaces.Avanzadas.R.color;
import pe.com.Interfaces.Avanzadas.R.id;
import pe.com.Interfaces.Avanzadas.R.layout;
import pe.com.data.CustomAdapter;
import pe.com.models.Room;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;


public class RoomListFragment extends ListFragment
implements android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener{
	
	//private PullToRefreshAttacher pull_refresh;
	private SwipeRefreshLayout swipeRefreshLayout;
	View view; 
	
	
	//Se construye el view
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub 
		view = inflater.inflate(R.layout.fragment_room_list, null);
		return view;
	}

	@Override
	public void onListItemClick(
			ListView l, View v,
			int position, long id) {
		Room clicked_room = (Room)l.getItemAtPosition(position);
		Intent i = new Intent(getActivity(), MainActivity.class);
		i.putExtra(MainActivity.ROOM_NUMBER, clicked_room.getRoomNumber());
		i.putExtra(MainActivity.ROOM_TYPE, clicked_room.getRoomType());
		startActivity(i);
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		
		
		//Obtenemos una referencia al viewgroup SwipeLayout
		swipeRefreshLayout = (SwipeRefreshLayout) getActivity().findViewById(R.id.swipe_container_list);
	    swipeRefreshLayout.setOnRefreshListener(this);
		 
	  
		ListView list = getListView();
	    
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
		
		CustomAdapter adapter =new CustomAdapter(getActivity(), rooms,true);
		
		list.setAdapter(adapter);

	}
	
	//ListFragment




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
	

}
