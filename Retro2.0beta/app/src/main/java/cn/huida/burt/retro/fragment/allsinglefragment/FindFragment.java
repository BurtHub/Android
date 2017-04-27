package cn.huida.burt.retro.fragment.allsinglefragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cn.huida.burt.retro.R;

/**
 * Created by wangjing on 2017/4/20.
 */

public class FindFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.findfragment_layout, container, false);
        return view;
    }

}
