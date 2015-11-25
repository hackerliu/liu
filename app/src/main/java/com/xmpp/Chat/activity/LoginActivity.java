package com.xmpp.Chat.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.*;
import com.xmpp.Chat.Entity.User;
import com.xmpp.Chat.R;
import com.xmpp.Chat.util.Constants;
import com.xmpp.Chat.util.ImgTools;
import com.xmpp.Chat.util.LogHelper;
import com.xmpp.Chat.util.XmppConnection;
import com.xmpp.Chat.view.TextURLView;

public class LoginActivity extends Activity{

    private Context mContext;
    private ImageView headImg;
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
                    Toast.makeText(mContext,getString(R.string.login_suc),Toast.LENGTH_LONG).show();
                    Intent intent=new Intent(mContext,MainActivity.class);
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
        headImg=(ImageView)findViewById(R.id.login_picture);
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
        try{
            Resources res = getResources();
            Bitmap bitmap = BitmapFactory.decodeResource(res,R.drawable.lufei);
            Bitmap bitmap1=ImgTools.toRoundBitmap(bitmap);
            headImg.setImageBitmap(bitmap1);
        }catch(Exception e){
            LogHelper.getInstance().writeLog(LoginActivity.class,e.getMessage());
        }
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
                Toast.makeText(LoginActivity.this,"please input your account/ChatNumber",Toast.LENGTH_LONG).show();
                return;
            }
            if(psw.equals("")){
                Toast.makeText(LoginActivity.this,"please input your password",Toast.LENGTH_LONG).show();
                return;
            }

            new Thread(new Runnable() {
                @Override
                public void run() {
                    boolean isSuc= XmppConnection.getInstance().login(account,psw);
                    if(isSuc){
                        handler.sendEmptyMessage(1);
                        User user=new User();
                        user.setUserid(account);
                        user.setPsw(psw);
                        Constants.setCurUser(user);
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
