package com.xmpp.Chat.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.xmpp.Chat.R;
import com.xmpp.Chat.adapter.ChatMsgAdapter;
import com.xmpp.Chat.util.Constants;
import com.xmpp.Chat.util.FormatTools;

import java.util.*;

public class ChatWithFriendActivity extends Activity{

    private List<Map<String,String>> chatMsgs=new ArrayList<Map<String, String>>();
    private ChatMsgAdapter msgAdapter=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chatwithfriend);

        final TextView content= (TextView) findViewById(R.id.id_chat_msg);
        Button send= (Button) findViewById(R.id.id_chat_send);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String conStr=content.getText().toString().trim();
                if(conStr==null||conStr.equals("")){
                    Toast.makeText(ChatWithFriendActivity.this,"不能发送空消息",Toast.LENGTH_SHORT).show();
                }
                Map<String,String> item=new HashMap<String, String>();
                item.put("chat_content",conStr);
                item.put("type","1");
                item.put("time", FormatTools.DateToString(new Date(), FormatTools.YMD_HMS));
                item.put("chat_name", "Me");
                item.put("chat_icon", null);
                chatMsgs.add(item);
                msgAdapter.notifyDataSetChanged();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                item.clear();
                item.put("chat_content",conStr);
                item.put("type","0");
                item.put("time", FormatTools.DateToString(new Date(), FormatTools.YMD_HMS));
                item.put("chat_name", "Robot");
                item.put("chat_icon", null);
                chatMsgs.add(item);
                msgAdapter.notifyDataSetChanged();
            }
        });
        RecyclerView recyclerView= (RecyclerView) findViewById(R.id.chat_lv);
        msgAdapter=new ChatMsgAdapter(this,chatMsgs);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(msgAdapter);
    }
}
