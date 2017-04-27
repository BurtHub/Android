package cn.huida.burt.retro.fragment;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.TextView;

import cn.huida.burt.retro.R;
import cn.huida.burt.retro.fragment.allsinglefragment.FindFragment;
import cn.huida.burt.retro.fragment.allsinglefragment.LoveFragment;

/**
 * Created by wangjing on 2017/4/20.
 */

public class SingleFragment extends BaseFragment {

    private RadioGroup rg_moneybasefragment;

    @Override
    public void setTitle() {
        myTitle.setText("『悬赏』");
    }

    @Override
    public void setView() {
        View view = myInflater.inflate(R.layout.moneybasefragment_layout, null);
        rg_moneybasefragment = (RadioGroup) view.findViewById(R.id.rg_moneybasefragment);
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.fl_moneybasefragment,new LoveFragment());
        transaction.commit();
        initEvent();
        myLayout.addView(view);
    }

    private void initEvent() {
        rg_moneybasefragment.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i){
                    case R.id.rb_find:
                        FragmentTransaction transaction = getFragmentManager().beginTransaction();
                        transaction.replace(R.id.fl_moneybasefragment,new FindFragment());
                        transaction.commit();
                        break;
                    case R.id.rb_love:
                        FragmentTransaction transaction2 = getFragmentManager().beginTransaction();
                        transaction2.replace(R.id.fl_moneybasefragment,new LoveFragment());
                        transaction2.commit();
                        break;
                }

            }
        });
    }
}
