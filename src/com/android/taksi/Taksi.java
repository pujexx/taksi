package com.android.taksi;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;

import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.os.Bundle;
import android.util.Log;

import android.view.View;
import android.view.View.OnClickListener;

import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

public class Taksi extends Activity {
	static final int DATE_DIALOG_ID = 0;
	static final int TIME_DIALOG_ID = 1;
	static final int DIALOG_LOADING_ID = 2;
	private int mYear;
	private int mMonth;
	private int mDay;
	private int mHour;
	private int mMinute;
	String url = "http://10.0.2.2/taksi/index.php/api/post";
	private EditText nama, alamat, tanggal, waktu, nope, tujuan, jumlah;
	private Button send, reset;

	private String sendTanggal,status;
	private ProgressDialog Loading;
	

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		nama = (EditText) findViewById(R.id.nama);
		alamat = (EditText) findViewById(R.id.alamat);
		tanggal = (EditText) findViewById(R.id.tanggal);
		waktu = (EditText) findViewById(R.id.waktu);
		nope = (EditText) findViewById(R.id.nope);
		tujuan = (EditText) findViewById(R.id.tujuan);
		jumlah = (EditText) findViewById(R.id.jumlah);
		send = (Button) findViewById(R.id.send);
		reset = (Button) findViewById(R.id.reset);
		tanggal.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub

				showDialog(DATE_DIALOG_ID);
			}
		});
		waktu.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				showDialog(TIME_DIALOG_ID);
			}
		});
		send.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(formValidation()==false){
					Toast.makeText(Taksi.this, "Harus diisi semua", Toast.LENGTH_LONG).show();
				}else{
				Loading = new ProgressDialog(Taksi.this);
				Loading.setMessage("Procesing..");
				Loading.show();
				getRequest();
				}
			}
		});
		reset.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				resetForm();
			}
		});
		final Calendar c = Calendar.getInstance();
		mYear = c.get(Calendar.YEAR);
		mMonth = c.get(Calendar.MONTH);
		mDay = c.get(Calendar.DAY_OF_MONTH);
		mHour = c.get(Calendar.HOUR_OF_DAY);
		mMinute = c.get(Calendar.MINUTE);
	}

	// create dialog
	@Override
	protected Dialog onCreateDialog(int id) {
		// TODO Auto-generated method stub
		switch (id) {
		case DATE_DIALOG_ID:

			return new DatePickerDialog(Taksi.this, setTanggal, mYear, mMonth,
					mDay);

		case TIME_DIALOG_ID:

			return new TimePickerDialog(Taksi.this, setWaktu, mHour, mMinute,
					true);
		}
		return null;
	}

	// action tanggal
	private DatePickerDialog.OnDateSetListener setTanggal = new OnDateSetListener() {

		public void onDateSet(DatePicker view, int year, int monthOfYear,
				int dayOfMonth) {
			tanggal.setText(Integer.toString(dayOfMonth) + "-"
					+ Integer.toString(monthOfYear + 1) + "-"
					+ Integer.toString(year));
			sendTanggal = Integer.toString(year) + "-"
					+ Integer.toString(monthOfYear + 1) + "-"
					+ Integer.toString(dayOfMonth);
		}
	};
	// action waktu
	private TimePickerDialog.OnTimeSetListener setWaktu = new OnTimeSetListener() {

		public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
			// TODO Auto-generated method stub
			waktu.setText(Integer.toString(hourOfDay) + ":"
					+ Integer.toString(minute));
		}
	};

	// function reset
	public void resetForm() {
		nama.setText("");
		alamat.setText("");
		tanggal.setText("");
		waktu.setText("");
		nope.setText("");
		tujuan.setText("");
		jumlah.setText("");
	}
	public boolean formValidation(){
		if(nama.getText().toString().equals("")||alamat.getText().toString().equals("")||tanggal.getText().toString().equals("")||waktu.getText().toString().equals("")||nope.getText().toString().equals("")||tujuan.getText().toString().equals("")||jumlah.getText().toString().equals("")){
		  return false;
		  }
		else {
		return true;
		}
	}
	// request to server
	@SuppressWarnings( { "unchecked" })
	public void getRequest() {
		Log.d("getRequest", url);
		HttpClient client = new DefaultHttpClient();
		HttpPost request = new HttpPost(url);
	
		try {
			List post = new ArrayList(1);

			post.add(new BasicNameValuePair("nama", nama.getText().toString()));
			post.add(new BasicNameValuePair("alamat", alamat.getText()
					.toString()));
			post.add(new BasicNameValuePair("tanggal", sendTanggal));
			post
					.add(new BasicNameValuePair("waktu", waktu.getText()
							.toString()));
			post.add(new BasicNameValuePair("nope", nope.getText().toString()));
			post.add(new BasicNameValuePair("tujuan", tujuan.getText()
					.toString()));
			post.add(new BasicNameValuePair("jumlah", jumlah.getText()
					.toString()));
			
			request.setEntity(new UrlEncodedFormEntity(post));
			
			HttpResponse response = client.execute(request);
			status =Koneksi.request(response);
			if(status.equals("true") || status.equals("false")){
				Loading.dismiss();
				if(status.equals("true")){
					resetForm();
					
				}
				Toast.makeText(Taksi.this, status,
						Toast.LENGTH_LONG).show();

			}
			
		
		
		} catch (Exception ex) {
			Log.d("konesi", ex.toString());
		}

	}
}