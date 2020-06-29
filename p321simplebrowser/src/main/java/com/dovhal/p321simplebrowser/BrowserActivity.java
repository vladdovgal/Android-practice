package com.dovhal.p321simplebrowser;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class BrowserActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browser);

        WebView webView = findViewById(R.id.webView);
        webView.setWebViewClient(new WebViewClient());
        Uri data = getIntent().getData();
        assert data != null;
        webView.loadUrl(data.toString());
    }
}