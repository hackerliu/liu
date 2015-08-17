package com.xmpp.Chat.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import com.xmpp.Chat.R;

public class WelcomeActivity extends Activity{

    private AlphaAnimation start_anima;
    private View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        view=View.inflate(this, R.layout.welcome,null);
        setContentView(view);

        start_anima=new AlphaAnimation(0.3f,1.0f);
        start_anima.setDuration(1000);
        view.setAnimation(start_anima);
        start_anima.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                WelcomeActivity.this.finish();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }
}
