package com.example.masterbedalarm;

import java.io.IOException;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;

public class AndroidAlarmService extends Service{
    private MediaPlayer player;
    private BroadcastReceiver mReceiver;
    private Context mContext;
    
    public IBinder onBind(Intent intent) {
        playMusic();   
        return null;
    }
    
    
    @Override
	public boolean onUnbind(Intent intent) {
		// TODO Auto-generated method stub
    	stopMusic();
		return super.onUnbind(intent);
	}


	@Override
    public void onCreate() {
        // TODO Auto-generated method stub
        super.onCreate();
        
        mContext = this;        
        mReceiver = new BroadcastReceiver() {
			@Override
			public void onReceive(Context context, Intent intent) {				
				stopMusic();
				stopSelf();
			}
      	  
        };
        
        IntentFilter filter = new IntentFilter();
        filter.addAction("alarm_stop");        
        this.registerReceiver(mReceiver, filter);
        playMusic();
        Intent intent = new Intent(this, AlarmNotificationActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        this.startActivity(intent);
    }
    
    

    @Override
    public void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
        if (player != null) {   
            player.stop();   
            player.release();   
        }
    }

    @Override
    public void onStart(Intent intent, int startId) {
        
         if (intent != null) {   
                Bundle bundle = intent.getExtras();   
                if (bundle != null) {
                    if(bundle.getBoolean("music"))
                        playMusic();
                    else
                        stopMusic();
                }   
            }
    }
    
    public void playMusic() 
    {
        if(player == null) {
            Uri uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
            try {
                player = new MediaPlayer();
                player.setDataSource(this, uri);
                final AudioManager audioManager = (AudioManager)this
                        .getSystemService(Context.AUDIO_SERVICE);
                if (audioManager.getStreamVolume(AudioManager.STREAM_ALARM) != 0) {
                    player.setAudioStreamType(AudioManager.STREAM_ALARM);
                    player.setLooping(true);
                    player.prepare();
                }
            } catch (IllegalStateException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        
        if(!player.isPlaying()) {
            player.start();
        }
    }
    
    public void stopMusic() 
    {   
    	if (player != null) { 
            player.stop();   
            try {                     
                player.prepare();   
            } catch (IOException ex) {   
                ex.printStackTrace();   
            }   
        }
    }
    
}
