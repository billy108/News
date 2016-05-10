package com.example.administrator.news.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.administrator.news.MainActivity;
import com.example.administrator.news.R;

import java.util.ArrayList;
import java.util.List;

public class RightMenuFragment extends Fragment implements AdapterView.OnItemClickListener{
    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = LayoutInflater.from(getActivity()).inflate(R.layout.list_view, null);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        ListView listView = (ListView) view.findViewById(R.id.list_view);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1
                , android.R.id.text1, initData());
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
        super.onActivityCreated(savedInstanceState);
    }

    private List<String> initData() {
        List<String> list = new ArrayList<String>();
        list.add("fragment1");
        list.add("fragment2");
        list.add("fragment3");
        list.add("fragment4");
        list.add("fragment5");

        return list;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Fragment f = null;
        switch (position) {
            case 0:
                f = new Fragment1();
                break;
            case 1:
                f = new Fragment2();
                break;
            case 2:
                f = new Fragment3();
                break;
            case 3:
                f = new Fragment4();
                break;
            case 4:
                f = new Fragment5();
                break;
        }
        switchFragment(f);
    }

    private void switchFragment(Fragment fragment) {
        if (fragment != null) {
            if (getActivity() instanceof MainActivity) {
                ((MainActivity) getActivity()).switchFragment(fragment);
            }
        }
    }
}
