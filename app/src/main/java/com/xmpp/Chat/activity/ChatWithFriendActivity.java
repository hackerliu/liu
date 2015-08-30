package com.xmpp.Chat.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.xmpp.Chat.R;

public class ChatWithFriendActivity extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chatwithfriend);

        Intent intent=getIntent();
        String userid=intent.getStringExtra("userID");
    }
}
