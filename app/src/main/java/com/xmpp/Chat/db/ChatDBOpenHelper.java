package com.xmpp.Chat.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.xmpp.Chat.util.Constants;

public class ChatDBOpenHelper extends SQLiteOpenHelper {

    //数据库版本
    private static final int VERSION = 1;

    public ChatDBOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public ChatDBOpenHelper(Context c){
        super(c, Constants.DB_NAME,null,VERSION);
    }

    public ChatDBOpenHelper(Context context,String name,int version){
        this(context, name, null,version);
    }

    public ChatDBOpenHelper(Context context,String name){
        this(context, name,VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
