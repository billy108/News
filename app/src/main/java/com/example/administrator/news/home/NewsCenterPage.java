package com.example.administrator.news.home;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.example.administrator.news.MainActivity;
import com.example.administrator.news.base.BaseFragment;
import com.example.administrator.news.base.BasePage;
import com.example.administrator.news.bean.NewsCenterCategory;
import com.example.administrator.news.fragment.MenuFragment2;
import com.example.administrator.news.utils.GsonUtils;
import com.example.administrator.news.utils.SharedPreferencesUtils;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.lidroid.xutils.util.LogUtils;

import java.util.ArrayList;
import java.util.List;

public class NewsCenterPage extends BasePage {
	private static final String NEWSCENTERPAGE = "NewsCenterPage";

	public NewsCenterPage(Context ct) {
		super(ct);
	}

	@Override
	public View initView(LayoutInflater inflater) {
		TextView textView = new TextView(ct);
		textView.setText("我是新闻中心");
		return textView;
	}

	@Override
	public void initData() {
		String vaule = SharedPreferencesUtils.getString(ct, NEWSCENTERPAGE);
		if (TextUtils.isEmpty(vaule)) {
			ProcessData(vaule);
		}
		TestPost();
	}

	private void TestPost() {
		HttpUtils http = new HttpUtils();
		http.send(HttpRequest.HttpMethod.GET,
				"http://appa.cncnews.cn/cnc/recommend/subject_new",
				new RequestCallBack<String>(){

					@Override
					public void onSuccess(ResponseInfo<String> responseInfo) {
//						textView.setText(responseInfo.result);
						LogUtils.d(responseInfo.result);
						SharedPreferencesUtils.saveString(ct, NEWSCENTERPAGE, responseInfo.result);
						ProcessData(responseInfo.result);
					}


					@Override
					public void onFailure(HttpException error, String msg) {
					}
				});
	}

	private List<String> menuNewCenterList = new ArrayList<>();
	private void ProcessData(String result) {
		NewsCenterCategory category = GsonUtils.jsonToBean(result, NewsCenterCategory.class);
		if (menuNewCenterList.size() == 0 && result != null) {
			BaseFragment.flag = true;
			menuNewCenterList.add("新闻");
			menuNewCenterList.add("专题");
			menuNewCenterList.add("组图");
			menuNewCenterList.add("互动");
			menuNewCenterList.add("投票");
		}

		MenuFragment2 menuFragment2 = ((MainActivity)ct).getMenuFragment2();
		menuFragment2.initMenu(menuNewCenterList);
	}

}
