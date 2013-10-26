package com.example.masterbedalarm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.os.Bundle;
import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.Toast;

public class GoodMorningActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_good_morning);
		
		final ImageButton btnOpenPopup = (ImageButton)findViewById(R.id.openpopup);
        btnOpenPopup.setOnClickListener(new ImageButton.OnClickListener(){
        	
        	@Override
        	public void onClick(View arg0) {
        		ArrayList<String> applicationNames = new ArrayList<String>();        		
        		List<PackageInfo> packages = getPackageManager().getInstalledPackages(0);
        		
        		if (packages != null){
        			for (PackageInfo p : packages){
        				applicationNames.add(getPackageManager().getApplicationLabel(p.applicationInfo).toString());
        			}
        		}
        		ArrayAdapter<String> aa = new ArrayAdapter<String>(GoodMorningActivity.this,android.R.layout.simple_list_item_1,applicationNames);
        		
        		LayoutInflater layoutInflater = (LayoutInflater)getBaseContext().getSystemService(LAYOUT_INFLATER_SERVICE);  
        	    View popupView = layoutInflater.inflate(R.layout.test, null);  
        	    final PopupWindow popupWindow = new PopupWindow(popupView,LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
                
        	    final ListView lv = (ListView)popupView.findViewById(R.id.applicationList);
        	    lv.setAdapter(aa);
        	            	    
        	    lv.setOnItemClickListener(new ListView.OnItemClickListener() {
        	    	
        	    	@Override      	    	
        	    	public void onItemClick(AdapterView<?> myAdapter, View myView, int myItemInt, long mylng) {
        	          //String selectedFromList =(String) (lv.getItemAtPosition(myItemInt));
        	          Toast.makeText(getApplicationContext(), "hello", 
        	        		   Toast.LENGTH_LONG).show();
        	          popupWindow.dismiss();
        	          
        	          /*
			        List<PackageInfo> packages = getPackageManager().getInstalledPackages(0);
			        for (PackageInfo pi : packages){
			      		if (getPackageManager().getApplicationLabel(p.applicationInfo).toString().equalsIgnoreCase(selectedFromList){
			      	
							ImageView imageView = new ImageView(this);
							imageView.setImageDrawable(pi.applicationInfo.loadIcon(getPackageManager()));
							
							RelativeLayout rl = (RelativeLayout) findViewById(R.id.relativeLayout2);
							RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(
							    RelativeLayout.LayoutParams.WRAP_CONTENT,
							    RelativeLayout.LayoutParams.WRAP_CONTENT);
							lp.addRule(RelativeLayout.BELOW, R.id.analogClock1);
							lp.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
							rl.addView(imageView, lp);
							
							imageView.setOnClickListener(new ImageView.OnClickListener() {
			    			@Override
			    			public void onClick(View arg0) {
			    				// TODO Auto-generated method stub
			    				List<PackageInfo> packages = getPackageManager().getInstalledPackages(0);
			    				startActivity(getPackageManager().getLaunchIntentForPackage(packages.get(0).applicationInfo.packageName));  
			    			}
			    		});
						}
					}
        	           */
        	          
        	          
        	        }                 
        	  });
        	    
                Button btnDismiss = (Button)popupView.findViewById(R.id.dismiss);
                btnDismiss.setOnClickListener(new Button.OnClickListener(){

		        @Override
		        public void onClick(View v) {
		         // TODO Auto-generated method stub
		        	
	        	List<PackageInfo> packages = getPackageManager().getInstalledPackages(0);
	    		ImageView imageView = new ImageView(GoodMorningActivity.this);
	    		imageView.setImageDrawable(packages.get(0).applicationInfo.loadIcon(getPackageManager()));
	    		
	    		RelativeLayout rl = (RelativeLayout) findViewById(R.id.relativeLayout2);
	    		RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(
	    		    RelativeLayout.LayoutParams.WRAP_CONTENT,
	    		    RelativeLayout.LayoutParams.WRAP_CONTENT);
	    		lp.addRule(RelativeLayout.BELOW, R.id.analogClock1);
	    		lp.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
	    		rl.addView(imageView, lp);
	    		
	    		imageView.setOnClickListener(new ImageView.OnClickListener() {
	    			@Override
	    			public void onClick(View arg0) {
	    				// TODO Auto-generated method stub
	    				List<PackageInfo> packages = getPackageManager().getInstalledPackages(0);
	    				startActivity(getPackageManager().getLaunchIntentForPackage(packages.get(0).applicationInfo.packageName));  
	    			}
	    		});
		        	
		         popupWindow.dismiss();
		        }});
		        
		                  
		         popupWindow.showAsDropDown(btnOpenPopup, 200, 170);
		         
		            
		      }});
		      
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
	

}
