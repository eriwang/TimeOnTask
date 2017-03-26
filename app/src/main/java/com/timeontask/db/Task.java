package com.timeontask.db;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Task {
	public String name;
	public String category;
	public String date;
	public String startTime;
	public String endTime;
	public long duration;

	public Task(String _name, String _category, long _startTime, long _endTime) {
		name = _name;
		category = _category;
		Date actualStartDate = new Date(_startTime);
		Date actualEndDate = new Date(_endTime);
		date = SimpleDateFormat.getDateInstance().format(actualStartDate);
		startTime = SimpleDateFormat.getTimeInstance().format(actualStartDate);
		endTime = SimpleDateFormat.getTimeInstance().format(actualEndDate);
		duration = _endTime - _startTime;
	}

	public String toString() {
		return name + ", " + category;
	}
}
