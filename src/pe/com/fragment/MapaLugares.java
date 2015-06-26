package pe.com.fragment;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import pe.com.Interfaces.Avanzadas.R;
import pe.com.models.Place;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;

public class MapaLugares extends Fragment{

	private MapView mMapView;
	private GoogleMap mMap;
	private Bundle mBundle;
	private final static String PLACES_FILE_NAME = "hotels.json";
	private HashMap<String, Marker> places_marker_map = new HashMap<String, Marker>();
	public static final LatLng MEJORANDOLA = new LatLng(4.667184,-74.059463);
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View inflatedView = inflater.inflate(R.layout.mapas, container, false);

		MapsInitializer.initialize(getActivity());

		mMapView = (MapView) inflatedView.findViewById(R.id.map);
		mMapView.onCreate(mBundle);
		setUpMapIfNeeded(inflatedView);

		return inflatedView;
	}
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		ArrayList<Place> places = loadPlacesFromAssets();
		for(Place place : places){
		Log.d("Place", place.toString());
		}
		

			
		for (Place place : places) {
  		  Marker marker = mMap.addMarker(new MarkerOptions()
        	.position(place.getLatLng())
        	.title(place.getTitle())
        	.snippet(place.getDescription())
        	.icon(BitmapDescriptorFactory.fromResource(place.getResourceMarker())));
  		    		  
  		  places_marker_map.put(place.getTitle(), marker);
		} 		
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mBundle = savedInstanceState;
	}

	private void setUpMapIfNeeded(View inflatedView) {
		if (mMap == null) {
			mMap = ((MapView) inflatedView.findViewById(R.id.map)).getMap();
			if (mMap != null) {
				setUpMap();
			}
		}
	}
	 private ArrayList<Place> loadPlacesFromAssets() {
	      StringBuilder builder = new StringBuilder();
	      BufferedReader reader;
	      ArrayList<Place> places = null;
	      
	      try {
	        reader = new BufferedReader(new InputStreamReader(getActivity().getAssets().open(PLACES_FILE_NAME)));
	        String line = "";
	        while ((line = reader.readLine()) != null) {
	          builder.append(line); 
	        }
	        reader.close();     
	      } catch (IOException e) {
	        e.printStackTrace();
	      }

	      String json = builder.toString();
	      if (!json.equals("")) {
	        Gson gson = new Gson();
	        places = new ArrayList<Place>(Arrays.asList(gson.fromJson(json, Place[].class)));
	      } 
	      return places;
	    }  	
	private void setUpMap() {
		mMap.addMarker(new MarkerOptions().position(new LatLng(0, 0)).title("Marker"));
	}

	@Override
	public void onResume() {
		super.onResume();
		mMapView.onResume();
	}

	@Override
	public void onPause() {
		super.onPause();
		mMapView.onPause();
	}

	@Override
	public void onDestroy() {
		super.onDestroy(); 
		
		mMapView.onDestroy();
	
	}

}
