package com.xmpp.Chat.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;
import com.xmpp.Chat.R;
import com.xmpp.Chat.util.XmppConnection;
import com.xmpp.Chat.view.TitleBarView;

public class RegisterActivity extends Activity{

    private EditText name_et;
    private EditText psw_et;
    private RadioButton sex_rb;
    private Context mContext;

    public static final int SUCCESS=1;
    public static final int FAILURE=0;

    private Handler handler=new Handler(){

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SUCCESS:
                    Intent intent=new Intent(mContext,RegisterResultActivity.class);
                    intent.putExtra("userID",msg.obj.toString());
                    startActivity(intent);
                    finish();
                    break;
                case  FAILURE:
                    Toast.makeText(mContext, getString(R.string.reg_fail), Toast.LENGTH_LONG).show();
                    break;
            }
            super.handleMessage(msg);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        Button btn_complete=(Button) findViewById(R.id.register_complete);

        mContext=this;

        name_et= (EditText) findViewById(R.id.name);
        psw_et= (EditText) findViewById(R.id.password);
        sex_rb= (RadioButton) findViewById(R.id.maleRB);

        TitleBarView mTitleBarView=(TitleBarView) findViewById(R.id.title_bar);
        mTitleBarView.setCommonTitle(View.VISIBLE, View.VISIBLE,View.GONE, View.GONE);
        mTitleBarView.setTitleText(R.string.title_register_info);
        mTitleBarView.setBtnLeft(R.drawable.boss_unipay_icon_back, R.string.back);
        mTitleBarView.setBtnLeftOnclickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btn_complete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String account = name_et.getText().toString().trim();
                final String psw = psw_et.getText().toString().trim();

                final String sex= sex_rb.isChecked() ? "男" : "女";
                if(account.equals("")){
                    Toast.makeText(mContext,"please input your nickname",Toast.LENGTH_LONG).show();
                    return;
                }
                if(psw.equals("")){
                    Toast.makeText(mContext,"please input your password",Toast.LENGTH_LONG).show();
                    return;
                }

                new Thread(new Runnable() {
                    @Override
                    public void run() {

                        //连接后台服务器
                        String userID = null;
                        if(userID != null){
                            String result= XmppConnection.getInstance().regist(userID,psw);
                            if(result=="1"){
                                Message msg=handler.obtainMessage(SUCCESS);
                                msg.what=SUCCESS;
                                msg.obj=userID;
                                handler.sendMessage(msg);
                            }else {
                                handler.sendEmptyMessage(0);
                            }
                        }else{
                            handler.sendEmptyMessage(0);
                        }
                    }
                }).start();
            }
        });
    }
}
