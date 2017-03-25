package com.timeontask;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.timeontask.db.Task;
import com.timeontask.db.TaskTableConnection;

import java.util.Date;
import java.util.List;

public class CategoryView extends AppCompatActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_category_view);

		TaskTableConnection dbConnection = new TaskTableConnection(getApplicationContext());
		Task[] tasks = {
			new Task("DoTA", "Gaming", new Date().getTime(), new Date().getTime()),
			new Task("LoL", "Gaming", new Date().getTime(), new Date().getTime()),
			new Task("Maple Story", "Gaming", new Date().getTime(), new Date().getTime()),
			new Task("EECS 482", "Class", new Date().getTime(), new Date().getTime()),
			new Task("EECS 485", "Class", new Date().getTime(), new Date().getTime()),
			new Task("STATS 413", "Class", new Date().getTime(), new Date().getTime()),
			new Task("MATH 423", "Class", new Date().getTime(), new Date().getTime()),
			new Task("TCHNCLCM 300", "Class", new Date().getTime(), new Date().getTime()),
			new Task("Sleep", "Misc", new Date().getTime(), new Date().getTime())
		};

		for (Task t : tasks) {
			dbConnection.addTask(t);
		}

		List<Task> taskList = dbConnection.getTasks(new Date(6), new Date());

		for (Task t : taskList) {
			System.out.println(t);
		}
	}
}
