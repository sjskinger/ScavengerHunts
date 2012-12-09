package model;

import java.io.Serializable;
import java.util.ArrayList;

public class User implements Serializable {

	private static final long serialVersionUID = 1L;
	private int id;
	private String name;
	private String email;
	private String password;
	private ArrayList<Integer> huntsId;
	public static int count = 0;
	
	public User() {
		count++;
		this.id = count;
	}
	
	public User(String name, String email, String password) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public ArrayList<Integer> getHuntsId() {
		return huntsId;
	}
	public void setHuntsId(ArrayList<Integer> hunts_id) {
		this.huntsId = hunts_id;
	}
}
