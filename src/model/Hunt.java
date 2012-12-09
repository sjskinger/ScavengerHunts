package model;

import java.io.Serializable;
import java.util.ArrayList;

import android.text.format.Time;

public class Hunt implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private int id;
	private String name;
	private int hostId;
	private Time timeStarted;
	private float duration;
	private ArrayList<Integer> participantId = new ArrayList<Integer>();
	private ArrayList<Integer> objectiveId = new ArrayList<Integer>();
	public static int count = 0;
	
	public Hunt() {
		count++;
		id = count;
	}
	
	public int getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getHostId() {
		return hostId;
	}
	public void setHostId(int hostId) {
		this.hostId = hostId;
	}
	public Time getTimeStarted() {
		return timeStarted;
	}
	public void setTimeStarted(Time today) {
		this.timeStarted = today;
	}
	public float getDuration() {
		return duration;
	}
	public void setDuration(float duration) {
		this.duration = duration;
	}
	public ArrayList<Integer> getParticipantId() {
		return participantId;
	}
	public void setParticipantId(ArrayList<Integer> participantId) {
		this.participantId = participantId;
	}
	public ArrayList<Integer> getObjectiveId() {
		return objectiveId;
	}
	public void setObjectiveId(ArrayList<Integer> objectiveId) {
		this.objectiveId = objectiveId;
	}
	
}
