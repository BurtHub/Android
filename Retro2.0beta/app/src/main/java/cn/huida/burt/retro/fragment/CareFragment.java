package cn.huida.burt.retro.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

import cn.huida.burt.retro.R;

/**
 * Created by lenovo on 2017/4/20.
 */

public class CareFragment extends BaseFragment {

    private View view;
    private RadioButton bt_idea_care;
    private RadioButton bt_team_care;
    private TextView tv_care;


    @Override
    public void setTitle() {
        myTitle.setText("『关注』");
    }

    @Override
    public void setView() {
        view = myInflater.inflate(R.layout.fragment_care, null);
        initView();
        initEvent();
        myLayout.addView(view);
    }

    private void initEvent() {
        bt_idea_care.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv_care.setText("idea");
            }
        });
        bt_team_care.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv_care.setText("team");
            }
        });
    }

    private void initView() {
        bt_idea_care = (RadioButton) view.findViewById(R.id.bt_idea_care);
        bt_team_care = (RadioButton) view.findViewById(R.id.bt_team_care);
        tv_care = (TextView) view.findViewById(R.id.tv_care);
    }
}
