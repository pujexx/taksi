package com.android.taksi;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;

public class About extends Activity{
	private WebView webView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.about);
		webView = (WebView)findViewById(R.id.webview);
		String html = "<html><body>Hello, World!</body></html>";
		String mime = "text/html";
		String encoding = "utf-8";
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadDataWithBaseURL(null, html, mime, encoding, null);
	}

}
