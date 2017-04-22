package com.huida.databasedemo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class PersonHelper extends SQLiteOpenHelper {

	//数据库帮助类的构造方法:确定数据库的名称和版本号
	public PersonHelper(Context context) {
		super(context, "person.db", null, 1);
		
	}

	//创建数据库的时候,数据库的初始化操作都放在此方法里,一般可以把表的初始化放在此方法里
	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("create table person(id integer primary key,name varchar(20),number varchar(20))");

	}

	//当数据库版本更新的时候执行此方法  2
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		
		
	}

}
