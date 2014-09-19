package com.reptoh.android_todo_app.test;

import android.content.Intent;
import android.graphics.Rect;
import android.test.ActivityUnitTestCase;
import android.view.View;
import android.widget.ListView;

import com.reptoh.android_todo_app.CustomAdapter;
import com.reptoh.android_todo_app.MainActivity;
import com.reptoh.android_todo_app.R;

public class MainActivityTest extends ActivityUnitTestCase<MainActivity> {

	// Count of todos in target app
	public static final int ADAPTER_COUNT = 4;
	public static final int INITIAL_POSITION = 0;

	private MainActivity mainActivity;
	private ListView todosList;
	private View mainLayout;
	private CustomAdapter mAdapter;

	public MainActivityTest() {
		super(MainActivity.class);
	}

	@Override
	protected void setUp() throws Exception {
		super.setUp();

		Intent intent = new Intent(getInstrumentation().getTargetContext(),
				MainActivity.class);
		startActivity(intent, null, null);

		mainActivity = getActivity();

		todosList = (ListView) mainActivity.findViewById(R.id.todos_list);
		mainLayout = (View) mainActivity.findViewById(R.id.main_layout);
		mAdapter = (CustomAdapter) todosList.getAdapter();

	}

	public void testPreConditions() {
		assertTrue(mainActivity != null);
		assertTrue(mainLayout != null);
		assertTrue(todosList != null);
		assertTrue(mAdapter != null);
		assertEquals(mAdapter.getCount(), ADAPTER_COUNT);
	}

	//wip
	public void testListViewUI() {

		mainActivity.runOnUiThread(new Runnable() {
			public void run() {
				todosList.requestFocus();
				todosList.setSelection(INITIAL_POSITION);
			}
		});
	}

}
