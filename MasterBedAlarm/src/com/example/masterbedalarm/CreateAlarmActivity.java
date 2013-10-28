package com.example.masterbedalarm;

import java.util.Calendar;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.preference.Preference;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TimePicker;

public class CreateAlarmActivity extends Activity {

	//private Button msetButton;
	private Calendar calendar;
	private TimePicker timepicker;
	private Activity mActivity;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);		
		setContentView(R.layout.activity_create_alarm);
		mActivity = this;
		
		calendar = Calendar.getInstance();
    	calendar.setTimeInMillis(System.currentTimeMillis());
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);                              
        
        new TimePickerDialog(CreateAlarmActivity.this, new TimePickerDialog.OnTimeSetListener() {
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                calendar.setTimeInMillis(System.currentTimeMillis());
                //set(f, value) changes field f to value.
                calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                calendar.set(Calendar.MINUTE, minute);
                calendar.set(Calendar.SECOND, 0);
                calendar.set(Calendar.MILLISECOND, 0);
                  
                Intent resultIntent = new Intent("action_add_alarm");
                resultIntent.putExtra("add_alarm", hourOfDay + ":" + minute);
                resultIntent.putExtra("alarmTime", calendar.getTimeInMillis());
                mActivity.setResult(RESULT_OK, resultIntent);
                finish();
            }
        },hour,minute,true).show();
		
	}
	/*@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		//timepicker = (TimePicker)this.findViewById(R.id.timePicker1);
		calendar = Calendar.getInstance();
				
		msetButton = (Button)this.findViewById(R.id.button1);
		msetButton.setOnClickListener(new OnClickListener() {            
            @Override
            public void onClick(View v) {               
            	calendar.setTimeInMillis(System.currentTimeMillis());
                int hour = calendar.get(Calendar.HOUR_OF_DAY);
                int minute = calendar.get(Calendar.MINUTE);                              
                
                new TimePickerDialog(CreateAlarmActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        calendar.setTimeInMillis(System.currentTimeMillis());
                        //set(f, value) changes field f to value.
                        calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                        calendar.set(Calendar.MINUTE, minute);
                        calendar.set(Calendar.SECOND, 0);
                        calendar.set(Calendar.MILLISECOND, 0);
                          
                        Intent resultIntent = new Intent("action_add_alarm");
                        resultIntent.putExtra("add_alarm", hourOfDay + ":" + minute);
                        resultIntent.putExtra("alarmTime", calendar.getTimeInMillis());
                        mActivity.setResult(RESULT_OK, resultIntent);
                        finish();
                    }
                },hour,minute,true).show();
            }
        });
		
	}*/
	
	
	
	
}
