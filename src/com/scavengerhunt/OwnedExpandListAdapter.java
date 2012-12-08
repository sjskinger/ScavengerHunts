package com.scavengerhunt;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

public class OwnedExpandListAdapter extends BaseExpandableListAdapter {

	private Context context;
	private ArrayList<Group> groups;

	public OwnedExpandListAdapter(Context context, ArrayList<Group> groups) {
		this.context = context;
		this.groups = groups;
	}

	public void addChild(Child item, Group group) {
		if (!groups.contains(group)) {
			groups.add(group);
		}
		int index = groups.indexOf(group);
		ArrayList<Child> ch = groups.get(index).getArrayChildren();
		ch.add(item);
		groups.get(index).setArrayChildren(ch);
	}

	@Override
	public int getGroupCount() {
		return groups.size();
	}

	@Override
	public int getChildrenCount(int i) {
		return groups.get(i).getArrayChildren().size();
	}

	@Override
	public Object getGroup(int groupPosition) {
		return groups.get(groupPosition);

	}

	@Override
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
	public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View view, ViewGroup parent) {
		LayoutInflater inf = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		view = inf.inflate(R.layout.owned_expand_list_child, parent, false);
		TextView textView = (TextView) view.findViewById(R.id.owned_expand_list_child_text);
		textView.setText(groups.get(groupPosition).getArrayChildren().get(childPosition).getName());
		return view;
	}

	@Override
	public View getGroupView(int groupPosition, boolean isExpanded,View view, ViewGroup parent) {
		LayoutInflater inf = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View mview = inf.inflate(R.layout.expand_list_group, parent, false);
		TextView textView = (TextView) mview.findViewById(R.id.expand_list_group_text);
		textView.setText(groups.get(groupPosition).getTitle());
		return mview;
	}
}