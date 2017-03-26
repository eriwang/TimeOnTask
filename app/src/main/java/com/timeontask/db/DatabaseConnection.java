package com.timeontask.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
	public ArrayList<Task> getTasks(Date startTime, Date endTime) {
		SQLiteDatabase db = dbHelper.getReadableDatabase();

		// TODO: _ID??
		String[] projection = {
			DatabaseContract.TaskTable.TASK_NAME,
			DatabaseContract.TaskTable.CATEGORY,
			DatabaseContract.TaskTable.START_TIME,
			DatabaseContract.TaskTable.END_TIME
		};

		String selection = DatabaseContract.TaskTable.START_TIME + " >= ? AND " +
			DatabaseContract.TaskTable.END_TIME + " <= ?";
		String[] selectionArgs = {Long.toString(startTime.getTime()), Long.toString(endTime.getTime())};

		// TODO: sort order??

		Cursor cursor = db.query(
			DatabaseContract.TaskTable.TABLE_NAME,
			projection,
			selection,
			selectionArgs,
			null,
			null,
			null
		);

		ArrayList<Task> results = new ArrayList<>();
		while (cursor.moveToNext()) {
			String name = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.TaskTable.TASK_NAME));
			String category = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.TaskTable.CATEGORY));
			long start = cursor.getLong(cursor.getColumnIndexOrThrow(DatabaseContract.TaskTable.START_TIME));
			long end = cursor.getLong(cursor.getColumnIndexOrThrow(DatabaseContract.TaskTable.END_TIME));

			results.add(new Task(name, category, start, end));
		}

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

	public ArrayList<Category> getCategoriesInformation(List<String> categories) {
		SQLiteDatabase db = dbHelper.getReadableDatabase();
		String sql = "SELECT COUNT(*), SUM(? - ?) FROM ? WHERE ? = ?";

		ArrayList<Category> results = new ArrayList<>();

		for (String category : categories) {
			String[] sqlArgs = {
				DatabaseContract.TaskTable.END_TIME,
				DatabaseContract.TaskTable.START_TIME,
				DatabaseContract.TaskTable.TABLE_NAME,
				DatabaseContract.TaskTable.CATEGORY,
				category
			};
			Cursor cursor = db.rawQuery(sql, sqlArgs);
			int count = cursor.getInt(0);
			int duration = cursor.getInt(1);
			cursor.close();

			results.add(new Category(category, duration, count));
		}

		return results;
	}
}