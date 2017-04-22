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
	// 从什么时候开始下载
	// 从什么位置开始下载
	// 下载完成后进行提示，提示主线程
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
				/* 断点续传需要获得读取文件的开始位置，读取方法 */
				ReadFile();
				handler.sendEmptyMessage(0);//更新UI为开始下载
				
				file.seek(start);
				InputStream stream = conn.getInputStream();
				int len = 0;
				byte[] ruf = new byte[1024 * 1024];
				while ((len = stream.read(ruf)) != -1) {
					file.write(ruf, 0, len);
					/* 断点续传需要写入下次文件的开始位置，写入方法 */
					WriteFile(len);
					handler.sendEmptyMessage(3);//更新UI进度条
					if(control.flag==1){												
						handler.sendEmptyMessage(1);//更新UI为暂停下载
						Thread.currentThread().interrupt();
					}
					
				}

				stream.close();
				synchronized (DownControl.class) {
					RunningThread--;
					if (RunningThread == 0) {
					DeleteFile();
					handler.sendEmptyMessage(4);//更新UI为下载结束					
					}
				}				
			}

		} catch (Exception e) {
			handler.sendEmptyMessage(2);//更新UI为下载失败
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
