package cn.huida.burt.retro.fragment;


import android.app.Fragment;
import android.app.FragmentTransaction;
import android.support.annotation.IdRes;

import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;


import cn.huida.burt.retro.R;

import cn.huida.burt.retro.fragment.recfragment.MostJoin;
import cn.huida.burt.retro.fragment.recfragment.Team;
import cn.huida.burt.retro.fragment.recfragment.Video;

public class RecFragment extends BaseFragment {

    private RadioGroup rg_recbase;

    @Override
    public void setTitle() {
        myTitle.setText("『推荐』");
    }

    @Override
    public void setView() {
        View view = myInflater.inflate(R.layout.recbase_layout, null);
        rg_recbase= (RadioGroup) view.findViewById(R.id.rg_recbase);
        initEvent();
        myLayout.addView(view);
    }

    private void initEvent() {
        rg_recbase.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                switch (checkedId){
                    case R.id.rb_joinmost:
                        transaction.replace(R.id.fl_recbase,new MostJoin());
                        break;
                    case R.id.rb_video:
                        transaction.replace(R.id.fl_recbase,new Video());
                        break;
                    case R.id.rb_team:
                        transaction.replace(R.id.fl_recbase,new Team());
                        break;
                }
                //transaction.addToBackStack("Home_frag");
                transaction.commit();
            }
        });
    }
}

