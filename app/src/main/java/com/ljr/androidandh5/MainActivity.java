package com.ljr.androidandh5;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button mJavaTojs;
    private Button mJavaTojsVideo;
    private Button mJavaTojsCall;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mJavaTojs = (Button) findViewById(R.id.javaTojs);
        mJavaTojsVideo = (Button) findViewById(R.id.javaTojs_video);
        mJavaTojsCall = (Button) findViewById(R.id.javaTojs_call);
        mJavaTojs.setOnClickListener(this);
        mJavaTojsVideo.setOnClickListener(this);
        mJavaTojsCall.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v == mJavaTojs){
            startActivity(new Intent(this,JavaAndJsActivity.class));
        }else if(v == mJavaTojsVideo){
            startActivity(new Intent(this,JsCallJavaVideoActivity.class));

        }else if(v == mJavaTojsCall){
            startActivity(new Intent(this,JsCallJavaCallPhoneActivity.class));

        }
    }
}
