package com.timeontask.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseOpenHelper extends SQLiteOpenHelper {
	public static final int DB_VERSION = 1;
	public static final String DB_NAME = "TaskTable.db";

	private static final String CREATE_ENTRIES =
		"CREATE TABLE " + DatabaseContract.TaskTable.TABLE_NAME + " (" +
			DatabaseContract.TaskTable._ID + " INTEGER PRIMARY KEY," +
			DatabaseContract.TaskTable.TASK_NAME + " TEXT," +
			DatabaseContract.TaskTable.CATEGORY + " TEXT," +
			DatabaseContract.TaskTable.START_TIME + " INTEGER," +
			DatabaseContract.TaskTable.END_TIME + " INTEGER)";

	public DatabaseOpenHelper(Context context) {
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
