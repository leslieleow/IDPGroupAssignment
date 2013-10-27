package com.example.masterbedalarm;

import java.util.Calendar;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Switch;
import android.widget.TextView;

public class AlarmCountdownActivity extends Activity {

	private Switch mcancelButton;
	private TextView mTextView;	
	private long mAlarmTime;
	private long hours, minius, seconds;
	private Calendar calendar;
	private ProgressBar pb;
	private PendingIntent pendingIntent;	
	private AlarmManager am;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);		
		setContentView(R.layout.activity_alarm_countdown);	
	}
	
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		
		Intent classintent = getIntent();
		mAlarmTime = classintent.getLongExtra("alarmTime", 0);
		mTextView = (TextView)this.findViewById(R.id.textView1);
		pb = (ProgressBar)this.findViewById(R.id.progressBar1);
		
		mTextView.setText("seconds remaining: 0");
		if(mAlarmTime != 0 && mAlarmTime > System.currentTimeMillis())
		{
			//Set up alarm
            Intent intent = new Intent(AlarmCountdownActivity.this, AlarmReceiver.class);
            intent.putExtra("music", true);
            pendingIntent = PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
			am = (AlarmManager)getSystemService(ALARM_SERVICE);
			am.set(AlarmManager.RTC_WAKEUP, mAlarmTime, pendingIntent); 
			
			new CountDownTimer(mAlarmTime - System.currentTimeMillis(), 1000) {
				 public void onTick(long millisUntilFinished) {
					 long remainMillis = mAlarmTime - System.currentTimeMillis();					
					 hours = (remainMillis/1000)/60/60;					
					 minius = (remainMillis/1000)/60;
					 seconds = (remainMillis/1000) % 60;
					 mTextView.setText(hours + " hours " + minius + " minutes "+ seconds + " seconds\r\nREMAINGING...");					 
				 }
	
				 public void onFinish() {
					 mTextView.setText("Alarm!");
					 //Cam 似乎單次的不用Cancel
					 am.cancel(pendingIntent);
					 finish();
				 }
			}.start();
		}
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
	
	public void finishCountdown(View view) {
		Intent intent = new Intent(this, MassageActivity.class);
		startActivity(intent);
	}
	
	public void finishCountdownSecond(View view) {
		Intent intent = new Intent(this, AlarmNotificationActivity.class);
		startActivity(intent);
	}



}
