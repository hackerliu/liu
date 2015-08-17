package com.xmpp.Chat.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;
import com.xmpp.Chat.R;
import com.xmpp.Chat.util.XmppConnection;
import com.xmpp.Chat.view.TextURLView;

public class LoginActivity extends Activity{

    private Context mContext;
    private RelativeLayout rl_user;
    private Button mLogin;
    private Button register;
    private EditText account_et;
    private EditText psw_et;
    private TextURLView mTextViewURL;

    public static final int SUCCESS=1;
    public static final int FAILURE=0;

    private Handler handler=new Handler(){

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SUCCESS:
                    Intent intent=new Intent(mContext,MainActivity.class);
                    intent.putExtra("userID",msg.obj.toString());
                    startActivity(intent);
                    finish();
                    break;
                case  FAILURE:
                    Toast.makeText(mContext,getString(R.string.login_fail),Toast.LENGTH_LONG).show();
                    break;
            }
            super.handleMessage(msg);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        mContext=this;
        findView();
        initTvUrl();
        init();
    }

    private void findView(){
        rl_user=(RelativeLayout) findViewById(R.id.rl_user);
        mLogin=(Button) findViewById(R.id.login);
        register=(Button) findViewById(R.id.register);
        mTextViewURL=(TextURLView) findViewById(R.id.tv_forget_password);
        account_et=(EditText)rl_user.findViewById(R.id.account);
        psw_et=(EditText)rl_user.findViewById(R.id.password);
    }

    private void init(){
        Animation anim= AnimationUtils.loadAnimation(mContext, R.anim.login_anim);
        anim.setFillAfter(true);
        rl_user.startAnimation(anim);

        mLogin.setOnClickListener(loginOnClickListener);
        register.setOnClickListener(registerOnClickListener);
    }

    private void initTvUrl(){
        mTextViewURL.setText(R.string.forget_password);
    }

    private View.OnClickListener loginOnClickListener=new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            final String account=account_et.getText().toString().trim();
            final String psw=psw_et.getText().toString().trim();
            if(account.equals("")){
                Toast.makeText(LoginActivity.this,"please input yout account/ChatNumber",Toast.LENGTH_LONG).show();
                return;
            }
            if(psw.equals("")){
                Toast.makeText(LoginActivity.this,"please input yout password",Toast.LENGTH_LONG).show();
                return;
            }

            new Thread(new Runnable() {
                @Override
                public void run() {
                    boolean isSuc=XmppConnection.getInstance().login(account,psw);
                    if(isSuc){
                        Message msg=handler.obtainMessage(SUCCESS);
                        msg.what=SUCCESS;
                        msg.obj=account;
                        handler.sendMessage(msg);
                    }else{
                        handler.sendEmptyMessage(0);
                    }
                }
            }).start();
        }
    };

    private View.OnClickListener registerOnClickListener=new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            Intent intent=new Intent(mContext, RegisterActivity.class);
            startActivity(intent);
        }
    };
}
