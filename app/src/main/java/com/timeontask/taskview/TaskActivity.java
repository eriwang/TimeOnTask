package com.timeontask.taskview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import com.timeontask.R;
import com.timeontask.categoryview.CategoryAdapter;
import com.timeontask.categoryview.CategoryView;
import com.timeontask.db.DatabaseConnection;
import com.timeontask.db.Task;

import java.util.ArrayList;
import java.util.Date;

public class TaskActivity extends AppCompatActivity {
	private TaskView taskView; // TODO: do I even need to extend it??
	private TaskAdapter taskAdapter;
	private RecyclerView.LayoutManager layoutManager;
	private DividerItemDecoration dividerItemDecoration;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_task_view);

		String category = getIntent().getStringExtra("category");

		taskView = (TaskView) findViewById(R.id.task_view);
		taskView.setHasFixedSize(true);

		layoutManager = new LinearLayoutManager(this);
		taskView.setLayoutManager(layoutManager);

		dividerItemDecoration = new DividerItemDecoration(taskView.getContext(), DividerItemDecoration.VERTICAL);
		taskView.addItemDecoration(dividerItemDecoration);

		DatabaseConnection db = new DatabaseConnection(getApplicationContext());

		taskAdapter = new TaskAdapter(db.getTasks(category, new Date(6), new Date()));
		taskView.setAdapter(taskAdapter);
	}
}
