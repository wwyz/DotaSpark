package com.examples.gg;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import android.os.AsyncTask;
import android.view.View;
import android.widget.Button;

public class MyAsyncTask extends AsyncTask<String, String, String> {
	protected String responseString = null;
	protected boolean taskCancel = false;
	public int type;
	public static final int INITTASK = 1;
	public static final int LOADMORETASK = 2;
	public boolean isException = false;
	protected View contentView;
	protected View loadingView;
	protected View retryView;
	protected Button mRetryButton;

	public MyAsyncTask(int type, View contentView, View loadingView,
			View retryView) {
		this.type = type;
		this.contentView = contentView;
		this.loadingView = loadingView;
		this.retryView = retryView;

		setRetryListener(this.type);
	}

	public void DisplayView(View viewToShow, View view_1ToHide,
			View view_2ToHide) {
		if (viewToShow != null)
			viewToShow.setVisibility(View.VISIBLE);
		if (view_1ToHide != null)
			view_1ToHide.setVisibility(View.GONE);
		if (view_2ToHide != null)
			view_2ToHide.setVisibility(View.GONE);
	}

	public void setRetryListener(final int type) {

	}

	public void handleCancelView() {

		if (isException) {

			DisplayView(retryView, contentView, loadingView);
		}

	}

	@Override
	protected void onPreExecute() {
		super.onPreExecute();
		if (type == INITTASK)
			DisplayView(loadingView, contentView, retryView);
	}

	@Override
	protected String doInBackground(String... uri) {

//		 String userAgent =
//		 "Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10.4; en-US; rv:1.9.2.2) Gecko/20100316 Firefox/3.6.2";
//		HttpClient httpclient = new DefaultHttpClient();
//		httpclient.getParams().setParameter(CoreProtocolPNames.USER_AGENT, userAgent);
//		HttpResponse response;

		// if (!ic.isOnline(sfa)) {
		// Log.d("AsyncDebug", "Ic not online!");
		//
		// cancel(true);
		// taskCancel = true;
		// } else
		try {
			
            URL url = new URL(uri[0]);  
            URLConnection conn = url.openConnection();  
            conn.connect();  
            InputStream is = conn.getInputStream();  
            responseString = getStringFromInputStream(is);
            is.close();  

		} catch (Exception e) {
			// throw new IOException(statusLine.getReasonPhrase());

			//Log.d("AsyncDebug", e.toString());

			isException = true;
			cancel(true);
			taskCancel = true;

		} finally {

			//Log.d("AsyncDebug", "shutdown");

//			httpclient.getConnectionManager().shutdown();
			//Log.d("AsyncDebug", "Do in background finished!");

		}
		return responseString;
	}

	@Override
	protected void onCancelled() {
		// Notify the loading more operation has finished
		//Log.d("AsyncDebug", "Into OnCancelled!");
		handleCancelView();
	}
	
	private static String getStringFromInputStream(InputStream is) {
		 
		BufferedReader br = null;
		StringBuilder sb = new StringBuilder();
 
		String line;
		try {
 
			br = new BufferedReader(new InputStreamReader(is));
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}
 
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
 
		return sb.toString();
 
	}

}
