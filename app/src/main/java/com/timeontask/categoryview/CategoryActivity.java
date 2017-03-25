package com.timeontask.categoryview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import com.timeontask.R;
import com.timeontask.db.Task;

import java.util.ArrayList;
//import com.timeontask.db.Task;
//import com.timeontask.db.TaskTableConnection;
//
//import java.util.Date;
//import java.util.List;

public class CategoryActivity extends AppCompatActivity {
	private CategoryView categoryView;
	private CategoryAdapter categoryAdapter;
	private RecyclerView.LayoutManager layoutManager;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_category_view);

		categoryView = (CategoryView) findViewById(R.id.category_view);
		categoryView.setHasFixedSize(true);

		layoutManager = new LinearLayoutManager(this);
		categoryView.setLayoutManager(layoutManager);

		categoryAdapter = new CategoryAdapter(new ArrayList<Task>());
		categoryView.setAdapter(categoryAdapter);
	}
}
