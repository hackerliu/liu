package com.xmpp.Chat.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import com.xmpp.Chat.util.LogHelper;

public class ChatDatabaseHelper {

    private static ChatDatabaseHelper _instance=null;
    private static Object _object=new Object();

    private ChatDatabaseHelper(){}

    public static ChatDatabaseHelper getInstance(){

        if(_instance==null){
            synchronized (_object){
                if(_instance==null){
                    _instance=new ChatDatabaseHelper();
                }
            }
        }
        return _instance;
    }

    /*
        创建表，以“_userID”为后缀区分
     */
    public void createTable(Context context, String userID){

        String createFriendTableSQL="CREATE TABLE if not exists friend_"+userID+" (" +
                "  userid VARCHAR(10) NOT NULL primary key," +
                "  nickname VARCHAR(45) NOT NULL," +
                "  headimg VARCHAR(45) NOT NULL," +
                "  sex varchar(4) CHECK( sex IN ('男','女') ) NOT NULL DEFAULT '男'," +
                "  birthday DATE ," +
                "  city VARCHAR(10) ," +
                "  realname VARCHAR(20)," +
                "  email VARCHAR(20)," +
                "  regtime DATETIME NOT NULL DEFAULT now())";

        String createUserInfoTableSQL="CREATE TABLE if not exists userinfo_"+userID+" (" +
                "  userid VARCHAR(10) NOT NULL primary key," +
                "  psw VARCHAR(45) NOT NULL," +
                "  nickname VARCHAR(45) NOT NULL," +
                "  headimg VARCHAR(45) NOT NULL," +
                "  sex varchar(4) CHECK(sex IN ('男','女')) NOT NULL DEFAULT '男'," +
                "  birthday DATE," +
                "  city VARCHAR(10)," +
                "  realname VARCHAR(20)," +
                "  email VARCHAR(20)," +
                "  regtime DATETIME NOT NULL DEFAULT now())";

        String createMsgInfoTableSQL="CREATE TABLE if not exists msg_"+userID+" (" +
                "  userid VARCHAR(10) NOT NULL primary key," +
                "  msg TEXT," +
                "  type int(1) check(type in (0,1)) NOT NULL DEFAULT 0," +
                "  time DATETIME NOT NULL DEFAULT now())";

        ChatDBOpenHelper dbOpenHelper=new ChatDBOpenHelper(context);
        SQLiteDatabase db=dbOpenHelper.getWritableDatabase();
        if(db != null){
            try {
                db.execSQL(createUserInfoTableSQL);
                db.execSQL(createFriendTableSQL);
                db.execSQL(createMsgInfoTableSQL);
            }catch (Exception e){
                LogHelper.getInstance().writeLog(ChatDatabaseHelper.class,"创建表失败");
                Log.e("DATATABLE_ERROR","创建表失败:"+e.getMessage());
            }finally {
                closeDB(db,dbOpenHelper);
            }
        }
    }

    public void closeDB(SQLiteDatabase db,ChatDBOpenHelper dbhelper){
        if(db != null && db.isOpen()){
            db.close();
        }

        if(dbhelper!=null){
            dbhelper.close();
        }
    }
}
