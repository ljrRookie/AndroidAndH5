package com.ljr.androidandh5;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class JavaAndJsActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText mUserName;
    private EditText mPassword;
    private Button mLogin;
    private WebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_java_and_js);
        initView();
    }

    private void initView() {
        mUserName = (EditText) findViewById(R.id.et_number);
        mPassword = (EditText) findViewById(R.id.et_password);
        mLogin = (Button) findViewById(R.id.btn_login);
        mLogin.setOnClickListener(this);
        initWebView();
    }

    private void initWebView() {
        mWebView = new WebView(this);
        WebSettings webSettings = mWebView.getSettings();
        //设置支持JavaScript脚步语言
        webSettings.setJavaScriptEnabled(true);
        //设置客户端--不跳转到默认浏览器
        mWebView.setWebViewClient(new WebViewClient());
        //设置支持js调用android  字段要与onclick="window.Android.showToast()中的Android一样
        mWebView.addJavascriptInterface(new AndroidAndJsInterface(),"Android");
        //加载网络资源
       // mWebView.loadUrl("http://wwww.baidu.com");
        //加载本地资源
        mWebView.loadUrl("file:///android_asset/JavaAndJs.html");
       //设置显示布局页面
      //  setContentView(webView);
    }

    @Override
    public void onClick(View v) {
        if(v == mLogin){
login();
        }
    }

    private void login() {
        String userName = mUserName.getText().toString().trim();
        String password = mPassword.getText().toString().trim();
        if (TextUtils.isEmpty(userName) || TextUtils.isEmpty(password)) {
            Toast.makeText(JavaAndJsActivity.this, "账号或者密码为空", Toast.LENGTH_SHORT).show();
        } else {
            //登录
           jsCallLogin(userName);
        }
    }

    /**
     * Java调用js
     * @param userName：参数
     */
    private void jsCallLogin(String userName) {
        mWebView.loadUrl("javascript:javaCallJs("+"'"+userName+"'"+")");
        setContentView(mWebView);
    }

    /**
     * js可以调用该类的方法
     */
    private class AndroidAndJsInterface {
        @JavascriptInterface
        public void showToast(){
            Toast.makeText(JavaAndJsActivity.this, "我是android我被js调用了", Toast.LENGTH_SHORT).show();
        }
    }
}
