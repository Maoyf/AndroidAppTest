package com.cdivtc.androidapptest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    //第一步：定义控件变量
    private ListView mListView;

    //定义需要适配的数据
    //一个名称，是一个字符串数组，存放的是各种应用的名称
    private String[] names ={"京东商城","QQ","QQ斗地主","新浪微博","天猫","UC浏览器","微信"};
    //各个名称对应的图片，是一个int类型的数组，存放是的各种应用的图片id
    private int[] icons = {R.drawable.jd,R.drawable.qq,R.drawable.dz,R.drawable.xl,R.drawable.tm,
            R.drawable.uc,R.drawable.wx};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //第二步：初始化控件
        mListView = findViewById(R.id.lv);
        //第三步：定义一个BaseAdapter适配器的子类，并创建一个Adapter的实例
        //创建Adapter的实例
        MyAdapter myAdapter = new MyAdapter();
        //设置Adapter
        //让Adapter与控件取得联系，即设置控件的Adapter适配器
        mListView.setAdapter(myAdapter);
    }

    //定义一个子类，继承自BaseAdapter适配器
    //第四步：分别重写MyAdatper中的四个方法，通过这四个方法来获取到底层数据，并能将数据显示到对应的
    //控件中
    class MyAdapter extends BaseAdapter{

        //得到item的总数
        @Override
        public int getCount() {
            //返回ListView 中item条目的总数
            return names.length;
        }

        //得到Item代表的对象
        @Override
        public Object getItem(int position) {
            //返回ListView 中Item条目代表的对象
            return names[position];
        }

        //得到Item的id
        @Override
        public long getItemId(int position) {
            //返回ListView中Item的id
            return position;
        }

        //得到Item的View视图
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
           /* //将list_item.xml文件找出来，并转换成View对象
            //将转换后的对象 加载到ListView中
            //得到这个View对象
            View view = View.inflate(MainActivity.this, R.layout.list_item,null);
            //定义两个控件变量，并绑定（初始化），来自list_item中的两个控件
            TextView mTextView = view.findViewById(R.id.item_tv);
            ImageView mImageView = view.findViewById(R.id.item_image);
            //设置这两个对象的内容
            mTextView.setText(names[position]);
            mImageView.setBackgroundResource(icons[position]);
            return view;*/

           //定义一个ViewHolder类的对象
            ViewHolder holder;
            //判断获取的convertView是否为空
            if (convertView == null){
                //第一次加载布局对象
                convertView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.list_item,parent,false);
                //实例化一个ViewHolder类的对象
                holder = new ViewHolder();
                //将两个对象分别加载到holder中
                holder.mTextView = convertView.findViewById(R.id.item_tv);
                holder.mImageView = convertView.findViewById(R.id.item_image);
                //设置convertView的标签
                convertView.setTag(holder);
            }else {
                //调用getTag方法，得到holder中的控件，不用事先分配
                holder = (ViewHolder) convertView.getTag();
            }
            //将底层数据添加到对应的控件中
            holder.mImageView.setBackgroundResource(icons[position]);
            holder.mTextView.setText(names[position]);
            //返回视图
            return convertView;
        }
        //定义一个ViewHolder类，用来定义两个list_item中的控件变量
        class ViewHolder{
            TextView mTextView;
            ImageView mImageView;
        }
    }
}
