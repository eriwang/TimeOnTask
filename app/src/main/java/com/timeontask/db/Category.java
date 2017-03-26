package com.timeontask.db;

import java.util.concurrent.TimeUnit;

public class Category {
	public String name;
	public double time;
	public long numTasks;

	public Category(String _name, long _time, long _numTasks) {
		name = _name;
		time = (double) _time / 1000 * 60 * 60;
		numTasks = _numTasks;
	}
}
