package com.timeontask.db;

public class Task {
	public String name;
	public String category;
	public long startTime;
	public long endTime;

	public Task(String _name, String _category, long _startTime, long _endTime) {
		name = _name;
		category = _category;
		startTime = _startTime;
		endTime = _endTime;
	}

	public String toString() {
		return name + ", " + category;
	}
}
