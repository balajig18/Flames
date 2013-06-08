package org.fun.flames;

import java.util.ArrayList;
import java.util.Arrays;

import android.app.ExpandableListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;

//import com.multilayerexpandable.ExpList;

//import com.multilayerexpandable.ExpList;

public class ExpList extends ExpandableListActivity implements
		OnChildClickListener {
	ExpandableListView view;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		/*
		 * ExpandableListView expandbleLis = getExpandableListView();
		 * expandbleLis.setDividerHeight(2);
		 * expandbleLis.setGroupIndicator(null);
		 * expandbleLis.setClickable(true);
		 */

		setContentView(R.layout.exp_lisr);
		view = (ExpandableListView) findViewById(android.R.id.list);
		setGroupData();
		setChildGroupData();

		NewAdapter mNewAdapter = new NewAdapter(groupItem, childItem);
		mNewAdapter
				.setInflater(
						(LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE),
						this);
		view.setAdapter(mNewAdapter);
		view.setOnChildClickListener(this);
	}

	public void setGroupData() {
		groupItem.add("Hollywood");
		groupItem.add("Bollywood");
		groupItem.add("Kollywood");
		groupItem.add("Tollywood");

	}

	ArrayList<String> groupItem = new ArrayList<String>();
	ArrayList<Object> childItem = new ArrayList<Object>();

	public void setChildGroupData() {
		/**
		 * Add Data For TecthNology
		 */
		ArrayList<String> child = new ArrayList<String>(
				Arrays.asList(getResources().getStringArray(
						R.array.Hollywood_Actress)));

		childItem.add(child);
		/**
		 * Add Data For Mobile
		 */
		child = new ArrayList<String>();
		child.add("Android");
		child.add("Window Mobile");
		child.add("iPHone");
		child.add("Blackberry");
		childItem.add(child);
		/**
		 * Add Data For Manufacture
		 */
		child = new ArrayList<String>(Arrays.asList(getResources()
				.getStringArray(R.array.Kollywood_actress)));
		childItem.add(child);
		/**
		 * Add Data For Extras
		 */
		child = new ArrayList<String>();
		child.add("Contact Us");
		child.add("About Us");
		child.add("Location");
		child.add("Root Cause");
		childItem.add(child);
	}

	@Override
	public boolean onChildClick(ExpandableListView parent, View v,
			int groupPosition, int childPosition, long id) {
		Intent intent = new Intent();
		@SuppressWarnings("unchecked")
		ArrayList<String> list = (ArrayList<String>) childItem
				.get(groupPosition);
		String name = list.get(childPosition);
		Bundle bundle = new Bundle();
		bundle.putString("Return", name);
		intent.putExtras(bundle);
		setResult(RESULT_OK, intent);
		finish();
		return true;
	}
}
