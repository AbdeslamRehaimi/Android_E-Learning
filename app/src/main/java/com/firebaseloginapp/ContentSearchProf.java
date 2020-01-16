package com.firebaseloginapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class ContentSearchProf extends Fragment {

    public WebView mWebView;

    public ContentSearchProf() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View v=inflater.inflate(R.layout.activity_content_search_prof, container, false);
        mWebView = (WebView) v.findViewById(R.id.webviewP);
        mWebView.loadUrl("http://www.fpbm.ma/");

        // Enable Javascript
        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        // Force links and redirects to open in the WebView instead of in a browser
        mWebView.setWebViewClient(new WebViewClient());

        //return inflater.inflate(R.layout.activity_content_search_etudiant, container, false);

        return v;

        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.activity_content_search_prof, container, false);
    }

}
