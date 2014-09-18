package com.reptoh.android_todo_app;

import java.util.ArrayList;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

public class CustomAdapter extends BaseAdapter {

	private ArrayList<Todo> todosList;
	private LayoutInflater mInflater;

	public CustomAdapter(Context c) {
		mInflater = LayoutInflater.from(c);
	}

	public void setData(ArrayList<Todo> poolList) {
		todosList = poolList;
	}

	@Override
	public int getCount() {
		return todosList.size();
	}

	@Override
	public Object getItem(int position) {
		return todosList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;

		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.todo, null);

			holder = new ViewHolder();

			holder.mTitleTxt = (TextView) convertView.findViewById(R.id.title);
			holder.mDescription = (TextView) convertView
					.findViewById(R.id.description);
			holder.mChecked = (CheckBox) convertView
					.findViewById(R.id.todo_checked);

			convertView.setTag(holder);

//			holder.mChecked.setOnClickListener(new View.OnClickListener() {
//				public void onClick(View v) {
//					CheckBox cb = (CheckBox) v;
//					Todo country = (Todo) cb.getTag();
//					country.setChecked(cb.isChecked());
//					Log.v("checkbox", "changed");
//				}
//			});

		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		final Todo todo = todosList.get(position);

		holder.mTitleTxt.setText(todo.getTitle());
		holder.mDescription.setText(todo.getDescription());
		holder.mChecked.setChecked(todo.getChecked());

		holder.mChecked.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				if (((CompoundButton) v).isChecked()) {
					todo.setChecked(true);
					Log.v("here", "fired!");
				} else {
					// Remove from checkbox array
				}
			}
		});

		return convertView;
	}

	static class ViewHolder {
		TextView mTitleTxt;
		TextView mDescription;
		CheckBox mChecked;
	}

}