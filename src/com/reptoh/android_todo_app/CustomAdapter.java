package com.reptoh.android_todo_app;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

public class CustomAdapter extends BaseAdapter {

	private ArrayList<Todo> todos;
	private LayoutInflater mInflater;

	public CustomAdapter(Context c) {
		mInflater = LayoutInflater.from(c);
	}

	public void setData(ArrayList<Todo> poolList) {
		todos = poolList;
	}

	@Override
	public int getCount() {
		return todos.size();
	}

	@Override
	public Object getItem(int position) {
		return todos.get(position);
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

		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		final Todo todo = todos.get(position);

		holder.mTitleTxt.setText(todo.getTitle());
		holder.mDescription.setText(todo.getDescription());
		holder.mChecked.setChecked(todo.getChecked());

		holder.mChecked.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				todo.setChecked(((CompoundButton) v).isChecked());
			}
		});

		convertView.setOnLongClickListener(new OnLongClickListener() {

			@Override
			public boolean onLongClick(View v) {
				todos.remove(todo);
				setData(todos);
				notifyDataSetChanged();
				return true;
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