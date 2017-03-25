package com.timeontask.categoryview;

import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

/**
 * Created by johne on 3/25/2017.
 */

public class CategoryViewHolder extends RecyclerView.ViewHolder {
	public TextView textView;
	public CategoryViewHolder(TextView v) {
		super(v);
		textView = v;
	}
}
