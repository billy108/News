package com.example.administrator.news.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import com.example.administrator.news.R;
import com.example.administrator.news.base.BaseFragment;
import com.example.administrator.news.base.BasePage;
import com.example.administrator.news.home.FunctionPage;
import com.example.administrator.news.home.GovAffairsPage;
import com.example.administrator.news.home.NewsCenterPage;
import com.example.administrator.news.home.SettingPage;
import com.example.administrator.news.home.SmartServicePage;
import com.example.administrator.news.view.CustomViewPager;
import com.example.administrator.news.view.LazyViewPager;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends BaseFragment {
    @ViewInject(R.id.viewpager)
    private CustomViewPager viewPager;
    @ViewInject(R.id.main_radio)
    private RadioGroup main_radio;
    private int checkId = R.id.rb_function;
    private View view;

    List<BasePage> list = new ArrayList<BasePage>();
    @Override
    public void initData(Bundle savedInstanceState) {
        list.add(new FunctionPage(ct));
        list.add(new NewsCenterPage(ct));
        list.add(new SmartServicePage(ct));
        list.add(new GovAffairsPage(ct));
        list.add(new SettingPage(ct));

        HomePageAdapter adapter = new HomePageAdapter(ct, list);
        viewPager.setAdapter(adapter);
        viewPager.setOnPageChangeListener(new LazyViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
//                if (0 == position) {
//                    sm.setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);
//                } else {
//                    sm.setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE);
//                }
                BasePage page = list.get(position);
                page.initData();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        main_radio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_function:
                        viewPager.setCurrentItem(0, false);
//                        checkedId = 0;
                        break;
                    case R.id.rb_news_center:
                        viewPager.setCurrentItem(1, false);
//                        checkedId = 1;
                        break;
                    case R.id.rb_smart_service:
                        viewPager.setCurrentItem(2, false);
//                        checkedId = 2;
                        break;
                    case R.id.rb_gov_affairs:
                        viewPager.setCurrentItem(3, false);
//                        checkedId = 3;
                        break;
                    case R.id.rb_setting:
                        viewPager.setCurrentItem(4, false);
//                        checkedId = 4;
                        break;
                }
            }
        });
    }

    class HomePageAdapter extends PagerAdapter {
        private Context ct;
        private List<BasePage> list;

        public HomePageAdapter(Context ct, List<BasePage> list) {
            this.ct = ct;
            this.list = list;
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            ((CustomViewPager) container).removeView(list.get(position).getRootView());
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ((CustomViewPager) container).addView(list.get(position).getRootView(), 0);
            return list.get(position).getRootView();
        }
    }

    @Override
    public View initView(LayoutInflater inflater) {
        view = inflater.inflate(R.layout.frag_home2, null);
        ViewUtils.inject(this, view);
        return view;
    }
}

