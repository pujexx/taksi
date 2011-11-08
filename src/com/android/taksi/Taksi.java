package com.android.taksi;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

public class Taksi extends Activity {
	static final int DATE_DIALOG_ID = 0;
	/** Called when the activity is first created. */
	String url = "http://10.0.2.2/taksi/index.php/api/post";
	private EditText nama,alamat,tanggal,waktu,nope,tujuan,jumlah;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        nama =(EditText)findViewById(R.id.nama);
        alamat = (EditText)findViewById(R.id.alamat);
        tanggal = (EditText)findViewById(R.id.tanggal);
        waktu = (EditText)findViewById(R.id.waktu);
        nope =(EditText)findViewById(R.id.nope);
        tujuan = (EditText)findViewById(R.id.tujuan);
        jumlah = (EditText)findViewById(R.id.jumlah);
    tanggal.setOnClickListener(new OnClickListener() {
		
		public void onClick(View v) {
			// TODO Auto-generated method stub
			
		showDialog(DATE_DIALOG_ID);	
		}
	});
    }
    
    @Override
	protected Dialog onCreateDialog(int id) {
		// TODO Auto-generated method stub
    	switch(id){
    	case DATE_DIALOG_ID :
    		return new DatePickerDialog(Taksi.this,null, 2011, 1, 1);
    	    	}
		return null;
	}

	public void getRequest(EditText txtResult){
        Log.d("getRequest",url);
         HttpClient client = new DefaultHttpClient();
         HttpPost request = new HttpPost(url);
       
         try{
        	 List post = new ArrayList(1);
             HttpResponse response = client.execute(request);
             //
             //txtResult.setText(Koneksi.request(response));
         }catch(Exception ex){
             //txtResult.setText("Failed Connect to server!");
         }

     }
}