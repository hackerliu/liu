package com.xmpp.Chat.util;

import com.xmpp.Chat.Entity.User;

public class Constants {

    public static final String DB_NAME = "chat.db";

    public static final String REGISTERHOST="http://192.168.1.101:8080/register";

    public static User curUser=null;

    public static void setCurUser(User curUser) {
        Constants.curUser = curUser;
    }
}
