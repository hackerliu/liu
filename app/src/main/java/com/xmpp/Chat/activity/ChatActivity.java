package com.xmpp.Chat.activity;

import android.app.Activity;
import android.os.Bundle;

import com.xmpp.Chat.R;

public class ChatActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat);
    }
}
