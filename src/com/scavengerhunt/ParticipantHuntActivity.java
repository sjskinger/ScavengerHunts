package com.scavengerhunt;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class ParticipantHuntActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_participant_hunt);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_participant_hunt, menu);
		return true;
	}

}
