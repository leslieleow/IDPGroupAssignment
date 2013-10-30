package com.example.masterbedalarm;
import java.io.IOException;
import java.util.Calendar;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;


public class AlarmReceiver extends BroadcastReceiver {

    Context context;

    @Override
    public void onReceive(Context context, Intent intent) {
    	intent.setClass(context, MassageActivity.class);
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		context.startActivity(intent);
		
//    	this.context = context;
//        Bundle bundle = intent.getExtras();
//        Intent serviceIntent = new Intent("chief_musicService");
//        serviceIntent.putExtras(bundle);
//        if(bundle != null) 
//        {
//            if(bundle.getBoolean("music"))
//            {
//                context.startService(serviceIntent);                        	
//            }
//            else
//                context.stopService(serviceIntent);
//        }        
    }

}
