package com.example.tj.showdemo.fun;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.tj.showdemo.R;

/**
 * author: zhulunjun
 * time:   2017/5/16
 * about:
 */
public class TestFuncActivity extends AppCompatActivity {

    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_func);
        textView= (TextView) findViewById(R.id.tv_activity_msg);
        FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.frame_fragment,new FuncFragment(),FuncFragment.class.getName());
        transaction.commit();

        findViewById(R.id.btn_test_scheme).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            toUrl("scheme_diver://diver/openwith?name=adfad&age=22");

            }
        });
        findViewById(R.id.btn_activity).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                startActivity(new Intent(TestFuncActivity.this,SchemeActivity.class));
                toUrl("scheme_shipper://shipper/openwith?name=shipper&age=22");
            }
        });
    }


    private void toUrl(String url){
        Intent intent=new Intent();
        intent.setAction("android.intent.action.VIEW");
        Uri content_url = Uri.parse(url);
        intent.setData(content_url);
        startActivity(intent);
    }
    /**
     * 设置fragment的监听
     */
    public void setFunction(){
        FragmentManager manager=getSupportFragmentManager();
        FuncFragment funcFragment= (FuncFragment) manager.findFragmentByTag(FuncFragment.class.getName());
        Function function=new Function().addFunction(new Function.FunctionNotParamNotResult(FuncFragment.class.getName()) {
            @Override
            public void function() {
                //这里收到fragment发过来的消息
                textView.setText("收到来自fragment的消息"+System.currentTimeMillis());
                Log.d("收到消息"," "+ SystemClock.currentThreadTimeMillis());
            }
        });
        //有参数有返回值
        Function function1=new Function().addFunction(new Function.FunctionWithParamWithResult<String, String>(FuncFragment.class.getName()) {
            @Override
            public String function(String s) {
                textView.setText("收到来自fragment的消息"+s+" "+System.currentTimeMillis());
                return "回调之后的返回值";
            }
        });
        funcFragment.setFunction(function1);

    }
}
