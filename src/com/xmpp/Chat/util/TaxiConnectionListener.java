package com.xmpp.Chat.util;

import android.util.Log;
import org.jivesoftware.smack.ConnectionListener;

import java.util.Timer;
import java.util.TimerTask;

/**
 * ���Ӽ�����
 *
 * @author Administrator
 *
 */
public class TaxiConnectionListener implements ConnectionListener {
    private Timer tExit;
    private String username;
    private String password;
    private int logintime = 2000;

    @Override
    public void connectionClosed() {
        Log.i("TaxiConnectionListener", "�B���P�]");
        // �P�]�B��
        XmppConnection.getInstance().closeConnection();
        // ����������
        tExit = new Timer();
        tExit.schedule(new timetask(), logintime);
    }

    @Override
    public void connectionClosedOnError(Exception e) {
        Log.i("TaxiConnectionListener", "�B���P�]����");
        // �Д��鎤̖�ѱ����
        boolean error = e.getMessage().equals("stream:error (conflict)");
        if (!error) {
            // �P�]�B��
            XmppConnection.getInstance().closeConnection();
            // ����������
            tExit = new Timer();
            tExit.schedule(new timetask(), logintime);
        }
    }

    class timetask extends TimerTask {
        @Override
        public void run() {
            username = "";
            password = "";
            if (username != null && password != null) {
                Log.i("TaxiConnectionListener", "�Lԇ���");
                // ���ӷ�����
                if (XmppConnection.getInstance().login(username, password)) {
                    Log.i("TaxiConnectionListener", "��䛳ɹ�");
                } else {
                    Log.i("TaxiConnectionListener", "���µ��");
                    tExit.schedule(new timetask(), logintime);
                }
            }
        }
    }

    @Override
    public void reconnectingIn(int arg0) {
    }

    @Override
    public void reconnectionFailed(Exception arg0) {
    }

    @Override
    public void reconnectionSuccessful() {
    }

}
