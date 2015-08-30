package com.xmpp.Chat.test;

import com.xmpp.Chat.util.XmppConnection;

public class Test {

    public static void main(String[] args) {
        if(XmppConnection.getInstance().login("admin","123qwe")){
            System.out.println("登陆成功");
        }else{
            System.out.println("登陆失败");
        }
    }
}
