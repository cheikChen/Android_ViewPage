package com.example.cheik.pageview;

import android.content.Intent;
import android.media.Image;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private  ViewPager viewPage;
    private int[] imageResIds;
    ArrayList<ImageView> imageViewList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //初始化视图
        initViews();

        //初始化数据
        initData();
        
        //控制器
        initAdapter();
    }

    private void initAdapter() {
        viewPage.setAdapter(new MyAdapter());
    }

    private void initData() {
        //图片资源数组
        imageResIds = new int[]{R.drawable.a,R.drawable.b,R.drawable.c,R.drawable.d,R.drawable.e};
        //初始化要展示的5个ImageView
        imageViewList = new ArrayList<ImageView>();
        ImageView imageView;
        for(int i = 0; i < imageResIds.length; i++){
            imageView = new ImageView(this);
            imageView.setBackgroundResource(imageResIds[i]);
            imageViewList.add(imageView);
        }
    }

    private void initViews() {
        viewPage = (ViewPager) findViewById(R.id.viewPager);
    }

    private class MyAdapter extends PagerAdapter {
        @Override
        public int getCount() {
            return imageViewList.size();
        }
        //3,指定复用的判断逻辑,固定写法
        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }
        //1,返回要显示的条目内容  创建条目
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            System.out.println("instantiateItem 初始化: "+position);
            ImageView imageView = imageViewList.get(position);

//            //a,把view添加到container 中
            container.addView(imageView);
            //b,把view返回给框架,适配器
            return imageView;
        }
        //2,销毁条目
        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            //销毁object 对象
            System.out.println("destroyItem销毁: "+position);
            container.removeView((View)object);
        }
    }
}
