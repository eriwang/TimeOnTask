package com.timeontask.timerview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import com.timeontask.R;
import com.timeontask.db.DatabaseConnection;
import com.timeontask.db.Task;

import java.util.ArrayList;
import java.util.Date;

public class TimerActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
	DatabaseConnection db;
	Spinner categorySpinner;
	Spinner taskNameSpinner;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_timer_view);

		categorySpinner = (Spinner) findViewById(R.id.category_spinner);
		taskNameSpinner = (Spinner) findViewById(R.id.task_spinner);

		ArrayAdapter<CharSequence> adapter = new ArrayAdapter<>(this, R.layout.spinner_item);

		db = new DatabaseConnection(getApplicationContext());
		ArrayList<String> categories = db.getCategories();
		categories.add("New Category");
		adapter.addAll(categories);

		categorySpinner.setAdapter(adapter);
		categorySpinner.setOnItemSelectedListener(this);
	}

	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
		if (parent.getId() == R.id.category_spinner) {
			String category = (String) parent.getItemAtPosition(position);
			if (category.equals("New Category")) {
				// TODO
			}
			else {
				// TODO: reused code
				ArrayAdapter<CharSequence> adapter = new ArrayAdapter<>(this, R.layout.spinner_item);
				ArrayList<String> taskNames = db.getTaskNames(category);
				taskNames.add("New Task");
				adapter.addAll(taskNames);

				taskNameSpinner.setAdapter(adapter);
				taskNameSpinner.setOnItemSelectedListener(this);
			}
		}
	}

	@Override
	public void onNothingSelected(AdapterView<?> parent) {
	}
}
