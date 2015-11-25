package com.xmpp.Chat.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import com.xmpp.Chat.R;
import com.xmpp.Chat.activity.ChatWithFriendActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MsgAdapter extends RecyclerView.Adapter<MsgAdapter.ViewHolder>{

    private List<Map<String,String>> chats=null;
    private Context mContext=null;
    public String userID=null;

    public MsgAdapter(Context mContext, List<Map<String,String>> chats){
        this.mContext=mContext;
        if(chats == null) {
            this.chats = new ArrayList<Map<String, String>>();
        }else {
            this.chats = chats;
        }
    }

    @Override
    public MsgAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.chat_item, viewGroup, false);
        return new ViewHolder(v,this.mContext,this.userID);
    }

    @Override
    public void onBindViewHolder(MsgAdapter.ViewHolder viewHolder, int i) {
        Map<String ,String> item=chats.get(i);
        Picasso.with(mContext).load(item.get("headimg")).into(viewHolder.user_headimg);
        viewHolder.user_msg.setText(item.get("msg"));
        viewHolder.user_time.setText(item.get("time"));
        viewHolder.user_name.setText(item.get("name"));
        this.userID=item.get("userid");
    }

    @Override
    public int getItemCount() {
        return this.chats.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        public ImageView user_headimg;
        public TextView user_time;
        public TextView user_name;
        public TextView user_msg;

        public ViewHolder(View view, final Context mContext,final String userID) {
            super(view);
            this.user_headimg= (ImageView) view.findViewById(R.id.user_headimg);
            this.user_msg= (TextView) view.findViewById(R.id.user_msg);
            this.user_name= (TextView) view.findViewById(R.id.user_name);
            this.user_time= (TextView) view.findViewById(R.id.user_time);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, ChatWithFriendActivity.class);
                    intent.putExtra("friendName", user_name.getText().toString());
                    mContext.startActivity(intent);
                }
            });
        }
    }
}
