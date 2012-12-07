package com.scavengerhunt;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import lib.FileHandler;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends Activity {
	EditText fullnamebox;
	EditText emailbox;
	EditText passwordbox;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);
		
		fullnamebox = (EditText)this.findViewById(R.id.fullNameBox);
		emailbox = (EditText)this.findViewById(R.id.reg_emailBox);
		passwordbox = (EditText)this.findViewById(R.id.reg_passwordBox);
		
		Button toLoginScreen = (Button) findViewById(R.id.btnRegister);
        toLoginScreen.setOnClickListener(new View.OnClickListener() {
             public void onClick(View arg0) {
                //validating..
            	 String fullname = fullnamebox.getText().toString();
            	 String email = emailbox.getText().toString();
            	 String password = passwordbox.getText().toString();
            	 if(email == null || email.length() == 0 || email.matches("/^[_a-z0-9-+]+(\\.[_a-z0-9-]+)*@[a-z0-9-]+(\\.[a-z0-9-]+)*(\\.[a-z]{2,4})$/")) {
            		 Toast.makeText(RegisterActivity.this, "Valid Email required!",Toast.LENGTH_LONG).show();
            		 return;
            	 }
            	if(fullname == null || fullname.length() == 0) {
            		Toast.makeText(RegisterActivity.this, "Full Name required!",Toast.LENGTH_LONG).show();
            		return;
            	}
            	if(password == null || password.length() < 4) {
            		 Toast.makeText(RegisterActivity.this, "Password must be atleast 4 characters!",Toast.LENGTH_LONG).show();
            		 return;
            	 }
            	 
            	 String reg_string = email+":"+password+":"+fullname+"\n";
            	 
            	 try {
            		 
            	 FileOutputStream fos = openFileOutput(MainActivity.FILENAME, MODE_APPEND);
				 FileHandler.writeFile(fos, reg_string );
            	 
            	 } catch(FileNotFoundException e) {
            		 Toast.makeText(RegisterActivity.this, "An error has occured. Try again.",Toast.LENGTH_LONG).show();
            		 return;
            	 } catch(IOException e) {
					 Toast.makeText(RegisterActivity.this, "Could not register! Try again!",Toast.LENGTH_LONG).show();
					 return;
				 }
            	 
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
