package cn.huida.burt.retro.fragment;

import android.app.Fragment;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import cn.huida.burt.retro.R;

public class UserFragment extends BaseFragment {

    @Override
    public void setTitle() {
        myTitle.setText("『个人』");
    }

    @Override
    public void setView() {
        View view = myInflater.inflate(R.layout.fragment_user, null, false);
        myLayout.addView(view);
    }
}
