package com.example.tj.showdemo;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

/**
 * author: zhulunjun
 * time:   2017/5/18
 * about: 测试Scheme启动app的某个页面
 */

public class SchemeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scheme);

        Intent i_getvalue = getIntent();
        String action = i_getvalue.getAction();
        String name="",age="";
        if(Intent.ACTION_VIEW.equals(action)){
            Uri uri = i_getvalue.getData();
            if(uri != null){
                name = uri.getQueryParameter("name");
                age= uri.getQueryParameter("age");

                Toast.makeText(SchemeActivity.this,"name "+name+" age "+age,Toast.LENGTH_LONG).show();
            }
        }

        TextView textView= (TextView) findViewById(R.id.tv_text);

        textView.setText(name+" "+age);
    }
}
