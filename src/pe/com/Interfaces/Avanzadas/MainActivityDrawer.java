package pe.com.Interfaces.Avanzadas;

import pe.com.fragment.MainFragment;
import pe.com.fragment.PlacesMapFragment;
import pe.com.fragment.TermsFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.internal.widget.AdapterViewCompat;
import android.support.v7.internal.widget.AdapterViewCompat.OnItemClickListener;
import android.annotation.SuppressLint;
import android.content.res.Configuration;
import android.nfc.Tag;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

//Drawer 
@SuppressLint("NewApi")
public class MainActivityDrawer extends ActionBarActivity implements
		ListView.OnItemClickListener {

	private ListView drawer_list;
	private DrawerLayout drawer_layout;
	private ActionBarDrawerToggle drawer_togle;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_main_activity_drawer);
		drawer_layout = (DrawerLayout) findViewById(R.id.drawer_layout);
		drawer_list = (ListView) findViewById(R.id.left_drawer);

		// el 2do arg es como un estilo
		ArrayAdapter<String> drawer_list_adapter = new ArrayAdapter<String>(
				this, R.layout.drawer_list_item, getResources().getStringArray(
						R.array.array_drawer_options));

		drawer_list.setAdapter(drawer_list_adapter);
		drawer_list.setOnItemClickListener(this);

		// -----Configuracion del Botón del Drawer-----//
		drawer_togle = new ActionBarDrawerToggle(this, drawer_layout,
				R.drawable.ic_drawer, R.string.drawer_open) {
			public void onDrawerClosed(View view) {
				invalidateOptionsMenu();
			}

			public void onDrawerOpened(View view) {
				invalidateOptionsMenu();
			}

		};
		drawer_layout.setDrawerListener(drawer_togle);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setHomeButtonEnabled(true);

		// ---------------------------------------------//

		SelectFragment(0);
	}
	
	

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		// TODO Auto-generated method stub
		super.onConfigurationChanged(newConfig);
		drawer_togle.onConfigurationChanged(newConfig);
	}

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onPostCreate(savedInstanceState);
		drawer_togle.syncState();
	}

	public void SelectFragment(int position) {
		Fragment f;
		if (position == 0) {
			f = new MainFragment();
		} else  {
			f = new TermsFragment();
		}
		
		FragmentManager fragment_support = getSupportFragmentManager();

		// FrameLayout
		fragment_support.beginTransaction().replace(R.id.main_content, f)
				.commit();
		drawer_list.setItemChecked(position, true);
		setTitle(drawer_list.getItemAtPosition(position).toString());
		drawer_layout.closeDrawer(drawer_list);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main_activity_drawer, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		
		//manejar el toggle
		else if(drawer_togle.onOptionsItemSelected(item)){
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int position,
			long arg3) {
		SelectFragment(position);

	}

}
