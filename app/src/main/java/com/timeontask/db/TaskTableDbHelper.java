package com.timeontask.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteTransactionListener;

public class TaskTableDbHelper extends SQLiteOpenHelper {
	public static final int DB_VERSION = 1;
	public static final String DB_NAME = "TaskTable.db";

	private static final String CREATE_ENTRIES =
		"CREATE TABLE " + TaskTableContract.TaskEntry.TABLE_NAME + " (" +
			TaskTableContract.TaskEntry._ID + " INTEGER PRIMARY KEY," +
			TaskTableContract.TaskEntry.TASK_NAME + " TEXT," +
			TaskTableContract.TaskEntry.CATEGORY + " TEXT," +
			TaskTableContract.TaskEntry.START_TIME + " INTEGER," +
			TaskTableContract.TaskEntry.END_TIME + " INTEGER)";

	public TaskTableDbHelper(Context context) {
		super(context, DB_NAME, null, DB_VERSION);
	}

	public void onCreate(SQLiteDatabase db) {
		db.execSQL(CREATE_ENTRIES);
	}

	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// FIXME
	}

	public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// FIXME
	}
}
