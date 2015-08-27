package com.xmpp.Chat.activity;

import android.app.TabActivity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.PopupWindow;
import android.widget.RadioGroup;
import android.widget.TabHost;
import android.widget.TextView;

import com.xmpp.Chat.R;
import com.xmpp.Chat.db.ChatDatabaseHelper;

public class MainActivity extends TabActivity {

    private TabHost tabHost;
    private PopupWindow mPopupWindow;
    private View mPopView;
    private FrameLayout buttomBarGroup;
    private String userID;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        Intent intent=getIntent();
        userID = intent.getStringExtra("userID");
        initView();
        creatTable();
        refresh();
    }

    private void initView(){
        tabHost=this.getTabHost();
        TabHost.TabSpec spec;

        spec=tabHost.newTabSpec(getString(R.string.tab1text))
                .setIndicator(getString(R.string.tab1text))
                .setContent(new Intent().setClass(this,ChatActivity.class));
        tabHost.addTab(spec);

        spec=tabHost.newTabSpec(getString(R.string.tab2text))
                .setIndicator(getString(R.string.tab2text))
                .setContent(new Intent().setClass(this,FriendActivity.class));
        tabHost.addTab(spec);

        spec=tabHost.newTabSpec(getString(R.string.tab3text))
                .setIndicator(getString(R.string.tab3text))
                .setContent(new Intent().setClass(this,EventsActivity.class));
        tabHost.addTab(spec);

        tabHost.setCurrentTab(0);

        RadioGroup radioGroup=(RadioGroup) this.findViewById(R.id.main_tab_group);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.main_tab_home:
                        tabHost.setCurrentTabByTag(getString(R.string.tab1text));
                        break;
                    case R.id.main_tab_search:
                        tabHost.setCurrentTabByTag(getString(R.string.tab2text));
                        break;
                    case R.id.main_tab_setting:
                        tabHost.setCurrentTabByTag(getString(R.string.tab3text));
                        break;
                    default:
                        break;
                }
            }
        });

        mPopView= LayoutInflater.from(this).inflate(R.layout.app_exit, null);
        TextView app_cancle=(TextView) mPopView.findViewById(R.id.app_cancle);
        TextView app_exit=(TextView) mPopView.findViewById(R.id.app_change_user);
        TextView app_change=(TextView) mPopView.findViewById(R.id.app_exit);
        buttomBarGroup=(FrameLayout) findViewById(R.id.buttom_bar_group);

        mPopupWindow=new PopupWindow(mPopView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);

        app_cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPopupWindow.dismiss();
            }
        });

        app_change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
                MainActivity.this.overridePendingTransition(R.anim.activity_up, R.anim.fade_out);
                finish();
            }
        });

        app_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void creatTable(){
        ChatDatabaseHelper.getInstance().createTable(this,userID);
    }

    private void refresh(){

    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode== KeyEvent.KEYCODE_MENU||keyCode== KeyEvent.KEYCODE_BACK){
            mPopupWindow.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#b0000000")));
            mPopupWindow.showAtLocation(buttomBarGroup, Gravity.BOTTOM, 0, 0);
            mPopupWindow.setAnimationStyle(R.style.app_pop);
            mPopupWindow.setOutsideTouchable(true);
            mPopupWindow.setFocusable(true);
            mPopupWindow.update();
        }
        return super.onKeyDown(keyCode, event);
    }
}
