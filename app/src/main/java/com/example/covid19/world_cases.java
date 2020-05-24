package com.example.covid19;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.example.covid19.Symptomhelper.Symptoms;

public class world_cases extends AppCompatActivity {
WebView webView;
ImageView backworld;
private String url="https://www.worldometers.info/coronavirus/";
ProgressDialog progress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_world_cases);
        webView=findViewById(R.id.webview);
        backworld=findViewById(R.id.back_world);
        startWebView(webView,url);
        backworld.setClickable(true);
        backworld.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(world_cases.this, MainActivity.class);
                finish();
                ///////

            }
        });

    }


    private void startWebView(WebView webView,String url) {
        webView.setWebViewClient(new WebViewClient() {

            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return false;
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
            }

            public void onLoadResource (WebView view, String url) {

                if (progress == null) {
                    progress =new ProgressDialog(world_cases.this);
                    progress.show();
                    progress.setContentView(R.layout.progress_dialog);
                    progress.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                }

            }
            public void onPageFinished(WebView view, String url) {
                if(progress!=null && progress.isShowing()) {
                    progress.dismiss();
                    progress.hide();
                }
                super.onPageFinished(view, url);
            }

        });

        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(url);
    }
    }

