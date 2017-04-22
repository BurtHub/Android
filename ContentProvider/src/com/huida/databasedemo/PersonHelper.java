package com.huida.databasedemo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class PersonHelper extends SQLiteOpenHelper {

	//���ݿ������Ĺ��췽��:ȷ�����ݿ�����ƺͰ汾��
	public PersonHelper(Context context) {
		super(context, "person.db", null, 1);
		
	}

	//�������ݿ��ʱ��,���ݿ�ĳ�ʼ�����������ڴ˷�����,һ����԰ѱ�ĳ�ʼ�����ڴ˷�����
	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("create table person(id integer primary key,name varchar(20),number varchar(20))");

	}

	//�����ݿ�汾���µ�ʱ��ִ�д˷���  2
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		
		
	}

}
