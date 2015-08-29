package com.xmpp.Chat.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.xmpp.Chat.Entity.User;
import com.xmpp.Chat.db.ChatDBOpenHelper;
import com.xmpp.Chat.db.ChatDatabaseHelper;
import com.xmpp.Chat.util.FormatTools;
import com.xmpp.Chat.util.LogHelper;

import java.util.ArrayList;
import java.util.List;

public class FriendDao implements IDataDao<User>{
    SQLiteDatabase db=null;
    ChatDBOpenHelper dbHelper=null;
    Context context;
    String tablename=null;

    public FriendDao(Context context,String userID){
        this.tablename="friend_"+userID;
        this.context=context;
    }

    @Override
    public void insert(ContentValues contentValues) {
        try {
            dbHelper=new ChatDBOpenHelper(context);
            db= dbHelper.getWritableDatabase();
            db.insert(tablename, null, contentValues);
        }catch (Exception e){
            LogHelper.getInstance().writeLog(FriendDao.class,e.getMessage());
            e.printStackTrace();
        }finally {
            ChatDatabaseHelper.getInstance().closeDB(db,dbHelper);
        }
    }

    public void insertOrUpdate(ContentValues contentValues){
        try {
            dbHelper=new ChatDBOpenHelper(context);
            db= dbHelper.getWritableDatabase();
            db.insertWithOnConflict(tablename,null,contentValues,SQLiteDatabase.CONFLICT_REPLACE);
        }catch (Exception e){
            LogHelper.getInstance().writeLog(FriendDao.class,e.getMessage());
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
    public void update(ContentValues contentValues,String ID) {
        try {
            dbHelper=new ChatDBOpenHelper(context);
            db= dbHelper.getWritableDatabase();
            db.update(tablename, contentValues, "userid=", new String[]{ID});
        }catch (Exception e){
            LogHelper.getInstance().writeLog(FriendDao.class,e.getMessage());
            e.printStackTrace();
        }finally {
            ChatDatabaseHelper.getInstance().closeDB(db,dbHelper);
        }
    }

    @Override
    public User select(String ID) {
        User user=new User();
        Cursor cursor=null;
        try {
            dbHelper=new ChatDBOpenHelper(context);
            db= dbHelper.getWritableDatabase();
            cursor=db.query(tablename,new String[]{"userid"},"userid=?",new String[]{ID},null,null,null);
            if(cursor.moveToNext()){
                user.setUserid(cursor.getString(cursor.getColumnIndex("userid")));
                user.setNickname(cursor.getString(cursor.getColumnIndex("nickname")));
                user.setHeadimg(cursor.getString(cursor.getColumnIndex("headimg")));
                user.setSex(cursor.getString(cursor.getColumnIndex("sex")));
                String birthday=cursor.getString(cursor.getColumnIndex("birthday"));
                user.setBirthday(FormatTools.StringToSqlDate(birthday));
                user.setCity(cursor.getString(cursor.getColumnIndex("city")));
                user.setRealname(cursor.getString(cursor.getColumnIndex("realname")));
                user.setEmail(cursor.getString(cursor.getColumnIndex("email")));
                String regtime=cursor.getString(cursor.getColumnIndex("regtime"));
                user.setRegtime(FormatTools.StringToSqlDate(regtime));
            }
        }catch (Exception e){
            LogHelper.getInstance().writeLog(FriendDao.class,e.getMessage());
            e.printStackTrace();
        }finally {
            ChatDatabaseHelper.getInstance().closeDB(db,dbHelper);
        }
        return user;
    }

    @Override
    public List<User> select() {
        String[] columns={"userid","nickname","headimg","sex",
                "birthday","city","realname","email","regtime"};
        List<User> userlist=new ArrayList<User>();
        User user=null;
        Cursor cursor=null;
        try {
            dbHelper=new ChatDBOpenHelper(context);
            db= dbHelper.getWritableDatabase();
            cursor=db.query(tablename, columns, null, null, null, null, null);
            while (cursor.moveToNext()){
                user=new User();
                user.setUserid(cursor.getString(cursor.getColumnIndex("userid")));
                user.setNickname(cursor.getString(cursor.getColumnIndex("nickname")));
                user.setHeadimg(cursor.getString(cursor.getColumnIndex("headimg")));
                user.setSex(cursor.getString(cursor.getColumnIndex("sex")));
                String birthday=cursor.getString(cursor.getColumnIndex("birthday"));
                user.setBirthday(FormatTools.StringToSqlDate(birthday));
                user.setCity(cursor.getString(cursor.getColumnIndex("city")));
                user.setRealname(cursor.getString(cursor.getColumnIndex("realname")));
                user.setEmail(cursor.getString(cursor.getColumnIndex("email")));
                String regtime=cursor.getString(cursor.getColumnIndex("birthday"));
                user.setRegtime(FormatTools.StringToSqlDate(regtime));
                userlist.add(user);
            }
        }catch (Exception e){
            LogHelper.getInstance().writeLog(FriendDao.class,e.getMessage());
            e.printStackTrace();
        }finally {
            ChatDatabaseHelper.getInstance().closeDB(db,dbHelper);
        }
        return userlist;
    }

    @Override
    public List<User> select(String[] columns,String[] values) {

        List<User> userlist=new ArrayList<User>();
        User user=null;
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

            cursor=db.rawQuery(sql.toString(), values);
            while (cursor.moveToNext()){
                user=new User();
                user.setUserid(cursor.getString(cursor.getColumnIndex("userid")));
                user.setNickname(cursor.getString(cursor.getColumnIndex("nickname")));
                user.setHeadimg(cursor.getString(cursor.getColumnIndex("headimg")));
                user.setSex(cursor.getString(cursor.getColumnIndex("sex")));
                String birthday=cursor.getString(cursor.getColumnIndex("birthday"));
                user.setBirthday(FormatTools.StringToSqlDate(birthday));
                user.setCity(cursor.getString(cursor.getColumnIndex("city")));
                user.setRealname(cursor.getString(cursor.getColumnIndex("realname")));
                user.setEmail(cursor.getString(cursor.getColumnIndex("email")));
                String regtime=cursor.getString(cursor.getColumnIndex("birthday"));
                user.setRegtime(FormatTools.StringToSqlDate(regtime));
                userlist.add(user);
            }
        }catch (Exception e){
            LogHelper.getInstance().writeLog(FriendDao.class,e.getMessage());
            e.printStackTrace();
        }finally {
            ChatDatabaseHelper.getInstance().closeDB(db,dbHelper);
        }
        return userlist;
    }

    public void execDataBase(String sql){
        try {
            dbHelper=new ChatDBOpenHelper(context);
            db= dbHelper.getWritableDatabase();
            db.execSQL(sql);
        }catch (Exception e){
            LogHelper.getInstance().writeLog(FriendDao.class,e.getMessage());
            e.printStackTrace();
        }finally {
            ChatDatabaseHelper.getInstance().closeDB(db,dbHelper);
        }
    }
}
