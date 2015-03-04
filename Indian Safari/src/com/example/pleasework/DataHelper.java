package com.example.pleasework;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

public class DataHelper {
	private Context context;
	private SQLiteDatabase db;
	public static final String COLUMN_NAME = "names";

	DataHelper(Context context) {
		this.context = context;
		OpenHelper openHelper = new OpenHelper(context);
		db = openHelper.getWritableDatabase();
	}

	public void insert(String a, String b, String c, String d, String e,
			String f, String g, String h, String i) {

		String sqlcommand = "INSERT INTO tblhotels(name, address, state, phone, fax, email, website, type, rooms) VALUES('"
				+ a
				+ "','"
				+ b
				+ "','"
				+ c
				+ "','"
				+ d
				+ "','"
				+ e
				+ "','"
				+ f
				+ "','" + g + "','" + h + "','" + i + "')";

		db.beginTransactionNonExclusive();
		SQLiteStatement stmt = db.compileStatement(sqlcommand);
		stmt.execute();
		stmt.clearBindings();
		db.setTransactionSuccessful();
		db.endTransaction();
	}

	public void insert2(String a, String b, String c, String d, String e,
			String f, String g, String h, String i, String j) {

		String sqlcommand = "INSERT INTO tbloperators(name, address, phone, fax, email, region, city, state, person, type) VALUES('"
				+ a
				+ "','"
				+ b
				+ "','"
				+ c
				+ "','"
				+ d
				+ "','"
				+ e
				+ "','"
				+ f
				+ "','" + g + "','" + h + "','" + i + "','" + j + "')";

		db.beginTransactionNonExclusive();
		SQLiteStatement stmt = db.compileStatement(sqlcommand);
		stmt.execute();
		stmt.clearBindings();
		db.setTransactionSuccessful();
		db.endTransaction();
	}

	void deleteAll() {
		db.delete(OpenHelper.HOTEL_TABLE_NAME, null, null);
	}

	void deleteAll2() {
		db.delete(OpenHelper.HOTEL_TABLE_OPERATORS, null, null);
	}

	public List<String> selectAll(String state, String type) {

		final ArrayList<String> names = new ArrayList<String>();
		String query;
		if (type.equals("All")) {
			query = "SELECT _id, name FROM tblhotels WHERE state='" + state
					+ "' ORDER BY name ASC";

		} else {

			query = "SELECT _id, name FROM tblhotels WHERE state='" + state
					+ "' AND type='" + type + "' ORDER BY name ASC";
		}
		final Cursor c = db.rawQuery(query, null);

		if (c != null) {
			if (c.moveToFirst()) {
				do {
					final String name = c.getString(c.getColumnIndex("name"));
					names.add(name);
				} while (c.moveToNext());
			}
		}
		c.close();

		return names;
	}

	public List<String> selectAll2(String region, String city, String state,
			String type) {

		final ArrayList<String> names = new ArrayList<String>();

		String query = "SELECT _id, name FROM tbloperators WHERE region = '"
				+ region + "'" + "AND city = '" + city + "' AND state = '"
				+ state + "' AND type = '" + type + "' ";

		final Cursor c2 = db.rawQuery(query, null);

		if (c2 != null) {
			if (c2.moveToFirst()) {
				do {
					final String name = c2.getString(c2.getColumnIndex("name"));
					names.add(name);
				} while (c2.moveToNext());
			}
		}
		c2.close();

		return names;
	}
	
	public List<String> spinContent(String value1, String tableName, String value2, String value3){
		final ArrayList<String> names = new ArrayList<String>();

		String query = "SELECT DISTINCT "+value1+" FROM "+tableName+" WHERE "+value2+" = '"+value3+"'";
				
		final Cursor c2 = db.rawQuery(query, null);

		if (c2 != null) {
			if (c2.moveToFirst()) {
				do {
					final String name = c2.getString(c2.getColumnIndex(value1));
					names.add(name);
				} while (c2.moveToNext());
			}
		}
		c2.close();

		return names;		
	}
	
	public List<String> spinContent2(String value1, String tableName, String value2, String value3, String value4, String value5){
		final ArrayList<String> names = new ArrayList<String>();

		String query = "SELECT DISTINCT "+value1+" FROM "+tableName+" WHERE "+value2+" = '"+value3+"' AND "+value4+"= '"+value5+"'";
				
		final Cursor c2 = db.rawQuery(query, null);

		if (c2 != null) {
			if (c2.moveToFirst()) {
				do {
					final String name = c2.getString(c2.getColumnIndex(value1));
					names.add(name);
				} while (c2.moveToNext());
			}
		}
		c2.close();

		return names;		
	}
	
	
	
	
	public Operators selectAllDetails2(String name, String state, String type) {

		Operators operator = new Operators();
		String query2 = "SELECT * FROM tbloperators WHERE name='" + name + "' AND state = '"+state+"' AND type = '"+type+"'";
		final Cursor c = db.rawQuery(query2, null);
		if (c != null) {
			if (c.moveToFirst()) {
				operator.setName(c.getString(c.getColumnIndex("name")));
				operator.setAddress(c.getString(c.getColumnIndex("address")));
				operator.setCity(c.getString(c.getColumnIndex("city")));
				operator.setFax(c.getString(c.getColumnIndex("fax")));
				operator.setMail(c.getString(c.getColumnIndex("email")));
				operator.setPerson(c.getString(c.getColumnIndex("person")));
				operator.setPhone(c.getString(c.getColumnIndex("phone")));
				operator.setType(c.getString(c.getColumnIndex("type")));
				operator.setState(c.getString(c.getColumnIndex("state")));
				operator.setRegion(c.getString(c.getColumnIndex("region")));
				;
			}
		}
		c.close();
		return operator;
	}

	public Hotels selectAllDetails(String hotel_name, String hotel_state) {

		Hotels hotel = new Hotels();
		String query2 = "SELECT * FROM tblhotels WHERE state='" + hotel_state
				+ "' AND name='" + hotel_name + "'";
		final Cursor c = db.rawQuery(query2, null);
		if (c != null) {
			if (c.moveToFirst()) {
				hotel.setName(c.getString(c.getColumnIndex("name")));
				hotel.setAddress(c.getString(c.getColumnIndex("address")));
				hotel.setPhone(c.getString(c.getColumnIndex("phone")));
				hotel.setEmail(c.getString(c.getColumnIndex("email")));
				hotel.setRooms(c.getString(c.getColumnIndex("rooms")));
				hotel.setWebsite(c.getString(c.getColumnIndex("website")));
				hotel.setFax(c.getString(c.getColumnIndex("fax")));
				hotel.setType(c.getString(c.getColumnIndex("type")));
			}
		}
		c.close();
		return hotel;
	}

}
