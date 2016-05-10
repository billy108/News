package com.example.administrator.news;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.Window;

import com.example.administrator.news.fragment.Fragment1;
import com.example.administrator.news.fragment.HomeFragment;
import com.example.administrator.news.fragment.MenuFragment;
import com.example.administrator.news.fragment.MenuFragment2;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;

public class MainActivity extends SlidingFragmentActivity {
    SlidingMenu slidingMenu;
    MenuFragment2 menuFragment;
    HomeFragment homeFragment;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.content);
        setBehindContentView(R.layout.menu);

//        Fragment1 fragment1 = new Fragment1();
//        getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, fragment1).commit();

        slidingMenu = getSlidingMenu();
        slidingMenu.setMode(SlidingMenu.LEFT);
        slidingMenu.setBehindWidthRes(R.dimen.slidingmenu_offset);
        slidingMenu.setShadowDrawable(R.drawable.shadow);
        slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);

        menuFragment = new MenuFragment2();
        getSupportFragmentManager().beginTransaction().replace(R.id.menu_frame,
                menuFragment, "Menu").commit();

        homeFragment = new HomeFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.content_frame,
                homeFragment, "Home").commit();
//        slidingMenu.setSecondaryMenu(R.layout.right_menu);

//        slidingMenu.setMode(SlidingMenu.LEFT_RIGHT);
//        slidingMenu.setSecondaryShadowDrawable(R.drawable.shadowright);
//        RightMenuFragment rightMenuFragment = new RightMenuFragment();
//        getSupportFragmentManager().beginTransaction().replace(R.id.right_menu_frame,
//                rightMenuFragment).commit();

    }

    public void switchFragment(Fragment fragment){
        getSupportFragmentManager().beginTransaction().replace(R.id.content_frame,
                fragment).commit();

        slidingMenu.toggle();
    }

    public MenuFragment2 getMenuFragment2(){
        menuFragment = (MenuFragment2) getSupportFragmentManager().findFragmentByTag("Menu");
        return menuFragment;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
}
