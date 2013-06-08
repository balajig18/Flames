package org.fun.flames.bgsource;

import java.sql.SQLException;
import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataStore {

	public static final String Key_RowId = "ID";
	public static final String Key_Name = "Your_Name";
	public static final String Key_Crush = "Your_Crush";
	public static final String Key_Relation = "Relation";

	private static final String Database_Name = "Flamesdb";
	private static final String Table_Name = "History";
	private static final int Database_Version = 1;

	private final Context ourContext;
	private DBHelper db;
	private SQLiteDatabase ourdb;

	private static class DBHelper extends SQLiteOpenHelper {

		public DBHelper(Context context) {
			super(context, Database_Name, null, Database_Version);
			// TODO Auto-generated constructor stub
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			// TODO Auto-generated method stub
			db.execSQL("CREATE TABLE " + Table_Name + " (" + Key_RowId
					+ " INTEGER PRIMARY KEY AUTOINCREMENT, " + Key_Name
					+ " TEXT NOT NULL, " + Key_Crush + " TEXT NOT NULL, "
					+ Key_Relation + " TEXT NOT NULL);");

		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {
			// TODO Auto-generated method stub
			db.execSQL("DROP TABLE IF EXISTS " + Table_Name);
			onCreate(db);
		}

	}

	public DataStore(Context context) {
		// TODO Auto-generated constructor stub
		ourContext = context;
	}

	public DataStore open() throws SQLException {
		db = new DBHelper(ourContext);
		ourdb = db.getWritableDatabase();
		return this;
	}

	public void close() {
		db.close();
	}

	public long createEntry(String _Name1, String _Name2, String result) {
		// TODO Auto-generated method stub
		ContentValues cv = new ContentValues();
		cv.put(Key_Name, _Name1);
		cv.put(Key_Crush, _Name2);
		cv.put(Key_Relation, result);
		return ourdb.insert(Table_Name, null, cv);
	}

	public ArrayList<String> getEntry() {
		// TODO Auto-generated method stub
		ArrayList<String> info = new ArrayList<String>();
		String columns[] = new String[] { Key_RowId, Key_Name, Key_Crush,
				Key_Relation };
		String result;
		Cursor c = ourdb.query(Table_Name, columns, null, null, null, null,
				null);

		int iRow = c.getColumnIndex(Key_RowId);
		int iName = c.getColumnIndex(Key_Name);
		int iCrush = c.getColumnIndex(Key_Crush);
		int iRelation = c.getColumnIndex(Key_Relation);

		for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
			result = c.getString(iRow) + "-" + c.getString(iName) + "-"
					+ c.getString(iCrush) + "-" + c.getString(iRelation);
			info.add(result);
		}
		return info;
	}

}
