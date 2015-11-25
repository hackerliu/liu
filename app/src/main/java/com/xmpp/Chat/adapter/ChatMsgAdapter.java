package com.xmpp.Chat.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.xmpp.Chat.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ChatMsgAdapter extends RecyclerView.Adapter<ChatMsgAdapter.ViewHolder>{

    List<Map<String,String>> chatMsgs=null;
    Context mContext=null;

    public ChatMsgAdapter(Context mContext, List<Map<String,String>> chatMsgs){
        if(chatMsgs==null){
            this.chatMsgs=new ArrayList<Map<String, String>>();
        }else{
            this.chatMsgs=chatMsgs;
        }
        this.mContext=mContext;
    }

    @Override
    public ChatMsgAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == 0) {
            View v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.main_chat_from_msg, parent, false);
            return new ViewHolder(v,"from");
        } else {
            View v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.main_chat_send_msg, parent, false);
            return new ViewHolder(v,"to");
        }
    }

    @Override
    public void onBindViewHolder(ChatMsgAdapter.ViewHolder holder, int position) {
        Map<String, String> item=chatMsgs.get(position);
        holder.chat_content.setText(item.get("chat_content"));
        holder.chat_name.setText(item.get("chat_name"));
        Picasso.with(mContext).load(item.get("chat_icon")).into(holder.chat_icon);
    }

    @Override
    public int getItemViewType(int position) {
        String type_str=this.chatMsgs.get(position).get("type");
        int type_int=0;
        try {
             type_int= Integer.parseInt(type_str);
        }catch (Exception e){
            e.printStackTrace();
        }
        return type_int;
    }

    @Override
    public int getItemCount() {
        return this.chatMsgs.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        public ImageView chat_icon;
        public TextView chat_content;
        public TextView chat_name;

        public ViewHolder(View view, String type) {
            super(view);
            if("from".equals(type)){
                this.chat_icon= (ImageView) view.findViewById(R.id.chat_from_icon);
                this.chat_content= (TextView) view.findViewById(R.id.chat_from_content);
                this.chat_name= (TextView) view.findViewById(R.id.chat_from_name);
            }else if("to".equals(type)){
                this.chat_icon= (ImageView) view.findViewById(R.id.chat_send_icon);
                this.chat_content= (TextView) view.findViewById(R.id.chat_send_content);
                this.chat_name= (TextView) view.findViewById(R.id.chat_send_name);
            }
        }
    }
}
