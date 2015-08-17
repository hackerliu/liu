package com.xmpp.Chat.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.xmpp.Chat.Entity.MsgInfo;
import com.xmpp.Chat.db.ChatDBOpenHelper;
import com.xmpp.Chat.db.ChatDatabaseHelper;
import com.xmpp.Chat.util.FormatTools;
import com.xmpp.Chat.util.LogHelper;

import java.util.ArrayList;
import java.util.List;

public class MsgInfoDao implements IDataDao<MsgInfo>{

    SQLiteDatabase db=null;
    ChatDBOpenHelper dbHelper=null;
    Context context;
    String tablename=null;

    public MsgInfoDao(Context context,String userID){
        this.tablename="msg_"+userID;
        this.context=context;
    }

    @Override
    public void insert(ContentValues contentValues) {
        try {
            dbHelper=new ChatDBOpenHelper(context);
            db= dbHelper.getWritableDatabase();
            db.insert(tablename, null, contentValues);
        }catch (Exception e){
            LogHelper.getInstance().writeLog(MsgInfoDao.class,e.getMessage());
            e.printStackTrace();
        }finally {
            ChatDatabaseHelper.getInstance().closeDB(db,dbHelper);
        }
    }

    @Override
    public void delete(String ID) {
        String sql="delete from "+tablename+"where userid="+ID;

        execDataBase(sql);
    }

    @Override
    public void update(ContentValues contentValues, String ID) {
        try {
            dbHelper=new ChatDBOpenHelper(context);
            db= dbHelper.getWritableDatabase();
            db.update(tablename, contentValues, "userid=", new String[]{ID});
        }catch (Exception e){
            LogHelper.getInstance().writeLog(MsgInfoDao.class,e.getMessage());
            e.printStackTrace();
        }finally {
            ChatDatabaseHelper.getInstance().closeDB(db,dbHelper);
        }
    }

    @Override
    public MsgInfo select(String ID) {
        MsgInfo msg=new MsgInfo();
        Cursor cursor=null;
        try {
            dbHelper=new ChatDBOpenHelper(context);
            db= dbHelper.getWritableDatabase();
            cursor=db.query(tablename,new String[]{"userid"},"userid=?",new String[]{ID},null,null,null);
            if(cursor.moveToNext()){
                msg.setUserid(cursor.getString(cursor.getColumnIndex("userid")));
                msg.setMsg(cursor.getString(cursor.getColumnIndex("msg")));
                msg.setType(cursor.getString(cursor.getColumnIndex("type")));
                msg.setType(cursor.getString(cursor.getColumnIndex("type")));
                String time=cursor.getString(cursor.getColumnIndex("time"));
                msg.setTime(FormatTools.StringToSqlDate(time));
            }
        }catch (Exception e){
            LogHelper.getInstance().writeLog(MsgInfoDao.class,e.getMessage());
            e.printStackTrace();
        }finally {
            ChatDatabaseHelper.getInstance().closeDB(db,dbHelper);
        }
        return msg;
    }

    @Override
    public List<MsgInfo> select() {
        String[] columns={"userid","msg","type","time"};
        List<MsgInfo> msglist=new ArrayList<MsgInfo>();
        MsgInfo msg=null;
        Cursor cursor=null;
        try {
            dbHelper=new ChatDBOpenHelper(context);
            db= dbHelper.getWritableDatabase();
            cursor=db.query(tablename,columns,null,null,null,null,null);
            if(cursor.moveToNext()){
                msg=new MsgInfo();
                msg.setUserid(cursor.getString(cursor.getColumnIndex("userid")));
                msg.setMsg(cursor.getString(cursor.getColumnIndex("msg")));
                msg.setType(cursor.getString(cursor.getColumnIndex("type")));
                String time=cursor.getString(cursor.getColumnIndex("time"));
                msg.setTime(FormatTools.StringToSqlDate(time));
                msglist.add(msg);
            }
        }catch (Exception e){
            LogHelper.getInstance().writeLog(MsgInfoDao.class,e.getMessage());
            e.printStackTrace();
        }finally {
            ChatDatabaseHelper.getInstance().closeDB(db,dbHelper);
        }
        return msglist;
    }

    @Override
    public List<MsgInfo> select(String[] columns, String[] values) {
        List<MsgInfo> msglist=new ArrayList<MsgInfo>();
        MsgInfo msg=null;
        Cursor cursor=null;
        try {
            dbHelper=new ChatDBOpenHelper(context);
            db= dbHelper.getWritableDatabase();

            StringBuilder sql=new StringBuilder();
            sql.append("select * from ").append(tablename).append(" where ");
            for(int i=0;i<columns.length;i++){
                if(i < columns.length-1) {
                    sql.append(columns[i]).append("=").append("?").append(" and ");
                }else{
                    sql.append(columns[i]).append("=").append("?");
                }
            }

            cursor=db.rawQuery(sql.toString(),values);
            if(cursor.moveToNext()){
                msg=new MsgInfo();
                msg.setUserid(cursor.getString(cursor.getColumnIndex("userid")));
                msg.setMsg(cursor.getString(cursor.getColumnIndex("msg")));
                msg.setType(cursor.getString(cursor.getColumnIndex("type")));
                String time=cursor.getString(cursor.getColumnIndex("time"));
                msg.setTime(FormatTools.StringToSqlDate(time));
                msglist.add(msg);
            }
        }catch (Exception e){
            LogHelper.getInstance().writeLog(MsgInfoDao.class,e.getMessage());
            e.printStackTrace();
        }finally {
            ChatDatabaseHelper.getInstance().closeDB(db,dbHelper);
        }
        return msglist;
    }

    public void execDataBase(String sql){
        try {
            dbHelper=new ChatDBOpenHelper(context);
            db= dbHelper.getWritableDatabase();
            db.execSQL(sql);
        }catch (Exception e){
            LogHelper.getInstance().writeLog(MsgInfoDao.class,e.getMessage());
            e.printStackTrace();
        }finally {
            ChatDatabaseHelper.getInstance().closeDB(db,dbHelper);
        }
    }
}
