package com.scavengerhunt;

import adapters.OwnedExpandListAdapter;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.text.format.Time;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ExpandableListView;
import java.util.ArrayList;

import lib.ObjectHandler;
import model.Child;
import model.Group;
import model.Hunt;
import model.User;
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
	ObjectHandler handler;
	User user;
	Time time;
	int huntID;
	private Hunt hunt;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_owned_hunt);

		try{
			Intent lastIntent = getIntent();
			user = (User) lastIntent.getSerializableExtra("userInfo");
			handler = (ObjectHandler) lastIntent.getSerializableExtra("objectHandler");
			huntID = lastIntent.getIntExtra("huntID", 0);

		}catch (NullPointerException e){
			return;
		}
		
		if (huntID == -1) {
			Hunt hunt = new Hunt();
		}
		else hunt = (Hunt) handler.readObject("hunt", huntID);	
		
		createObjective=(Button)this.findViewById(R.id.add_objective_button);
		startHunt=(Button)this.findViewById(R.id.start_hunt_button);
		timer=(TextView)this.findViewById(R.id.time_remaining_text);
		mExpandableList = (ExpandableListView)findViewById(R.id.expandableListView);
		groups = setGroups();
		adapter = new OwnedExpandListAdapter(this, groups);
		mExpandableList.setAdapter(adapter);

		startHunt.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				time = new Time(Time.getCurrentTimezone());
				time.setToNow();
				
				if ((hunt.getObjectiveId().length < 1)||(hunt.getParticipantId().length < 2)){
					Toast.makeText(OwnedHuntActivity.this, "A hunt must have at least one objective and two participants!", Toast.LENGTH_SHORT).show();
					return;
				}
				if (hunt.getTimeStarted().before(time)){
					Toast.makeText(OwnedHuntActivity.this, "This hunt has already started!", Toast.LENGTH_SHORT).show();
					return;
				}
				
				Toast.makeText(OwnedHuntActivity.this, "Let the Hunt Begin!", Toast.LENGTH_SHORT).show();
				time.setToNow();
				hunt.setTimeStarted(time);
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
		ArrayList<Group> groupList = new ArrayList<Group>();
		ArrayList<Child> ownChildList = new ArrayList<Child>();
		ArrayList<Child> inChildList = new ArrayList<Child>();

		Group huntsImIn = new Group();
		huntsImIn.setTitle("Objectives");
		Group huntsIOwn = new Group();
		huntsIOwn.setTitle("Participants");

		try{
			int [] hunts = user.getHuntsId();
			if(hunts != null){
				for(int id : hunts){
					Hunt h = (Hunt) handler.readObject("string", id);				
					Child ch = new Child(h.getName(), h.getId());
					if(user.getId() == h.getHostId()) ownChildList.add(ch);
					else inChildList.add(ch);
				}
				huntsImIn.setArrayChildren(inChildList);
				huntsIOwn.setArrayChildren(ownChildList);
			}


			groupList.add(huntsImIn);
			groupList.add(huntsIOwn);

		} catch(NullPointerException e) {
			Toast.makeText(OwnedHuntActivity.this, "An error has occured. Try again.",Toast.LENGTH_LONG).show();
			return null;
		} 
		return groupList;
	}

}


