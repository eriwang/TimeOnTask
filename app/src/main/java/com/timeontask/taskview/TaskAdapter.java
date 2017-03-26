package com.timeontask.taskview;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.timeontask.R;
import com.timeontask.db.Task;

import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder> {
	public class TaskViewHolder extends RecyclerView.ViewHolder {
		View view;
		public TaskViewHolder(View v) {
			super(v);
			view = v;
		}
	}

	public TaskAdapter(List<Task> tasks) {

	}

	@Override
	public TaskViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View v = LayoutInflater.from(parent.getContext())
			.inflate(R.layout.task_view_holder_layout, parent, false);
		TaskViewHolder vh = new TaskViewHolder(v);
		return vh;
	}

	@Override
	public void onBindViewHolder(TaskViewHolder holder, int position) {
		System.out.println("OnBindView called");
	}

	@Override
	public int getItemCount() {
		return 3;
	}
}
