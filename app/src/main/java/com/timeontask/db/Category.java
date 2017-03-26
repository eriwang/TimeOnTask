package com.timeontask.db;

public class Category {
	public String name;
	public long time;
	public long numTasks;

	public Category(String _name, long _time, long _numTasks) {
		name = _name;
		time = _time;
		numTasks = _numTasks;
	}
}
