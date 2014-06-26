package com.example.ninetwodatabasebasic;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;
import android.os.Build;

public class MainActivity extends ActionBarActivity {
	private SimpleDatabaseHelper helper = null;
	private EditText txtIsbn = null;
	private EditText txtTitle = null;
	private EditText txtPrice = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
/*
		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
*/		
		//prepare helper
		helper = new SimpleDatabaseHelper(this);
		txtIsbn = (EditText)findViewById(R.id.txtIsbn);
		txtTitle = (EditText)findViewById(R.id.txtTitle);
		txtPrice = (EditText)findViewById(R.id.txtPrice);
	}
	
	public void onSave(View view) {
		SQLiteDatabase db = helper.getWritableDatabase();
		ContentValues cv = new ContentValues();
		cv.put("isbn", txtIsbn.getText().toString());
		cv.put("title", txtTitle.getText().toString());
		cv.put("price", txtPrice.getText().toString());
		//db.insert("books", null, cv);
		db.insertWithOnConflict("books", null, cv, SQLiteDatabase.CONFLICT_REPLACE);
		Toast.makeText(this, "データ登録成功", Toast.LENGTH_SHORT).show();
	}
	
	public void onDelete(View view) {
		String[] params = { txtIsbn.getText().toString() };
		SQLiteDatabase db = helper.getWritableDatabase();
		db.delete("books", "isbn = ?", params);
		Toast.makeText(this, "データ削除成功", Toast.LENGTH_SHORT).show();
	}
	
	public void onSearch(View view) {
		SQLiteDatabase db = helper.getReadableDatabase();
		String[] cols = { "isbn", "title", "price" };
		String[] params = { txtIsbn.getText().toString() };
		Cursor cs = db.query("books", cols, "isbn = ?", params, null, null, null, null);
		if (cs.moveToFirst()) {
			txtTitle.setText(cs.getString(1));
			txtPrice.setText(cs.getString(2));
		} else {
			Toast.makeText(this, "データなし", Toast.LENGTH_SHORT).show();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main, container,
					false);
			return rootView;
		}
	}
*/
}
