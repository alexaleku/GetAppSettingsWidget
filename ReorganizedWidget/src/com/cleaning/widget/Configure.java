package com.cleaning.widget;

import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

import android.app.ListActivity;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RemoteViews;
import android.widget.TextView;
import android.widget.Toast;

public class Configure extends ListActivity {
	private Configure context;
	private int widgetID;
	private SortedMap<String, String> appInfo;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		appInfo = getSortAppInfoList();
		String[] aPPSnames = appInfo.keySet().toArray(new String[appInfo.size()]);	
		setContentView(R.layout.configure);
		setListAdapter(new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, aPPSnames));
		ListView listView = getListView();
		listView.setTextFilterEnabled(true);
		setResult(RESULT_CANCELED);

		context = this;
		Bundle extras = getIntent().getExtras();
		if (extras != null) {
			widgetID = extras.getInt(AppWidgetManager.EXTRA_APPWIDGET_ID,
					AppWidgetManager.INVALID_APPWIDGET_ID);
		}
		final AppWidgetManager widgetManager = AppWidgetManager
				.getInstance(context);
		final RemoteViews views = new RemoteViews(context.getPackageName(),
				R.layout.main);

		listView.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

				Log.d("MainActivity", "HEREYOUGO");
				String chosenAppName = (String) ((TextView) view).getText();
				System.out.println(chosenAppName);
				String packegeName = appInfo.get(chosenAppName);
				System.out.println(appInfo.get(chosenAppName));

				Toast.makeText(getApplicationContext(),
						((TextView) view).getText(), Toast.LENGTH_SHORT).show();

				Intent intent = new Intent(context, MainActivity.class);
				
				// Saving to SharedPreferences
				final String PREFS_NAME = "MyPrefsFile";
				SharedPreferences.Editor prefs = context.getSharedPreferences(
						PREFS_NAME, 0).edit();
				
//				intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, widgetID);
				
				String widgIDstr = Integer.toString(widgetID);
				intent.putExtra("widgetID", widgIDstr);
				System.out.println(widgetID + "  " + widgIDstr);
				prefs.putString(widgIDstr, packegeName);
				System.out.println("SharedPref !!!!! " + widgIDstr + "  " + packegeName);
				prefs.commit();
				

				PendingIntent pending = PendingIntent.getActivity(context, widgetID,
						intent, PendingIntent.FLAG_UPDATE_CURRENT);
				views.setOnClickPendingIntent(R.id.imageButton1, pending);
				widgetManager.updateAppWidget(widgetID, views);

				Intent resultValue = new Intent();
				resultValue.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID,
						widgetID);
				setResult(RESULT_OK, resultValue);

				
				finish();
			}
		});
	}

	private SortedMap<String, String> getSortAppInfoList() {
		List<PackageInfo> installedPackagesInfo = getPackageManager()
				.getInstalledPackages(0);

		SortedMap<String, String> appsInfoList = new TreeMap<String, String>();
		for (int i = 0; i < installedPackagesInfo.size(); i++) {
			PackageInfo installedPackag = installedPackagesInfo.get(i);
			if ((installedPackag.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) != 1) {
			String appName = installedPackag.applicationInfo
					.loadLabel(getPackageManager()).toString();
			
			String packegeName = installedPackag.packageName.toString();
			appsInfoList.put(appName, packegeName);
			}

		}
		System.out.println(appsInfoList.size());
		return appsInfoList;
}
}