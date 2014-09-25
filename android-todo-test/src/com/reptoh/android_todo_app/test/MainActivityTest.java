package com.reptoh.android_todo_app.test;

import android.app.Instrumentation;
import android.content.Intent;
import android.graphics.Rect;
import android.test.ActivityUnitTestCase;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ListView;

import com.reptoh.android_todo_app.CustomAdapter;
import com.reptoh.android_todo_app.MainActivity;
import com.reptoh.android_todo_app.R;
import com.reptoh.android_todo_app.Todo;

public class MainActivityTest extends ActivityUnitTestCase<MainActivity> {

	// Count of todos in target app
	public static final int ADAPTER_COUNT = 4;
	
	public static final int INITIAL_POSITION = 0;
	public static final int TEST_LIST_POSITION = 0;
	// Title of your first todo
	public static final String TEST_TITLE = "Create todo-app";

	private MainActivity mainActivity;
	private ListView todosList;
	private View mainLayout;
	private CustomAdapter mAdapter;
	private Instrumentation mInstrumentation;

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
		mInstrumentation = getInstrumentation();

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

	public void testListViewTitlePosition() {

		mainActivity.runOnUiThread(new Runnable() {
			public void run() {
				todosList.requestFocus();
				todosList.setSelection(INITIAL_POSITION);
			}
		});

		mInstrumentation.waitForIdleSync();

		Todo testItem = mAdapter.getItem(TEST_LIST_POSITION);

		assertEquals(TEST_TITLE, testItem.getTitle());
	}

}
