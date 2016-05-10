package com.example.administrator.news.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.example.administrator.news.base.BasePage;


public class FunctionPage extends BasePage {

	public FunctionPage(Context ct) {
		super(ct);
	}

	@Override
	public View initView(LayoutInflater inflater) {
		TextView textView = new TextView(ct);
		textView.setText("我是首页");
		return textView;
	}

	@Override
	public void initData() {

	}

	

}
