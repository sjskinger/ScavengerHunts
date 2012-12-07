package com.scavengerhunt;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

public class ExpandListAdapter extends BaseExpandableListAdapter {


	private LayoutInflater inflater;
	private ArrayList<ExpandListGroup> groups;
	
	public ExpandListAdapter(Context context, ArrayList<ExpandListGroup> groups) {
		inflater = LayoutInflater.from(context);
		this.groups = groups;
	}

	public void addItem(ExpandListChild item, ExpandListGroup group) {
		if (!groups.contains(group)) {
			//groups.add(group);
		}
		int index = groups.indexOf(group);
		ArrayList<ExpandListChild> ch = groups.get(index).getArrayChildren();
		ch.add(item);
		groups.get(index).setArrayChildren(ch);
	}
	
	@Override
	//counts the number of group/parent items so the list knows how many times calls getGroupView() method
	public int getGroupCount() {
		return groups.size();
	}

	@Override
	//counts the number of children items so the list knows how many times calls getChildView() method
	public int getChildrenCount(int i) {
		return groups.get(i).getArrayChildren().size();
	}

	@Override
	//gets the title of each parent/group
	public Object getGroup(int groupPosition) {
		return groups.get(groupPosition);

	}

	@Override
	//gets the name of each item
	public Object getChild(int i, int i1) {
		return groups.get(i).getArrayChildren().get(i1);
	}

	@Override
	public long getGroupId(int i) {
		return i;
	}

	@Override
	public long getChildId(int i, int i1) {
		return i1;
	}

	@Override
	public boolean hasStableIds() {
		return true;
	}


	@Override
	public boolean isChildSelectable(int i, int i1) {
		return true;
	}

	@Override
	public View getChildView(int groupPosition, int childPosition,
			boolean isLastChild, View view, ViewGroup parent) {
		if (view == null) {
			view = inflater.inflate(R.layout.expand_list_child, parent,false);
		}

		TextView textView = (TextView) view.findViewById(R.id.expand_list_child_text);
		//"i" is the position of the parent/group in the list and 
		//"i1" is the position of the child
		textView.setText((CharSequence) groups.get(groupPosition).getArrayChildren().get(childPosition));

		//return the entire view
		return view;
	}

	@Override
	public View getGroupView(int groupPosition, boolean isExpanded,
			View view, ViewGroup parent) {

		if (view == null) {
			view = inflater.inflate(R.layout.expand_list_group, parent,false);
		}

		TextView textView = (TextView) view.findViewById(R.id.expand_list_group_text);
		textView.setText(getGroup(groupPosition).toString());
		return view;
	}
}