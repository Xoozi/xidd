package com.galotec.xidd;

import java.util.Timer;
import java.util.TimerTask;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;


/**
 * splash页面
 * @author xoozi
 *
 */
public class ActivitySplash extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);
		
		_initWork();
	}
	
	
	private void _initWork(){
		Timer timer = new Timer();
		
		timer.schedule(new AutoJumpTimer(), 2000);
	}
	
	private class AutoJumpTimer extends TimerTask{
		@Override
		public void run() 
		{
			Message message = new Message();
			message.what = 1;
			_handlerJump.sendMessage(message);
		}   	
   }
	
	
	@SuppressLint("HandlerLeak")
	private Handler 	_handlerJump	= new Handler()
	{
		public void handleMessage(Message msg) 
		{
			switch (msg.what) 
			{
			case 1:
				Intent intent = new Intent(ActivitySplash.this,ActivityMain.class);
				startActivity(intent);
				finish();
				break;
			}
		};
	};

}
