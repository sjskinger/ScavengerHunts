package com.scavengerhunt;

import model.User;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.widget.TextView;

public class AcctInfoActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_acct_info);
		Intent i = getIntent();
		User u = (User) i.getSerializableExtra("userInfo");
		TextView name = (TextView) findViewById(R.id.textViewName);
		TextView email = (TextView) findViewById(R.id.textViewEmail);
		TextView password = (TextView) findViewById(R.id.textViewPassword);
		name.setText("Full Name: " + u.getName());
		email.setText("Full Name: " + u.getEmail());
		password.setText("Full Name: " + u.getPassword());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_acct_info, menu);
		return true;
	}

}
