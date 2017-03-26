package com.timeontask.categoryview;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.timeontask.R;
import com.timeontask.db.Category;

import java.util.ArrayList;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryViewHolder> {
	private ArrayList<Category> categories;

	public CategoryAdapter(ArrayList<Category> _categories) {
		categories = _categories;
	}

	@Override
	public CategoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View v = LayoutInflater.from(parent.getContext())
			.inflate(R.layout.category_view_holder_layout, parent, false);
		return new CategoryViewHolder(v);
	}

	@Override
	public void onBindViewHolder(CategoryViewHolder holder, int position) {
		holder.changeText(categories.get(position));
	}

	@Override
	public int getItemCount() {
		return categories.size();
	}
}
