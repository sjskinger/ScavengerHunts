package model;

import java.util.ArrayList;


public class Group {
	private String mTitle;
	private ArrayList<Child> mArrayChildren;
	
	public Group() {}
	
	public Group(String mTitle,
			ArrayList<Child> mArrayChildren) {
		super();
		this.mTitle = mTitle;
		this.mArrayChildren = mArrayChildren;
	}

	public String getTitle() {
        return mTitle;
    }
 
    public void setTitle(String mTitle) {
        this.mTitle = mTitle;
    }
 
    public ArrayList<Child> getArrayChildren() {
        return mArrayChildren;
    }
 
    public void setArrayChildren(ArrayList<Child> mArrayChildren) {
        this.mArrayChildren = mArrayChildren;
    }
}