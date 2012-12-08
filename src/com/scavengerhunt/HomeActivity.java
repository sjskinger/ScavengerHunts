package com.scavengerhunt;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;

public class HomeActivity extends Activity {
	private ExpandableListView mExpandableList;
	private ExpandableListAdapter adapter;
	private ArrayList<Group> groups;
	Button huntsIOwn;
	Button huntsImIn;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		//Got the user info
		Intent lastIntent = getIntent();
		String userInfo = (String) lastIntent.getExtras().get("userInfo");
		huntsIOwn=(Button)this.findViewById(R.id.buttonHuntsIOwn);
		huntsImIn=(Button)this.findViewById(R.id.buttonHuntsImIn);
		TextView welcome = (TextView)this.findViewById(R.id.welcomeString);
		mExpandableList = (ExpandableListView)findViewById(R.id.expandableListView);
		groups = setGroups();
		adapter = new InExpandListAdapter(this, groups);
		mExpandableList.setAdapter(adapter);

		char[] name = ("Welcome, " + userInfo.substring(userInfo.lastIndexOf(':')+1)).toCharArray();
		welcome.setTextColor(Color.BLUE);
		welcome.setText(name, 0, name.length);

		huntsIOwn.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent gotoHuntsIOwn = new Intent(getApplicationContext(), OwnedHuntActivity.class);
				startActivity(gotoHuntsIOwn);
			}
		});     
		huntsImIn.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent gotoHuntsImIn = new Intent(getApplicationContext(), InHuntActivity.class);
				startActivity(gotoHuntsImIn);
			}
		});     



		mExpandableList.setOnChildClickListener(new OnChildClickListener() {

			@Override
			public boolean onChildClick(ExpandableListView arg0, View arg1, int arg2, int arg3, long arg4)
			{
				return false;
			}
		});

		mExpandableList.setOnGroupClickListener(new OnGroupClickListener()
		{
			@Override
			public boolean onGroupClick(ExpandableListView parent, View v,
					int groupPosition, long id) {
				return false;
			}
		});
	}

	public ArrayList<Group> setGroups() {
		ArrayList<Group> groupList = new ArrayList<Group>();
		ArrayList<Child> childList = new ArrayList<Child>();

		Group gru1 = new Group();
		gru1.setTitle("Hunts I'm In");
		Child ch1_1 = new Child("CMU Search", "1");
		Child ch1_2 = new Child("Name that Professor", "2");
		childList.add(ch1_1);
		childList.add(ch1_2);
		gru1.setArrayChildren(childList);


		Group gru2 = new Group();
		childList = new ArrayList<Child>();
		gru2.setTitle("Hunts I Own");
		Child ch2_1 = new Child("Squirrel Hill", "1");
		Child ch2_2 = new Child("Craig Street", "2");
		Child ch2_3 = new Child("Hunt Library", "3");
		Child ch2_4 = new Child("ECE Day", "4");
		Child ch2_5 = new Child("Spring Carnival", "5");
		childList.add(ch2_1);
		childList.add(ch2_2);
		childList.add(ch2_3);
		childList.add(ch2_4);
		childList.add(ch2_5);
		gru2.setArrayChildren(childList);

		groupList.add(gru1);
		groupList.add(gru2);
		return groupList;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_home, menu);
		return true;
	}

}
