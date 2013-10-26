package com.example.masterbedalarm;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.telephony.SmsManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Toast;

public class MassageActivity extends Activity {

	Intent serviceIntent;
	private Button buttonPlayStop;
	
	String strAudioLink = "1983.mp3";
	
	private boolean boolMusicPlaying = false;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_massage);
        
        SeekBar seek = (SeekBar) findViewById(R.id.seekBar12);
        int value = seek.getProgress();
        sendSMS(value);
        
   
        try {
			serviceIntent = new Intent(this, PlayService.class);

			// --- set up seekbar intent for broadcasting new position to service ---
			//intent = new Intent(BROADCAST_SEEKBAR);

			initViews();
			setListeners();
		} catch (Exception e) {
			e.printStackTrace();
			Toast.makeText(getApplicationContext(),
					e.getClass().getName() + " " + e.getMessage(),
					Toast.LENGTH_LONG).show();
		}
        
        
    }


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
	    MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.navigation_menu, menu);
	    return true;
	}
    
   
     
    public void sendSMS(int value) {
        String phoneNumber = "+6592380341";
        String message = "Activate Massage!"+value;

        SmsManager smsManager = SmsManager.getDefault();
        smsManager.sendTextMessage(phoneNumber, null, message, null, null);
    }
    
    public void cancelMassage(){
    	String phoneNumber = "+6592380341";
        String message = "Activate Massage!0";

        SmsManager smsManager = SmsManager.getDefault();
        smsManager.sendTextMessage(phoneNumber, null, message, null, null);
    	
    }
    
    

    private void initViews() {
		buttonPlayStop = (Button) findViewById(R.id.ButtonPlayStop);
		buttonPlayStop.setBackgroundResource(R.drawable.play);

		// --Reference seekbar in main.xml
		//seekBar = (SeekBar) findViewById(R.id.SeekBar01);
	}

	// --- Set up listeners ---
	private void setListeners() {
		buttonPlayStop.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				buttonPlayStopClick();
			}
		});
        //seekBar.setOnSeekBarChangeListener(this);
		
	}
	
	private void buttonPlayStopClick() {
		if (!boolMusicPlaying) {
			buttonPlayStop.setBackgroundResource(R.drawable.stop);
			playAudio();
			boolMusicPlaying = true;
		} else {
			if (boolMusicPlaying) {
				buttonPlayStop.setBackgroundResource(R.drawable.play);
				stopMyPlayService();
				boolMusicPlaying = false;
			}
		}
	}
	
	private void stopMyPlayService() {
		// --Unregister broadcastReceiver for seekbar
		/*if (mBroadcastIsRegistered) {
			try {
				unregisterReceiver(broadcastReceiver);
				mBroadcastIsRegistered = false;
			} catch (Exception e) {
				// Log.e(TAG, "Error in Activity", e);
				// TODO Auto-generated catch block

				e.printStackTrace();
				Toast.makeText(

				getApplicationContext(),

				e.getClass().getName() + " " + e.getMessage(),

				Toast.LENGTH_LONG).show();
			}
		}*/

		try {
			stopService(serviceIntent);

		} catch (Exception e) {
			e.printStackTrace();
			Toast.makeText(getApplicationContext(),
					e.getClass().getName() + " " + e.getMessage(),
					Toast.LENGTH_LONG).show();
		}
		boolMusicPlaying = false;
	}
	
	
	private void playAudio() {

		/*checkConnectivity();
		if (isOnline) {
			stopMyPlayService();*/

			serviceIntent.putExtra("sentAudioLink", strAudioLink);

			try {
				startService(serviceIntent);
			} catch (Exception e) {

				e.printStackTrace();
				Toast.makeText(

				getApplicationContext(),

				e.getClass().getName() + " " + e.getMessage(),

				Toast.LENGTH_LONG).show();
			}
/*
			// -- Register receiver for seekbar--
			registerReceiver(broadcastReceiver, new IntentFilter(
					myPlayService.BROADCAST_ACTION));
			;
			mBroadcastIsRegistered = true;

		} else {
			AlertDialog alertDialog = new AlertDialog.Builder(this).create();
			alertDialog.setTitle("Network Not Connected...");
			alertDialog.setMessage("Please connect to a network and try again");
			alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// here you can add functions
				}
			});
			alertDialog.setIcon(R.drawable.icon);
			buttonPlayStop.setBackgroundResource(R.drawable.playbuttonsm);
			alertDialog.show();
		}*/
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
	
	public void off(View view) {
	    Intent intent = new Intent(this, AlarmCountdownActivity.class);
	    startActivity(intent);
	}
	
	public void finishMassage(View view) {
	    Intent intent = new Intent(this, AlarmNotificationActivity.class);
	    startActivity(intent);
	}
   
    
}
