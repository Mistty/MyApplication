package com.example.administrator.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;
/*               View
*
*        ViewGroup    Button
*
*   RelativeLayout
* */
public class MainActivity extends Activity {

    @Override  // activity初始化时会执行
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // 1：获取listview
        ListView listview =  (ListView)findViewById(R.id.lv);
        // 不能直接添加,源码抛出异常
//        listview.addView(null);
        // 2：向listview填充数据
    }
}
