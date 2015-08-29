package com.xmpp.Chat.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import com.xmpp.Chat.R;
import com.xmpp.Chat.view.TitleBarView;

public class EventsActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.events);

        TitleBarView mTitleBarView = (TitleBarView) findViewById(R.id.title_bar);
        mTitleBarView.setCommonTitle(View.GONE, View.VISIBLE, View.GONE, View.GONE);
        mTitleBarView.setTitleText(R.string.title_friend);
    }
}
