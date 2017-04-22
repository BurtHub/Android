package com.huida.databasedemo;

import com.huida.databasedemo.dao.PersonDao;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

public class ContactsContentProvider extends ContentProvider {

	private static final int INSERT = 1;
	private static final int DELETE = 2;
	private static final int UPDATE = 3;
	private static final int QUERY = 4;

	//创建的时候做实始化操作
	@Override
	public boolean onCreate() {
		System.out.println("内容提供者被创建了...");
		helper = new PersonHelper(getContext());
		return false;
	}

	//URI匹配器  和添加器
	
	public static UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);
	private PersonDao dao;
	private PersonHelper helper;
	
	
	static{
		//提前规定好操作  
		matcher.addURI("com.huida.provider", "insert", INSERT);
		matcher.addURI("com.huida.provider", "delete", DELETE);
		matcher.addURI("com.huida.provider", "update", UPDATE);
		matcher.addURI("com.huida.provider", "query", QUERY);
		
	}
	
	
	@Override
	public Cursor query(Uri uri, String[] projection, String selection,String[] selectionArgs, String sortOrder) {
			
		if(matcher.match(uri)==INSERT){
			SQLiteDatabase sdb=helper.getReadableDatabase();
			return sdb.query("person", projection, selection, selectionArgs, null, null, sortOrder);
			
		}
		return null;
	}

	@Override
	public String getType(Uri uri) {
		
		return null;
	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {
		if(matcher.match(uri)==INSERT){
			SQLiteDatabase sdb = helper.getWritableDatabase();
			sdb.insert("person", null, values);
		}
		
		return null;
	}

	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		if(matcher.match(uri)==DELETE){
			SQLiteDatabase sdb = helper.getWritableDatabase();
			sdb.delete("person", selection, selectionArgs);
		}
		return 0;
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection,String[] selectionArgs) {
			if(matcher.match(uri)==UPDATE){
				SQLiteDatabase sdb=helper.getWritableDatabase();
				sdb.update("person", values, selection, selectionArgs);
			}
	
		return 0;
	}

}
