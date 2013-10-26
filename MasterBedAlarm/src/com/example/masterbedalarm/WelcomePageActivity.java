package com.example.masterbedalarm;

import android.os.Bundle;
import android.app.Activity;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.support.v4.app.NavUtils;
import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;

public class WelcomePageActivity extends Activity {


	@Override
	protected void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);

	    setContentView(R.layout.activity_welcome_page);
	    
	    RelativeLayout rlayout = (RelativeLayout) findViewById(R.id.mainlayout);
	    
	    // Get the message from the intent
	    Intent intent = getIntent();
	    String name = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

	    // Create the text view
	    TextView textView = new TextView(this);
	    textView.setTextSize(40);
	    textView.setGravity(Gravity.CENTER);
	    textView.setText("Hello " + name + "!");
	    
	    rlayout.addView(textView);

	    // Set the text view as the activity layout
	    

		
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
	
	/** Called when the user clicks anywhere along the screen */
	public void onClick(View view) {
	    Intent intent = new Intent(this, AlarmListActivity.class);
	    startActivity(intent);
	}

}
