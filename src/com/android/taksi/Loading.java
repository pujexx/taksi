package com.android.taksi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class Loading extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.loading);
		Thread splashThread = new Thread() {
	         @Override
	         public void run() {
	            try {
	               int waited = 0;
	               while (waited < 5000) {
	                  sleep(100);
	                  waited += 100;
	               }
	            } catch (InterruptedException e) {
	               // do nothing
	            } finally {
	               finish();
	               Intent i = new Intent();
	               i.setClassName("com.android.taksi",
	                              "com.android.taksi.Menu");
	               startActivity(i);
	            }
	         }
	      };
	      splashThread.start();
	}

}
