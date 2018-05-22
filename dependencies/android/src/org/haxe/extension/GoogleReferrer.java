package org.haxe.extension;


import android.app.Activity;
import android.content.res.AssetManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.RemoteException;
import android.view.View;
import android.net.Uri;
import android.util.Log;

import com.android.installreferrer.api.InstallReferrerStateListener;
import com.android.installreferrer.api.InstallReferrerClient;
import com.android.installreferrer.api.ReferrerDetails;
import com.android.installreferrer.api.InstallReferrerClient.InstallReferrerResponse;

public class GoogleReferrer extends Extension {

	static final String TAG = "GOOGLE-REF-EXTENSION";
	static InstallReferrerClient mReferrerClient;
	
	public static int sampleMethod (int inputValue) {

		Log.d(TAG, "test alert with param: " + inputValue);

		return inputValue * 100;
		
	}
	
	
	/**
	 * Called when an activity you launched exits, giving you the requestCode 
	 * you started it with, the resultCode it returned, and any additional data 
	 * from it.
	 */
	public boolean onActivityResult (int requestCode, int resultCode, Intent data) {
		
		return true;
		
	}
	
	
	/**
	 * Called when the activity is starting.
	 */
	public void onCreate (Bundle savedInstanceState) {

		mReferrerClient = InstallReferrerClient.newBuilder(mainContext).build();
		mReferrerClient.startConnection(new InstallReferrerStateListener() {
			@Override
			public void onInstallReferrerSetupFinished(int responseCode) {
				switch (responseCode) {
					case InstallReferrerResponse.OK:
						try {

							Log.d(TAG, "Connected");

							ReferrerDetails response = mReferrerClient.getInstallReferrer();

							Log.d(TAG, "ReferrerDetails: " + response.getInstallReferrer() + ", " + response.getReferrerClickTimestampSeconds() + ", " + response.getInstallBeginTimestampSeconds());

							mReferrerClient.endConnection();

						} catch (RemoteException e) {
							e.printStackTrace();
						}
						break;

					case InstallReferrerResponse.FEATURE_NOT_SUPPORTED:
						Log.d(TAG, "Not Supported");
						break;

					case InstallReferrerResponse.SERVICE_UNAVAILABLE:
						Log.d(TAG, "Service Unavailable");
						break;

				}
			}

			@Override
			public void onInstallReferrerServiceDisconnected() {
				// Try to restart the connection on the next request to
				// Google Play by calling the startConnection() method.
			}
		});
		
	}
	
	
	/**
	 * Perform any final cleanup before an activity is destroyed.
	 */
	public void onDestroy () {
		
		
		
	}
	
	
	/**
	 * Called as part of the activity lifecycle when an activity is going into
	 * the background, but has not (yet) been killed.
	 */
	public void onPause () {
		
		
		
	}
	
	
	/**
	 * Called after {@link #onStop} when the current activity is being 
	 * re-displayed to the user (the user has navigated back to it).
	 */
	public void onRestart () {
		
		
		
	}
	
	
	/**
	 * Called after {@link #onRestart}, or {@link #onPause}, for your activity 
	 * to start interacting with the user.
	 */
	public void onResume () {
		
		
		
	}
	
	
	/**
	 * Called after {@link #onCreate} &mdash; or after {@link #onRestart} when  
	 * the activity had been stopped, but is now again being displayed to the 
	 * user.
	 */
	public void onStart () {
		
		
		
	}
	
	
	/**
	 * Called when the activity is no longer visible to the user, because 
	 * another activity has been resumed and is covering this one. 
	 */
	public void onStop () {
		
		
		
	}
	
	
}