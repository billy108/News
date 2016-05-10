package com.example.administrator.news.utils;

import com.example.administrator.news.bean.NewsCenterCategory;
import com.google.gson.Gson;

public class GsonUtils {
    public static<T> T jsonToBean(String jsonResult, Class<T> clz){
        Gson gson = new Gson();
        T t = gson.fromJson(jsonResult,clz);
        return t;

    }
}
