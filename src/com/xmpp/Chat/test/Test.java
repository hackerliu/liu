package com.xmpp.Chat.test;

import com.xmpp.Chat.util.XmppConnection;

public class Test {

    public static void main(String[] args) {
        if(XmppConnection.getInstance().login("admin","123qwe")){
            System.out.println("��½�ɹ�");
        }else{
            System.out.println("��½ʧ��");
        }
    }
}
