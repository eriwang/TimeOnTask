package com.timeontask.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class DatabaseConnection {
	private DatabaseOpenHelper dbHelper;

	public DatabaseConnection(Context context) {
		dbHelper = new DatabaseOpenHelper(context);
	}

	public void addTask(Task task) {
		SQLiteDatabase db = dbHelper.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(DatabaseContract.TaskTable.TASK_NAME, task.name);
		values.put(DatabaseContract.TaskTable.CATEGORY, task.category);
		values.put(DatabaseContract.TaskTable.START_TIME, task.startTime);
		values.put(DatabaseContract.TaskTable.END_TIME, task.endTime);

		db.insert(DatabaseContract.TaskTable.TABLE_NAME, null, values); // TODO: do we want to use nullColumnHack?
	}

	// TODO: split into different functions maybe
	// TODO: add different ways to query, maybe with a "TaskQuery" object??
	public ArrayList<Task> getTasks(String category, Date startTime, Date endTime) {
		SQLiteDatabase db = dbHelper.getReadableDatabase();

		String sql = String.format(Locale.ENGLISH,
			"SELECT %s, %s, %s, %s FROM %s WHERE %s >= %d AND %s <= %d AND %s = '%s' ORDER BY %s ASC",
			DatabaseContract.TaskTable.TASK_NAME,
			DatabaseContract.TaskTable.CATEGORY,
			DatabaseContract.TaskTable.START_TIME,
			DatabaseContract.TaskTable.END_TIME,
			DatabaseContract.TaskTable.TABLE_NAME,
			DatabaseContract.TaskTable.START_TIME,
			startTime.getTime(),
			DatabaseContract.TaskTable.END_TIME,
			endTime.getTime(),
			DatabaseContract.TaskTable.CATEGORY,
			category,
			DatabaseContract.TaskTable.CATEGORY
		);

		Cursor cursor = db.rawQuery(sql, null);

		ArrayList<Task> results = new ArrayList<>();
		while (cursor.moveToNext()) {
			String name = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.TaskTable.TASK_NAME));
			String cat = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.TaskTable.CATEGORY)); // TODO: remove
			long start = cursor.getLong(cursor.getColumnIndexOrThrow(DatabaseContract.TaskTable.START_TIME));
			long end = cursor.getLong(cursor.getColumnIndexOrThrow(DatabaseContract.TaskTable.END_TIME));

			results.add(new Task(name, cat, start, end));
		}

		System.out.println(results);

		cursor.close();

		return results;
	}

	public ArrayList<String> getTaskNames(String category) {
		SQLiteDatabase db = dbHelper.getReadableDatabase();

		String sql = String.format(Locale.ENGLISH,
			"SELECT %s FROM %s WHERE %s = '%s' GROUP BY %s ORDER BY %s ASC",
			DatabaseContract.TaskTable.TASK_NAME,
			DatabaseContract.TaskTable.TABLE_NAME,
			DatabaseContract.TaskTable.CATEGORY,
			category,
			DatabaseContract.TaskTable.TASK_NAME,
			DatabaseContract.TaskTable.TASK_NAME
		);

		Cursor cursor = db.rawQuery(sql, null);

		ArrayList<String> taskNames = new ArrayList<>();
		while (cursor.moveToNext()) {
			taskNames.add(cursor.getString(0));
		}
		cursor.close();

		return taskNames;
	}

	// FIXME: get rid of this
	public ArrayList<Task> getAllTasksHack() {
		SQLiteDatabase db = dbHelper.getReadableDatabase();

		// TODO: _ID??
		String[] projection = {
			DatabaseContract.TaskTable.TASK_NAME,
			DatabaseContract.TaskTable.CATEGORY,
			DatabaseContract.TaskTable.START_TIME,
			DatabaseContract.TaskTable.END_TIME
		};

		Cursor cursor = db.query(DatabaseContract.TaskTable.TABLE_NAME,	projection,	null, null, null, null,	null);

		ArrayList<Task> results = new ArrayList<>();
		while (cursor.moveToNext()) {
			String name = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.TaskTable.TASK_NAME));
			String cat = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.TaskTable.CATEGORY));
			long start = cursor.getLong(cursor.getColumnIndexOrThrow(DatabaseContract.TaskTable.START_TIME));
			long end = cursor.getLong(cursor.getColumnIndexOrThrow(DatabaseContract.TaskTable.END_TIME));

			results.add(new Task(name, cat, start, end));
		}

		System.out.println(results);

		cursor.close();

		return results;
	}

	// TODO: generate list of categories first, then asynchronously update the data for them??
	public ArrayList<String> getCategories() {
		SQLiteDatabase db = dbHelper.getReadableDatabase();
		String sql = "SELECT " + DatabaseContract.TaskTable.CATEGORY +
			" FROM " + DatabaseContract.TaskTable.TABLE_NAME +
			" GROUP BY " + DatabaseContract.TaskTable.CATEGORY;

		Cursor cursor = db.rawQuery(sql, null);

		ArrayList<String> categories = new ArrayList<>();
		while (cursor.moveToNext()) {
			String category = cursor.getString(0);
			categories.add(category);
			System.out.println(category);
		}

		cursor.close();

		return categories;
	}

	// FIXME: make sure this works
	public ArrayList<Category> getCategoriesInformation(List<String> categories) {
		SQLiteDatabase db = dbHelper.getReadableDatabase();

		ArrayList<Category> results = new ArrayList<>();

		for (String category : categories) {
			String sql = String.format(Locale.ENGLISH,
				"SELECT COUNT(*), SUM(%s - %s) FROM %s WHERE %s = '%s'",
				DatabaseContract.TaskTable.END_TIME,
				DatabaseContract.TaskTable.START_TIME,
				DatabaseContract.TaskTable.TABLE_NAME,
				DatabaseContract.TaskTable.CATEGORY,
				category
			);
			Cursor cursor = db.rawQuery(sql, null);
			cursor.moveToFirst();
			int count = cursor.getInt(0);
			int duration = cursor.getInt(1);
			cursor.close();

			results.add(new Category(category, duration, count));
		}

		return results;
	}
}