package pe.com.Interfaces.Avanzadas;

import pe.com.fragment.SendDataDialogFragment;
import pe.com.models.Room;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ToggleButton;

//RoomDetailActivity
public class MainActivity extends ActionBarActivity implements
		SendDataDialogFragment.DialogListener, SearchView.OnQueryTextListener {

	public final static String ROOM_TYPE = "tipo de habitación";
	public final static String ROOM_NUMBER = "número de habitación";
	public final static String DIALOG_TAG = "dialogo";

	private Room room;
	private boolean favorito = false;
	private SearchView mSearchView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_room_detail);
		// recuperar el intent
		Intent intent = getIntent();
		room = new Room(intent.getStringExtra(ROOM_NUMBER),
				intent.getStringExtra(ROOM_TYPE));
		ToggleButton togle_recomeds = (ToggleButton) findViewById(R.id.togle);
		togle_recomeds.setChecked(true);

		int resource = -1;
		if (room.getRoomType().equals(Room.STANDARD_ROOM)) {
			resource = R.drawable.hotel1;
		} else {
			resource = R.drawable.hotel2;
		}
		// img_header = hotel1
		ImageView img_header = (ImageView) findViewById(R.id.hotel1);
		img_header.setImageResource(resource);
		setTitle(room.getRoomNumber());

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		MenuItem searchItem = menu.findItem(R.id.action_buscar);
		mSearchView = (SearchView) MenuItemCompat.getActionView(searchItem);

		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		int itemId = item.getItemId();
		if (itemId == R.id.action_buscar) {
			mSearchView.setIconified(true);
			return true;
		} else if (itemId == R.id.action_share) {
			compartir();
			return true;
		} else if (itemId == R.id.action_fav) {
			favorito(item);
			return true;
		} else if (itemId == R.id.action_dialog) {
			SendDataDialogFragment f = new SendDataDialogFragment();
			f.show(getSupportFragmentManager(), "dialogo");
			return true;
		}
		return true;
	}

	private void compartir() {
		Intent share = new Intent();
		share.setAction(Intent.ACTION_SEND);
		// el mensaje que se envia
		String msg = getResources().getString(R.string.compartir);
		share.putExtra(Intent.EXTRA_TEXT, msg);
		Uri img_res = Uri.parse("android.resource://" + getPackageName()
				+ "/drawable/" + R.drawable.hotel1);
		share.putExtra(Intent.EXTRA_STREAM, img_res);
		share.setType("image/jpeg");
		startActivity(Intent.createChooser(share, "Compartir en :"));
	}

	public void favorito(MenuItem item) {
		Drawable icon;
		if (!favorito) {
			icon = getResources().getDrawable(R.drawable.rating_important);
		} else
			icon = getResources().getDrawable(R.drawable.rating_not_important);
		favorito = !favorito;

		item.setIcon(icon);

	}

	@Override
	public boolean onQueryTextChange(String arg0) {

		Toast.makeText(this, arg0 + "change", Toast.LENGTH_LONG).show();
		return false;
	}

	@Override
	public boolean onQueryTextSubmit(String arg0) {
		// TODO Auto-generated method stub

		Toast.makeText(this, arg0 + "submit", Toast.LENGTH_LONG).show();
		return false;
	}

	// asignado en el activity_room_detail
	public void toggle_action(View view) {
		Log.e("TAG", "Togle");

	}

	@Override
	public void OnDialogPositiveClic(DialogFragment dailog) {
		// TODO Auto-generated method stub
		Log.e("TAGSi", "Dijo que si");
	}

	@Override
	public void OnDialogNegativeClic(DialogFragment dailog) {
		// TODO Auto-generated method stub
		Log.e("TAGNo", "Dijo que No");
	}

}

// para action bar

// http://romannurik.github.io/AndroidAssetStudio/icons-actionbar.html#source.type=clipart&source.space.trim=0&source.space.pad=0.1&source.clipart=res%2Fclipart%2Ficons%2Faction_thumb_up.svg&name=ic_action_action_thumb_up&theme=dark&color=33b5e5%2C60
