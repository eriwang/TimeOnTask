package com.timeontask.timerview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import com.timeontask.R;
import com.timeontask.db.DatabaseConnection;

import java.util.ArrayList;

public class TimerActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
	DatabaseConnection db;
	Spinner categorySpinner;
	Spinner taskNameSpinner;
	EditText categoryEditText;
	EditText taskNameEditText;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_timer_view);

		categorySpinner = (Spinner) findViewById(R.id.category_spinner);
		taskNameSpinner = (Spinner) findViewById(R.id.task_spinner);
		categoryEditText = (EditText) findViewById(R.id.category_edit_text);
		taskNameEditText = (EditText) findViewById(R.id.task_edit_text);

		ArrayAdapter<CharSequence> adapter = new ArrayAdapter<>(this, R.layout.spinner_item);

		db = new DatabaseConnection(getApplicationContext());
		ArrayList<String> categories = db.getCategories();
		categories.add("New Category");
		adapter.addAll(categories);

		categorySpinner.setAdapter(adapter);
		categorySpinner.setOnItemSelectedListener(this);
	}

	// TODO: clean up
	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
		if (parent.getId() == R.id.category_spinner) {
			String category = (String) parent.getItemAtPosition(position);
			if (category.equals("New Category")) {
				categoryEditText.setEnabled(true);
				taskNameEditText.setEnabled(true);

				ArrayAdapter<CharSequence> adapter = new ArrayAdapter<>(this, R.layout.spinner_item);
				adapter.add("New Task");

				taskNameSpinner.setAdapter(adapter);
				taskNameSpinner.setOnItemSelectedListener(this);
			}
			else {
				categoryEditText.setEnabled(false);
				taskNameEditText.setEnabled(false);
				// TODO: reused code
				ArrayAdapter<CharSequence> adapter = new ArrayAdapter<>(this, R.layout.spinner_item);
				ArrayList<String> taskNames = db.getTaskNames(category);
				taskNames.add("New Task");
				adapter.addAll(taskNames);

				taskNameSpinner.setAdapter(adapter);
				taskNameSpinner.setOnItemSelectedListener(this);
			}
		}
		else {
			String task = (String) parent.getItemAtPosition(position);
			taskNameEditText.setEnabled(task.equals("New Task"));
		}
	}

	@Override
	public void onNothingSelected(AdapterView<?> parent) {
	}

	public void startTimer(View view) {

	}
}
