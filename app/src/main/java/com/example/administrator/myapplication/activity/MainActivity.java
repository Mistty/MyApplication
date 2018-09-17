package com.example.administrator.myapplication.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.model.Content;

import java.util.ArrayList;
import java.util.List;

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
        // 2：模拟数据,后面会访问后台
        List<Content> conList = new ArrayList<Content>();
        for (int i=1;i<=20;i++){
            conList.add(new Content("imageUrl","我是标题" + i,"日期类型"));
        }

        // 不能直接添加,源码抛出异常
//        listview.addView(null);
        // 2：向listview填充数据
        listview.setAdapter(new MyAdapter(conList));
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
            TextView txt_url = (TextView)list_item.findViewById(R.id.txt_url);
            TextView txt_title = (TextView)list_item.findViewById(R.id.txt_title);
            TextView txt_date = (TextView)list_item.findViewById(R.id.txt_date);
            txt_url.setText(content.getImgUrl());
            txt_title.setText(content.getTitel());
            txt_date.setText(content.getDate());

            return list_item;
        }
    }
}



