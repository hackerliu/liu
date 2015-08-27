package com.xmpp.Chat.util;

import org.apache.log4j.Logger;

public class LogHelper {

    private static LogHelper _instance=null;

    private LogHelper(){}

    public static LogHelper getInstance(){
        if(_instance==null){
            synchronized (LogHelper.class){
                if(_instance==null){
                    _instance=new LogHelper();
                }
            }
        }
        return _instance;
    }

    public void writeLog(Class cls,String msg){
        Logger log=Logger.getLogger(cls);
        log.info(msg);
    }
}
