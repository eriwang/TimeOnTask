package com.timeontask.categoryview;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;
import com.timeontask.R;
import com.timeontask.db.Task;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryViewHolder> {
	public CategoryAdapter(List<Task> tasks) {

	}

	@Override
	public CategoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		TextView v = new TextView(parent.getContext());
		v.setText("TEST");
		CategoryViewHolder vh = new CategoryViewHolder(v);
		return vh;
	}

	@Override
	public void onBindViewHolder(CategoryViewHolder holder, int position) {
		// TODO
	}

	@Override
	public int getItemCount() {
		return 10;
	}
}
