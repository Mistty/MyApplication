package com.example.administrator.myapplication.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.model.Content;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.image.ImageOptions;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/*               View
*
*        ViewGroup    Button
*
*   RelativeLayout
* */
@ContentView(value = R.layout.activity_main)
public class MainActivity extends Activity {
    @ViewInject(R.id.lv)
    private ListView listView = null;


    @Event(value = R.id.btn_set)
    private void openSecond(View view){
        // 意圖跳轉activty
        Intent intent = new Intent();
        // aciton 隐式跳转
//        intent.setAction("android.intent.action.SECOND");
//        intent.addCategory("android.intent.category.DEFAULT");
        // 类的方式是显示跳转
        intent.setClass(this,SecondActivity.class);
        startActivity(intent);
    }



    @Override  // activity初始化时会执行
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // IOC注入必须执行当前代码
        x.view().inject(this);
        Log.i("test", this.getClass() + " onCreate........");

//        setContentView(R.layout.activity_main);
        // 1：获取listview
//        final ListView listview =  (ListView)findViewById(R.id.lv);
        RequestParams params = new RequestParams("http://hiwbs101083.jsp.jspee.com.cn/ajaxServlet");
//        params.addQueryStringParameter("wd", "xUtils");
        // 发送get请求信息
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override  // result需要转化json格式
            public void onSuccess(String result) {
                Log.i("test","result:" + result);
                // 把当前string转化json格式
                Gson gson=new Gson();
                List<Content> conList = gson.fromJson(result,new TypeToken<List<Content>>(){}.getType());
                // 不能直接添加,源码抛出异常
                //   listview.addView(null);
                // 2：向listview填充数据
                listView.setAdapter(new MyAdapter(conList));
//              Toast.makeText(x.app(), result, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Toast.makeText(x.app(), ex.getMessage(), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onCancelled(CancelledException cex) {
                Toast.makeText(x.app(), "cancelled", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFinished() {

            }
        });












    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("test", this.getClass() + " onStart........");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("test", this.getClass() + " onResume........");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("test", this.getClass() + " onPause........");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("test", this.getClass() + " onStop........");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("test", this.getClass() + " onDestroy........");
    }

    private class MyAdapter extends BaseAdapter{

        private List<Content> conList =null;

        // 在构造方法中传入
        public MyAdapter(List<Content> conList){
            this.conList = conList;
        }

        @Override  // 返回集合大小
        public int getCount() {
            return conList.size();
        }

        @Override
        public Object getItem(int position) {
            Log.i("test","position:" + position);
            return conList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override  // 把数据交给list_item,然后返回   convertView : 之前已经获取过的list_item
        public View getView(int position, View convertView, ViewGroup parent) {
            Log.i("test","position:" + position + ",view: " + convertView  + ",ViewGroup:" + parent);
            // 1: 获取当前要显示数据
            Content content =  (Content)getItem(position);
            // 2：此方法可以获取layout目录中的布局文件
            View list_item = null;
            if (convertView!=null){
                list_item = convertView;
            }else{
                list_item = View.inflate(MainActivity.this,R.layout.list_item,null);
            }
            // 3: 从list_item中获取相应的组件,并且进行赋值操作
            ImageView img_url = (ImageView)list_item.findViewById(R.id.img_url);
            TextView txt_title = (TextView)list_item.findViewById(R.id.txt_title);
            TextView txt_date = (TextView)list_item.findViewById(R.id.txt_date);
//            txt_url.setText(content.getImgUrl());
            ImageOptions options = new ImageOptions.Builder().build();
            x.image().bind(img_url, content.getImgUrl(), options);
            txt_title.setText(content.getTitel());
            txt_date.setText(content.getDate());

            return list_item;
        }
    }
}



