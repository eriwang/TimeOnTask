package com.timeontask.timerview;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.view.View;
import android.widget.*;
import com.timeontask.R;
import com.timeontask.db.DatabaseConnection;

import java.util.ArrayList;

public class TimerActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
	Spinner categorySpinner;
	Spinner taskNameSpinner;
	EditText categoryEditText;
	EditText taskNameEditText;
	Button startButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_timer_view);

		categorySpinner = (Spinner) findViewById(R.id.category_spinner);
		taskNameSpinner = (Spinner) findViewById(R.id.task_spinner);
		categoryEditText = (EditText) findViewById(R.id.category_edit_text);
		taskNameEditText = (EditText) findViewById(R.id.task_edit_text);
		startButton = (Button) findViewById(R.id.start);
		DatabaseConnection db = new DatabaseConnection(getApplicationContext());


		if (db.isTimingActive()) {
			// TODO: method for this
			startButton.setBackgroundColor(Color.BLUE);
			startButton.setText("STOP");
		}
		else {
			ArrayAdapter<CharSequence> adapter = new ArrayAdapter<>(this, R.layout.spinner_item);

			db = new DatabaseConnection(getApplicationContext());
			ArrayList<String> categories = db.getCategories();
			categories.add("New Category");
			adapter.addAll(categories);

			categorySpinner.setAdapter(adapter);
			categorySpinner.setOnItemSelectedListener(this);
		}
	}

	// TODO: clean up
	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
		DatabaseConnection db  = new DatabaseConnection(getApplicationContext()); // TODO: make this a member somehow?
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

	public void toggleTimer(View view) {
		DatabaseConnection db  = new DatabaseConnection(getApplicationContext()); // TODO: make this a member somehow?

		if (!db.isTimingActive()) {
			startTimer();
		}
		else {
			stopTimer();
		}
	}

	private void startTimer() {
		NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
			.setSmallIcon(R.mipmap.ic_launcher)
			.setContentTitle("HELLO")
			.setContentText("I NEED SLEEP");

		// TODO: abstract out, add assert for UI
		startButton.setBackgroundColor(Color.BLUE);
		startButton.setText("STOP");

		DatabaseConnection db = new DatabaseConnection(getApplicationContext());
		db.startTiming("test", "ur face");

		// TODO: look again at this and see if i need it
		Intent resultIntent = new Intent(this, this.getClass());
		resultIntent.putExtra("TEST", "ASASDASDSADASDASDSADASDSA");

		TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
		// Adds the back stack for the Intent (but not the Intent itself)
		stackBuilder.addParentStack(this.getClass());
		// Adds the Intent that starts the Activity to the top of the stack
		stackBuilder.addNextIntent(resultIntent);
		PendingIntent resultPendingIntent =	stackBuilder.getPendingIntent(
			0,
			PendingIntent.FLAG_UPDATE_CURRENT
		);
		builder.setContentIntent(resultPendingIntent);
		builder.setDeleteIntent(resultPendingIntent);
		NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
		// mId allows you to update the notification later on.
		mNotificationManager.notify(0, builder.build());

		Intent startMain = new Intent(Intent.ACTION_MAIN);
		startMain.addCategory(Intent.CATEGORY_HOME);
		startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		startActivity(startMain);
	}

	private void stopTimer() {
		NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
		mNotificationManager.cancel(0); // FIXME: magic number

		DatabaseConnection db = new DatabaseConnection(getApplicationContext());
		db.stopTiming();

		// TODO: abstract out, add assert for UI
		startButton.setBackgroundColor(getResources().getColor(R.color.pie_chart_blue));
		startButton.setText("START");
	}
}
