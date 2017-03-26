package com.timeontask.categoryview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import com.timeontask.R;
import com.timeontask.db.DatabaseConnection;
import com.timeontask.db.Task;

import java.util.ArrayList;
import java.util.Date;

public class CategoryActivity extends AppCompatActivity {
	private CategoryView categoryView; // TODO: do I even need to extend it??
	private CategoryAdapter categoryAdapter;
	private RecyclerView.LayoutManager layoutManager;
	private DividerItemDecoration dividerItemDecoration;

	// TODO: do I even need these member variables??
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_category_view);

		categoryView = (CategoryView) findViewById(R.id.category_view);
		categoryView.setHasFixedSize(true);

		layoutManager = new LinearLayoutManager(this);
		categoryView.setLayoutManager(layoutManager);

		dividerItemDecoration = new DividerItemDecoration(categoryView.getContext(), DividerItemDecoration.VERTICAL);
		categoryView.addItemDecoration(dividerItemDecoration);

		DatabaseConnection db = new DatabaseConnection(getApplicationContext());

//		Task[] tasks = {
//			new Task("DoTA", "Gaming", new Date().getTime(), new Date().getTime()),
//			new Task("LoL", "Gaming", new Date().getTime(), new Date().getTime()),
//			new Task("Maple Story", "Gaming", new Date().getTime(), new Date().getTime()),
//			new Task("EECS 482", "Class", new Date().getTime(), new Date().getTime()),
//			new Task("EECS 485", "Class", new Date().getTime(), new Date().getTime()),
//			new Task("STATS 413", "Class", new Date().getTime(), new Date().getTime()),
//			new Task("MATH 423", "Class", new Date().getTime(), new Date().getTime()),
//			new Task("TCHNCLCM 300", "Class", new Date().getTime(), new Date().getTime()),
//			new Task("Sleep", "Misc", new Date().getTime(), new Date().getTime())
//		};
//
//		for (Task t : tasks) {
//			db.addTask(t);
//		}

		categoryAdapter = new CategoryAdapter(db.getCategoriesInformation(db.getCategories()));
		categoryView.setAdapter(categoryAdapter);
	}
}
