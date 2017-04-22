package com.burt.muitdownload;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;

import android.os.Environment;
import android.os.Handler;

public class ChildThread extends Thread {
	// ��ʲôʱ��ʼ����
	// ��ʲôλ�ÿ�ʼ����
	// ������ɺ������ʾ����ʾ���߳�
	private String path = "http://10.0.2.2:8080/download.zip";
	private static int RunningThread;
	private int ThreadCount;
	private int start;
	private int finish;
	private int id;
	private DownControl control;
	private Handler handler;
	
	public ChildThread(Handler handler,int start, int finish, int Running, int id,DownControl control) {
		this.start = start;
		this.finish = finish;
		this.id = id;
		this.control=control;
		this.handler=handler;
		RunningThread = Running;
		ThreadCount=Running;

	}

	@Override
	public void run() {
		URL url;
		try {

			url = new URL(path);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setConnectTimeout(5000);
			
			RandomAccessFile file = new RandomAccessFile(Environment
					.getExternalStorageDirectory().getPath()
					+ File.separator
					+ "temp.zip", "rw");
			conn.setRequestProperty("Range", "bytes=" + start + "-" + finish);
			int code = conn.getResponseCode();
			System.out.println(code);
			if (code == 206) {
				/* �ϵ�������Ҫ��ö�ȡ�ļ��Ŀ�ʼλ�ã���ȡ���� */
				ReadFile();
				handler.sendEmptyMessage(0);//����UIΪ��ʼ����
				
				file.seek(start);
				InputStream stream = conn.getInputStream();
				int len = 0;
				byte[] ruf = new byte[1024 * 1024];
				while ((len = stream.read(ruf)) != -1) {
					file.write(ruf, 0, len);
					/* �ϵ�������Ҫд���´��ļ��Ŀ�ʼλ�ã�д�뷽�� */
					WriteFile(len);
					handler.sendEmptyMessage(3);//����UI������
					if(control.flag==1){												
						handler.sendEmptyMessage(1);//����UIΪ��ͣ����
						Thread.currentThread().interrupt();
					}
					
				}

				stream.close();
				synchronized (DownControl.class) {
					RunningThread--;
					if (RunningThread == 0) {
					DeleteFile();
					handler.sendEmptyMessage(4);//����UIΪ���ؽ���					
					}
				}				
			}

		} catch (Exception e) {
			handler.sendEmptyMessage(2);//����UIΪ����ʧ��
		}
	}

	private void ReadFile() throws Exception {
		File file = new File(Environment.getExternalStorageDirectory()
				.getPath() + File.separator + id + ".txt");
		if (file.exists() && file.length() != 0) {
			
				FileInputStream is = new FileInputStream(file);
				BufferedReader bfr = new BufferedReader(new InputStreamReader(
						is));
				start = Integer.parseInt(bfr.readLine());
				is.close();
			

		}
	}
//setdate(mianbao)
	private void WriteFile(int len) throws Exception {
		
			RandomAccessFile file = new RandomAccessFile(Environment
					.getExternalStorageDirectory().getPath()
					+ File.separator
					+ id + ".txt", "rwd");
			int currentPos = start + len;
			file.write((currentPos + "").getBytes());
			file.close();
	}	
	private void DeleteFile(){
		for(int i=0;i<ThreadCount;i++){
		File file = new File(Environment.getExternalStorageDirectory()
				.getPath() + File.separator + i+ ".txt");
		file.delete();
		}
	}
	

}
