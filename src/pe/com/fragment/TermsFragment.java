package pe.com.fragment;

import pe.com.Interfaces.Avanzadas.R;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class TermsFragment extends Fragment {

	private WebView web_view;
	private static final String url="https://www.google.com.pe";
	ActionBar actionbar;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.fragment_terms, null);
		web_view= (WebView)view.findViewById(R.id.web);
		return view;
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		web_view.loadUrl(url);
		//asignar a la vista
		web_view.setWebViewClient(new WebViewClient(){

			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				// TODO Auto-generated method stub
				view.loadUrl(url);
				return true;
			}
			
		});
		 actionbar =((ActionBarActivity) getActivity()).getSupportActionBar();
		 actionbar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
	}

	
	
	
}
