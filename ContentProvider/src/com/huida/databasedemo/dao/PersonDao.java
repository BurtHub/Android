package com.huida.databasedemo.dao;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.huida.databasedemo.PersonHelper;
import com.huida.databasedemo.bean.Contact;

public class PersonDao {
	
	
	private PersonHelper helper;

	public PersonDao(Context context){
		helper = new PersonHelper(context);
		helper.getReadableDatabase();
		
		/*File file = new File("aa.txt");
		FileOutputStream fos;
		try {
			fos = new FileOutputStream(file);
			fos.write("asdf".getBytes());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		
	}
	public void add(String name,String number){
		SQLiteDatabase db = helper.getWritableDatabase();
		//db.execSQL("insert into person(name,number)values(?,?)",new Object[]{name,number});
		ContentValues values = new ContentValues();
		values.put("name", name);
		values.put("number", number);
		db.insert("person", null, values);
		db.close();
		
	}
	
	
	public void delete(String name){
		SQLiteDatabase db = helper.getWritableDatabase();
		//db.execSQL("delete from person where name = ?", new Object[]{name});
		db.delete("person", "name=?", new String[]{name});
		db.close();
		
	}
	
	public void update(String name,String number){
		SQLiteDatabase db = helper.getWritableDatabase();
		//db.execSQL("update person set number = ? where name= ?", new Object[]{number,name});
		ContentValues values = new ContentValues();
		values.put("number", number);
		db.update("person", values, "name=?", new String[]{name});
		db.close();
		
	}
	
	public void find(String name){
		SQLiteDatabase db = helper.getReadableDatabase();
		//db.execSQL("select * from person where name = ?", new Object[]{name});
		//结果集
		//Cursor cursor = db.rawQuery("select * from person where name = ?", new String[]{name});
		Cursor cursor = db.query("person", new String[]{"name","number"}, "name=?", new String[]{name}, null, null, null);
		boolean result = cursor.moveToNext();
		System.out.println("result="+result);
		db.close();
	}
	
	public List<Contact> findAll(){
		List<Contact> list = new ArrayList<Contact>();
		SQLiteDatabase db = helper.getReadableDatabase();
		//Cursor cursor = db.rawQuery("select * from person", null);
		Cursor cursor = db.query("person",  new String[]{"id","name","number"}, null, null, null, null, null);
		while(cursor.moveToNext()){
			
			String id = cursor.getString(cursor.getColumnIndex("id"));
			
			String name = cursor.getString(cursor.getColumnIndex("name"));
			
			String number = cursor.getString(cursor.getColumnIndex("number"));
			
			System.out.println("id="+id+";name="+name+";number="+number);
			
			Contact c = new Contact(id, name, number);
			
			list.add(c);
			
			
		}
		return list;
		
	}
	
	
	public void trans(){
		SQLiteDatabase db = helper.getReadableDatabase();
		db.beginTransaction();
		//中间的操作
		db.endTransaction();
		
	}

}
