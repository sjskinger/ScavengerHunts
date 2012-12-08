package com.scavengerhunt;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import lib.FileHandler;
import lib.ObjectHandler;
import model.Hunt;
import model.User;
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
import android.widget.Toast;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;

public class HomeActivity extends Activity {
	private ExpandableListView mExpandableList;
	private ExpandableListAdapter adapter;
	private ArrayList<Group> groups;
	ObjectHandler handler;
	User user;

	Button huntsIOwn;
	Button huntsImIn;
	Button createAHunt;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);

		Intent lastIntent = getIntent();
		user = (User) lastIntent.getSerializableExtra("user");
		handler = (ObjectHandler) lastIntent.getSerializableExtra("handler");

		TextView welcome = (TextView)this.findViewById(R.id.welcomeString);
		welcome.setTextColor(Color.BLUE);
		welcome.setText("Welcome, "+user.getName());
		
		huntsIOwn=(Button)this.findViewById(R.id.buttonHuntsIOwn);
		huntsImIn=(Button)this.findViewById(R.id.buttonHuntsImIn);
		createAHunt=(Button)this.findViewById(R.id.create_hunt);

		mExpandableList = (ExpandableListView)findViewById(R.id.expandableListView);
		groups = setGroups();
		adapter = new InExpandListAdapter(this, groups);
		mExpandableList.setAdapter(adapter);


		createAHunt.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent create = new Intent(getApplicationContext(), OwnedHuntActivity.class);
				startActivity(create);
			}
		});  

		/* 		
   		huntsImIn.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent gotoHuntsImIn = new Intent(getApplicationContext(), InHuntActivity.class);
				startActivity(gotoHuntsImIn);
			}
		});     
		 */


		mExpandableList.setOnChildClickListener(new OnChildClickListener() {
			@Override
			public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id)
			{
				//mExpandableList.get(groupPosition).get(childPosition)
				//Hunt obj = (Hunt) parent.getSelectedItem();
				Intent gotoHunt = new Intent(getApplicationContext(), OwnedHuntActivity.class);
				startActivity(gotoHunt);
				return true;
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
		ArrayList<Child> ownChildList = new ArrayList<Child>();
		ArrayList<Child> inChildList = new ArrayList<Child>();

		Group huntsImIn = new Group();
		huntsImIn.setTitle("Hunts I'm In");
		Group huntsIOwn = new Group();
		huntsIOwn.setTitle("Hunts I Own");

		int [] hunts = user.getHuntsId();

		try{
			for(int id : hunts){
				Hunt h = (Hunt) handler.readObject("string", id);				
				Child ch = new Child(h.getName(), h.getId());
				if(user.getId() == h.getHostId()) ownChildList.add(ch);
				else inChildList.add(ch);
			}
		} catch(NullPointerException e) {
			Toast.makeText(HomeActivity.this, "An error has occured. Try again.",Toast.LENGTH_LONG).show();
			return null;
		} 

		huntsImIn.setArrayChildren(inChildList);
		huntsIOwn.setArrayChildren(ownChildList);
		groupList.add(huntsImIn);
		groupList.add(huntsIOwn);
		return groupList;
	}

}
