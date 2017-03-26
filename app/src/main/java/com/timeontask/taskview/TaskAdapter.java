package com.timeontask.taskview;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.timeontask.R;
import com.timeontask.db.Task;

import java.util.ArrayList;
import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskViewHolder> {
	private ArrayList<Task> tasks;

	public TaskAdapter(ArrayList<Task> _tasks) {
		tasks = _tasks;
	}

	@Override
	public TaskViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View v = LayoutInflater.from(parent.getContext())
			.inflate(R.layout.task_view_holder_layout, parent, false);
		return new TaskViewHolder(v);
	}

	@Override
	public void onBindViewHolder(TaskViewHolder holder, int position) {
		holder.changeText(tasks.get(position));
	}

	@Override
	public int getItemCount() {
		return tasks.size();
	}
}
