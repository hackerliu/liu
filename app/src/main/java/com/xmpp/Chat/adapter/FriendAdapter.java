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
import com.xmpp.Chat.Entity.User;
import com.xmpp.Chat.R;
import com.xmpp.Chat.activity.ChatWithFriendActivity;

import java.util.List;
import java.util.Map;

public class FriendAdapter extends RecyclerView.Adapter<FriendAdapter.ViewHolder>{

    private List<User> friends=null;
    private Context mContext=null;
    public String userID=null;

    public FriendAdapter(Context mContext, List<User> friends){
        this.mContext=mContext;
        this.friends=friends;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.friend_item, viewGroup, false);
        return new ViewHolder(v,this.mContext,this.userID);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        User friend=friends.get(i);
        Picasso.with(mContext).load(friend.getHeadimg()).into(viewHolder.user_pic);
        this.userID=friend.getUserid();
        viewHolder.user_name.setText(friend.getNickname());
    }

    @Override
    public int getItemCount() {
        return friends.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        public ImageView user_pic;
        public TextView user_name;

        public ViewHolder(View view, final Context mContext,final String userID) {
            super(view);
            this.user_pic= (ImageView) view.findViewById(R.id.user_picture);
            this.user_name= (TextView) view.findViewById(R.id.user_name);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, ChatWithFriendActivity.class);
                    intent.putExtra("userID", userID);
                    mContext.startActivity(intent);
                }
            });
        }
    }
}
