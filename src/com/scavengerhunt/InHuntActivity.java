package com.scavengerhunt;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;

public class InHuntActivity extends Activity {

	private ExpandableListView mExpandableList;
	private ExpandableListAdapter adapter;
	private ArrayList<Group> groups;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_in_hunt);

		mExpandableList = (ExpandableListView)findViewById(R.id.expandableListView);
		groups = setGroups();
		adapter = new InExpandListAdapter(this, groups);
		mExpandableList.setAdapter(adapter);

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
		return groupList;
		*/
		return null;
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_participant_hunt, menu);
		return true;
	}

}
