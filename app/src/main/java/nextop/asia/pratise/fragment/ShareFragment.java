package nextop.asia.pratise.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import nextop.asia.pratise.adapter.MainAdapter;
import pratice.asia.nextop.tabsmaterial.R;

/**
 * Created by billymobile on 11/17/15.
 */
public class ShareFragment extends Fragment {

    private MainAdapter adapter;
    public ShareFragment() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Toast.makeText(getContext(), "ONCREATE",Toast.LENGTH_SHORT).show();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState ) {
        View rootView = inflater.inflate(R.layout.share_fragment, container, false);
        RecyclerView list = (RecyclerView) rootView.findViewById(R.id.list);
        adapter = new MainAdapter();
        GridLayoutManager manager =
                new GridLayoutManager(getActivity(), 3);
        list.setLayoutManager(manager);
        adapter.setLayoutManager(manager);
        adapter.shouldShowHeadersForEmptySections(false);
        list.setAdapter(adapter);
        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }



    @Override
    public void onViewStateRestored(Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
    }



    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            if(position==0){
                return new OneFragment();
            }

            if(position==1){
                return new TwoFragment();
            }

            if(position==2){
                return new ThreeFragment();
            }
            return null;
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);

        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }


}