package com.examples.gg;

import com.actionbarsherlock.view.MenuItem;

import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.ListView;

public class LoadMore_Base_UP extends LoadMore_Base {

	protected LoadMore_Base mLoadMore;
	protected String nextFragmentAPI;
	
	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {

		// check network first
//		if (ic.isOnline(sfa)) {
			
//			FragmentTransaction ft = getFragmentManager().beginTransaction();
//			
//			// Putting the current fragment into stack for later call back
//			ft.addToBackStack(null);
			//get the API corresponding to the item selected
			nextFragmentAPI = videolist.get(position).getPlaylistUrl();
			String title = videolist.get(position).getTitle();
			
			Intent i = new Intent(this.getSherlockActivity(), LoadMore_BaseActivity.class);
			i.putExtra("API", nextFragmentAPI);
			i.putExtra("title", title);
			startActivity(i);

			
			//intialize  fragment by passing a API to it
//			InitializingNextFragment();
//			ft.hide(this);
//			ft.add(R.id.content_frame, mLoadMore);
//			ft.commit();
//		} else {
//			//ic.networkToast(sfa);
//			ic.setNetworkError(InternetConnection.fullscreenLoadingError);
//		}

	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		if (item.getItemId() == android.R.id.home) {
			sfa.getSupportFragmentManager().popBackStack();
		}

		return super.onOptionsItemSelected(item);
	}

	public void InitializingNextFragment() {


	}
	
}
