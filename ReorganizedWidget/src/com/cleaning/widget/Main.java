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
//	private String packegeName;
	
	public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
	
	for(int i=0; i<appWidgetIds.length; i++) {
	int widgetID = appWidgetIds[i];
		
//ertterth	int appWidgetId = 0;
		
	
	// kmkooooknonk
	
		Intent intent = new Intent(context, MainActivity.class);
		intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, widgetID);
		Log.w("THIS", "MAIN INTENT CREATED !!!");
		Toast.makeText(context, "TOAST !!!", Toast.LENGTH_SHORT).show();
//		context.get
//		int widgetID = intent.getExtras().getInt(AppWidgetManager.EXTRA_APPWIDGET_ID,
//				AppWidgetManager.INVALID_APPWIDGET_ID);
		System.out.println("MAIN ID " + widgetID);
		
		
		
		PendingIntent pending = PendingIntent.getActivity(context, widgetID, intent, PendingIntent.FLAG_UPDATE_CURRENT);
		
		RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.main);
		views.setOnClickPendingIntent(R.id.imageButton1, pending);
		
//		appWidgetManager.updateAppWidget(appWidgetId, views);
		
//		finish();
	}
	}
//		}
}
//	public void onEnabled (Context context) {
		
//		this.packegeName = packegeName;
//		onUpdate(context, appWidgetManager appWidgetManager, appWidgetIds);
//		AppWidgetManager widgetManager = AppWidgetManager.getInstance(context);
//		RemoteViews views = new RemoteViews(context.getPackageName(),R.layout.main);
//		widgetManager.updateAppWidget(widgetID, views);
//	}

//}

