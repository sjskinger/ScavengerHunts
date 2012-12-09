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
import model.Objective;
import model.User;
import android.os.CountDownTimer;

import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.TextView;
import android.widget.Toast;


public class OwnedHuntActivity extends Activity{

	private ExpandableListView mExpandableList;
	private OwnedExpandListAdapter adapter;
	private ArrayList<Group> groups;
	TextView timer;
	Button createObjective;
	Button startHunt;
	Button addParticipant;
	EditText par;
	ObjectHandler handler;
	User user;
	Time time;
	int huntID;
	Hunt hunt;
	Objective obj;
	Group objectives; 
	Group participants; 


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
			hunt = new Hunt();
		}
		else hunt = (Hunt) handler.readObject("hunt", huntID);	

		createObjective =  (Button)this.findViewById(R.id.add_objective_button);
		startHunt = (Button)this.findViewById(R.id.start_hunt_button);
		addParticipant = (Button)this.findViewById(R.id.add_participant_button);
		par = (EditText) this.findViewById(R.id.add_participant_box);
		timer = (TextView)this.findViewById(R.id.time_remaining_text);
		mExpandableList = (ExpandableListView)findViewById(R.id.expandableListView);


		groups = setGroups();
		adapter = new OwnedExpandListAdapter(this, groups);
		mExpandableList.setAdapter(adapter);

		startHunt.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				time = new Time(Time.getCurrentTimezone());
				time.setToNow();

				if ((hunt.getObjectiveId().size() < 1) || (hunt.getParticipantId().size() < 2)){
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
				handler.writeObject("hunt", hunt.getId(), hunt);
			}
		});  

		createObjective.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent create = new Intent(getApplicationContext(), CreateObjectiveActivity.class);
				create.putExtra("objectHandler", handler);	
				create.putExtra("hunt", hunt);
				startActivityForResult(create, 0);
			}
		});    


		addParticipant.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				/*User u = handler.searchUser(par.getText().toString());
				if (u == null){
					User u = new User();
					u.setEmail(par.getText().toString());
					handler.writeObject("user", u.getId(), obj);
					Child ch = new Child(u.getEmail(), u.getId());
					Toast.makeText(OwnedHuntActivity.this, "User is not recognized. Addding in via email.", Toast.LENGTH_SHORT);
					adapter.addChild(ch, participants);
				}
				else{
					handler.writeObject("user", u.getId(), obj);
					Child ch = new Child(u.getName(), u.getId());
					adapter.addChild(ch, participants);
				}*/
				User u = new User();
					u.setEmail(par.getText().toString());
					handler.writeObject("user", u.getId(), obj);
					Child ch = new Child(u.getEmail(), u.getId());
					Toast.makeText(OwnedHuntActivity.this, "User is not recognized. Addding in via email.", Toast.LENGTH_SHORT).show();
					adapter.addChild(ch, participants);
					handler.writeObject("hunt", hunt.getId(), hunt);
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

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode == RESULT_OK && data != null){
			if (requestCode == 0){
				obj = (Objective) data.getSerializableExtra("objective");
				obj.setHuntId(hunt.getId());
				handler.writeObject("objective", obj.getId(), obj);
				Child ch = new Child(obj.getName(), obj.getId());
				adapter.addChild(ch, objectives);
				handler.writeObject("hunt", hunt.getId(), hunt);
			}
		}
	}

	public ArrayList<Group> setGroups() {
		ArrayList<Group> groupList = new ArrayList<Group>();
		ArrayList<Child> objChildList = new ArrayList<Child>();
		ArrayList<Child> parChildList = new ArrayList<Child>();

		objectives = new Group();
		objectives.setTitle("Objectives");
		participants = new Group();
		participants.setTitle("Participants");

		try{
			ArrayList<Integer> objs = hunt.getObjectiveId();
			for(int id : objs){
				Objective o = (Objective) handler.readObject("objective", id);				
				Child ch = new Child(o.getName(), o.getId());
				objChildList.add(ch);
			}

			ArrayList<Integer> pars = hunt.getParticipantId();
			for(int id : pars){
				User o = (User) handler.readObject("user", id);				
				Child ch = new Child(o.getName(), o.getId());
				parChildList.add(ch);
			}
			objectives.setArrayChildren(objChildList);
			participants.setArrayChildren(parChildList);



			groupList.add(objectives);
			groupList.add(participants);

		} catch(NullPointerException e) {
			Toast.makeText(OwnedHuntActivity.this, "An error has occured. Try again.",Toast.LENGTH_LONG).show();
			return null;
		} 

		return groupList;
	}

}


