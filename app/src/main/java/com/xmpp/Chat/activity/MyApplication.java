package com.xmpp.Chat.activity;

import android.app.Application;
import android.os.Environment;
import com.xmpp.Chat.util.LogHelper;
import de.mindpipe.android.logging.log4j.LogConfigurator;
import org.apache.log4j.Level;

import java.io.File;

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        LogConfigurator logConfigurator = new LogConfigurator();
        logConfigurator.setFileName(Environment.getExternalStorageDirectory()
                + File.separator+"Chat"+File.separator+"logs"+File.separator+"log.txt");
        logConfigurator.setRootLevel(Level.DEBUG);
        logConfigurator.setFilePattern("%d %-5p [%c{2}]-[%L] %m%n");
        logConfigurator.setMaxFileSize(1024 * 1024 * 5);
        logConfigurator.setImmediateFlush(true);
        logConfigurator.configure();

        LogHelper.getInstance().writeLog(MyApplication.class,"Application start!");
    }
}
