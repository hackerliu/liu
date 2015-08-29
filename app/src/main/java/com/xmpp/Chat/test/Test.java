package com.xmpp.Chat.test;

import com.xmpp.Chat.util.XmppConnection;

public class Test {

    public static void main(String[] args) {
        if(XmppConnection.getInstance().login("admin","123qwe")){
            System.out.println("µÇÂ½³É¹¦");
        }else{
            System.out.println("µÇÂ½Ê§°Ü");
        }
    }
}
