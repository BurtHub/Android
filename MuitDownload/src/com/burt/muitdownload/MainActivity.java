package com.burt.muitdownload;

import java.io.File;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener {
	private String path="http://10.0.2.2:8080/download.zip";
	        
	private int ThreadCount=3;//开启多线程数量
	private DownControl control=new DownControl();
	private ProgressBar bar;
	private static int process=0;
	private Handler handler=new Handler(){
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case 0:
				text.setText("下载中");
				break;
			case 1:
				text.setText("暂停下载");
				break;
			case 2:
				text.setText("下载失败");
				break;
			case 3:
				bar.setProgress(process++);
				break;
			case 4:
				text.setText("下载完成");
				break;
			}
		};};
	private TextView text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_main);
        InitViews();
        
      
    }
    private void InitViews() {
		control.flag=0;//默认零为开始，1为暂停
		Button bt=(Button) findViewById(R.id.button1);
		Button bt1=(Button) findViewById(R.id.button2);
		bt.setOnClickListener(this);
		bt1.setOnClickListener(this);
		text = (TextView) findViewById(R.id.textView1);
		bar = (ProgressBar) findViewById(R.id.progressBar1);
		bar.setMax(1000);
		
	}
	public void Download(){
    	new Thread(){
    		public void run(){
    			try {
					URL url=new URL(path);
					
					HttpURLConnection conn=(HttpURLConnection) url.openConnection();					
					conn.setConnectTimeout(5000);					
					conn.setRequestMethod("GET");										
					int code=conn.getResponseCode();
					System.out.println(code);
					if(code==200){//服务器成功链接
						
						int Length = conn.getContentLength();
						RandomAccessFile file=new RandomAccessFile(Environment.getExternalStorageDirectory().getPath()+File.separator+"temp.zip", "rw");						
						file.setLength(Length);//设置文件大小
						file.close();
						
						int blocksize=Length/ThreadCount;
						int finish=0;
						int start=0;
						for(int i=0;i<ThreadCount;i++){
							
							start=i*blocksize;
							finish=(i+1)*blocksize-1;
							if(i==(ThreadCount-1)){finish=Length-1;}//finish=Length-start+1
							
							new ChildThread(handler,start, finish, ThreadCount, i,control).start();
						}
						//0 1 2    3 4 5    6 7 8 9
						
						//onactvoit(int)
						//intectxt
					}
					
				} catch (Exception e) {
					
				}
    		};
    	}.start();
    }
    public void Stop(){
    	control.flag=1;
    }
	@Override
	public void onClick(View v) {
		if(v.getId()==R.id.button1){//下载			
			control.flag=0;
			bar.setProgress(0);
			Download();						
		}else if(v.getId()==R.id.button2){//暂停
			Stop();						
		}
		
	}

}
