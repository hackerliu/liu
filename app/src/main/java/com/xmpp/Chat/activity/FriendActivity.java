package com.xmpp.Chat.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.xmpp.Chat.Entity.User;
import com.xmpp.Chat.R;
import com.xmpp.Chat.adapter.FriendAdapter;
import com.xmpp.Chat.dao.FriendDao;
import com.xmpp.Chat.util.Constants;
import com.xmpp.Chat.view.TitleBarView;

import java.util.List;

public class FriendActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.friend);

        TitleBarView mTitleBarView = (TitleBarView) findViewById(R.id.title_bar);
        mTitleBarView.setCommonTitle(View.GONE, View.VISIBLE, View.GONE, View.GONE);
        mTitleBarView.setTitleText(R.string.title_friend);

        FriendDao friendDao=new FriendDao(this, Constants.curUser.getUserid());
        List<User> friends=friendDao.select();

        RecyclerView friendList= (RecyclerView) findViewById(R.id.friendlv);
        friendList.setHasFixedSize(true);
        friendList.setLayoutManager(new LinearLayoutManager(this));
        friendList.setAdapter(new FriendAdapter(this,friends));
    }
}
