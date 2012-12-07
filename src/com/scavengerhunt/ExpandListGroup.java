package com.scavengerhunt;

import java.util.ArrayList;

public class ExpandListGroup {
	private String mTitle;
	private ArrayList<ExpandListChild> mArrayChildren;

    public String getTitle() {
        return mTitle;
    }
 
    public void setTitle(String mTitle) {
        this.mTitle = mTitle;
    }
 
    public ArrayList<ExpandListChild> getArrayChildren() {
        return mArrayChildren;
    }
 
    public void setArrayChildren(ArrayList<ExpandListChild> mArrayChildren) {
        this.mArrayChildren = mArrayChildren;
    }
}