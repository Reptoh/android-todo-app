package com.reptoh.android_todo_app;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

public class CreateTodoDialogFragment extends DialogFragment {

	private EditText title;
	private EditText description;

	public static interface OnCompleteListener {
		public abstract void onComplete(Todo todo);
	}
	
	// make sure the Activity implemented it
	public void onAttach(Activity activity) {
		super.onAttach(getActivity());
	    try {
	        this.mListener = (OnCompleteListener)activity;
	    }
	    catch (final ClassCastException e) {
	        throw new ClassCastException(activity.toString() + " must implement OnCompleteListener");
	    }
	}

	private OnCompleteListener mListener;

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		// Get the layout inflater
		LayoutInflater inflater = getActivity().getLayoutInflater();

		builder.setTitle(R.string.dialog_create_message);

		View view = inflater.inflate(R.layout.dialog_create_todo, null);
		
		// Inflate and set the layout for the dialog
		// Pass null as the parent view because its going in the dialog layout
		builder.setView(view);
		
		title = (EditText) view.findViewById(R.id.dialog_todo_title);
		description = (EditText) view.findViewById(
				R.id.dialog_todo_description);
		
		// Add action buttons
		builder.setPositiveButton(R.string.dialog_create_positive_button,
				new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int id) {

						Todo todo = new Todo();
						todo.setTitle(title.getText().toString());
						todo.setDescription(description.getText().toString());

						mListener.onComplete(todo);
					}
				});
		builder.setNegativeButton(R.string.dialog_create_negative_button,
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						CreateTodoDialogFragment.this.getDialog().cancel();
					}
				});

		return builder.create();
	}
}
