package pe.com.fragment;

import pe.com.Interfaces.Avanzadas.R;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

public class SendDataDialogFragment extends DialogFragment {

	public interface DialogListener {
		public void OnDialogPositiveClic(DialogFragment dailog);

		public void OnDialogNegativeClic(DialogFragment dailog);
	}

	DialogListener listener;

	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		super.onAttach(activity);
		try {
			listener = (DialogListener) activity;
		} catch (ClassCastException e) {
			// TODO: handle exception
		}
	}

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		// TODO Auto-generated method stub

		// crear el elemento que construira el dialog
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

		// construcción del dialog
		builder.setTitle("Enviar Datos")
				.setSingleChoiceItems(R.array.dialogo_option, -1, null)
				.setPositiveButton(R.string.togle_yes,
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								// TODO Auto-generated method stub
								listener.OnDialogPositiveClic(SendDataDialogFragment.this);
							}

						})
				.setNegativeButton(R.string.togle_no,
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								// TODO Auto-generated method stub
								listener.OnDialogNegativeClic(SendDataDialogFragment.this);
							}
						});

		return builder.create();
	}

}