package model;

import java.io.Serializable;

import android.net.Uri;

public class Objective implements Serializable {

	private static final long serialVersionUID = 1L;
	private int id;
	private String name;
	private String description;
	private Uri uri;
	private int huntId;
	public static int count = 0;
	
	public Objective () {
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Uri getUri() {
		return uri;
	}
	public void setUri(Uri uri) {
		this.uri = uri;
	}
	public int getHuntId() {
		return huntId;
	}
	public void setHuntId(int huntId) {
		this.huntId = huntId;
	}
}
