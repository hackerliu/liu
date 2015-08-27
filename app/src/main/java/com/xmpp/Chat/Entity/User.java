package com.xmpp.Chat.Entity;

import java.sql.Date;

public class User {

    public String userid="";
    public String psw="";
    public String nickname="";
    public String headimg="";
    public String sex="";
    public Date birthday=new Date(2015,8,8);
    public String city="";
    public String realname="";
    public String email="";
    public Date regtime;

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHeadimg() {
        return headimg;
    }

    public void setHeadimg(String headimg) {
        this.headimg = headimg;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPsw() {
        return psw;
    }

    public void setPsw(String psw) {
        this.psw = psw;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public Date getRegtime() {
        return regtime;
    }

    public void setRegtime(Date regtime) {
        this.regtime = regtime;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
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

        User user = (User) o;

        if (!userid.equals(user.userid)) return false;
        if (!psw.equals(user.psw)) return false;
        if (!nickname.equals(user.nickname)) return false;
        if (!headimg.equals(user.headimg)) return false;
        if (!sex.equals(user.sex)) return false;
        if (birthday != null ? !birthday.equals(user.birthday) : user.birthday != null) return false;
        if (city != null ? !city.equals(user.city) : user.city != null) return false;
        if (realname != null ? !realname.equals(user.realname) : user.realname != null) return false;
        if (email != null ? !email.equals(user.email) : user.email != null) return false;
        return regtime.equals(user.regtime);

    }

    @Override
    public int hashCode() {
        int result = userid.hashCode();
        result = 31 * result + psw.hashCode();
        result = 31 * result + nickname.hashCode();
        result = 31 * result + headimg.hashCode();
        result = 31 * result + sex.hashCode();
        result = 31 * result + (birthday != null ? birthday.hashCode() : 0);
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (realname != null ? realname.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + regtime.hashCode();
        return result;
    }
}
