package com.xmpp.Chat.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import com.xmpp.Chat.Entity.MsgInfo;
import com.xmpp.Chat.Entity.User;
import com.xmpp.Chat.R;
import com.xmpp.Chat.adapter.MsgAdapter;
import com.xmpp.Chat.dao.FriendDao;
import com.xmpp.Chat.dao.MsgInfoDao;
import com.xmpp.Chat.view.TitleBarView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.xmpp.Chat.R.id.chatlv;

public class ChatActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat);

        TitleBarView mTitleBarView = (TitleBarView) findViewById(R.id.title_bar);
        mTitleBarView.setCommonTitle(View.GONE, View.VISIBLE, View.GONE, View.GONE);
        mTitleBarView.setTitleText(R.string.title_msg);

        RecyclerView recyclerView= (RecyclerView) findViewById(chatlv);
//        List<Map<String , String>> chats=getChatsFromDB(this, Constants.curUser.getUserid());
        List<Map<String , String>> chats=new ArrayList<Map<String, String>>();
        MsgAdapter msgAdapter=new MsgAdapter(this,chats);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(msgAdapter);
    }

    private List<Map<String, String>> getChatsFromDB(Context context, String userID) {
        List<Map<String,String>> chats=new ArrayList<Map<String, String>>();

        MsgInfoDao msgInfoDao=new MsgInfoDao(context,userID);
        FriendDao friendDao=new FriendDao(context,userID);
        List<MsgInfo> msgs = msgInfoDao.select(userID,"0","1","desc");
        List<User> friends=friendDao.select();
        return null;
    }
}
