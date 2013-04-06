package com.cleaning.widget;

import android.app.Activity;
import android.appwidget.AppWidgetManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;


public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);	
		String value = "com.android.quicksearchbox";
		String packege = null;
			
		SharedPreferences prefs = getSharedPreferences("MyPrefsFile", 0);
		packege = prefs.getString("packege", "");

		if (packege != null) {
		    value = packege;
		}
		
		startActivity(new Intent(android.provider.Settings.ACTION_APPLICATION_DETAILS_SETTINGS, Uri.parse(("package:" + value))));
		finish();
	}
}
 