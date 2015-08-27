package com.xmpp.Chat.dao;

import android.content.ContentValues;

import java.util.List;

public interface IDataDao<T> {

    public void insert(ContentValues contentValues);
    public void delete(String ID);
    public void update(ContentValues contentValues, String ID);
    public T select(String ID);
    public List<T> select();
    public List<T> select(String[] columns, String[] values);
}
