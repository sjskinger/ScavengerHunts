package com.scavengerhunt;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.view.Menu;
import android.widget.TextView;

public class HomeActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		//Got the user info
		Intent lastIntent = getIntent();
		String userInfo = (String) lastIntent.getExtras().get("userInfo");
		
		TextView welcome = (TextView)this.findViewById(R.id.welcomeString);
		
		char[] name = ("Welcome, " + userInfo.substring(userInfo.lastIndexOf(':')+1)).toCharArray();
		welcome.setTextColor(Color.BLUE);
		welcome.setText(name, 0, name.length);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_home, menu);
		return true;
	}

}
