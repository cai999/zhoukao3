package com.bwie.kangxiaoling;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.bwie.kangxiaoling.fragment.FiveFragment;
import com.bwie.kangxiaoling.fragment.FourFragment;
import com.bwie.kangxiaoling.fragment.OneFragment;
import com.bwie.kangxiaoling.fragment.ThreeFragment;
import com.bwie.kangxiaoling.fragment.TwoFragment;
import com.hjm.bottomtabbar.BottomTabBar;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MainActivity extends AppCompatActivity {


    private BottomTabBar bottom_tab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
    }
    private void initData() {
        bottom_tab = (BottomTabBar) findViewById(R.id.bottom_tab);
        bottom_tab.init(getSupportFragmentManager())
                .addTabItem("新品",R.drawable.new_icon,OneFragment.class)
                .addTabItem("精选",R.drawable.choice_icon,TwoFragment.class)
                .addTabItem("分类",R.drawable.classify_icon,ThreeFragment.class)
                .addTabItem("我的衣橱",R.drawable.wardrobe_icon,FourFragment.class)
                .addTabItem("个人中心",R.drawable.person_icon,FiveFragment.class);
    }
}
