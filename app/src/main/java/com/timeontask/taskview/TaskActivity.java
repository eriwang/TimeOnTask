package com.timeontask.taskview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import com.timeontask.R;
import com.timeontask.categoryview.CategoryAdapter;
import com.timeontask.categoryview.CategoryView;
import com.timeontask.db.Task;

import java.util.ArrayList;

public class TaskActivity extends AppCompatActivity {
	private TaskView taskView; // TODO: do I even need to extend it??
	private TaskAdapter taskAdapter;
	private RecyclerView.LayoutManager layoutManager;
	private DividerItemDecoration dividerItemDecoration;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_task_view);

		taskView = (TaskView) findViewById(R.id.task_view);
		taskView.setHasFixedSize(true);

		layoutManager = new LinearLayoutManager(this);
		taskView.setLayoutManager(layoutManager);

		dividerItemDecoration = new DividerItemDecoration(taskView.getContext(), DividerItemDecoration.VERTICAL);
		taskView.addItemDecoration(dividerItemDecoration);

		taskAdapter = new TaskAdapter(new ArrayList<Task>());
		taskView.setAdapter(taskAdapter);
	}
}
