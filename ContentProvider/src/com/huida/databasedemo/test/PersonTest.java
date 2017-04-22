package com.huida.databasedemo.test;

import com.huida.databasedemo.dao.PersonDao;

import android.test.AndroidTestCase;

public class PersonTest extends AndroidTestCase {

	public void testPerson(){
		PersonDao dao = new PersonDao(getContext());
	}
	
	public void testAdd(){
		PersonDao dao = new PersonDao(getContext());
		for(int i=0;i<20;i++){
			dao.add("xiaoming"+i, "88888888"+i);
		}
		dao.add("lisi", "1111111");
		
	}
	public void testDelete(){
		PersonDao dao = new PersonDao(getContext());
		dao.delete("lisi");
		
	}
	
	public void testUpdate(){
		PersonDao dao = new PersonDao(getContext());
		dao.update("xiaoming", "666666666666");
		
	}
	
	
	public void testFind(){
		PersonDao dao = new PersonDao(getContext());
		dao.find("lisi");
	}
	
	public void testFindAll(){
		PersonDao dao = new PersonDao(getContext());
		dao.findAll();
		}
	
}
