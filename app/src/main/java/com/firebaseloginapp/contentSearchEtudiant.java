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


public class contentSearchEtudiant extends Fragment {

    public contentSearchEtudiant() {
        // Required empty public constructor
    }
    public WebView mWebView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        /*View v = inflater.inflate(R.layout.activity_content_search_etudiant, container, false);
        // Inflate the layout for this fragment
       //WebView myWebView = (WebView) v.findViewById(R.id.webview);
        WebView myWebView = new WebView(contentSearchEtudiant.this.getActivity());
        myWebView.loadUrl("http://www.facebook.com/");*/


        View v=inflater.inflate(R.layout.activity_content_search_etudiant, container, false);
        mWebView = (WebView) v.findViewById(R.id.webview);
        mWebView.loadUrl("http://www.fpbm.ma/");

        // Enable Javascript
        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        // Force links and redirects to open in the WebView instead of in a browser
        mWebView.setWebViewClient(new WebViewClient());

        //return inflater.inflate(R.layout.activity_content_search_etudiant, container, false);

        return v;
    }

}
