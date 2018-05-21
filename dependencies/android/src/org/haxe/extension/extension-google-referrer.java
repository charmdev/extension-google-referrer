package org.haxe.extension;


import android.app.Activity;
import android.content.res.AssetManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.net.Uri;
import android.util.Log;


/* 
	You can use the Android Extension class in order to hook
	into the Android activity lifecycle. This is not required
	for standard Java code, this is designed for when you need
	deeper integration.
	
	You can access additional references from the Extension class,
	depending on your needs:
	
	- Extension.assetManager (android.content.res.AssetManager)
	- Extension.callbackHandler (android.os.Handler)
	- Extension.mainActivity (android.app.Activity)
	- Extension.mainContext (android.content.Context)
	- Extension.mainView (android.view.View)
	
	You can also make references to static or instance methods
	and properties on Java classes. These classes can be included 
	as single files using <java path="to/File.java" /> within your
	project, or use the full Android Library Project format (such
	as this example) in order to include your own AndroidManifest
	data, additional dependencies, etc.
	
	These are also optional, though this example shows a static
	function for performing a single task, like returning a value
	back to Haxe from Java.
*/
public class Extension_google_referrer extends Extension {

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

		mReferrerClient = InstallReferrerClient.newBuilder(this).build();
		mReferrerClient.startConnection(new InstallReferrerStateListener() {
			@Override
			public void onInstallReferrerSetupFinished(int responseCode) {
				switch (responseCode) {
					case InstallReferrerResponse.OK:

						Log.d(TAG, "Connected");

						ReferrerDetails response = mReferrerClient.getInstallReferrer();

						Log.d(TAG, "ReferrerDetails: " + response.getInstallReferrer() + ", " + response.getReferrerClickTimestampSeconds() + ", " + response.getInstallBeginTimestampSeconds());

						mReferrerClient.endConnection();

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