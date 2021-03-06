package com.timeontask.categoryview;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import com.timeontask.R;
import com.timeontask.db.Category;
import com.timeontask.taskview.TaskActivity;

import java.util.Locale;

public class CategoryViewHolder extends RecyclerView.ViewHolder {
	private TextView nameView;
	private TextView timeView;
	private TextView numTasksView;

	public CategoryViewHolder(View v) {
		super(v);

		nameView = (TextView) v.findViewById(R.id.category_name);
		timeView = (TextView) v.findViewById(R.id.total_time_spent);
		numTasksView = (TextView) v.findViewById(R.id.num_tasks);

		v.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(v.getContext(), TaskActivity.class);
				intent.putExtra("category", nameView.getText()); // TODO: constant instead of hard code string

				v.getContext().startActivity(intent);
			}
		});

		// TODO: edit the category
//		v.setOnLongClickListener(new View.OnLongClickListener() {
//			@Override
//			public boolean onLongClick(View v) {
//				Toast.makeText(v.getContext(), "I was long clicked!", Toast.LENGTH_SHORT).show();
//				return true;
//			}
//		});
	}

	public void changeText(Category category) {
		nameView.setText(category.name);
		timeView.setText(String.format(Locale.getDefault(), "%.2f Hours", category.time));
		numTasksView.setText(String.format(Locale.getDefault(), "%d Tasks", category.numTasks));
	}
}
