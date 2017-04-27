package cn.huida.burt.retro.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import cn.huida.burt.retro.R;

/**
 * Created by lenovo on 2017/4/20.
 */

public class MoreFragment extends BaseFragment {

    @Override
    public void setTitle() {
        myTitle.setText("『更多』");
    }

    @Override
    public void setView() {
        View v = myInflater.inflate(R.layout.fragment_more, null);
        myLayout.addView(v);
    }
}
