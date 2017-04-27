package cn.huida.burt.retro.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import cn.huida.burt.retro.R;


public class WelcomeActivity extends Activity {


    private ImageView rl_welcome;
    private TextView tv_welcome;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置无标题
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //设置全屏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_welcome);

        rl_welcome = (ImageView) findViewById(R.id.activity_welcome);

        tv_welcome = (TextView) findViewById(R.id.welcome_textView);
        tv_welcome.setVisibility(View.GONE);

        init();
    }

    /**
     * 初始化动画
     */
    private void init() {


        //旋转动画  0~360
        RotateAnimation ra = new RotateAnimation(0, 360,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);

        ra.setDuration(3000);//时长
        ra.setFillAfter(true);

        //缩放   0~1
        ScaleAnimation sa = new ScaleAnimation(0, 1, 0, 1,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);

        sa.setDuration(3000);
        sa.setFillAfter(true);

        //渐变   0~1
        AlphaAnimation aa = new AlphaAnimation(0, 1);
        aa.setDuration(3000);
        aa.setFillAfter(true);

        //动画合集  插值器：定义了动画的变化率
        AnimationSet set = new AnimationSet(false);//参数：是否要共享同一个插值器

        set.addAnimation(ra);
        set.addAnimation(sa);
        set.addAnimation(aa);

        //执行动画
        rl_welcome.startAnimation(set);

        //动画执行完之后，跳转到引导页面
        set.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                //动画结束，跳转到主页面
                    tv_welcome.setVisibility(View.VISIBLE);
                    Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });


    }
}
