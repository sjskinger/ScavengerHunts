package com.scavengerhunt;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ExpandableListView;
import java.util.ArrayList;
import android.os.CountDownTimer;

import android.widget.Button;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.TextView;
import android.widget.Toast;


public class OwnedHuntActivity extends Activity{

	private ExpandableListView mExpandableList;
	private ExpandableListAdapter adapter;
	private ArrayList<Group> groups;
	TextView timer;
	Button createObjective;
	Button startHunt;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_owned_hunt);

		createObjective=(Button)this.findViewById(R.id.add_objective_button);
		startHunt=(Button)this.findViewById(R.id.start_hunt_button);
		timer=(TextView)this.findViewById(R.id.time_remaining_text);
		mExpandableList = (ExpandableListView)findViewById(R.id.expandableListView);
		groups = setGroups();
		adapter = new OwnedExpandListAdapter(this, groups);
		mExpandableList.setAdapter(adapter);

		startHunt.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Toast mess = Toast.makeText(OwnedHuntActivity.this, "Let the Hunt Begin!", Toast.LENGTH_SHORT);
				mess.show();
				new CountDownTimer(30000, 1000) {
					public void onTick(long millisUntilFinished) {
						timer.setText("seconds remaining: " + millisUntilFinished / 1000);
					}
					public void onFinish() {
						timer.setText("Time is up!");
					}
				}.start();
			}
		});  

		createObjective.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent create = new Intent(getApplicationContext(), CreateObjectiveActivity.class);
				startActivity(create);
			}
		});    

		mExpandableList.setOnChildClickListener(new OnChildClickListener() {
			@Override
			public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id)
			{
				return false;
			}
		});

		mExpandableList.setOnGroupClickListener(new OnGroupClickListener() {
			@Override
			public boolean onGroupClick(ExpandableListView parent, View v,
					int groupPosition, long id) {
				return false;
			}
		});
	}

	public ArrayList<Group> setGroups() {
		/*ArrayList<Group> groupList = new ArrayList<Group>();
		ArrayList<Child> childList = new ArrayList<Child>();

		Group gru1 = new Group();
		gru1.setTitle("Objectives");
		Child ch1_1 = new Child("Scottie Dog", "1");
		Child ch1_2 = new Child("Helicopter", "2");
		childList.add(ch1_1);
		childList.add(ch1_2);
		gru1.setArrayChildren(childList);


		Group gru2 = new Group();
		childList = new ArrayList<Child>();
		gru2.setTitle("Participants");
		Child ch2_1 = new Child("Joe Buddy", "1");
		Child ch2_2 = new Child("Sarah Smith", "2");
		Child ch2_3 = new Child("Bob Singh", "3");
		Child ch2_4 = new Child("Kelly Jones", "4");
		Child ch2_5 = new Child("Jim Bob", "5");
		childList.add(ch2_1);
		childList.add(ch2_2);
		childList.add(ch2_3);
		childList.add(ch2_4);
		childList.add(ch2_5);
		gru2.setArrayChildren(childList);

		groupList.add(gru1);
		groupList.add(gru2);
		return groupList;*/
		return null;
	}
}


