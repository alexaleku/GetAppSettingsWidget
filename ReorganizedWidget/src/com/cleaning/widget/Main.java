package com.cleaning.widget;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.TextView;
import android.widget.Toast;

public class Main extends AppWidgetProvider {

	public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
	
	for(int i=0; i<appWidgetIds.length; i++) {
	int widgetID = appWidgetIds[i];

		Intent intent = new Intent(context, MainActivity.class);
		Log.w("THIS", "MAIN INTENT CREATED !!!");
//		Toast.makeText(context, "TOAST !!!", Toast.LENGTH_SHORT).show();
		
		PendingIntent pending = PendingIntent.getActivity(context, 0, intent, 0);
		
		RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.main);
		views.setOnClickPendingIntent(R.id.imageButton1, pending);
		
		appWidgetManager.updateAppWidget(widgetID, views);
		
	}
	}
}


