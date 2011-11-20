package com.android.taksi;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

import android.widget.Button;

public class Menu extends Activity{
	private Button pesan,info,tentang;
	private  Intent intent = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.menu);
		pesan =(Button)findViewById(R.id.pesan);
		info = (Button)findViewById(R.id.info);
		tentang = (Button)findViewById(R.id.tentang);
		pesan.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				intent =new Intent(Menu.this, Taksi.class);
				startActivity(intent);
			}
		});
		info.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				 intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://10.0.2.2/taksi"));
						
				 startActivity(intent);

			}
		});
		tentang.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
			intent = new Intent(Menu.this, About.class);
			startActivity(intent);
			}
		});
	}
	
	

}
