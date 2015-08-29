package com.xmpp.Chat.Entity;

import java.sql.Date;

public class MsgInfo {

    public int id;
    public String userid;
    public String msg;
    public String type;         //0:tome    1:tohim
    public Date time;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MsgInfo msgInfo = (MsgInfo) o;

        if (!userid.equals(msgInfo.userid)) return false;
        if (!msg.equals(msgInfo.msg)) return false;
        if (!type.equals(msgInfo.type)) return false;
        return time.equals(msgInfo.time);

    }

    @Override
    public int hashCode() {
        int result = userid.hashCode();
        result = 31 * result + msg.hashCode();
        result = 31 * result + type.hashCode();
        result = 31 * result + time.hashCode();
        return result;
    }
}
