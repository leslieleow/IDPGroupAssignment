package com.example.masterbedalarm;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends Activity {
	public final static String EXTRA_MESSAGE = "com.example.masterbedalarm.NAME";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
	    MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.navigation_menu, menu);
	    return true;
	}
	
	/** Called when the user clicks the Send button */
	public void sendName(View view) {
	    Intent intent = new Intent(this, WelcomePageActivity.class);
	    EditText editText = (EditText) findViewById(R.id.editText1);
	    String name = editText.getText().toString();
	    intent.putExtra(EXTRA_MESSAGE, name);
	    startActivity(intent);
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

}
