package com.example.masterbedalarm;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.support.v4.app.NavUtils;
import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import java.util.Calendar;
import java.util.ArrayList;

//import com.htc.widget.HtcCheckBox;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.app.Activity;
import android.app.AlarmManager;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class AlarmListActivity extends Activity {

	private final static int LAUNCH_SET_ALARM = 0;
	
	
	private ListView mListView;
	private ArrayList<AlarmItem> mAlarmList;
	private AlarmClockAdapter mAlarmClockAdapter;
	private Activity mActivity;
	private long mAlarmTime;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_alarm_list);
		mActivity = this;
		initList();
	}
	
			
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		Log.d("Ray", "onResume");
		updateList();
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
	
	public void set(View view) {
	    Intent intent = new Intent(this, AlarmCountdownActivity.class);
	    startActivity(intent);			
	}

	
	private void initList() {
		mListView = (ListView) this.findViewById(R.id.alarm_list);
		mAlarmList = new ArrayList<AlarmItem>();
		
		AlarmItem item = new AlarmItem();
		item.time_stamp = "00:00";
		item.alarmTime = 0;
		mAlarmList.add(item);
		
		mAlarmClockAdapter = new AlarmClockAdapter(mAlarmList);
		mListView.setAdapter(mAlarmClockAdapter);
	}
	
	private void updateList() {
		if(mAlarmClockAdapter != null) {
			Log.d("Ray", "updateList");
			mAlarmClockAdapter.changeList((ArrayList<AlarmItem>) mAlarmList.clone());
			mAlarmClockAdapter.notifyDataSetChanged();			
		}
	}
	
	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		
		if(item.getItemId() == R.id.action_add_alarm) {
	    	Intent intent = new Intent(this, CreateAlarm.class);
	    	startActivityForResult(intent, LAUNCH_SET_ALARM);
		}
		return super.onMenuItemSelected(featureId, item);
	}

	/** Called when the user clicks + */
    public void add(View view) {
        // Do something in response to button

    }

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		Log.d("Ray", "onActivityResult");
		if(data.getAction().equals("action_add_alarm") && resultCode == RESULT_OK) {
			
			Log.d("Ray", "onActivityResult");
			AlarmItem item = new AlarmItem();
			item.time_stamp = data.getStringExtra("add_alarm");
			item.alarmTime = data.getLongExtra("alarmTime", 0);
			ArrayList<AlarmItem> temp = (ArrayList<AlarmItem>) mAlarmList.clone();
			temp.add(item);
			mAlarmList.clear();
			mAlarmList.addAll(temp);
			Log.d("Ray", "AlarmList size = " + mAlarmList.size());
		}
	}
    
    class AlarmClockAdapter extends BaseAdapter {
        protected ArrayList<AlarmItem> mItems = null;
        protected LayoutInflater mInflater;
        protected View mLayout;

        public AlarmClockAdapter(ArrayList<AlarmItem> list) {
            if (list != null) {
                mItems = new ArrayList<AlarmItem>(list);
            }
            mInflater = (LayoutInflater) mActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        public void changeList(ArrayList<AlarmItem> list) {
            if (list != null) {
                mItems = new ArrayList<AlarmItem>(list);
            }
        }

        public void addItem(int position, AlarmItem data) {
            mItems.add(position, data);
        }

        public void removeItem(int position) {
            mItems.remove(position);
        }

        @Override
        public int getCount() {
            if (mItems == null) {
                return 0;
            }
            return mItems.size();
        }

        @Override
        public Object getItem(int position) {
            return mItems.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
        	Log.d("Ray", "getView");
            if (convertView != null) {
                mLayout = convertView;
            } else {
                mLayout = mInflater.inflate(R.layout.alarm_item, null);
            }
        	
            AlarmItem item = mItems.get(position);
            mAlarmTime = item.alarmTime;
            TextView timeStamp = (TextView) mLayout.findViewById(R.id.time_stamp);
            timeStamp.setText(item.time_stamp);
            
            Switch btn = (Switch) mLayout.findViewById(R.id.togglebutton);
            btn.setOnCheckedChangeListener(new ToggleButton.OnCheckedChangeListener() { 
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        Log.d("tglbtn", "This checkbox is: checked");
                        Intent intent = new Intent(mActivity, AlarmCountdownActivity.class);
                        intent.putExtra("alarmTime", mAlarmTime);
                        startActivity(intent);
                    } else {
                        Log.d("tglbtn", "This checkbox is: unchecked");
                    }
                }
            });

            return mLayout;
        }
    }


}









