package com.reptoh.android_todo_app;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.google.gson.Gson;

public class MainActivity extends Activity implements
		CreateTodoDialogFragment.OnCompleteListener {

	private ListView todosList;
	private ArrayList<Todo> todos;
	private CustomAdapter mAdapter;

	public void refreshList(ArrayList<Todo> todos, CustomAdapter adapter) {
		mAdapter.setData(todos);
		todosList.setAdapter(adapter);
	}

	public void saveData() {

		Gson gson = new Gson();
		String json = gson.toJson(todos);

		SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
		SharedPreferences.Editor editor = sharedPref.edit();
		editor.putString(getString(R.string.shared_pref_todos), json);
		editor.commit();

	}

	public void restoreData() {

		SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
		String defaultValue = getResources().getString(
				R.string.shared_pref_todos_default);
		String jsonTodos = sharedPref.getString(
				getString(R.string.shared_pref_todos), defaultValue);

		Todo[] list = new Gson().fromJson(jsonTodos, Todo[].class);

		todos.clear();
		if (list != null) {
			for (Todo a : list) {
				todos.add(a);
			}
		};
		
		refreshList(todos, mAdapter);

	}

	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		super.onRestoreInstanceState(savedInstanceState);

		restoreData();
		refreshList(todos, mAdapter);
	}

	@Override
	public void onSaveInstanceState(Bundle savedInstanceState) {

		saveData();

		// Always call the superclass so it can save the view hierarchy state
		super.onSaveInstanceState(savedInstanceState);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		todosList = (ListView) findViewById(R.id.todos_list);
		mAdapter = new CustomAdapter(getApplicationContext());
		todos = new ArrayList<Todo>();
		restoreData();

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_add) {

			CreateTodoDialogFragment dialog = new CreateTodoDialogFragment();

			dialog.show(getFragmentManager(), "create");

			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onComplete(Todo todo) {
		todos.add(todo);
		refreshList(todos, mAdapter);

	}

	@Override
	public void onResume() {
		super.onResume();
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		saveData();
	}

}
