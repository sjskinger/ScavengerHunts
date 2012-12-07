package com.scavengerhunt;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class AcctInfoActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_acct_info);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_acct_info, menu);
		return true;
	}

}
