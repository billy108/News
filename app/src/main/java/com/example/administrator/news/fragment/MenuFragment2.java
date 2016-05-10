package com.example.administrator.news.fragment;


import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.administrator.news.R;
import com.example.administrator.news.base.BaseFragment;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;

public class MenuFragment2 extends BaseFragment{
    private View view;
    @ViewInject(R.id.lv_menu_news_center)
    private ListView lv_menu_news_center;
    private MenuAdapter adapter;

    @Override
    public void initData(Bundle savedInstanceState) {

    }

    @Override
    public View initView(LayoutInflater inflater) {
        view = inflater.inflate(R.layout.layout_left_menu, null);
        ViewUtils.inject(this, view);
        lv_menu_news_center.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                adapter.setCurPostion(position);
                sm.toggle();
            }
        });
        return view;
    }

    private List<String> menuList = new ArrayList<String>();
    public void initMenu(List<String> menuNewCenterList) {
        menuList.clear();
        menuList = menuNewCenterList;
        if (adapter == null) {
            adapter = new MenuAdapter(ct, menuList);
            lv_menu_news_center.setAdapter(adapter);
        } else {
            adapter.notifyDataSetChanged();
        }
        adapter.setCurPostion(0);
    }

    class MenuAdapter extends BaseAdapter{
        private Context ct;
        private List<String> menuList = new ArrayList<String>();
        private int curPostion = 0;

        public MenuAdapter(Context ct, List<String> menuList) {
            this.ct = ct;
            this.menuList = menuList;
        }

        public void setCurPostion(int postion){
            this.curPostion = postion;
            notifyDataSetChanged();
        }

        @Override
        public int getCount() {
            return menuList.size();
        }

        @Override
        public Object getItem(int position) {
            return menuList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = view.inflate(ct, R.layout.layout_item_menu, null);
            }
            TextView tv = (TextView) convertView.findViewById(R.id.tv_menu_item);
            ImageView iv = (ImageView) convertView.findViewById(R.id.iv_menu_item);
            tv.setText(menuList.get(position));
            if (curPostion == position) {
                tv.setTextColor(getResources().getColor(R.color.red));
                iv.setBackgroundResource(R.drawable.menu_arr_select);
                convertView.setBackgroundResource(R.drawable.menu_item_bg_select);
            } else {
                tv.setTextColor(getResources().getColor(R.color.white));
                iv.setBackgroundResource(R.drawable.menu_arr_normal);
                convertView.setBackgroundResource(android.R.color.transparent);
            }
            return convertView;
        }
    }
}
