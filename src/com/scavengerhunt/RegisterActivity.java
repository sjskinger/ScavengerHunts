package com.scavengerhunt;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class RegisterActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);
		Button toLoginScreen = (Button) findViewById(R.id.btnRegister);
        toLoginScreen.setOnClickListener(new View.OnClickListener() {
             public void onClick(View arg0) {
                //add code here to check if info is valid before finishing
            	 Toast.makeText(RegisterActivity.this, "You are now registered!",Toast.LENGTH_LONG).show();
            	 
            	 finish();
            }
        });
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_register, menu);
		return true;
	}

}
