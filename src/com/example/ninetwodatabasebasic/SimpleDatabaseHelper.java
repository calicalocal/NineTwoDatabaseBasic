package com.example.ninetwodatabasebasic;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class SimpleDatabaseHelper extends SQLiteOpenHelper {
	static final private String DBNAME = "sample.sqlite";
	static final private int VERSION = 1;

	public SimpleDatabaseHelper(Context context) {
		super(context, DBNAME, null, VERSION);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void onOpen(SQLiteDatabase db) {
		super.onOpen(db);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
	    db.execSQL("CREATE TABLE books (" +
	    	      "isbn TEXT PRIMARY KEY, title TEXT, price INTEGER)");
	    	    db.execSQL("INSERT INTO books(isbn, title, price)" +
	    	      " VALUES('978-4-8222-9613-1', 'アプリを作ろう！Android入門', 1995)");
	    	    db.execSQL("INSERT INTO books(isbn, title, price)" +
	    	        " VALUES('978-4-7981-2631-9', '10日でおぼえるPHP入門教室', 2709)");
	    	    db.execSQL("INSERT INTO books(isbn, title, price)" +
	    	        " VALUES('987-4-8222-9612-4', 'アプリを作ろう！HTML5入門', 1995)");
	    	    db.execSQL("INSERT INTO books(isbn, title, price)" +
	    	        " VALUES('978-4-7741-5067-3', 'HTML5開発ポケットリファレンス', 2709)");
	    	    db.execSQL("INSERT INTO books(isbn, title, price)" +
	    	      " VALUES('978-4-7741-4980-6', 'Rails 3ポケットリファレンス', 2780)");        
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		db.execSQL("DROP TABLE IF EXISTS books");
		onCreate(db);
	}

}
