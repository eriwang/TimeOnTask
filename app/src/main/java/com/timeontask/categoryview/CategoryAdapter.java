package com.timeontask.categoryview;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
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
		View v = LayoutInflater.from(parent.getContext())
			.inflate(R.layout.category_view_holder_layout, parent, false);
		CategoryViewHolder vh = new CategoryViewHolder(v);
		return vh;
	}

	@Override
	public void onBindViewHolder(CategoryViewHolder holder, int position) {
		// TODO
	}

	@Override
	public int getItemCount() {
		return 3;
	}
}
