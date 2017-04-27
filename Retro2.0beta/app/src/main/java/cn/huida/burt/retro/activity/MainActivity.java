package cn.huida.burt.retro.activity;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

import com.nightonke.boommenu.BoomButtons.OnBMClickListener;
import com.nightonke.boommenu.BoomButtons.TextOutsideCircleButton;
import com.nightonke.boommenu.BoomMenuButton;

import cn.huida.burt.retro.R;
import cn.huida.burt.retro.fragment.HomeFragment;

public class MainActivity extends Activity {

    //boomMenu数据
    private BoomMenuButton boom;
    private static int index = 0;
    private static String[] texts = new String[]{"众筹","悬赏","关注","推荐","收藏"};
    private static int[] images = new int[]{R.mipmap.tz, R.mipmap.zc, R.mipmap.xs, R.mipmap.gz, R.mipmap.xs, R.mipmap.gd}; //图片

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置无标题
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //设置全屏
       // getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        initViews();
        IsBoomShow(false);
    }

    private void initViews() {
        FragmentManager frm = getFragmentManager();
        FragmentTransaction frt = frm.beginTransaction();
        frt.replace(R.id.home_frag_container,new HomeFragment());
        //frt.addToBackStack("HomeActivity");
        frt.commit();

        //BoomMenu
        boom = (BoomMenuButton) findViewById(R.id.boom_menu_button);
        for (int i = 0; i < boom.getPiecePlaceEnum().pieceNumber();i++){
            TextOutsideCircleButton.Builder builder = new TextOutsideCircleButton.Builder().listener(new OnBMClickListener() {
                @Override
                public void onBoomButtonClick(int index) {
                    Toast.makeText(MainActivity.this, "hit"+index, Toast.LENGTH_SHORT).show();
                }
            }).normalImageRes(getImageRes()).normalText(getText());
            boom.addBuilder(builder);
        }


    }


    private  String getText() {
        if (index >= texts.length)
            index = 0;
        return texts[index++];
    }
    private int getImageRes() {
        if (index >= images.length)
            index = 0;
        return images[index++];
    }
    public void IsBoomShow(boolean isShow){
        if(isShow){
            boom.setVisibility(View.VISIBLE);
        }else{
            boom.setVisibility(View.GONE);
        }
    }

}
