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
		System.out.println("hey");
		String widgetID;
		
		widgetID = this.getIntent().getExtras().getString("widgetID");
		if (widgetID == null) {
		int appWidgetId = this.getIntent().getExtras().getInt(AppWidgetManager.EXTRA_APPWIDGET_ID);
		widgetID = Integer.toString(appWidgetId);
		}
//		Bundle extras = this.getIntent().getExtras();
		
//		
//			System.out.println(widgetID);
			
		SharedPreferences prefs = getSharedPreferences("MyPrefsFile", 0);
		packege = prefs.getString(widgetID, "");
		System.out.println("Retrieved from SharedPref !!! " + widgetID + packege);
		
		System.out.println(packege);
		if (packege != null) {
		    value = packege;
		    System.out.println(value);
		}
		
		
		startActivity(new Intent(android.provider.Settings.ACTION_APPLICATION_DETAILS_SETTINGS, Uri.parse(("package:" + value))));
		finish();
	}
}
