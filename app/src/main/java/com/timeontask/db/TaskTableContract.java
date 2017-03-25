package com.timeontask.db;

import android.provider.BaseColumns;

public class TaskTableContract {
	private TaskTableContract() {}

	public static class TaskEntry implements BaseColumns {
		public static final String TABLE_NAME = "tasks";
		public static final String TASK_NAME = "task_name";
		public static final String CATEGORY = "category";
		public static final String START_TIME = "start";
		public static final String END_TIME = "end";
	}
}
