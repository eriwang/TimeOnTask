package com.timeontask.taskview;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import com.timeontask.R;
import com.timeontask.db.Task;

import java.util.Date;

public class TaskViewHolder extends RecyclerView.ViewHolder {
	TextView nameView;
	TextView dateView;
	TextView startTimeView;
	TextView endTimeView;

	public TaskViewHolder(View v) {
		super(v);

		nameView = (TextView) v.findViewById(R.id.task_name);
		dateView = (TextView) v.findViewById(R.id.task_date);
		startTimeView = (TextView) v.findViewById(R.id.start_time);
		endTimeView = (TextView) v.findViewById(R.id.end_time);
	}

	public void changeText(Task t) {
		nameView.setText(t.name);
		dateView.setText(new Date().toString());
		startTimeView.setText(new Date().toString());
		endTimeView.setText(new Date().toString());
	}
}