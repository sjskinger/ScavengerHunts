package com.scavengerhunt;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	EditText txtUserName;
	EditText txtPassword;
	TextView registerLink;
	Button btnLogin;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		txtUserName=(EditText)this.findViewById(R.id.emailBox);
		txtPassword=(EditText)this.findViewById(R.id.passwordBox);
		btnLogin=(Button)this.findViewById(R.id.btnLogin);
		registerLink = (TextView)this.findViewById(R.id.link_to_register);
		
		btnLogin.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				if((txtUserName.getText().toString()).equals(txtPassword.getText().toString())){
					Toast.makeText(MainActivity.this, "Login Successful",Toast.LENGTH_LONG).show();
					Intent home = new Intent(getApplicationContext(), HomeActivity.class);
					startActivity(home);
				} else{
					Toast.makeText(MainActivity.this, "Invalid Login",Toast.LENGTH_LONG).show();
				}

			}
		});     

		registerLink.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Intent register = new Intent(getApplicationContext(), RegisterActivity.class);
				startActivity(register);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

}
