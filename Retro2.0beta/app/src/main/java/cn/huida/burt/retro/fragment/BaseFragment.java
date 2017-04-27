package cn.huida.burt.retro.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import cn.huida.burt.retro.R;
import cn.huida.burt.retro.activity.MainActivity;

/**
 * Created by Burt on 2017/4/20 0020.
 */

public abstract class BaseFragment extends Fragment {

    public FrameLayout myLayout;
    public TextView myTitle;
    public LayoutInflater myInflater;
    private MainActivity myActivity;



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        myActivity.IsBoomShow(false);
    }

    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container, Bundle savedInstanceState) {
        myInflater=inflater;
        View view = inflater.inflate(R.layout.basefragment_layout, null);
         myLayout = (FrameLayout) view.findViewById(R.id.base_frag_container);
        myTitle = (TextView) view.findViewById(R.id.tv_title);

        myActivity = (MainActivity) getActivity();
        myActivity.IsBoomShow(true);
        setTitle();
        setView();

        return view;
    }
    public abstract void setTitle();
    public abstract void setView() ;

}
