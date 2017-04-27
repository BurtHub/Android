package cn.huida.burt.retro.fragment;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.SimpleAdapter;

import com.jude.rollviewpager.RollPagerView;
import com.jude.rollviewpager.adapter.StaticPagerAdapter;
import com.jude.rollviewpager.hintview.ColorPointHintView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import cn.huida.burt.retro.R;

/**
 * Created by Burt on 2017/4/6 0006.
 */

public class HomeFragment extends Fragment {

    private GridView grid;
    private FragmentManager fragmentManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_fragment, null);
        fragmentManager = getFragmentManager();
        //轮播图
        RollPagerView mRollViewPager = (RollPagerView) view.findViewById(R.id.roll_view_pager);
        mRollViewPager.setPlayDelay(2000);
        mRollViewPager.setAnimationDurtion(500);
        mRollViewPager.setAdapter(new TestNormalAdapter());
        mRollViewPager.setHintView(new ColorPointHintView(getActivity(), Color.YELLOW,Color.WHITE));




        view.findViewById(R.id.foot_person).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.home_frag_container,new UserFragment());
                fragmentTransaction.addToBackStack("Home_frag");
                fragmentTransaction.commit();
               // Toast.makeText(getActivity(), "person", Toast.LENGTH_SHORT).show();
            }
        });
        view.findViewById(R.id.foot_push).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.home_frag_container,new PushFragment());
                fragmentTransaction.addToBackStack("Home_frag");
                fragmentTransaction.commit();
                //Toast.makeText(getActivity(), "push", Toast.LENGTH_SHORT).show();
            }
        });
        view.findViewById(R.id.foot_setting).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.home_frag_container,new SettingFragment());
                fragmentTransaction.addToBackStack("Home_frag");
                fragmentTransaction.commit();
                //Toast.makeText(getActivity(), "setting", Toast.LENGTH_SHORT).show();
            }
        });



        grid = (GridView) view.findViewById(R.id.grid_home);

        String [] from ={"image","text"};
        int [] to = {R.id.ib_grid_item,R.id.tv_grid_item};
        ArrayList<Map<String, Object>> data_list = new ArrayList<Map<String, Object>>();
        int[] icon = { R.mipmap.tj, R.mipmap.zc, R.mipmap.xs, R.mipmap.gz, R.mipmap.cy, R.mipmap.gd};
        final String[] iconName = { "『推荐』", "『众筹』", "『悬赏』", "『关注』", "『参与』" , "『更多』" };
        for(int i=0;i<icon.length;i++){
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("image", icon[i]);
            map.put("text", iconName[i]);
            data_list.add(map);
        }
        SimpleAdapter simpleAdapter = new SimpleAdapter(getActivity(), data_list, R.layout.grid_item, from, to);
        grid.setAdapter(simpleAdapter);

        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                //"『推荐』", "『众筹』", "『悬赏』", "『关注』", "『参与』" , "『更多』"
                switch (position){
                    case 0:
                        fragmentTransaction.replace(R.id.home_frag_container,new RecFragment());
                        break;
                    case 1:
                        fragmentTransaction.replace(R.id.home_frag_container,new AllFragment());
                        break;
                    case 2:
                        fragmentTransaction.replace(R.id.home_frag_container,new SingleFragment());
                        break;
                    case 3:
                        fragmentTransaction.replace(R.id.home_frag_container,new CareFragment());
                        break;
                    case 4:
                        fragmentTransaction.replace(R.id.home_frag_container,new JoinFragment());
                        break;
                    case 5:
                        fragmentTransaction.replace(R.id.home_frag_container,new MoreFragment());

                        break;
                }

                fragmentTransaction.addToBackStack("Home_frag");
                fragmentTransaction.commit();

            }
        });
        return view;
    }


    private class TestNormalAdapter extends StaticPagerAdapter {
        private int[] imgs = {
                R.drawable.img1,
                R.drawable.img2,
                R.drawable.img3,
                R.drawable.img4,
        };


        @Override
        public View getView(ViewGroup container, int position) {
            ImageView view = new ImageView(container.getContext());
            view.setImageResource(imgs[position]);
            view.setScaleType(ImageView.ScaleType.CENTER_CROP);
            view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            return view;
        }


        @Override
        public int getCount() {
            return imgs.length;
        }
    }


}
