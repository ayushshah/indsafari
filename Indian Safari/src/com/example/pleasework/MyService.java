package com.example.pleasework;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

public class MyService extends Service {
	MediaPlayer player;
	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	public void onCreate()
	{
		super.onCreate();
		player = MediaPlayer.create(this, R.raw.mu);
		player.setLooping(false);
	}
	
	
	public int onStartCommand(Intent intent, int flags, int startId){
		player.start();
		return super.onStartCommand(intent, flags, startId);
		
	}
	public void  onDestroy(){
		player.stop();
		super.onDestroy();
		
	}
	
	
	
}
