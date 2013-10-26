package com.example.masterbedalarm;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnBufferingUpdateListener;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnErrorListener;
import android.media.MediaPlayer.OnInfoListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.media.MediaPlayer.OnSeekCompleteListener;
import android.os.IBinder;
import android.widget.Toast;

public class PlayService extends Service implements OnCompletionListener,
OnPreparedListener, OnErrorListener, OnSeekCompleteListener,
OnInfoListener, OnBufferingUpdateListener {
	private String sntAudioLink;

	private MediaPlayer mediaPlayer = new MediaPlayer();
	
	@Override
	public void onCreate() {
		//Log.v(TAG, "Creating Service");
		// android.os.Debug.waitForDebugger();
		// Instantiate bufferIntent to communicate with Activity for progress
		// dialogue
		//bufferIntent = new Intent(BROADCAST_BUFFER);
		// ---Set up intent for seekbar broadcast ---
		//seekIntent = new Intent(BROADCAST_ACTION);
		mediaPlayer.setOnCompletionListener(this);
		mediaPlayer.setOnErrorListener(this);
		mediaPlayer.setOnPreparedListener(this);
		mediaPlayer.setOnBufferingUpdateListener(this);
		mediaPlayer.setOnSeekCompleteListener(this);
		mediaPlayer.setOnInfoListener(this);
		mediaPlayer.reset();

		// Register headset receiver
		//registerReceiver(headsetReceiver, new IntentFilter(
		//		Intent.ACTION_HEADSET_PLUG));

	}
	
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
/*
		// ---Set up receiver for seekbar change ---
		registerReceiver(broadcastReceiver, new IntentFilter(
				MyActivity.BROADCAST_SEEKBAR));

		// Manage incoming phone calls during playback. Pause mp on incoming,
		// resume on hangup.
		// -----------------------------------------------------------------------------------
		// Get the telephony manager
		Log.v(TAG, "Starting telephony");
		telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
		Log.v(TAG, "Starting listener");
		phoneStateListener = new PhoneStateListener() {
			@Override
			public void onCallStateChanged(int state, String incomingNumber) {
				// String stateString = "N/A";
				Log.v(TAG, "Starting CallStateChange");
				switch (state) {
				case TelephonyManager.CALL_STATE_OFFHOOK:
				case TelephonyManager.CALL_STATE_RINGING:
					if (mediaPlayer != null) {
						pauseMedia();
						isPausedInCall = true;
					}

					break;
				case TelephonyManager.CALL_STATE_IDLE:
					// Phone idle. Start playing.
					if (mediaPlayer != null) {
						if (isPausedInCall) {
							isPausedInCall = false;
							playMedia();
						}

					}
					break;
				}

			}
		};

		// Register the listener with the telephony manager
		telephonyManager.listen(phoneStateListener,
				PhoneStateListener.LISTEN_CALL_STATE);

		// Insert notification start
		initNotification();*/

		sntAudioLink = intent.getExtras().getString("sentAudioLink");
		mediaPlayer.reset();

		// Set up the MediaPlayer data source using the strAudioLink value
		if (!mediaPlayer.isPlaying()) {
			try {
				File path = android.os.Environment.getExternalStorageDirectory();
				
				
				mediaPlayer.setDataSource(path + "/music/1983.mp3");
				

				// Send message to Activity to display progress dialogue
				//sendBufferingBroadcast();
				// Prepare mediaplayer
				mediaPlayer.prepareAsync();

			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
			}
		}
		// --- Set up seekbar handler ---
		//setupHandler();

		return START_STICKY;
	}
	
	
	@Override
	public void onDestroy() {
		super.onDestroy();
		if (mediaPlayer != null) {
			if (mediaPlayer.isPlaying()) {
				mediaPlayer.stop();
			}
			mediaPlayer.release();
		}
/*
		if (phoneStateListener != null) {
			telephonyManager.listen(phoneStateListener,
					PhoneStateListener.LISTEN_NONE);
		}

		// Cancel the notification
		cancelNotification();

		// Unregister headsetReceiver
		unregisterReceiver(headsetReceiver);

		// Unregister seekbar receiver
		unregisterReceiver(broadcastReceiver);

		// Stop the seekbar handler from sending updates to UI
		handler.removeCallbacks(sendUpdatesToUI);

		// Service ends, need to tell activity to display "Play" button
		resetButtonPlayStopBroadcast();*/
	}
	
	@Override
	public void onBufferingUpdate(MediaPlayer arg0, int arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean onInfo(MediaPlayer arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void onSeekComplete(MediaPlayer arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean onError(MediaPlayer arg0, int what, int extra) {
		switch (what) {
		case MediaPlayer.MEDIA_ERROR_NOT_VALID_FOR_PROGRESSIVE_PLAYBACK:
			Toast.makeText(this,
					"MEDIA ERROR NOT VALID FOR PROGRESSIVE PLAYBACK " + extra,
					Toast.LENGTH_SHORT).show();
			break;
		case MediaPlayer.MEDIA_ERROR_SERVER_DIED:
			Toast.makeText(this, "MEDIA ERROR SERVER DIED " + extra,
					Toast.LENGTH_SHORT).show();
			break;
		case MediaPlayer.MEDIA_ERROR_UNKNOWN:
			Toast.makeText(this, "MEDIA ERROR UNKNOWN " + extra,
					Toast.LENGTH_SHORT).show();
			break;
		}
		return false;
	}

	@Override
	public void onPrepared(MediaPlayer mp) {
		// TODO Auto-generated method stub
		
		playMedia();
	}

	@Override
	public void onCompletion(MediaPlayer arg0) {
		// TODO Auto-generated method stub
		stopMedia();
		stopSelf();
	}

	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void playMedia() {
		if (!mediaPlayer.isPlaying()) {
			mediaPlayer.start();
		}
	}

	// Add for Telephony Manager
	public void pauseMedia() {
		// Log.v(TAG, "Pause Media");
		if (mediaPlayer.isPlaying()) {
			mediaPlayer.pause();
		}

	}

	public void stopMedia() {
		if (mediaPlayer.isPlaying()) {
			mediaPlayer.stop();
		}
	}

}
