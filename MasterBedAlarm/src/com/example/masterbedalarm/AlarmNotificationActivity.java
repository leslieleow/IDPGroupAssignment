package com.example.masterbedalarm;

import java.io.IOException;
import java.util.Calendar;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

public class AlarmNotificationActivity extends Activity {
	private MediaPlayer player;
	private Switch mcancelButton;
	private Activity mActivity;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);		
		setContentView(R.layout.activity_alarm_notification);
		mActivity = this;
		playMusic();
		
		mcancelButton = (Switch)this.findViewById(R.id.switch1);
		mcancelButton.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if(!isChecked)
				{
//					Intent intent = new Intent(AlarmAlert.this, AlarmReceiver.class);
//		            intent.putExtra("music", false);
//		            PendingIntent pendingIntent = PendingIntent.getBroadcast(AlarmAlert.this, 0, intent, 0);
//		            AlarmManager am;
//		            am = (AlarmManager)getSystemService(ALARM_SERVICE);		            
//		            //cancel
//		            am.cancel(pendingIntent);	
					stopMusic();
		            Intent nextintent = new Intent(mActivity, GoodMorningActivity.class);
			    	startActivity(nextintent);                   
                    finish();
				}
			}
		});
	}
	
	
	
    @Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
    	stopMusic();
		super.onDestroy();
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

	public void ToMassage(View view)
	{
		Intent intent = new Intent(this,MassageActivity.class);
        startActivity(intent);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
	    MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.navigation_menu, menu);
	    return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle presses on the action bar items
	    switch (item.getItemId()) {
	        case R.id.settings:
	            openSettings();
	            return true;
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}
	
	public void openSettings() {
	    Intent intent = new Intent(this, SettingsActivity.class);
	    startActivity(intent);		
	}
	
	public void offAlarm(View view) {
		Intent intent = new Intent(this, GoodMorningActivity.class);
		startActivity(intent);
	}
	
	public void neverOffAlarm(View view) {
		Intent intent = new Intent(this, AntiSnoozeActivity.class);
		startActivity(intent);
	}

}



