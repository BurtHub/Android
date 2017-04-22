package com.huida.databasedemo;

import java.util.List;

import com.huida.databasedemo.bean.Contact;
import com.huida.databasedemo.dao.PersonDao;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends Activity {

	private List<Contact> list;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		PersonDao dao = new PersonDao(this);
		
		list = dao.findAll();
		
		
		ListView lv = (ListView) findViewById(R.id.lv);
		
		lv.setAdapter(new MyAdapter());
		
	}

	class MyAdapter extends BaseAdapter{

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return list.size();
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View view = View.inflate(MainActivity.this, R.layout.listviewitem, null);
			
			//ÕÒ¿Ø¼þ
			TextView tv_id = (TextView) view.findViewById(R.id.tv_id);
			TextView tv_name = (TextView) view.findViewById(R.id.tv_name);
			TextView tv_number = (TextView) view.findViewById(R.id.tv_number);
			
			
			//¸³Öµ
			
			tv_id.setText(list.get(position).getId());
			tv_name.setText(list.get(position).getName());
			tv_number.setText(list.get(position).getNumber());
			
			
			return view;
		}
		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return 0;
		}

		
		
	}
	

}
