package com.scavengerhunt;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.ExpandableListView;
import java.util.ArrayList;
import android.os.Handler;
import android.os.Message;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.Toast;


public class OwnedHuntActivity extends Activity{

	private ExpandableListView mExpandableList;
	private ExpandableListAdapter adapter;
	private ArrayList<ExpandListGroup> groups;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_owned_hunt);
		mExpandableList = (ExpandableListView) findViewById(R.id.expandableListView1);
		groups = SetStandardGroups();
		adapter = new ExpandListAdapter(OwnedHuntActivity.this, groups);
		mExpandableList.setAdapter(adapter);

		
		mExpandableList.setOnChildClickListener(new OnChildClickListener() {

			@Override
			public boolean onChildClick(ExpandableListView arg0, View arg1, int arg2, int arg3, long arg4)
			{
				Toast.makeText(getBaseContext(), "Child clicked", Toast.LENGTH_LONG).show();
				return false;
			}
		});

		mExpandableList.setOnGroupClickListener(new OnGroupClickListener()
		{
			@Override
			public boolean onGroupClick(ExpandableListView parent, View v,
					int groupPosition, long id) {
				Toast.makeText(getBaseContext(), "Group clicked", Toast.LENGTH_LONG).show();
				return false;
			}
		});
	}

	public ArrayList<ExpandListGroup> SetStandardGroups() {
		ArrayList<ExpandListGroup> list = new ArrayList<ExpandListGroup>();
		//ArrayList<ExpandListChild> list2 = new ArrayList<ExpandListChild>();
		ExpandListGroup gru1 = new ExpandListGroup();
		gru1.setTitle("Objectives");
		ExpandListGroup gru2 = new ExpandListGroup();
		gru2.setTitle("Participants");
		list.add(gru1);
		list.add(gru2);
		return list;
	}
}


