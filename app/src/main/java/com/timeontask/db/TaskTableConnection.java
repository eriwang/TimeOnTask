package com.timeontask.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TaskTableConnection {
	private TaskTableDbHelper dbHelper;

	public TaskTableConnection(Context context) {
		dbHelper = new TaskTableDbHelper(context);
	}

	public void addTask(Task task) {
		SQLiteDatabase db = dbHelper.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(TaskTableContract.TaskEntry.TASK_NAME, task.name);
		values.put(TaskTableContract.TaskEntry.CATEGORY, task.category);
		values.put(TaskTableContract.TaskEntry.START_TIME, task.startTime);
		values.put(TaskTableContract.TaskEntry.END_TIME, task.endTime);

		db.insert(TaskTableContract.TaskEntry.TABLE_NAME, null, values); // TODO: do we want to use nullColumnHack?
	}

	// TODO: split into different functions maybe
	// TODO: add different ways to query, maybe with a "TaskQuery" object??
	public List<Task> getTasks(Date startTime, Date endTime) {
		SQLiteDatabase db = dbHelper.getReadableDatabase();

		// TODO: _ID??
		String[] projection = {
			TaskTableContract.TaskEntry.TASK_NAME,
			TaskTableContract.TaskEntry.CATEGORY,
			TaskTableContract.TaskEntry.START_TIME,
			TaskTableContract.TaskEntry.END_TIME
		};

		String selection = TaskTableContract.TaskEntry.START_TIME + " >= ? AND " +
			TaskTableContract.TaskEntry.END_TIME + " <= ?";
		String[] selectionArgs = {Long.toString(startTime.getTime()), Long.toString(endTime.getTime())};

		// TODO: sort order??

		Cursor cursor = db.query(
			TaskTableContract.TaskEntry.TABLE_NAME,
			projection,
			selection,
			selectionArgs,
			null,
			null,
			null
		);

		List<Task> results = new ArrayList<>();
		while (cursor.moveToNext()) {
			String name = cursor.getString(cursor.getColumnIndexOrThrow(TaskTableContract.TaskEntry.TASK_NAME));
			String category = cursor.getString(cursor.getColumnIndexOrThrow(TaskTableContract.TaskEntry.CATEGORY));
			long start = cursor.getLong(cursor.getColumnIndexOrThrow(TaskTableContract.TaskEntry.START_TIME));
			long end = cursor.getLong(cursor.getColumnIndexOrThrow(TaskTableContract.TaskEntry.END_TIME));

			results.add(new Task(name, category, start, end));
		}

		return results;
	}
}