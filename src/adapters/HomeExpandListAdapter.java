package adapters;

import java.util.ArrayList;

import com.scavengerhunt.R;
import com.scavengerhunt.R.id;
import com.scavengerhunt.R.layout;

import model.Child;
import model.Group;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

public class HomeExpandListAdapter extends BaseExpandableListAdapter {

	private Context context;
	private ArrayList<Group> groups;

	public HomeExpandListAdapter(Context context, ArrayList<Group> groups) {
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
	//counts the number of group/parent items so the list knows how many times calls getGroupView() method
	public int getGroupCount() {
		return groups.size();
	}

	@Override
	//counts the number of children items so the list knows how many times calls getChildView() method
	public int getChildrenCount(int i) {
		try{
			return groups.get(i).getArrayChildren().size();
		}catch (NullPointerException e){
			return 0;
		}
	}

	@Override
	//gets the title of each parent/group
	public Object getGroup(int groupPosition) {
		return groups.get(groupPosition);

	}

	@Override
	//gets the name of each item
	public Object getChild(int i, int i1) {
		try{
			return groups.get(i).getArrayChildren().get(i1);
		}catch (NullPointerException e){
			return 0;
		}
	}

	@Override
	public long getGroupId(int i) {
		return i;
	}

	@Override
	public long getChildId(int i, int i1) {
		try{
			return i1;
		}catch (NullPointerException e){
			return 0;
		}
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
		if (view == null){
			LayoutInflater inf = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			view = inf.inflate(R.layout.home_expand_list_child, parent, false);
		}
		TextView textView = (TextView) view.findViewById(R.id.home_expand_list_child_text);
		textView.setText(groups.get(groupPosition).getArrayChildren().get(childPosition).getName());
		return view;
	}

	@Override
	public View getGroupView(int groupPosition, boolean isExpanded, View view, ViewGroup parent) {
		if (view == null){
			LayoutInflater inf = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			view = inf.inflate(R.layout.expand_list_group, parent, false);
		}

		TextView textView = (TextView) view.findViewById(R.id.expand_list_group_text);
		textView.setText(groups.get(groupPosition).getTitle());
		return view;
	}
}
